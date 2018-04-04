package com.game.common.server.msg;

import com.game.common.exception.ErrorCode;

/**
 * 消息类
 * 消息格式:
 * [长度 int ][groupId int][subGroupId int][errorCode int][是否压缩(1 压缩 0 不压缩) byte][数据包 byte[]]
 * @author tangjp
 *
 */
public class GameMessage {
	
	private int groupId;
	private int subGroupId;
	private ErrorCode errorCode=ErrorCode.SUCCESS;
	private byte[] body;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getSubGroupId() {
		return subGroupId;
	}
	public void setSubGroupId(int subGroupId) {
		this.subGroupId = subGroupId;
	}
	public byte[] getBody() {
		return body;
	}
	public void setBody(byte[] body) {
		this.body = body;
	}
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
}
