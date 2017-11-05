package com.game.common.server.queue;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.game.common.pb.object.GameObject;

/**
 * @author tangjp
 *
 */
public class SingleMessageQueue extends MessageQueue {
	
	private static ExecutorService executor=Executors.newScheduledThreadPool(5);
	
	private static Queue<GameObject.GamePbObject> queue=new LinkedBlockingQueue<>();

	@Override
	protected ExecutorService getExecutorService() {
		// TODO Auto-generated method stub
		return executor;
	}

	@Override
	protected Queue<GameObject.GamePbObject> getQueue() {
		// TODO Auto-generated method stub
		return queue;
	}

}
