package com.game.common.server.config;

import java.util.Properties;

/**
 * @author tangjp
 *
 */
public class Config {
	
	public static int NETTY_BOSS ;
	public static int NETTY_WORK ;
	public static int NETTY_BACK ;
	public static int NETTY_PORT ;
	public static int NETTY_RCVBUF ;
	public static int NETTY_SNDBUF ;
	
	public static String XML_PATH ;
	
	@Deprecated
	public static int MESSAGE_MULTI ;
	public static int MESSAGE_SINGLE ;
	public static int MESSAGE_POOL_NUM = 5;
	
	public static String MYBATIS_XML ;
	
	public static boolean SQL_AUTO_CLOSE ;
	public static int SQL_AUTO_TIME ;
	public static String SQL_POOL ;
	
	public static int REDIS_LOCAL_POOL_MAXACTIVE ;
	public static int REDIS_LOCAL_POOL_MAXIDLE ;
	public static int REDIS_LOCAL_POOL_MINIDLE ;
	public static long REDIS_LOCAL_POOL_MAXWAIT ;
	public static boolean REDIS_LOCAL_POOL_BORROW ;
	public static boolean REDIS_LOCAL_POOL_RETURN ;
	public static int REDIS_LOCAL_POOL_PORT ;
	public static String REDIS_LOCAL_POOL_IP ;
	public static int RPC_PORT ;
	
	public static int GAME_SCHEDULE_THREAD_POOL_NUM ;
	
	public static String AGENT_JAR ;
	
	public static long SESSION_TIMEOUT ;
	
	public static String RPC_FILE_PATH ;
	
	public static int PUSH_QUEUE_THREAD ;
	
	public static int PUSH_QUEUE_MAX_THREAD ;
	
	public static int PUSH_QUEUE_MAX_QUEUE ;
	
	public static String LOGIN_HANDLER ;
	
	public static String LOGIN_CMD ;
	
	public static void load(){
		Properties prop = PropertiesManager.getSystemConfig();
		
		NETTY_BOSS=getIntItem(prop, "netty.boss", 1);
		NETTY_WORK=getIntItem(prop, "netty.work", 5);
		NETTY_BACK=getIntItem(prop, "netty.back", 512);
		NETTY_PORT=getIntItem(prop, "netty.port", 8000);
		NETTY_RCVBUF=getIntItem(prop, "netty.rcvbuf", 1024);
		NETTY_SNDBUF=getIntItem(prop, "netty.sndbuf", 32768);
		
		MESSAGE_MULTI=getIntItem(prop, "message.multi", 1);
		MESSAGE_SINGLE=getIntItem(prop, "message.single", 1);
		
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
		
		GAME_SCHEDULE_THREAD_POOL_NUM=getIntItem(prop, "schedule.thread.pool", 2);
		
		AGENT_JAR=getStringItem(prop, "agent.jar.path", "lib/game_agent.jar");
		
		SESSION_TIMEOUT=getLongItem(prop, "session.timeout", 300000);
		
		RPC_FILE_PATH=getStringItem(prop, "rpc.client.file", "Rpc/rpcClient.xml");
		
		PUSH_QUEUE_THREAD=getIntItem(prop, "push.thread", 2);
		
		PUSH_QUEUE_MAX_THREAD=getIntItem(prop, "push.thread.max", 5);
		
		LOGIN_HANDLER=getStringItem(prop, "game.login.handler", "");
		
		LOGIN_CMD=getStringItem(prop, "game.login.cmd", "");

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
