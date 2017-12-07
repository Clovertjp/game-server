package com.game.common.server.queue;

import java.util.Queue;
import java.util.concurrent.ExecutorService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.exception.GameException;
import com.game.common.pb.object.GameObject;
import com.game.common.server.action.IAction;
import com.game.common.server.handler.GameHandlerManager;
import com.game.pb.server.message.MessageObj;

import io.netty.util.internal.StringUtil;

/**
 * @author tangjp
 * @param <T>
 *
 */
public abstract class AbstractMessageQueue implements IMessageQueue {
	
	private static final Logger logger = LogManager.getLogger(AbstractMessageQueue.class);
	
	public volatile boolean exec=false;
	
	public Object lock=new Object();
	
	protected abstract ExecutorService getExecutorService();
	
	protected abstract Queue<IAction<MessageObj.NetMessage>> getQueue();
	
	private void execute(){
		
		synchronized (lock) {
			if(getQueue().isEmpty()){
				exec=false;
				return ;
			}
		}
		IAction<MessageObj.NetMessage> msg=getQueue().poll();
		getExecutorService().execute(new MessageTask(msg,this));
		
	}
	
	public void addQueue(IAction<MessageObj.NetMessage> msg){
		getQueue().add(msg);
		synchronized (lock) {
			if(!exec){
				exec=true;
				execute();
			}
		}
	}
	
	
	private class MessageTask implements Runnable {
		
		private IAction<MessageObj.NetMessage> msg;
		private AbstractMessageQueue queue;
		
		public MessageTask(IAction<MessageObj.NetMessage> msg,AbstractMessageQueue queue) {
			// TODO Auto-generated constructor stub
			this.msg=msg;
			this.queue=queue;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				if(StringUtil.isNullOrEmpty(msg.getMsgObject().getCmd())){
					throw new GameException("cmd is null");
				}
				GameHandlerManager.getInstance().execHandler(msg);
			}catch (Exception e) {
				// TODO: handle exception
				logger.error(e);
			}finally {
				queue.execute();
			}
		}

	}


}
