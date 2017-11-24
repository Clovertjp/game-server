package com.game.common;

import com.game.common.pb.object.GameObject;
import com.game.common.pb.object.GameObject.GamePbObject;
import com.game.pb.server.message.MessageObj;
import com.googlecode.protobuf.format.JsonFormat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author tangjp
 *
 */
public class ClientHandler extends SimpleChannelInboundHandler<GameObject.GamePbObject> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, GamePbObject msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("read "+msg);
		Thread.sleep(3000);
		int i=1;
		if(msg.containsIntValues("num")){
			i=msg.getIntValuesOrThrow("num");
		}
		GameObject.GamePbObject gobj=GameObject.GamePbObject.newBuilder().setCmd("a").putStringValues("hhh", "aaa").build();
		MessageObj.NetMessage obj=MessageObj.NetMessage.newBuilder().setCmd("a")
				.setClassName("com.game.common.pb.object.GameObject$GamePbObject")
				.setUid("1").setClassData(gobj.toByteString())
				.build();
		JsonFormat format=new JsonFormat();
		System.out.println(format.printToString(obj));
		ctx.writeAndFlush(obj);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		GameObject.GamePbObject gobj=GameObject.GamePbObject.newBuilder().setCmd("a").putStringValues("hhh", "aaabbb").build();
		MessageObj.NetMessage obj=MessageObj.NetMessage.newBuilder().setCmd("a")
				.setClassName("com.game.common.pb.object.GameObject$GamePbObject")
				.setUid("1").setClassData(gobj.toByteString())
				.build();
		System.out.println(GameObject.GamePbObject.class.getName());
		JsonFormat format=new JsonFormat();
		System.out.println(format.printToString(obj));
		ctx.writeAndFlush(obj);
	}

}
