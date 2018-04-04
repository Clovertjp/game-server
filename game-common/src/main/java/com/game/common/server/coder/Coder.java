package com.game.common.server.coder;

import com.game.common.exception.GameException;

public interface Coder {
	
	byte[] encode(Object obj, Class<?> type) throws GameException;
	
	Object decode(byte[] bytes, Class<?> type) throws GameException;

}
