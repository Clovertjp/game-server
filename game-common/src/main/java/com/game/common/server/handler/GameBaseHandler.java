package com.game.common.server.handler;

import com.game.common.exception.GameException;
import com.game.common.pb.object.GameObject;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;

/**
 * @author tangjp
 *
 */
public abstract class GameBaseHandler {
	
	public abstract MessageLite handlerRequest(MessageLite msg) throws GameException;
	
	public abstract String getPbClassName();
	
}
