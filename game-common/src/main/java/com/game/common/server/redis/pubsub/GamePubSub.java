package com.game.common.server.redis.pubsub;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import redis.clients.jedis.JedisPubSub;

/**
 * @author tangjp
 *
 */
public class GamePubSub extends JedisPubSub {
	private static final Logger logger = LogManager.getLogger(GamePubSub.class);
	
	@Override
	public void onMessage(String channel, String message) {
		// TODO Auto-generated method stub
		try {
            logger.info("receive {} from channel: {}", message, channel);
            IPubSubChannel channelInstance = PubSubChannelFactory.getInstance().getChannel(channel);
            if (channelInstance != null) {
            	channelInstance.handle(message);
            }
        } catch(Throwable t) {
            logger.error("redis subscribe error", t);
        }
	}
	
}
