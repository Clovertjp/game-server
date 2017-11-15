package com.game.common.server.redis;

/**
 * @author tangjp
 *
 */
public class R {
	
	private static GameRedis LOCAL_REDIS = new GameRedis(new LocalRedisPool());
	
	public static GameRedis getLocalRedis(){
		return LOCAL_REDIS;
	}
	
	public static GameRedis getNoPoolRedis(String ip,int port){
		return new GameRedis(new NoPoolRedis(port, ip));
	}

}
