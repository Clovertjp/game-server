package com.game.common.server.redis;

import redis.clients.jedis.Jedis;

/**
 * @author tangjp
 *
 */
public interface IGameRedisBatch {
	
	public Object run(Jedis jedis);

}
