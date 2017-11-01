package com.game.common.server;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.exception.GameException;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 游戏逻辑服
 * @author tangjp
 *
 */
public class NettyGameServer extends GameServer {
	private final static String className="NettyGameServer";
	private static final Logger logger = LogManager.getLogger(NettyGameServer.class);
	private EventLoopGroup boss;
	private EventLoopGroup worker;
	private ChannelFuture channelFuture;
	private final int BOSS_NUM;
	private final int WORKER_NUM;
	private final int PORT;
	private final int BACKLOG_NUM;
	private final int RCVBUF;
	private final int SNDBUF;
	
	public NettyGameServer() {
		// TODO Auto-generated constructor stub
		super(className);
		BOSS_NUM=1;
		WORKER_NUM=2;
		PORT=8000;
		BACKLOG_NUM=512;
		RCVBUF=1024;
		SNDBUF=1024*32;
	}

	@Override
	public void initServer() throws GameException {
		// TODO Auto-generated method stub
	}

	@Override
	public void startServer() throws GameException {
		// TODO Auto-generated method stub
		boss = new NioEventLoopGroup(BOSS_NUM);
		worker = new NioEventLoopGroup(WORKER_NUM);
		ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss,worker)
        	.channel(NioServerSocketChannel.class)
        	.childHandler(new NettyGameServerInit())
        	// backlog参数指定队列的大小 暂定512
        	.option(ChannelOption.SO_BACKLOG,BACKLOG_NUM)
        	// 当设置该选项以后，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文
        	.option(ChannelOption.SO_KEEPALIVE,true)
        	// Nagle算法
        	.option(ChannelOption.TCP_NODELAY,true)
        	// 允许重复使用本地地址和端口
        	.option(ChannelOption.SO_REUSEADDR, true)
        	// 接收缓冲区
        	.option(ChannelOption.SO_RCVBUF, RCVBUF)  
        	// 发送缓冲区
            .option(ChannelOption.SO_SNDBUF, SNDBUF)
            .option(EpollChannelOption. SO_REUSEPORT, true)
            .childOption(ChannelOption.SO_RCVBUF, RCVBUF)  
            .childOption(ChannelOption.SO_SNDBUF, SNDBUF)
        	.childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
			channelFuture=bootstrap.bind(PORT).sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error(className,e);
			throw new GameException(className, e);
		}
	}

	@Override
	public void stopServer() throws GameException {
		// TODO Auto-generated method stub
		try{
			if(channelFuture!=null){
				channelFuture.awaitUninterruptibly();
			}
		}finally {
			if(worker!=null){
				worker.shutdownGracefully();
			}
			if(boss!=null){
				boss.shutdownGracefully();
			}
		}
		
	}

	

}
