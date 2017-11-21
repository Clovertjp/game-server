package com.game.common.server.config;

import java.util.Properties;

/**
 * @author tangjp
 *
 */
public class Config {
	
	public static int NETTY_BOSS = 1;
	public static int NETTY_WORK = 5;
	public static int NETTY_BACK = 512;
	public static int NETTY_PORT = 8000;
	public static int NETTY_RCVBUF = 1024;
	public static int NETTY_SNDBUF = 32768;
	
	public static String XML_PATH = "Resource";
	
	public static int MESSAGE_MULTI = 1;
	public static int MESSAGE_SINGLE = 5;
	
	public static String MYBATIS_XML = "mybatis-config.xml";
	
	public static boolean SQL_AUTO_CLOSE = true;
	public static int SQL_AUTO_TIME = 60;
	public static String SQL_POOL = "hikaricp";
	
	public static int REDIS_LOCAL_POOL_MAXACTIVE = 8;
	public static int REDIS_LOCAL_POOL_MAXIDLE = 16;
	public static int REDIS_LOCAL_POOL_MINIDLE = 4;
	public static long REDIS_LOCAL_POOL_MAXWAIT = 1000;
	public static boolean REDIS_LOCAL_POOL_BORROW = false;
	public static boolean REDIS_LOCAL_POOL_RETURN = false;
	public static int REDIS_LOCAL_POOL_PORT = 6379;
	public static String REDIS_LOCAL_POOL_IP = "127.0.0.1";
	public static int RPC_PORT = 9000;
	
	public static void load(){
		Properties prop = PropertiesManager.getSystemConfig();
		
		NETTY_BOSS=getIntItem(prop, "netty.boss", 1);
		NETTY_WORK=getIntItem(prop, "netty.work", 5);
		NETTY_BACK=getIntItem(prop, "netty.back", 512);
		NETTY_PORT=getIntItem(prop, "netty.port", 8000);
		NETTY_RCVBUF=getIntItem(prop, "netty.rcvbuf", 1024);
		NETTY_SNDBUF=getIntItem(prop, "netty.sndbuf", 32768);
		
		MESSAGE_MULTI=getIntItem(prop, "message.multi", 1);
		MESSAGE_SINGLE=getIntItem(prop, "message.single", 5);
		
		XML_PATH=getStringItem(prop, "xml.path", "Resource");
		
		MYBATIS_XML=getStringItem(prop, "mybatis.path", "mybatis-config.xml");
		
		SQL_POOL=getStringItem(prop, "sql.pool", "hikaricp");
		SQL_AUTO_TIME=getIntItem(prop, "sql.auto.time", 60);
		SQL_AUTO_CLOSE=getBooleanItem(prop, "sql.auto.close", true);
		
		REDIS_LOCAL_POOL_MAXACTIVE=getIntItem(prop, "redis.local.pool.maxActive", 8);
		REDIS_LOCAL_POOL_MAXIDLE=getIntItem(prop, "redis.local.pool.maxIdle", 16);
		REDIS_LOCAL_POOL_MINIDLE=getIntItem(prop, "redis.local.pool.minIdle", 4);
		REDIS_LOCAL_POOL_MAXWAIT=getLongItem(prop, "redis.pool.maxWait", 1000);
		REDIS_LOCAL_POOL_BORROW=getBooleanItem(prop, "redis.pool.testOnBorrow", false);
		REDIS_LOCAL_POOL_RETURN=getBooleanItem(prop, "redis.pool.testOnReturn", false);
		REDIS_LOCAL_POOL_PORT=getIntItem(prop, "redis.local.pool.port", 6379);
		REDIS_LOCAL_POOL_IP=getStringItem(prop, "redis.local.pool.ip", "127.0.0.1");
		
		RPC_PORT=getIntItem(prop, "rpc.port", 9000);

	}
	
	public static int getIntItem(Properties prop,String key,int defVal){
		if(prop.containsKey(key)){
			return Integer.parseInt(prop.getProperty(key));
		}
		return defVal;
	}
	
	public static String getStringItem(Properties prop,String key,String defVal){
		if(prop.containsKey(key)){
			return prop.getProperty(key);
		}
		return defVal;
	}
	
	public static boolean getBooleanItem(Properties prop,String key,boolean defVal){
		if(prop.containsKey(key)){
			return Boolean.parseBoolean(prop.getProperty(key));
		}
		return defVal;
	}
	
	public static long getLongItem(Properties prop,String key,long defVal){
		if(prop.containsKey(key)){
			return Long.parseLong(prop.getProperty(key));
		}
		return defVal;
	}

}
