package com.game.common.server.redis;

import redis.clients.jedis.Jedis;

/**
 * @author tangjp
 *
 */
public class NoPoolRedis extends GameRedisPool {

	public NoPoolRedis(int port, String ip) {
		super(port, ip);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Jedis getJedisClient() {
		// TODO Auto-generated method stub
		return new Jedis(getIp(), getPort());
	}

}
