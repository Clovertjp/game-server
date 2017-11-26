package com.game.common.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.server.config.Config;
import com.game.common.server.redis.pubsub.GamePubSub;
import com.game.common.server.thread.GameThreadFactory;

/**
 * @author tangjp
 *
 */
public class GameScheduler {
	
	private static final Logger logger = LogManager.getLogger(GameScheduler.class);
	
	private static GameScheduler gameScheduler=new GameScheduler();
	private GameScheduler(){
		
	}
	
	public static GameScheduler getInstance(){
		return gameScheduler;
	}
	
	
	private ExecutorService threadExecutor=Executors.newFixedThreadPool(Config.GAME_SCHEDULE_THREAD_POOL_NUM,
			new GameThreadFactory("GameScheduler_thread"));
	
	public ExecutorService getThreadExecutor() {
		return threadExecutor;
	}
	
	
	

}
