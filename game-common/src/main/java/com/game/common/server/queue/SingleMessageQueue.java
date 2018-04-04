package com.game.common.server.queue;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.game.common.pb.object.GameObject;
import com.game.common.server.action.IAction;
import com.game.common.server.config.Config;
import com.game.common.server.msg.GameMessage;
import com.game.common.server.thread.GameThreadFactory;

/**
 * @author tangjp
 *
 */
public class SingleMessageQueue extends AbstractMessageQueue {
	
	private static ExecutorService executor=Executors.newFixedThreadPool(Config.MESSAGE_SINGLE,
			new GameThreadFactory("SingleMessageQueue"));
	
	private static Queue<IAction<GameMessage>> queue=new LinkedBlockingQueue<>(Config.MESSAGE_LENTH);

	@Override
	protected ExecutorService getExecutorService() {
		return executor;
	}

	@Override
	protected Queue<IAction<GameMessage>> getQueue() {
		return queue;
	}

}
