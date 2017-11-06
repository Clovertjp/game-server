package com.game.common.server.handler;

import com.game.common.exception.GameException;
import com.game.common.pb.object.GameObject;

/**
 * @author tangjp
 *
 */
public abstract class GameBaseHandler {
	
	public abstract GameObject.GamePbObject handlerRequest(GameObject.GamePbObject msg) throws GameException;
	
}
