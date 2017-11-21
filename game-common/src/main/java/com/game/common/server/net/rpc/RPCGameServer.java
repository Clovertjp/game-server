package com.game.common.server.net.rpc;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.exception.GameException;
import com.game.common.server.config.Config;
import com.game.common.server.net.GameServer;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * @author tangjp
 *
 */
public class RPCGameServer extends GameServer {
	private final static String className="RPCServer";
	private static final Logger logger = LogManager.getLogger(RPCGameServer.class);
	
	private Server server;

	public RPCGameServer() {
		super(className);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initServer() throws GameException {
		// TODO Auto-generated method stub
	}

	@Override
	protected void startServer() throws GameException {
		// TODO Auto-generated method stub
		try {
			server = ServerBuilder.forPort(Config.RPC_PORT)
			        .addService(new RPCServiceImpl())
			        .build()
			        .start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("RPC",e);
			throw new GameException("RPC ERROR ",e);
		}
	}

	@Override
	protected void stopServer() throws GameException {
		// TODO Auto-generated method stub
		if(server!=null){
			server.shutdown();
		}
		
	}

}
