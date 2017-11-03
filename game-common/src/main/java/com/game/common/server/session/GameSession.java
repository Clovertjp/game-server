package com.game.common.server.session;

import java.util.concurrent.atomic.AtomicInteger;

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
	
	public GameSession(Channel channel) {
		// TODO Auto-generated constructor stub
		id=atomicId.incrementAndGet();
		createTime=System.currentTimeMillis();
		readTime=createTime;
		this.channel=channel;
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
