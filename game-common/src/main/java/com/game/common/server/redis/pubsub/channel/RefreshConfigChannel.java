package com.game.common.server.redis.pubsub.channel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.exception.GameException;
import com.game.common.server.config.GameConfigManager;
import com.game.common.server.config.GameConfigXml;
import com.game.common.server.redis.pubsub.IPubSubChannel;
import com.google.common.base.Splitter;

/**
 * @author tangjp
 *
 */
public class RefreshConfigChannel implements IPubSubChannel {
	
	private static final Logger logger = LogManager.getLogger(RefreshConfigChannel.class);

	@Override
	public void handle(String fileStr) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(fileStr) || "all".equals(fileStr)){
			try {
				GameConfigManager.refreshAll();
			} catch (GameException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			}
		}else{
			List<String> fileStrList=Splitter.on(",").splitToList(fileStr);
			List<String> fileList=new ArrayList<>();
			for(String str: fileStrList){
				if(StringUtils.isBlank(str)){
					continue;
				}if(str.endsWith(".xml")){
					fileList.add(str.substring(0, str.indexOf(".xml")));
				}else{
					fileList.add(str);
				}
			}
			try {
				GameConfigManager.refreshFileList(fileList);
			} catch (GameException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			}
		}
	}

}
