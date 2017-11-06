package com.game.common.server.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.pb.object.GameObject;

/**
 * @author tangjp
 *
 */
public class GameHandlerManager {
	private static final String SEPARATOR="|";
	private static final Logger logger = LogManager.getLogger(GameHandlerManager.class);
	private static GameHandlerManager handlerManager=new GameHandlerManager();
	private GameHandlerManager(){
		
	}
	
	private Map<String,Class<? extends GameBaseHandler>> handlerMap=new ConcurrentHashMap<>();
	
	public static GameHandlerManager getInstance(){
		return handlerManager;
	}
	
	public void execHandler(GameObject.GamePbObject msg){
		String cmd=msg.getCmd();
		String uid=msg.getUid();
		GameObject.GamePbObject ret=GameObject.GamePbObject.getDefaultInstance();
		long start=System.currentTimeMillis();
		try{
			Class<? extends GameBaseHandler> handler=handlerMap.get(cmd);
			ret=((GameBaseHandler)handler.newInstance()).handlerRequest(msg);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}finally {
			writeLog(start, msg, ret, uid, cmd);
		}
	}
	
	private void writeLog(long start,GameObject.GamePbObject msg,GameObject.GamePbObject ret,String uid,String cmd){
		StringBuilder sb=new StringBuilder();
		sb.append(cmd).append(GameHandlerManager.SEPARATOR).append(uid).append(GameHandlerManager.SEPARATOR)
		.append(System.currentTimeMillis()-start).append(GameHandlerManager.SEPARATOR)
		.append(msg).append(GameHandlerManager.SEPARATOR)
		.append(ret);
		logger.info(sb);
	}
	
	public void registHandler(String cmd,Class<? extends GameBaseHandler> handler){
		handlerMap.put(cmd, handler);
	}

}
