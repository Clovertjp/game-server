package com.game.common.server.net.rpc.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

import com.game.common.server.config.Config;
import com.game.common.server.config.Constants;
import com.game.common.server.config.GameServerConfig;

/**
 * @author tangjp
 *
 */
public class RpcClientManager {
	
	private static final String filter="rpc/server[id='%d']/%s";
	
	private static RpcClientManager rpcManager=new RpcClientManager();
	private Map<Integer,RpcClient> clientMap=new ConcurrentHashMap<>();
	
	private RpcClientManager() {
		
	}
	public static RpcClientManager getInstance() {
		return rpcManager;
	}
	
	public RpcClient getRpcClientByServerId(int serverId) {
		if(serverId==Constants.SERVER_ID) {
			return null;
		}
		if(clientMap.containsKey(serverId)) {
			return clientMap.get(serverId);
		}
		synchronized (this) {
			if(clientMap.containsKey(serverId)) {
				return clientMap.get(serverId);
			}
			XMLConfiguration xmlConfig;
			try {
				xmlConfig=GameServerConfig.getInstance().getXMLConfigurationByName(Config.RPC_FILE_PATH);
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			String ipFi=String.format(filter, serverId,"ip");
			String PortFi=String.format(filter, serverId,"port");
			
			RpcClient client=new RpcClient(xmlConfig.getString(ipFi), serverId, xmlConfig.getInt(PortFi));
			clientMap.put(serverId, client);
			return client;
		}
	}

}
