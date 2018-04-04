package com.game.common.server.coder;

import java.io.IOException;

import org.xerial.snappy.Snappy;

import com.game.common.pb.object.GameObject;
import com.game.common.pb.object.GameObject.GamePbObject;
import com.game.common.server.msg.GameMessage;
import com.game.pb.server.message.MessageObj;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author tangjp
 *
 */
public class GameServerEncoder extends MessageToByteEncoder<GameMessage> {

	@Override
	protected void encode(ChannelHandlerContext ctx, GameMessage msg, ByteBuf out) throws Exception {
		byte[] data=msg.getBody();
		data=compress(data);
		int len=data.length+13;
		out.writeInt(len);
		out.writeInt(msg.getGroupId());
		out.writeInt(msg.getSubGroupId());
		out.writeInt(msg.getErrorCode().getCode());
		out.writeByte(1);
		out.writeBytes(data);
	}
	
	//对数据进行压缩
	public byte[] compress(byte[] data) throws IOException{
		return Snappy.compress(data);
	}

}
