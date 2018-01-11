package com.game.common.server;

import java.util.concurrent.CountDownLatch;

import com.game.common.exception.GameException;
import com.game.common.server.config.Config;
import com.game.common.server.config.GameConfigManager;
import com.game.common.server.handler.GameHandlerManager;
import com.game.common.server.net.netty.NettyGameServer;
import com.game.common.server.net.rpc.RPCGameServer;
import com.game.common.server.redis.pubsub.PubSubChannelFactory;
import com.game.common.server.scheduler.GameScheduler;
import com.game.pb.server.message.ReqLoginOuterClass.ReqLogin;

/**
 * @author tangjp
 *
 */
public class GameMain {
	
	public static CountDownLatch latch=new CountDownLatch(1);
	private static NettyGameServer nettyServer;
	private static RPCGameServer rpcServer;
	
	public static void start() throws GameException, InterruptedException, ClassNotFoundException{
		GameConfigManager.refreshAll();
		PubSubChannelFactory.getInstance().init();
		Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		GameScheduler.getInstance().init();
		GameHandlerManager.getInstance().init();
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
		GameScheduler.getInstance().stop();
	}

	public static void main(String[] args) {
		
		try {
			start();
		} catch (GameException | InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}

}
