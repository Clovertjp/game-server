package com.game.common.server.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game.common.exception.GameException;

import io.netty.util.internal.StringUtil;

/**
 * @author tangjp
 *
 */
public class GameConfigManager {
	
	private static volatile boolean readInCache=true;
	
	public static Map<String,String> getItem(String fileName,String id){
		if(StringUtil.isNullOrEmpty(fileName) || StringUtil.isNullOrEmpty(id)){
			return new HashMap<>();
		}
		if(!readInCache){
			return GameConfigXml.readItemByXml(fileName, id);
		}
		return GameConfigCache.getInstance().getItem(fileName, id);
	}
	
	public static List<Map<String,String > > getAll(String fileName){
		if(StringUtil.isNullOrEmpty(fileName)){
			return new ArrayList<>();
		}
		if(!readInCache){
			return GameConfigXml.readInfoByXml(fileName);
		}
		return GameConfigCache.getInstance().getAllItem(fileName);
	}
	
	public static void refreshAll() throws GameException{
		GameConfigXml.loadAllXML();
	}
	
	public static void refreshFileList(List<String> fileList) throws GameException{
		for(String fileName : fileList){
			GameConfigXml.readXMLByName(fileName);
		}
	}

}
