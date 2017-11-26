package com.game.common.server.redis.pubsub;

/**
 * @author tangjp
 *
 */
public interface IPubSubChannel {
	
	public void handle(String message);

}
