package com.game.common.server;

import java.util.concurrent.CountDownLatch;

import com.game.common.exception.GameException;
import com.game.common.server.config.Config;
import com.game.common.server.config.GameConfigManager;
import com.game.common.server.net.netty.NettyGameServer;
import com.game.common.server.net.rpc.RPCGameServer;

/**
 * @author tangjp
 *
 */
public class GameMain {
	
	public static CountDownLatch latch=new CountDownLatch(1);
	private static NettyGameServer nettyServer;
	private static RPCGameServer rpcServer;
	
	public static void start() throws GameException, InterruptedException{
		Config.load();
		GameConfigManager.refreshAll();
		Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		nettyServer=new NettyGameServer();
		nettyServer.init();
		nettyServer.start();
		rpcServer=new RPCGameServer();
		rpcServer.init();
		rpcServer.start();
	}
	
	public static void stop() throws GameException{
		nettyServer.stop();
		rpcServer.stop();
	}

	public static void main(String[] args) throws GameException, InterruptedException {
		// TODO Auto-generated method stub
		
		start();
		

	}

}
