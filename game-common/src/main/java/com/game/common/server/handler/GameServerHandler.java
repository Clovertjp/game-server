package com.game.common.server.handler;

import java.net.InetSocketAddress;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.exception.ErrorCode;
import com.game.common.exception.GameException;
import com.game.common.pb.object.GameObject;
import com.game.common.pb.object.GameObject.GamePbObject;
import com.game.common.server.action.IAction;
import com.game.common.server.action.MessageAction;
import com.game.common.server.engine.GameEngine;
import com.game.common.server.filter.FilterManager;
import com.game.common.server.filter.FilterManager.DynFilterType;
import com.game.common.server.manager.GameSessionManager;
import com.game.common.server.msg.GameMessage;
import com.game.common.server.net.AbstractGameServer;
import com.game.common.server.session.GameSession;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author tangjp
 *
 */
public class GameServerHandler extends SimpleChannelInboundHandler<GameMessage> {
	private static final Logger logger = LogManager.getLogger(GameServerHandler.class);
	

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, GameMessage msg) throws Exception {
		GameSession session=GameSessionManager.getInstance().getSession(ctx.channel());
		if(session==null){
			throw new GameException("session is null",ErrorCode.SESSION_ERROR);
		}
		String ip=((InetSocketAddress)ctx.channel().remoteAddress()).getAddress().getHostAddress();
		FilterManager.getInstance().addMsgFilterCount(session.getUid(), (DynFilterType) FilterManager.DynFilterType.DYN_UID);
		FilterManager.getInstance().addMsgFilterCount(ip, (DynFilterType) FilterManager.DynFilterType.DYN_IP);
		session.updateReadTime();
		IAction<GameMessage> actionMsg=new MessageAction<>(session,msg);
		session.addMessage(actionMsg);
		logger.info("session create id "+session.getId()+" uid: "+session.getUid()+"  add message"+"   ip:"+ip);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String ip=((InetSocketAddress)ctx.channel().remoteAddress()).getAddress().getHostAddress();
		FilterManager.getInstance().addMsgFilterCount(ip, (DynFilterType) FilterManager.DynFilterType.DYN_IP);
		
		GameSession session=new GameSession(ctx.channel());
		GameSessionManager.getInstance().addSession(session);
		logger.info("session create id"+session.getId()+"   ip:"+ctx.channel().remoteAddress());
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		GameSession session=GameSessionManager.getInstance().removeSession(ctx.channel());
		if(session!=null){
			GameEngine.getInstance().removePlayer(session.getUid());
			logger.info("session destory id"+session.getId()+" uid: "+session.getUid()+"   ip:"+ctx.channel().remoteAddress());
		}else{
			logger.info("session is null ,destory ip:"+ctx.channel().remoteAddress());
		}
		ctx.close();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		GameSession session=GameSessionManager.getInstance().removeSession(ctx.channel());
		if(session!=null){
			GameEngine.getInstance().removePlayer(session.getUid());
			logger.info("session destory id"+session.getId()+" uid: "+session.getUid()+"   ip:"+ctx.channel().remoteAddress(),cause);
		}else{
			logger.info("session is null ,destory ip:"+ctx.channel().remoteAddress(),cause);
		}
		ctx.close();
	}

}
