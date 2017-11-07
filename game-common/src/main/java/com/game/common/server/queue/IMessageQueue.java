package com.game.common.server.queue;

import com.game.common.pb.object.GameObject;
import com.game.common.server.action.IAction;

/**
 * @author tangjp
 *
 */
public interface IMessageQueue {
	
	public void addQueue(IAction<GameObject.GamePbObject> msg);

}
