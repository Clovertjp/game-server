package com.game.common.exception;

/**
 * 
 * @author tangjp
 *
 */
public class GameException extends Exception {

	public GameException(String msg) {
		super(msg);
	}

	public GameException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
