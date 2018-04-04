package com.game.common.server.handler;

import com.game.common.exception.GameException;
import com.game.common.server.palyer.GamePlayer;
import com.game.common.server.session.GameSession;
import com.google.protobuf.Message;

/**
 * @author tangjp
 *
 */
public interface IGameLoginHandler<T,K> {
	public abstract K handlerRequest(T msg,GameSession session) throws GameException;
}
