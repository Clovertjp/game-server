package com.game.common.server.lock;

import java.util.List;

import com.game.common.exception.GameException;

/**
 * @author tangjp
 *
 */
public interface ILockBatch {
	
	public List<Object> getLockList();
	
	public Object run();
	
	public void lockFalse() throws GameException;

}
