package com.game.agent;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameAgent {
	
	private static final Logger logger = LogManager.getLogger(GameAgent.class);
	private static final String PATCH_DIR = "patches";
	private static final String PACKAGE_START="com.game";
	
	public static void premain(String args, Instrumentation inst){
    }
	
	public static void agentmain(String args, Instrumentation inst)
	{

		logger.info("[hot swap] begin, agentmain method invoked with args: {} and inst: {}, RedefineClasses: {} and RetransformClasses: {}", args, inst, inst.isRedefineClassesSupported(), inst.isRetransformClassesSupported());
        Map<String, String> classNamePathMap = getFullClassNameFilePathMap();
        if (classNamePathMap.isEmpty()) {
            logger.info("[hot swap] no patch files, finish.");
            return;
        }
        
        logger.info("[hot swap] FullClassNameFilePathMap: {}", classNamePathMap);
        GameTransform classTransformer = new GameTransform(classNamePathMap);
        inst.addTransformer(classTransformer, true);
        Class[] allLoadedClasses = inst.getAllLoadedClasses();
        List<Class> transformClasses = new ArrayList<>();
        for (Class loadedClass : allLoadedClasses) {
            String loadedClassName = loadedClass.getName();
            if (classNamePathMap.containsKey(loadedClassName)) {
                transformClasses.add(loadedClass);
            }
        }
        try {
            inst.retransformClasses(transformClasses.toArray(new Class[]{}));
        } catch (Throwable t) {
            logger.error("[hot swap] re transform classes error, classes: " + classNamePathMap.keySet().toString(), t);
        }
        inst.removeTransformer(classTransformer);
        logger.info("[hot swap] finish");
	}
	
	private static Map<String, String> getFullClassNameFilePathMap() {
        Map<String, String> map = new HashMap<>();
        Collection<File> patchClassFiles = FileUtils.listFiles(new File(PATCH_DIR), new String[]{"class"}, true);
        for (File patchFile : patchClassFiles) {
            String filePath = patchFile.getPath();
            String fullClassName = getFullClassName(filePath);
            if (fullClassName.startsWith(PACKAGE_START)) {
                map.put(fullClassName, filePath);
            }
        }
        return map;
    }

    private static String getFullClassName(String filePath) {
        int start = filePath.indexOf("/") + 1;
        int end = filePath.lastIndexOf('.');
        String fullClassPath = filePath.substring(start, end);
        return fullClassPath.replace("/", ".");
    }
	
}
