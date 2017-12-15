package com.game.common.server.handler;

import org.apache.commons.lang3.StringUtils;

import com.game.common.exception.GameException;
import com.game.common.server.engine.GameEngine;
import com.game.common.server.palyer.GamePlayer;
import com.game.common.server.session.GameSession;
import com.game.pb.server.message.error.ErrorCodeOuterClass.ErrorCode;
import com.google.protobuf.Message;

/**
 * @author tangjp
 *
 */
public abstract class AbstractLoginHandler implements IGameLoginHandler {

	@Override
	public Message handlerRequest(Message msg, GameSession session,GamePlayer gamePlayer,String uid) throws GameException {
		if(StringUtils.isBlank(uid)) {
			throw new GameException("uid is null",ErrorCode.UID_NULL);
		}
		if(gamePlayer!=null) {
			throw new GameException("player has login",ErrorCode.GAME_PLAYER_NULL);
		}
		gamePlayer=new GamePlayer(uid);
		Message message=onLogin(gamePlayer,msg);
		gamePlayer.setGameSession(session);
		GameEngine.getInstance().addPlayer(gamePlayer);
		return message;
	}
	
	public abstract Message onLogin(GamePlayer gamePlayer,Message msg);

}
