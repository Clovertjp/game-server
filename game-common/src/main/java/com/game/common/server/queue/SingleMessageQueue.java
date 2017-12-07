package com.game.common.server.queue;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.game.common.pb.object.GameObject;
import com.game.common.server.action.IAction;
import com.game.common.server.config.Config;
import com.game.common.server.thread.GameThreadFactory;
import com.game.pb.server.message.MessageObj;

/**
 * @author tangjp
 *
 */
public class SingleMessageQueue extends AbstractMessageQueue {
	
	private static ExecutorService executor=Executors.newFixedThreadPool(Config.MESSAGE_SINGLE,
			new GameThreadFactory("SingleMessageQueue"));
	
	private static Queue<IAction<MessageObj.NetMessage>> queue=new LinkedBlockingQueue<>();

	@Override
	protected ExecutorService getExecutorService() {
		// TODO Auto-generated method stub
		return executor;
	}

	@Override
	protected Queue<IAction<MessageObj.NetMessage>> getQueue() {
		// TODO Auto-generated method stub
		return queue;
	}

}
