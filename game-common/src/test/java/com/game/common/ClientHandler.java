package com.game.common;

import com.game.common.pb.object.GameObject;
import com.game.common.pb.object.GameObject.GamePbObject;
import com.game.pb.server.message.MessageObj;
import com.game.pb.server.message.ReqLoginOuterClass.ReqLogin;
import com.googlecode.protobuf.format.JsonFormat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author tangjp
 *
 */
public class ClientHandler extends SimpleChannelInboundHandler<MessageObj.NetMessage> {
	
	public ClientHandler(String uid) {
		this.uid=uid;
	}
	
	String uid;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageObj.NetMessage msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("read "+msg);
		int i=1;
		ReqLogin req=ReqLogin.newBuilder().setParam("read").setUid("1").build();
		MessageObj.NetMessage obj=MessageObj.NetMessage.newBuilder().setCmd("test")
				.setClassName(req.getClass().getSimpleName())
				.setUid(uid).setClassData(req.toByteString())
				.build();
		System.out.println(req.getClass().getSimpleName());
		JsonFormat format=new JsonFormat();
		System.out.println(format.printToString(obj));
		for(int j=0;j<1;j++) {
			ctx.writeAndFlush(obj);
			Thread.sleep(1000);
		}
			
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		ReqLogin req=ReqLogin.newBuilder().setParam("active").setUid("1").build();
		MessageObj.NetMessage obj=MessageObj.NetMessage.newBuilder().setCmd("login")
				.setClassName(req.getClass().getSimpleName())
				.setUid(uid).setClassData(req.toByteString())
				.build();
		System.out.println(req.getClass().getSimpleName());
		JsonFormat format=new JsonFormat();
		System.out.println(format.printToString(obj));
		ctx.writeAndFlush(obj);
	}

}
