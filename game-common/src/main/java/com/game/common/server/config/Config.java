package com.game.common.server.config;

import java.util.Properties;

/**
 * @author tangjp
 *
 */
public class Config {
	
	private Config() {}
	
	public static final int NETTY_BOSS ;
	public static final int NETTY_WORK ;
	public static final int NETTY_BACK ;
	public static final int NETTY_PORT ;
	public static final int NETTY_RCVBUF ;
	public static final int NETTY_SNDBUF ;
	
	public static final String XML_PATH ;
	
	@Deprecated
	public static final int MESSAGE_MULTI ;
	public static final int MESSAGE_SINGLE ;
	public static final int MESSAGE_POOL_NUM;
	public static final int MESSAGE_LENTH;
	
	public static final String MYBATIS_XML ;
	
	public static final boolean SQL_AUTO_CLOSE ;
	public static final int SQL_AUTO_TIME ;
	public static final String SQL_POOL ;
	
	public static final int REDIS_LOCAL_POOL_MAXACTIVE ;
	public static final int REDIS_LOCAL_POOL_MAXIDLE ;
	public static final int REDIS_LOCAL_POOL_MINIDLE ;
	public static final long REDIS_LOCAL_POOL_MAXWAIT ;
	public static final boolean REDIS_LOCAL_POOL_BORROW ;
	public static final boolean REDIS_LOCAL_POOL_RETURN ;
	public static final int REDIS_LOCAL_POOL_PORT ;
	public static final String REDIS_LOCAL_POOL_IP ;
	public static final int RPC_PORT ;
	
	public static final int GAME_SCHEDULE_THREAD_POOL_NUM ;
	
	public static final String AGENT_JAR ;
	
	public static final long SESSION_TIMEOUT ;
	
	public static final String RPC_FILE_PATH ;
	
	public static final int PUSH_QUEUE_THREAD ;
	
	public static final int PUSH_QUEUE_MAX_THREAD ;
	
	public static final int PUSH_QUEUE_MAX ;
	
	public static final String LOGIN_HANDLER ;
	
	public static final String LOGIN_CMD ;
	
	public static final String FILTER_IP_KEY ;
	
	public static final String FILTER_UID_KEY ;
	
	public static final String FILTER_DY_IP_KEY ;
	
	public static final String FILTER_DY_UID_KEY ;
	
	public static final int FILTER_DY_IP_MAX ;
	
	public static final int FILTER_DY_UID_MAX ;
	
	public static final long FILTER_DY_IP_TIME ;
	
	public static final long FILTER_DY_UID_TIME ;
	
	public static final String HANDLER_PATH ;
	
	static {
		Properties prop = PropertiesManager.getSystemConfig();
		
		NETTY_BOSS=getIntItem(prop, "netty.boss", 1);
		NETTY_WORK=getIntItem(prop, "netty.work", 5);
		NETTY_BACK=getIntItem(prop, "netty.back", 512);
		NETTY_PORT=getIntItem(prop, "netty.port", 8000);
		NETTY_RCVBUF=getIntItem(prop, "netty.rcvbuf", 1024);
		NETTY_SNDBUF=getIntItem(prop, "netty.sndbuf", 32768);
		
		MESSAGE_MULTI=getIntItem(prop, "message.multi", 1);
		MESSAGE_SINGLE=getIntItem(prop, "message.single", 1);
		MESSAGE_POOL_NUM=getIntItem(prop, "message.pool.num", 5);
		MESSAGE_LENTH=getIntItem(prop, "messgae.lenth", 100);
		
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
		
		PUSH_QUEUE_MAX=getIntItem(prop, "push.queue.max", 20);
		
		FILTER_IP_KEY=getStringItem(prop, "filter.ip.key", "filter.ip");
		
		FILTER_UID_KEY=getStringItem(prop, "filter.uid.key", "filter.uid");
		
		FILTER_DY_IP_KEY =getStringItem(prop, "filter.dy.ip.key", "filter.dy.ip");
		
		FILTER_DY_UID_KEY =getStringItem(prop, "filter.dy.uid.key", "filter.dy.uid");
		
		FILTER_DY_IP_MAX =getIntItem(prop, "filter.dy.ip.max", 400);
		
		FILTER_DY_UID_MAX =getIntItem(prop, "filter.dy.uid.max", 100);
		
		FILTER_DY_IP_TIME =getLongItem(prop, "filter.dy.ip.time", 24*60*60);
		
		FILTER_DY_UID_TIME =getLongItem(prop, "filter.dy.uid.time", 3*24*60*60);
		
		HANDLER_PATH =getStringItem(prop, "handler.path", "com.game.common.server.handler.message");

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
