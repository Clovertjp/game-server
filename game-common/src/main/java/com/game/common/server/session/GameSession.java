package com.game.common.server.session;

import java.util.concurrent.atomic.AtomicInteger;

import com.game.common.pb.object.GameObject;
import com.game.common.server.action.IAction;
import com.game.common.server.config.Config;
import com.game.common.server.msg.GameMessage;
import com.game.common.server.queue.AbstractMessageQueue;
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
	private AbstractMessageQueue messageQueue;
	private String uid;
	
	public GameSession(Channel channel) {
		id=atomicId.incrementAndGet();
		createTime=System.currentTimeMillis();
		readTime=createTime;
		this.channel=channel;
		int poolId=id%Config.MESSAGE_POOL_NUM;
		messageQueue=MessageQueueFactory.getInstance()
				.getMessageQueue(MessageQueueFactory.MessageQueueType.multiType,poolId);
	}
	
	public void addMessage(IAction<GameMessage> msg){
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
}
