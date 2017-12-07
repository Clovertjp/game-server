package com.game.common.server.net;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.exception.GameException;

/**
 * 
 * @author tangjp
 *
 */
public abstract class AbstractGameServer implements IGameServer {
	private static final Logger logger = LogManager.getLogger(AbstractGameServer.class);
	
	private boolean initialized=false;
	private volatile boolean started=false;
	private String serverName="defaultName";
	
	public AbstractGameServer(String serverName){
		this.serverName=serverName;
	}
	
	protected abstract void initServer() throws GameException;
	protected abstract void startServer() throws GameException;
	protected abstract void stopServer() throws GameException;

	@Override
	public void init() throws GameException {
		// TODO Auto-generated method stub
		if(isInitialized()){
			throw new GameException("the "+getServerName()+" server is already initialized!");
		}
		initServer();
		initialized=true;
		logger.info("the "+getServerName()+" server initialized!");
	}

	@Override
	public void start() throws GameException {
		// TODO Auto-generated method stub
		if(!isInitialized()){
			throw new GameException("the "+getServerName()+" server should init at first");
		}
		
		if(isStarted()){
			throw new GameException("the "+getServerName()+" server is already started!");
		}
		
		startServer();
		started=true;
		logger.info("the "+getServerName()+" server started!");
	}

	@Override
	public void stop() throws GameException {
		// TODO Auto-generated method stub
		if(!isStarted()){
			throw new GameException("the "+getServerName()+" server is not started!");
		}
		stopServer();
		started=false;
		logger.info("the "+getServerName()+" server stoped!");
	}

	@Override
	public void restart() throws GameException {
		// TODO Auto-generated method stub
		if(isStarted()){
			stop();
		}
		if(!isInitialized()){
			init();
		}
		start();

	}

	public boolean isInitialized() {
		return initialized;
	}

	public boolean isStarted() {
		return started;
	}

	public String getServerName() {
		return serverName;
	}
	
	

}
