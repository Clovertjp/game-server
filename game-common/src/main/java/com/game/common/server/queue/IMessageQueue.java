package com.game.common.server.queue;

import com.game.common.pb.object.GameObject;

/**
 * @author tangjp
 *
 */
public interface IMessageQueue {
	
	public void addQueue(GameObject.GamePbObject msg);

}
