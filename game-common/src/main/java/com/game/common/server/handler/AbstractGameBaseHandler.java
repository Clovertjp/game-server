package com.game.common.server.handler;

import com.game.common.exception.GameException;
import com.game.common.pb.object.GameObject;
import com.game.common.server.palyer.GamePlayer;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;

/**
 * @author tangjp
 *
 */
public abstract class AbstractGameBaseHandler {
	
	public abstract Message handlerRequest(Message msg,GamePlayer gamePlayer) throws GameException;
	
}
