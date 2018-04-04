package com.game.common.server.coder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.game.common.exception.ErrorCode;
import com.game.common.exception.GameException;
import com.google.common.base.Strings;
import com.google.protobuf.ByteString;
import com.google.protobuf.Message;

public class ProtobufCoder extends AbstractCoder {
	
	private static ConcurrentMap<String, Method> parseFromMethods = new ConcurrentHashMap<>();

	@Override
	protected byte[] doEncode(Object obj, Class<?> type) throws GameException {
		return ((Class<? extends com.google.protobuf.AbstractMessageLite>)type)
				.cast(obj).toByteArray();
	}

	@Override
	protected Object doDecode(byte[] bytes, Class<?> type) throws GameException {
		return byteToMessage(bytes,type);
	}
	
	private Message byteToMessage(byte[] data,Class<?> type){
		Method parseFromMethod = getParseFromMethod(type);
		
		try {
			return (Message) parseFromMethod.invoke(null, data);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private Method getParseFromMethod(Class<?> type) {
		return parseFromMethods.computeIfAbsent(type.getName(), (k)->{
			Class<? extends Message> clazz = (Class<? extends Message>)type;
			Method m = null;
			try {
				m = clazz.getMethod("parseFrom", byte[].class);
			} catch (NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(e);
			}
			return m;
		});
	}

}
