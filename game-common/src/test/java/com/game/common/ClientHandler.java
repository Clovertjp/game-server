package com.game.common;

import com.game.common.pb.object.GameObject;
import com.game.common.pb.object.GameObject.GamePbObject;

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
		GameObject.GamePbObject obj=GameObject.GamePbObject.newBuilder().setCmd("a").putIntValues("num", ++i)
				.putStringValues("hhh", "aaa").putStringValues("11", "aaa").putStringValues("22", "aaa")
				.build();
		System.out.println(obj);
		ctx.writeAndFlush(obj);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		GameObject.GamePbObject obj=GameObject.GamePbObject.newBuilder().setCmd("a").putStringValues("hhh", "aaa").build();
		System.out.println(obj);
		ctx.writeAndFlush(obj);
	}

}
