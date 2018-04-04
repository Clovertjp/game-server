package com.game.common.server.handler;

import org.apache.commons.lang3.StringUtils;

import com.game.common.exception.ErrorCode;
import com.game.common.exception.GameException;
import com.game.common.server.engine.GameEngine;
import com.game.common.server.palyer.GamePlayer;
import com.game.common.server.session.GameSession;
import com.google.protobuf.Message;

/**
 * @author tangjp
 *
 */
public abstract class AbstractLoginHandler<T,K> implements IGameLoginHandler<T,K> {

	@Override
	public K handlerRequest(T msg, GameSession session) throws GameException {
		GamePlayer gamePlayer=new GamePlayer();
		gamePlayer.setGameSession(session);
		K message=onLogin(gamePlayer,msg);
		GameEngine.getInstance().addPlayer(gamePlayer);
		return message;
	}
	
	public abstract K onLogin(GamePlayer gamePlayer,T msg);

}
