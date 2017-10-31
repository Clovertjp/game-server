package com.game.agent;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.ProtectionDomain;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameTransform implements ClassFileTransformer {
	
	private static final Logger logger = LogManager.getLogger(GameTransform.class);
    private Map<String, String> classNamePathMap = null;
    
    public GameTransform(Map<String, String> classNamePathMap) {
        this.classNamePathMap = classNamePathMap;
    }
    

	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		// TODO Auto-generated method stub
		logger.info("[hot swap] transform class name: {}, class loader: {}", className, loader);
        String fullClassName = className.replace("/", ".");
        String path = classNamePathMap.get(fullClassName);
        if (path == null) {
            return classfileBuffer;
        }
        try {
            byte[] classContents = Files.readAllBytes(Paths.get(path));
            logger.info("[hot swap] successfully redefine class {}", fullClassName);
            return classContents;
        } catch (IOException e) {
            logger.error("[hot swap] error in reading class file, file path: " + path, e);
            return classfileBuffer;
        }
	}

}
