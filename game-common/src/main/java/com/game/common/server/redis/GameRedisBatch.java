package com.game.common.server.redis;

import redis.clients.jedis.Jedis;

/**
 * @author tangjp
 *
 */
public interface GameRedisBatch {
	
	public Object run(Jedis jedis);

}
