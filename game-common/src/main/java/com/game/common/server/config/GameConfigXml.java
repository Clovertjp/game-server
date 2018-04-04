package com.game.common.server.config;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.game.common.exception.ErrorCode;
import com.game.common.exception.GameException;

import io.netty.util.internal.StringUtil;


/**
 * @author tangjp
 *
 */
public class GameConfigXml {
	
	private GameConfigXml() {}
	
	private static final Logger logger = LogManager.getLogger(GameConfigXml.class);
	
	private static final String LOG_ERROR="loading xml is error,file name is ";
	
	private static final String PATH=Config.XML_PATH;
	
	public static void loadAllXML() throws GameException{
		logger.info("loading xml start");
		File xmlPath = new File(PATH);
		if(!xmlPath.isDirectory()){
			throw new GameException(PATH+" is not a directory",ErrorCode.RESOURCE_ERROR);
		}
		
		File[] fileList=xmlPath.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				String fileName = pathname.getName();
				return fileName.matches("^.+\\.xml$");
			}
		});
		
		GameConfigCache.getInstance().clearAllFile();
		SAXReader reader = new SAXReader();
		String fileName="";
		try{
			for(File file : fileList){
				Document document = reader.read(file);
				fileName=file.getName();
				fileName=fileName.substring(0, fileName.length() - 4);
				Element root=document.getRootElement();
				loadXML(root,fileName);
			}
			
			logger.info("loading xml finish");
		}catch (Exception e) {
			logger.error(LOG_ERROR+fileName,e);
			throw new GameException(fileName+" is error ",e,ErrorCode.RESOURCE_ERROR);
		}
	}
	
	public static void readXMLByName(String fileName) throws GameException{
		logger.info("loading "+fileName+" start");
		File xml = new File(PATH+File.pathSeparator+fileName+".xml");
		GameConfigCache.getInstance().clearAllItem(fileName);
		SAXReader reader = new SAXReader();
		try{
			Document document = reader.read(xml);
			Element root=document.getRootElement();
			loadXML(root,fileName);
		}catch (Exception e) {
			logger.error(LOG_ERROR+fileName,e);
			throw new GameException(fileName+" is error ",e,ErrorCode.RESOURCE_ERROR);
		}
		
	}
	
	private static void loadXML(Element root,String fileName) throws GameException{
		Iterator<Element> iterator=root.elementIterator();
		while(iterator.hasNext()){
			Element element = iterator.next();
			Map<String,String> attrMap=new HashMap<>();
			List<Attribute> attrList = element.attributes();
			for(Attribute attr : attrList){
				attrMap.put(attr.getName(), attr.getValue());
			}
			if(!attrMap.containsKey("id")){
				throw new GameException(fileName+" not contain id",ErrorCode.RESOURCE_ERROR);
			}
			GameConfigCache.getInstance().addItem(fileName, attrMap.get("id"), attrMap);
		}
	}
	
	public static List<Map<String,String> > readInfoByXml(String fileName){
		List<Map<String,String> > infoList=new ArrayList<>();
		if(StringUtil.isNullOrEmpty(fileName)){
			return infoList;
		}
		File xml = new File(PATH+File.pathSeparator+fileName+".xml");
		SAXReader reader = new SAXReader();
		try{
			Document document = reader.read(xml);
			Element root=document.getRootElement();
			Iterator<Element> iterator=root.elementIterator();
			while(iterator.hasNext()){
				Element element = iterator.next();
				Map<String,String> attrMap=new HashMap<>();
				List<Attribute> attrList = element.attributes();
				for(Attribute attr : attrList){
					attrMap.put(attr.getName(), attr.getValue());
				}
				infoList.add(attrMap);
			}
			
		}catch (Exception e) {
			logger.error(LOG_ERROR+fileName,e);
			infoList.clear();
		}
		return infoList;
	}
	
	
	public static Map<String,String> readItemByXml(String fileName,String id){
		if(StringUtil.isNullOrEmpty(fileName) || StringUtil.isNullOrEmpty(id)){
			return new HashMap<>();
		}
		File xml = new File(PATH+File.pathSeparator+fileName+".xml");
		SAXReader reader = new SAXReader();
		try{
			Document document = reader.read(xml);
			Element root=document.getRootElement();
			Iterator<Element> iterator=root.elementIterator();
			while(iterator.hasNext()){
				Element element = iterator.next();
				Map<String,String> attrMap=new HashMap<>();
				List<Attribute> attrList = element.attributes();
				for(Attribute attr : attrList){
					attrMap.put(attr.getName(), attr.getValue());
				}
				
				if(!attrMap.containsKey("id")){
					throw new GameException(fileName+" not contain id",ErrorCode.RESOURCE_ERROR);
				}
				if(id.equals(attrMap.get("id"))){
					return attrMap;
				}
				
			}
			
		}catch (Exception e) {
			logger.error(LOG_ERROR+fileName,e);
		}
		return new HashMap<>();
	}
	

}
