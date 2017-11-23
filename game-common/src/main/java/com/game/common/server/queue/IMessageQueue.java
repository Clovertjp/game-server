package com.game.common.server.queue;

import com.game.common.pb.object.GameObject;
import com.game.common.server.action.IAction;
import com.game.pb.server.message.MessageObj;

/**
 * @author tangjp
 *
 */
public interface IMessageQueue {
	
	public void addQueue(IAction<MessageObj.NetMessage> msg);

}
