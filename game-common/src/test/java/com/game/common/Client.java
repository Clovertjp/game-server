package com.game.common;

import java.net.InetSocketAddress;

import com.game.common.server.coder.GameServerDecoder;
import com.game.common.server.coder.GameServerEncoder;
import com.game.common.server.handler.GameServerHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author tangjp
 *
 */
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//worker负责读写数据
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            //辅助启动类
            Bootstrap bootstrap = new Bootstrap();

            //设置线程池
            bootstrap.group(worker);

            //设置socket工厂
            bootstrap.channel(NioSocketChannel.class);

            //设置管道
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    //获取管道
                    ch.pipeline().addLast("encoder",new GameServerEncoder());
            		
            		// 都属于ChannelIntboundHandler，按照顺序执行
            		ch.pipeline().addLast("decoder",new GameServerDecoder());
            		
            		ch.pipeline().addLast("handler",new ClientHandler());
                }
            });

            //发起异步连接操作
            ChannelFuture futrue = bootstrap.connect(new InetSocketAddress("127.0.0.1",8000)).sync();

            //等待客户端链路关闭
            futrue.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅的退出，释放NIO线程组
            worker.shutdownGracefully();
        }
	}

}
