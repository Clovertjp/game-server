package com.game.common.server.handler;

import com.game.common.exception.GameException;
import com.game.common.server.engine.GameEngine;
import com.game.common.server.palyer.GamePlayer;
import com.game.common.server.session.GameSession;
import com.google.protobuf.Message;

/**
 * @author tangjp
 *
 */
public abstract class AbstractLoginHandler implements IGameLoginHandler {

	@Override
	public Message handlerRequest(Message msg, GameSession session) throws GameException {
		// TODO Auto-generated method stub
		GamePlayer gamePlayer=new GamePlayer();
		Message message=onLogin(gamePlayer);
		gamePlayer.setGameSession(session);
		GameEngine.getInstance().addPlayer(gamePlayer);
		return message;
	}
	
	public abstract Message onLogin(GamePlayer gamePlayer);

}
