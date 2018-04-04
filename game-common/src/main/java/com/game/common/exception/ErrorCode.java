package com.game.common.exception;

public enum ErrorCode {
	
	SUCCESS(0),
	UNKNOW(1),
	UID_NULL(2),
	GAME_PLAYER_NULL(3),
	PARAM_ERROR(4),
	RESOURCE_ERROR(5),
	SESSION_ERROR(6),
	NET_ERROR(7),
	CMD_NULL(8),
	PLAYER_HAS_LOGIN(9),
	FORBID(10),
	BODY_NULL(11),
	TYPE_NULL(12),
	HANDLER_NULL(13),
	
	;
	
	private int code;
	private ErrorCode(int code) {
		this.code=code;
	}
	public int getCode() {
		return code;
	}
	
	public static ErrorCode toErrorCode(int code) {
		return values()[code];
	}
}
