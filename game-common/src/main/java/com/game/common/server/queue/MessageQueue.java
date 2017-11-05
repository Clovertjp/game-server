package com.game.common.server.queue;

import java.util.Queue;
import java.util.concurrent.ExecutorService;

import com.game.common.pb.object.GameObject;

/**
 * @author tangjp
 * @param <T>
 *
 */
public abstract class MessageQueue implements IMessageQueue {
	
	public volatile boolean exec=false;
	
	public Object lock=new Object();
	
	protected abstract ExecutorService getExecutorService();
	
	protected abstract Queue<GameObject.GamePbObject> getQueue();
	
	private void execute(){
		
		synchronized (lock) {
			if(getQueue().isEmpty()){
				exec=false;
				return ;
			}
		}
		GameObject.GamePbObject msg=getQueue().poll();
		getExecutorService().execute(new MessageTask(msg,this));
		
	}
	
	public void addQueue(GameObject.GamePbObject msg){
		getQueue().add(msg);
		synchronized (lock) {
			if(!exec){
				exec=true;
				execute();
			}
		}
	}
	
	
	private class MessageTask implements Runnable {
		
		private GameObject.GamePbObject msg;
		private MessageQueue queue;
		
		public MessageTask(GameObject.GamePbObject msg,MessageQueue queue) {
			// TODO Auto-generated constructor stub
			this.msg=msg;
			this.queue=queue;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// TODO 添加执行方法
			
			queue.execute();
		}

	}


}
