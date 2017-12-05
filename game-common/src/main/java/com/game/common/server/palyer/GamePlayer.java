package com.game.common.server.palyer;

import com.game.common.server.session.GameSession;
import com.google.protobuf.Message;

/**
 * @author tangjp
 *
 */
public class GamePlayer {
	
	private GameSession gameSession;
	private String uid;
	
	public GamePlayer() {
		
	}
	
	public Message onLogin() {
		return null;
	}

	public GameSession getGameSession() {
		return gameSession;
	}

	public void setGameSession(GameSession gameSession) {
		this.gameSession = gameSession;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
}
