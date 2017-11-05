package com.game.common.server.queue;

import java.awt.Color;

/**
 * @author tangjp
 *
 */
public class MessageQueueFactory {
	
	public static class MessageQueueType{
		public static final MessageQueueType singleType=new MessageQueueType();
		public static final MessageQueueType multiType=new MessageQueueType();
	}
	
	
	private static SingleMessageQueue single;
	
	public static MessageQueue getMessageQueue(MessageQueueType type){
		
		if(MessageQueueType.singleType==type){
			if(single==null){
				single=new SingleMessageQueue();
			}
			return single;
		}else if(MessageQueueType.multiType == type){
			return new MultiMessageQueue();
		}
		return null;
	}

}
