package com.game.common.server;

import com.game.common.exception.GameException;

/**
 * 服务器接口
 * @author tangjp
 *
 */
public interface IGameServer {
	
	public void init() throws GameException;
	public void start() throws GameException;
	public void stop() throws GameException;
	public void restart() throws GameException;

}
