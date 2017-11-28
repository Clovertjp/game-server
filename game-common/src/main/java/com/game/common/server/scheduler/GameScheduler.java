package com.game.common.server.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.server.config.Config;
import com.game.common.server.redis.pubsub.GamePubSub;
import com.game.common.server.scheduler.task.SessionSchedulerTask;
import com.game.common.server.thread.GameThreadFactory;

/**
 * @author tangjp
 *
 */
public class GameScheduler {
	
	private static final Logger logger = LogManager.getLogger(GameScheduler.class);
	
	private static GameScheduler gameScheduler=new GameScheduler();
	
	private ExecutorService threadExecutor=Executors.newFixedThreadPool(Config.GAME_SCHEDULE_THREAD_POOL_NUM,
			new GameThreadFactory("GameScheduler_thread"));
	
	private ScheduledExecutorService scheduledExecutor=Executors.newScheduledThreadPool(Config.GAME_SCHEDULE_THREAD_POOL_NUM,
			new GameThreadFactory("GameScheduler_thread"));
	
	private GameScheduler(){
		
	}
	
	public static GameScheduler getInstance(){
		return gameScheduler;
	}

	public ExecutorService getThreadExecutor() {
		return threadExecutor;
	}

	public ScheduledExecutorService getScheduledExecutor() {
		return scheduledExecutor;
	}

	public void init(){
		initSessionScheduled();
	}
	
	private void initSessionScheduled(){
		getScheduledExecutor().scheduleAtFixedRate(new SessionSchedulerTask(), 0, 1, TimeUnit.MINUTES);
	}

}
