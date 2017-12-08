package com.game.common.server.redis;

import redis.clients.jedis.Jedis;

/**
 * @author tangjp
 *
 */
public class NoPoolRedis extends GameRedisPool {

	public NoPoolRedis(int port, String ip) {
		super(port, ip);
	}
	
	@Override
	public Jedis getJedisClient() {
		return new Jedis(getIp(), getPort());
	}

}
