package com.game.common.server.action;

import com.game.common.server.session.GameSession;

/**
 * @author tangjp
 *
 */
public class MessageAction<T> implements IAction<T> {
	
	private GameSession session;
	
	private T msg;
	
	private long time;
	
	public MessageAction(GameSession session,T msg) {
		this.session=session;
		this.msg=msg;
		time=System.currentTimeMillis();
	}

	@Override
	public GameSession getSession() {
		return session;
	}

	@Override
	public void setSession(GameSession session) {
		this.session=session;
	}

	@Override
	public T getMsgObject() {
		return msg;
	}

	@Override
	public void setMsgObject(T msg) {
		this.msg=msg;
	}

	@Override
	public long getCreateTime() {
		return time;
	}


}
