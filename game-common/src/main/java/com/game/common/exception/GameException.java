package com.game.common.exception;

import com.game.pb.server.message.error.ErrorCodeOuterClass.ErrorCode;

/**
 * 
 * @author tangjp
 *
 */
public class GameException extends Exception {
	
	private ErrorCode errorCode;
	
	public GameException(String msg) {
		super(msg);
		this.errorCode=ErrorCode.UNKNOW;
	}

	public GameException(String msg,ErrorCode errorCode) {
		super(msg);
		this.errorCode=errorCode;
	}

	public GameException(String msg, Throwable cause) {
		super(msg, cause);
		this.errorCode=ErrorCode.UNKNOW;
	}
	
	public GameException(String msg, Throwable cause,ErrorCode errorCode) {
		super(msg, cause);
		this.errorCode=errorCode;
	}
	
}
