package com.game.common.server.coder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import org.xerial.snappy.Snappy;

import com.game.common.pb.object.GameObject;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author tangjp
 *
 */
public class GameServerDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext paramChannelHandlerContext, ByteBuf paramByteBuf,
			List<Object> paramList) throws Exception {
		// TODO Auto-generated method stub
		if (paramByteBuf.readableBytes() < 4) {
            return;
        }
		paramByteBuf.markReaderIndex();
        int dataLength = paramByteBuf.readInt();
        if (dataLength < 0) {
        	paramChannelHandlerContext.close();
        }

        if (paramByteBuf.readableBytes() < dataLength) {
        	paramByteBuf.resetReaderIndex();
            return;
        }

        byte[] body = new byte[dataLength];
        paramByteBuf.readBytes(body); 
        body = uncompress(body);
        GameObject.GamePbObject pbObj=GameObject.GamePbObject.parseFrom(body);
        paramList.add(pbObj);
        
	}
	
	//对数据进行解压
	public byte[] uncompress(byte[] data) throws IOException{
		return Snappy.uncompress(data);
	}
	
	

}
