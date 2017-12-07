package com.game.common.server.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.pb.object.GameObject;
import com.game.common.server.action.IAction;
import com.game.common.server.config.Config;
import com.game.pb.server.message.MessageObj;
import com.google.common.base.Strings;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.googlecode.protobuf.format.JsonFormat;

/**
 * @author tangjp
 *
 */
public class GameHandlerManager {
	private static final String SEPARATOR=" | ";
	private static final String REQ_RES_STR="REQ_RES";
	private static final Logger logger = LogManager.getLogger(GameHandlerManager.class);
	private static GameHandlerManager handlerManager=new GameHandlerManager();
	private static JsonFormat format=new JsonFormat();
	private static ConcurrentMap<String, Method> parseFromMethods = new ConcurrentHashMap<String, Method>();
	private GameHandlerManager(){
		
	}
	
	private Map<String,Class<? extends AbstractGameBaseHandler>> handlerMap=new ConcurrentHashMap<>();
	
	private Class<? extends IGameLoginHandler> gameLoginHandler;
	
	public static GameHandlerManager getInstance(){
		return handlerManager;
	}
	
	public void init() throws ClassNotFoundException {
		if(!StringUtils.isBlank(Config.LOGIN_HANDLER)) {
			gameLoginHandler=(Class<? extends IGameLoginHandler>) Class.forName(Config.LOGIN_HANDLER);
		}
	}
	
	public void execHandler(IAction<MessageObj.NetMessage> actionMsg){
		MessageObj.NetMessage msg=actionMsg.getMsgObject();
		String cmd=msg.getCmd();
		String className=msg.getClassName();
		String uid=msg.getUid();
		ByteString data = msg.getClassData();
		String msgStr="";
		String retStr="";
		String retClsName="";
		MessageObj.NetMessage ret=MessageObj.NetMessage.getDefaultInstance();
		long start=System.currentTimeMillis();
		try{
			
			Message msgMessage=byteStringToMessage(data,className);
			msgStr=format.printToString(msgMessage);
			Message retBuilder=null ;
			
			if(Config.LOGIN_CMD.equals(cmd) && gameLoginHandler!=null) {
				IGameLoginHandler login=gameLoginHandler.newInstance();
				retBuilder=login.handlerRequest(msgMessage, actionMsg.getSession());
			}else {
				Class<? extends AbstractGameBaseHandler> handler=handlerMap.get(cmd);
				AbstractGameBaseHandler gameHandler=(AbstractGameBaseHandler)handler.newInstance();
				retBuilder=gameHandler.handlerRequest(msgMessage);
			}
			if(retBuilder!=null){
				retStr=format.printToString(retBuilder);
				retClsName=retBuilder.getClass().getName();
			}
			if(retBuilder!=null){
				ret=MessageObj.NetMessage.newBuilder().setClassData(retBuilder.toByteString())
						.setClassName(retClsName).setUid(uid).build();
				actionMsg.getSession().getChannel().writeAndFlush(ret);
			}
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
		}finally {
			writeLog(start, cmd, uid, className, msgStr, retClsName, retStr);
		}
	}
	
	private Message byteStringToMessage(ByteString data,String className){
		if(Strings.isNullOrEmpty(className)){
			return null;
		}
		
		Method parseFromMethod = getParseFromMethod(className);
		
		try {
			return (Message) parseFromMethod.invoke(null, data);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private static Method getParseFromMethod(String className) {
		return parseFromMethods.computeIfAbsent(className, (k) -> {
			Class<? extends Message> clazz = null;
			try {
				clazz = (Class<? extends Message>) Class.forName(k);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
			Method m = null;
			try {
				m = clazz.getMethod("parseFrom", ByteString.class);
			} catch (NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(e);
			}
			return m;
		});
	}
	
	private void writeLog(long start,String cmd,String uid,String clsName,String msg,String retClsName,String ret){
		StringBuilder sb=new StringBuilder();
		sb.append(REQ_RES_STR).append(SEPARATOR)
		.append(clsName).append(GameHandlerManager.SEPARATOR)
		.append(retClsName).append(GameHandlerManager.SEPARATOR)
		.append(uid).append(GameHandlerManager.SEPARATOR)
		.append(cmd).append(GameHandlerManager.SEPARATOR)
		.append(System.currentTimeMillis()-start).append(GameHandlerManager.SEPARATOR)
		.append(msg).append(GameHandlerManager.SEPARATOR)
		.append(ret);
		logger.info(sb);
	}
	
	public void registHandler(String cmd,Class<? extends AbstractGameBaseHandler> handler){
		handlerMap.put(cmd, handler);
	}

}
