package com.game.common.server.queue;

import com.game.common.server.action.IAction;
import com.game.common.server.msg.GameMessage;

/**
 * @author tangjp
 *
 */
public interface IMessageQueue {
	
	public void addQueue(IAction<GameMessage> msg);

}
