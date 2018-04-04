package com.game.common.server.queue;

import java.util.Queue;
import java.util.concurrent.ExecutorService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.exception.ErrorCode;
import com.game.common.exception.GameException;
import com.game.common.pb.object.GameObject;
import com.game.common.server.action.IAction;
import com.game.common.server.handler.GameHandlerManager;
import com.game.common.server.msg.GameMessage;

import io.netty.util.internal.StringUtil;

/**
 * @author tangjp
 * @param <T>
 *
 */
public abstract class AbstractMessageQueue implements IMessageQueue {
	
	private static final Logger logger = LogManager.getLogger(AbstractMessageQueue.class);
	
	protected volatile boolean exec=false;
	
	protected Object lock=new Object();
	
	protected abstract ExecutorService getExecutorService();
	
	protected abstract Queue<IAction<GameMessage>> getQueue();
	
	private void execute(){
		
		synchronized (lock) {
			if(getQueue().isEmpty()){
				exec=false;
				return ;
			}
		}
		IAction<GameMessage> msg=getQueue().poll();
		getExecutorService().execute(new MessageTask(msg,this));
		
	}
	
	public void addQueue(IAction<GameMessage> msg){
		getQueue().add(msg);
		synchronized (lock) {
			if(!exec){
				exec=true;
				execute();
			}
		}
	}
	
	
	private class MessageTask implements Runnable {
		
		private IAction<GameMessage> msg;
		private AbstractMessageQueue queue;
		
		public MessageTask(IAction<GameMessage> msg,AbstractMessageQueue queue) {
			this.msg=msg;
			this.queue=queue;
		}

		@Override
		public void run() {
			try{
				if(msg.getMsgObject().getGroupId()<=0 || msg.getMsgObject().getSubGroupId()<=0){
					throw new GameException("cmd is null",ErrorCode.CMD_NULL);
				}
				GameHandlerManager.getInstance().execHandler(msg);
			}catch (Exception e) {
				logger.error(e);
			}finally {
				queue.execute();
			}
		}

	}


}
