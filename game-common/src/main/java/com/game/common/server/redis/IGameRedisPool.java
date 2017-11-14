package com.game.common.server.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author tangjp
 *
 */
public interface IGameRedisPool {
	
	public Jedis getJedisClient();
	
	public void initPool(JedisPoolConfig poolConfig,int poolPort,String poolIp);
	
}
