package com.game.common.server;

import java.util.concurrent.CountDownLatch;

import com.game.common.exception.GameException;
import com.game.common.server.config.Config;
import com.game.common.server.config.GameConfigManager;

/**
 * @author tangjp
 *
 */
public class GameMain {
	
	public static CountDownLatch latch=new CountDownLatch(1);
	private static NettyGameServer nettyServer;
	
	public static void start() throws GameException, InterruptedException{
		Config.load();
		GameConfigManager.refreshAll();
		Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		nettyServer=new NettyGameServer();
		nettyServer.init();
		nettyServer.start();	
	}
	
	public static void stop() throws GameException{
		nettyServer.stop();
		
	}

	public static void main(String[] args) throws GameException, InterruptedException {
		// TODO Auto-generated method stub
		
		start();
		

	}

}
