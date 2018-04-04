package com.game.common.server.format;

import com.game.common.exception.GameException;

public interface IMessageFormat {
	public String messageFormat(Object data) throws GameException;
}
