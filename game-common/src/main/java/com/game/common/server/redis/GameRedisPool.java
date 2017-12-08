package com.game.common.server.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author tangjp
 *
 */
public class GameRedisPool implements IGameRedisPool {

	private JedisPool pool = null;
	private int port = 6379;
	private String ip = "127.0.0.1";

	public GameRedisPool(int port, String ip) {
		this.port = port;
		this.ip = ip;
	}
	
	public GameRedisPool(JedisPoolConfig poolConfig,int port, String ip) {
		this.port = port;
		this.ip = ip;
		initPool(poolConfig,port,ip);
	}

	@Override
	public Jedis getJedisClient() {
		return pool.getResource();
	}

	@Override
	public void initPool(JedisPoolConfig poolConfig, int poolPort, String poolIp) {
		pool = new JedisPool(poolConfig, poolIp, poolPort);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
