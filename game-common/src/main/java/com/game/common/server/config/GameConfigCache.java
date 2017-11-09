package com.game.common.server.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tangjp
 *
 */
public class GameConfigCache {
	
	private static GameConfigCache gameConfigCache=new GameConfigCache();
	
	private Map<String,Map<String,String > > configItem;
	private Map<String,Set<String > > configKeySet;
	
	
	public static GameConfigCache getInstance(){
		return gameConfigCache;
	}
	private GameConfigCache(){
		configItem=new ConcurrentHashMap<>();
		configKeySet=new HashMap<>();
	}
	
	public void addItem(String fileName,String itemId,Map<String,String> val){
		String key=fileName+"_"+itemId;
		configItem.put(key, val);
		if(!configKeySet.containsKey(fileName)){
			configKeySet.put(fileName, new HashSet<>());
		}
		configKeySet.get(fileName).add(key);
	}
	
	public Map<String,String> getItem(String fileName,String itemId){
		String key=fileName+"_"+itemId;
		if(configItem.containsKey(key)){
			return new HashMap<>(configItem.get(key));
		}
		return new HashMap<>();
	}
	
	public List<Map<String,String > > getAllItem(String fileName){
		List<Map<String,String > > list=new ArrayList<>();
		if(!configKeySet.containsKey(fileName)){
			return list;
		}
		for(String id : configKeySet.get(fileName)){
			Map<String,String> map;
			if(configItem.containsKey(id)){
				map= new HashMap<>(configItem.get(id));
			}else{
				map= new HashMap<>();
			}
			
			list.add(new HashMap<>(map));
		}
		return list;
	}
	
	public void clearItem(String fileName,String itemId){
		String key=fileName+"_"+itemId;
		configItem.remove(key);
		if(!configKeySet.containsKey(fileName)){
			return;
		}
		configKeySet.get(fileName).remove(key);
	}
	
	public void clearAllItem(String fileName){
		if(!configKeySet.containsKey(fileName)){
			return ;
		}
		for(String id : configKeySet.get(fileName)){
			configItem.remove(id);
		}
		configKeySet.get(fileName).clear();
	}
	
	public void clearAllFile(){
		configKeySet.clear();
		configItem.clear();
	}

}
