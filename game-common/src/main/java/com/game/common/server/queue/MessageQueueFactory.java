package com.game.common.server.queue;

import java.awt.Color;

import com.game.common.server.config.Config;

/**
 * @author tangjp
 *
 */
public class MessageQueueFactory {
	
	private static MessageQueueFactory messageQueueFactory=new MessageQueueFactory();
	
	public static MessageQueueFactory getInstance(){
		return messageQueueFactory;
	}
	
	private MessageQueueFactory(){
		single=new SingleMessageQueue[Config.MESSAGE_POOL_NUM];
	}
	
	public static class MessageQueueType{
		public static final MessageQueueType singleType=new MessageQueueType();
		public static final MessageQueueType multiType=new MessageQueueType();
	}
	
	
	private volatile SingleMessageQueue[] single;
	
	public MessageQueue getMessageQueue(MessageQueueType type,int id){
		
		if(MessageQueueType.singleType==type){
			if(id>=Config.MESSAGE_POOL_NUM){
				return null;
			}
			if(single[id]==null){
				synchronized (this) {
					if(single[id]==null){
						single[id]=new SingleMessageQueue();
					}
				}
			}
			return single[id];
		}else if(MessageQueueType.multiType == type){
			return new SingleMessageQueue();
		}
		return null;
	}

}
