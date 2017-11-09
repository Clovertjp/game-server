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

import com.game.common.exception.GameException;

import io.netty.util.internal.StringUtil;


/**
 * @author tangjp
 *
 */
public class GameConfigXml {
	
	private static final Logger logger = LogManager.getLogger(GameConfigXml.class);
	
	private static final String PATH="Resource";
	
	public static void loadAllXML() throws GameException{
		logger.info("loading xml start");
		File xmlPath = new File(PATH);
		if(!xmlPath.isDirectory()){
			throw new GameException(PATH+" i snot a directory");
		}
		
		File[] fileList=xmlPath.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				String fileName = pathname.getName();
				if(!fileName.matches("^.+\\.xml$")) {
					return false;
				} else {
					return true;
				}
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
			// TODO: handle exception
			logger.error("loading xml is error,file name is "+fileName,e);
			throw new GameException(fileName+" is error ",e);
		}
	}
	
	public static void readXMLByName(String fileName) throws GameException{
		logger.info("loading "+fileName+" start");
		File xml = new File(PATH+"/"+fileName+".xml");
		GameConfigCache.getInstance().clearAllItem(fileName);
		SAXReader reader = new SAXReader();
		try{
			Document document = reader.read(xml);
			Element root=document.getRootElement();
			loadXML(root,fileName);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("loading xml is error,file name is "+fileName,e);
			throw new GameException(fileName+" is error ",e);
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
				throw new GameException(fileName+" not contain id");
			}
			GameConfigCache.getInstance().addItem(fileName, attrMap.get("id"), attrMap);
		}
	}
	
	public static List<Map<String,String> > readInfoByXml(String fileName){
		List<Map<String,String> > infoList=new ArrayList<>();
		if(StringUtil.isNullOrEmpty(fileName)){
			return infoList;
		}
		File xml = new File(PATH+"/"+fileName+".xml");
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
			// TODO: handle exception
			logger.error("loading xml is error,file name is "+fileName,e);
			infoList.clear();
		}
		return infoList;
	}
	
	
	public static Map<String,String> readItemByXml(String fileName,String id){
		if(StringUtil.isNullOrEmpty(fileName) || StringUtil.isNullOrEmpty(id)){
			return new HashMap<>();
		}
		File xml = new File(PATH+"/"+fileName+".xml");
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
					throw new GameException(fileName+" not contain id");
				}
				if(id.equals(attrMap.get("id"))){
					return attrMap;
				}
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("loading xml is error,file name is "+fileName,e);
		}
		return new HashMap<>();
	}
	

}
