package com.game.common.server.format;

import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;

public class ProtobufMessageFormat extends AbstractMessageFormat {

	private static JsonFormat format=new JsonFormat();
	
	@Override
	protected String doFormat(Object data) {
		return format.printToString((Message)data);
	}

}
