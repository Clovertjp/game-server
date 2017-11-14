package com.game.common.server.redis;

import com.game.common.server.config.Config;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @author tangjp
 *
 */
public class LocalRedisPool extends GameRedisPool {

	public LocalRedisPool() {
		super(Config.REDIS_LOCAL_POOL_PORT, Config.REDIS_LOCAL_POOL_IP);
		JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(Config.REDIS_LOCAL_POOL_MAXACTIVE);
        poolConfig.setMaxIdle(Config.REDIS_LOCAL_POOL_MAXIDLE);
        poolConfig.setMinIdle(Config.REDIS_LOCAL_POOL_MINIDLE);
        poolConfig.setMaxWaitMillis(Config.REDIS_LOCAL_POOL_MAXWAIT);
        poolConfig.setTestOnBorrow(Config.REDIS_LOCAL_POOL_BORROW);
        poolConfig.setTestOnReturn(Config.REDIS_LOCAL_POOL_RETURN);
        initPool(poolConfig,getPort(),getIp());
	}

}
