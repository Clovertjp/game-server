package com.game.common.server.engine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.game.common.server.palyer.GamePlayer;

/**
 * @author tangjp
 *
 */
public class GameEngine {
	private static GameEngine gameEngine=new GameEngine();
	private GameEngine() {
		
	}
	public static GameEngine getInstance() {
		return gameEngine;
	}
	
	Map<String,GamePlayer> gamePlayerMap=new ConcurrentHashMap<>();
	
	public void addPlayer(GamePlayer gamePlayer) {
		gamePlayerMap.put(gamePlayer.getUid(), gamePlayer);
	}
	
	public void removePlayer(String uid) {
		if(StringUtils.isBlank(uid)) {
			return;
		}
		gamePlayerMap.remove(uid);
	}
	
	public GamePlayer findPlayerById(String uid) {
		return gamePlayerMap.get(uid);
	}
	public Map<String, GamePlayer> getGamePlayerMap() {
		return gamePlayerMap;
	}
	public void setGamePlayerMap(Map<String, GamePlayer> gamePlayerMap) {
		this.gamePlayerMap = gamePlayerMap;
	}
	
	
	
}
