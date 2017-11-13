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
	
	public static void load(){
		Properties prop = PropertiesManager.getSystemConfig();
		
		if(prop.containsKey("netty.boss")){
			NETTY_BOSS=Integer.parseInt(prop.getProperty("netty.boss"));
		}
		if(prop.containsKey("netty.work")){
			NETTY_WORK=Integer.parseInt(prop.getProperty("netty.work"));
		}
		if(prop.containsKey("netty.back")){
			NETTY_BACK=Integer.parseInt(prop.getProperty("netty.back"));
		}
		if(prop.containsKey("netty.port")){
			NETTY_PORT=Integer.parseInt(prop.getProperty("netty.port"));
		}
		if(prop.containsKey("netty.rcvbuf")){
			NETTY_RCVBUF=Integer.parseInt(prop.getProperty("netty.rcvbuf"));
		}
		if(prop.containsKey("netty.sndbuf")){
			NETTY_SNDBUF=Integer.parseInt(prop.getProperty("netty.sndbuf"));
		}
		if(prop.containsKey("xml.path")){
			XML_PATH=prop.getProperty("xml.path");
		}
		if(prop.containsKey("message.multi")){
			MESSAGE_MULTI=Integer.parseInt(prop.getProperty("message.multi"));
		}
		if(prop.containsKey("message.single")){
			MESSAGE_SINGLE=Integer.parseInt(prop.getProperty("message.single"));
		}
		if(prop.containsKey("mybatis.path")){
			MYBATIS_XML=prop.getProperty("mybatis.path");
		}
		if(prop.containsKey("sql.auto.close")){
			SQL_AUTO_CLOSE = Boolean.parseBoolean(prop.getProperty("sql.auto.close"));
		}
		if(prop.containsKey("sql.auto.time")){
			SQL_AUTO_TIME=Integer.parseInt(prop.getProperty("sql.auto.time"));
		}
		if(prop.containsKey("sql.pool")){
			SQL_POOL=prop.getProperty("sql.pool");
		}
	}
	
	

}
