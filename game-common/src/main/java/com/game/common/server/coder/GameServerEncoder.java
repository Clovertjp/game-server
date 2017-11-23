package com.game.common.server.coder;

import java.io.IOException;

import org.xerial.snappy.Snappy;

import com.game.common.pb.object.GameObject;
import com.game.common.pb.object.GameObject.GamePbObject;
import com.game.pb.server.message.MessageObj;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author tangjp
 *
 */
public class GameServerEncoder extends MessageToByteEncoder<MessageObj.NetMessage> {

	@Override
	protected void encode(ChannelHandlerContext ctx, MessageObj.NetMessage msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub
		byte[] data=msg.toByteArray();
		System.out.println("------begin "+data.length);
		data=compress(data);
		System.out.println("------end "+data.length);
		int len=data.length;
		out.writeInt(len);
		out.writeBytes(data);
	}
	
	//对数据进行压缩
	public byte[] compress(byte[] data) throws IOException{
		return Snappy.compress(data);
	}

}
