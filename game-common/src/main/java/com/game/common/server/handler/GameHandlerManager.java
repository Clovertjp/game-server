package com.game.common.server.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import com.game.common.exception.ErrorCode;
import com.game.common.exception.GameException;
import com.game.common.pb.object.GameObject;
import com.game.common.server.action.IAction;
import com.game.common.server.coder.Coder;
import com.game.common.server.coder.ProtobufCoder;
import com.game.common.server.config.Config;
import com.game.common.server.engine.GameEngine;
import com.game.common.server.format.IMessageFormat;
import com.game.common.server.format.ProtobufMessageFormat;
import com.game.common.server.handler.message.LoginHandler;
import com.game.common.server.handler.message.TestHandler;
import com.game.common.server.msg.GameMessage;
import com.game.common.server.palyer.GamePlayer;
import com.game.pb.server.message.ReqLoginOuterClass.ReqLogin;
import com.google.common.base.Strings;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.googlecode.protobuf.format.JsonFormat;

import javassist.Modifier;

/**
 * @author tangjp
 *
 */
public class GameHandlerManager {
	private static final String SEPARATOR=" | ";
	private static final String REQ_RES_STR="REQ_RES";
	private static final Logger logger = LogManager.getLogger(GameHandlerManager.class);
	private static GameHandlerManager handlerManager=new GameHandlerManager();
	
	private static Coder messageCoder;
	private static IMessageFormat messageFormat;
	private GameHandlerManager(){
		//TODO 暂时定义为这个 以后从配置获取
		messageCoder=new ProtobufCoder();
		messageFormat=new ProtobufMessageFormat();
	}
	
	private Map<String,Class<? extends AbstractGameBaseHandler>> handlerMap=new ConcurrentHashMap<>();
	private Map<String,Class<?>> classMap=new HashMap<>();
	
	private Class<? extends IGameLoginHandler> gameLoginHandler;
	
	public static GameHandlerManager getInstance(){
		return handlerManager;
	}
	
	public void init() throws ClassNotFoundException {
		if(!StringUtils.isBlank(Config.LOGIN_HANDLER)) {
			gameLoginHandler=(Class<? extends IGameLoginHandler>) Class.forName(Config.LOGIN_HANDLER);
		}
//		System.out.println(LoginHandler.class.getPackage().getName());
//		registHandler(LoginHandler.class.getPackage().getName());
		addHandlerMap();
	}
	
	public void addHandlerMap() {
		handlerMap.put(TestHandler.CMD, TestHandler.class);
	}
	
	/**
	 * 暂时不用
	 * @param path
	 */
	@Deprecated
	public void registHandler(String path) {
		Reflections reflections = new Reflections(path);
		Set<String> allClasses = reflections.getAllTypes();
		for (String className : allClasses) {
			Class clazz = ReflectionUtils.forName(className);
			if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {
				continue; // 忽略接口和抽象类
			}
			
			if(!clazz.isAssignableFrom(AbstractGameBaseHandler.class)) {
				continue;
			}
			
			AbstractGameBaseHandler handlerInstance = null;
			try {
				handlerInstance = (AbstractGameBaseHandler)clazz.newInstance();
			} catch (InstantiationException e) {
				logger.error("类[" + className + "]无法生成实例，没有默认构造函数？", e);
				continue; // 忽略不能实例化的类
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
//			handlerMap.put(handlerInstance.getCmd(), handlerInstance.getClass());
		}
	}
	
	public void execHandler(IAction<GameMessage> actionMsg){
		GameMessage msg=actionMsg.getMsgObject();
		String cmd=msg.getGroupId()+"_"+msg.getSubGroupId();
		String msgStr="";
		String retStr="";
		String retClsName="";
		long createTime=actionMsg.getCreateTime();
		ErrorCode errorCode=ErrorCode.SUCCESS;
		long start=System.currentTimeMillis();
		GameMessage ret=null;
		try{
			Object msgMessage=messageCoder.decode(msg.getBody(), classMap.get(cmd+"_req"));
			msgStr=messageFormat.messageFormat(msgMessage);
			Object retBuilder;
			if(Config.LOGIN_CMD.equals(cmd) && gameLoginHandler!=null) {
				IGameLoginHandler login=gameLoginHandler.newInstance();
				retBuilder=login.handlerRequest(msgMessage, actionMsg.getSession());
			}else {
				if(StringUtils.isBlank(actionMsg.getSession().getUid())) {
					throw new GameException("uid is null",ErrorCode.UID_NULL);
				}
				Class<? extends AbstractGameBaseHandler> handler=handlerMap.get(cmd);
				if(handler==null) {
					throw new GameException("handler is null", ErrorCode.HANDLER_NULL);
				}
				GamePlayer gamePlayer=GameEngine.getInstance().findPlayerById(actionMsg.getSession().getUid());
				AbstractGameBaseHandler gameHandler=(AbstractGameBaseHandler)handler.newInstance();
				retBuilder=gameHandler.handlerRequest(msgMessage,gamePlayer);
			}
			if(retBuilder!=null){
				retStr=messageFormat.messageFormat(retBuilder);
			}
			errorCode=ErrorCode.SUCCESS;
			if(retBuilder!=null){
				ret=new GameMessage();
				ret.setErrorCode(errorCode);
				ret.setGroupId(msg.getGroupId());
				ret.setSubGroupId(msg.getSubGroupId());
				ret.setBody(messageCoder.encode(retBuilder, classMap.get(cmd+"_res")));
			}
		}catch (GameException e) {
			errorCode=e.getErrorCode();
			logger.error(e.getMessage(),e);
		}catch (Exception e) {
			errorCode=ErrorCode.UNKNOW;
			logger.error(e.getMessage(),e);
		}finally {
			try {
				if(ret==null) {
					ret=new GameMessage();
					ret.setErrorCode(errorCode);
					ret.setGroupId(msg.getGroupId());
					ret.setSubGroupId(msg.getSubGroupId());
				}
				actionMsg.getSession().getChannel().writeAndFlush(ret);
			}catch(Exception e) {
				logger.error(e.getMessage(),e);
			}
			writeLog(start,createTime, cmd, actionMsg.getSession().getUid(), msgStr, retClsName, retStr,errorCode);
		}
	}
	
	private void writeLog(long start,long create,String cmd,String uid,String msg,String retClsName,String ret
			,ErrorCode errorCode){
		StringBuilder sb=new StringBuilder();
		long now=System.currentTimeMillis();
		sb.append(REQ_RES_STR).append(SEPARATOR)
		.append(retClsName).append(GameHandlerManager.SEPARATOR)
		.append(uid).append(GameHandlerManager.SEPARATOR)
		.append(cmd).append(GameHandlerManager.SEPARATOR)
		.append(errorCode.name()).append(GameHandlerManager.SEPARATOR)
		.append(start-create).append(GameHandlerManager.SEPARATOR)
		.append(now-start).append(GameHandlerManager.SEPARATOR)
		.append(msg).append(GameHandlerManager.SEPARATOR)
		.append(ret);
		logger.info(sb);
	}
	
	public void registHandler(String cmd,Class<? extends AbstractGameBaseHandler> handler){
		handlerMap.put(cmd, handler);
	}

}
