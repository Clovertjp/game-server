package com.game.common.server.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.game.common.server.session.GameSession;

import io.netty.channel.Channel;

/**
 * @author tangjp
 *
 */
public class GameSessionManager {
	
	private static GameSessionManager sessionManager=new GameSessionManager();
	
	private Map<Channel,GameSession> sessionMap=new ConcurrentHashMap<Channel, GameSession>();
	
	public static GameSessionManager getInstance(){
		return sessionManager;
	}
	
	public void addSession(GameSession session){
		sessionMap.put(session.getChannel(), session);
	}
	
	public GameSession removeSession(GameSession session){
		return sessionMap.remove(session.getChannel());
	}
	
	public GameSession removeSession(Channel channel){
		return sessionMap.remove(channel);
	}
	
	public GameSession getSession(Channel channel){
		return sessionMap.get(channel);
	}

}
