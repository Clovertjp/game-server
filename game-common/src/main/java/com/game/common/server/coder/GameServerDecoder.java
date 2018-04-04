package com.game.common.server.coder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import org.xerial.snappy.Snappy;

import com.game.common.exception.ErrorCode;
import com.game.common.pb.object.GameObject;
import com.game.common.server.msg.GameMessage;
import com.game.pb.server.message.MessageObj;

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
		
		if (paramByteBuf.readableBytes() < 4) {
            return;
        }
		paramByteBuf.markReaderIndex();
        int dataLength = paramByteBuf.readInt();
        if (dataLength < 0) {
        		paramChannelHandlerContext.close();
        }
        
        //协议头
        if(paramByteBuf.readableBytes()<13) {
        		paramByteBuf.resetReaderIndex();
            return;
        }
        
        int groupId=paramByteBuf.readInt();
        int subGroupId=paramByteBuf.readInt();
        int errorCode=paramByteBuf.readInt();
        byte compressAble=paramByteBuf.readByte();
        dataLength-=13;
        if (paramByteBuf.readableBytes() < dataLength) {
        		paramByteBuf.resetReaderIndex();
            return;
        }

        byte[] body = new byte[dataLength];
        paramByteBuf.readBytes(body); 
        if(compressAble==1) {
        		body = uncompress(body);
        }
        
        GameMessage message=new GameMessage();
        message.setBody(body);
        message.setGroupId(groupId);
        message.setSubGroupId(subGroupId);
        message.setErrorCode(ErrorCode.toErrorCode(errorCode));
        paramList.add(message);
        
	}
	
	//对数据进行解压
	public byte[] uncompress(byte[] data) throws IOException{
		return Snappy.uncompress(data);
	}
	
	

}
