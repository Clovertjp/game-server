package com.game.common.server.format;

import com.game.common.exception.ErrorCode;
import com.game.common.exception.GameException;

public abstract class AbstractMessageFormat implements IMessageFormat {

	@Override
	public String messageFormat(Object data) throws GameException {
		if(data ==null) {
			throw new GameException("body is null", ErrorCode.BODY_NULL);
		}
		return doFormat(data);
	}
	
	protected abstract String doFormat(Object data);
}
