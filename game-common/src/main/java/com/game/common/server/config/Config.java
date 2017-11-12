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
	
	public static void load(){
		Properties prop = PropertiesManager.getSystemConfig();
		
		if(prop.contains("netty.boss")){
			NETTY_BOSS=Integer.parseInt(prop.getProperty("netty.boss"));
		}
		if(prop.contains("netty.work")){
			NETTY_WORK=Integer.parseInt(prop.getProperty("netty.work"));
		}
		if(prop.contains("netty.back")){
			NETTY_BACK=Integer.parseInt(prop.getProperty("netty.back"));
		}
		if(prop.contains("netty.port")){
			NETTY_PORT=Integer.parseInt(prop.getProperty("netty.port"));
		}
		if(prop.contains("netty.rcvbuf")){
			NETTY_RCVBUF=Integer.parseInt(prop.getProperty("netty.rcvbuf"));
		}
		if(prop.contains("netty.sndbuf")){
			NETTY_SNDBUF=Integer.parseInt(prop.getProperty("netty.sndbuf"));
		}
		if(prop.contains("xml.path")){
			XML_PATH=prop.getProperty("xml.path");
		}
		if(prop.contains("message.multi")){
			MESSAGE_MULTI=Integer.parseInt(prop.getProperty("message.multi"));
		}
		if(prop.contains("message.single")){
			MESSAGE_SINGLE=Integer.parseInt(prop.getProperty("message.single"));
		}
		
		
	}
	
	

}
