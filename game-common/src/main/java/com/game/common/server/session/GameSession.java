package com.game.common.server.session;

import java.util.concurrent.atomic.AtomicInteger;

import com.game.common.pb.object.GameObject;
import com.game.common.server.action.IAction;
import com.game.common.server.queue.MessageQueue;
import com.game.common.server.queue.MessageQueueFactory;

import io.netty.channel.Channel;

/**
 * @author tangjp
 *
 */
public class GameSession {
	public static final AtomicInteger atomicId=new AtomicInteger();
	private int id;
	private long createTime;
	private long readTime;
	private Channel channel;
	private MessageQueue messageQueue;
	
	public GameSession(Channel channel) {
		// TODO Auto-generated constructor stub
		id=atomicId.incrementAndGet();
		createTime=System.currentTimeMillis();
		readTime=createTime;
		this.channel=channel;
		messageQueue=MessageQueueFactory.getMessageQueue(MessageQueueFactory.MessageQueueType.multiType);
	}
	
	public void addMessage(IAction<GameObject.GamePbObject> msg){
		messageQueue.addQueue(msg);
	}

	public int getId() {
		return id;
	}

	public long getCreateTime() {
		return createTime;
	}

	public long getReadTime() {
		return readTime;
	}

	public void updateReadTime() {
		this.readTime = System.currentTimeMillis();
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	
}
