package com.game.common.server.handler;

import com.game.common.exception.GameException;
import com.game.common.server.session.GameSession;
import com.google.protobuf.Message;

/**
 * @author tangjp
 *
 */
public interface IGameLoginHandler {
	public abstract Message handlerRequest(Message msg,GameSession session,String uid) throws GameException;
}
