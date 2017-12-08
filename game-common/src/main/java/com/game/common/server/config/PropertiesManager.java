package com.game.common.server.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author tangjp
 *
 */
public class PropertiesManager {
	
	private PropertiesManager() {}
	
	private static Properties systemConfig=getConfigProperties();
	
	public static Properties getProperties(String fileName){
		Properties prop = new Properties();
		try {
			InputStream inStream = PropertiesManager.class.getClassLoader().getResourceAsStream(fileName);
			// 将属性文件流装载到Properties对象中
			prop.load(inStream);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return prop;
	}
	
	private static Properties getConfigProperties(){
		return getProperties("config.properties");
	}
	
	public static Properties getSystemConfig(){
		return systemConfig;
	}
	
	

}
