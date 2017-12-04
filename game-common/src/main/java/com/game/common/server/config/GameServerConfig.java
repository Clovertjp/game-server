package com.game.common.server.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;

/**
 * @author tangjp
 *
 */
public class GameServerConfig {
	
	private Map<String,XMLConfiguration> configurationMap=new ConcurrentHashMap<>();
	
	private static GameServerConfig gameServerConfig=new GameServerConfig();
	private GameServerConfig() {
		
	}
	
	public static GameServerConfig getInstance() {
		return gameServerConfig;
	}
	
	public XMLConfiguration getXMLConfigurationByName(String path) throws ConfigurationException {
		if(configurationMap.containsKey(path)) {
			return configurationMap.get(path);
		}
		XMLConfiguration xml=new XMLConfiguration(path);
		xml.setExpressionEngine(new XPathExpressionEngine());
		configurationMap.put(path, xml);
		return xml;
	}
	
	public static void main(String[] args) throws ConfigurationException {
		XMLConfiguration conf=GameServerConfig.getInstance().getXMLConfigurationByName("Rpc/rpcClient.xml");
		System.out.println(conf.getString("rpc/server[id='2']/ip"));
	}

}
