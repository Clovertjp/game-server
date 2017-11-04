package com.game.common.server;

import com.game.common.server.coder.GameServerDecoder;
import com.game.common.server.coder.GameServerEncoder;
import com.game.common.server.handler.GameServerHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author tangjp
 *
 */
public class NettyGameServerInit extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		// 都属于ChannelOutboundHandler，逆序执行 
		ch.pipeline().addLast("encoder",new GameServerEncoder());
		
		// 都属于ChannelIntboundHandler，按照顺序执行
		ch.pipeline().addLast("decoder",new GameServerDecoder());
		
		ch.pipeline().addLast("handler",new GameServerHandler());
		
	}

}
