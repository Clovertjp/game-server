package com.game.common.server.coder;

import com.game.common.exception.ErrorCode;
import com.game.common.exception.GameException;

public abstract class AbstractCoder implements Coder {

	@Override
	public byte[] encode(Object obj, Class<?> type) throws GameException {
		if(obj==null) {
			throw new GameException("encode body is null", ErrorCode.BODY_NULL);
		}
		if(type==null) {
			throw new GameException("encode type is null", ErrorCode.TYPE_NULL);
		}
		return doEncode(obj, type);
	}

	@Override
	public Object decode(byte[] bytes, Class<?> type) throws GameException {
		if(bytes==null || bytes.length<=0) {
			throw new GameException("encode body is null", ErrorCode.BODY_NULL);
		}
		if(type==null) {
			throw new GameException("encode type is null", ErrorCode.TYPE_NULL);
		}
		return doDecode(bytes, type);
	}
	
	protected abstract byte[] doEncode(Object obj, Class<?> type) throws GameException ;

	protected abstract Object doDecode(byte[] bytes, Class<?> type) throws GameException;

}
