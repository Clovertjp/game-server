package com.game.common.server.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.game.common.server.config.Config;
import com.game.common.server.session.GameSession;

import io.netty.channel.Channel;

/**
 * @author tangjp
 *
 */
public class GameSessionManager {
	
	private static GameSessionManager sessionManager=new GameSessionManager();
	
	private Map<Channel,GameSession> sessionMap=new ConcurrentHashMap<>();
	
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
	
	public void checkSession(){
		List<GameSession> deleteList=new ArrayList<>();
		long now=System.currentTimeMillis();
		for(GameSession session : sessionMap.values()){
			if(now-session.getReadTime() >= Config.SESSION_TIMEOUT){
				deleteList.add(session);
			}
		}
		for(GameSession session : deleteList){
			sessionMap.remove(session);
			session.getChannel().close();
		}
	}

}
