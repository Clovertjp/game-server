package com.game.common.server.handler;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.exception.GameException;
import com.game.common.pb.object.GameObject;
import com.game.common.pb.object.GameObject.GamePbObject;
import com.game.common.server.action.IAction;
import com.game.common.server.action.MessageAction;
import com.game.common.server.engine.GameEngine;
import com.game.common.server.manager.GameSessionManager;
import com.game.common.server.net.AbstractGameServer;
import com.game.common.server.session.GameSession;
import com.game.pb.server.message.MessageObj;
import com.game.pb.server.message.error.ErrorCodeOuterClass.ErrorCode;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author tangjp
 *
 */
public class GameServerHandler extends SimpleChannelInboundHandler<MessageObj.NetMessage> {
	private static final Logger logger = LogManager.getLogger(GameServerHandler.class);
	

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageObj.NetMessage msg) throws Exception {
		GameSession session=GameSessionManager.getInstance().getSession(ctx.channel());
		if(session==null){
			throw new GameException("session is null",ErrorCode.SESSION_ERROR);
		}
		if(StringUtils.isBlank(session.getUid())) {
			session.setUid(msg.getUid());
		}
		session.setUid(msg.getUid());
		session.updateReadTime();
		IAction<MessageObj.NetMessage> actionMsg=new MessageAction<>(session,msg);
		session.addMessage(actionMsg);
		logger.info("session create id "+session.getId()+" uid: "+session.getUid()+"  add message");
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
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
