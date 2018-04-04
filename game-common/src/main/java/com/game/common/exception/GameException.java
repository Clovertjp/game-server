package com.game.common.exception;


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

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	
	
}
