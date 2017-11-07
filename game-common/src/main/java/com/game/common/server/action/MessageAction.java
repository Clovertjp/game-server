package com.game.common.server.action;

import com.game.common.server.session.GameSession;

/**
 * @author tangjp
 *
 */
public class MessageAction<T> implements IAction<T> {
	
	private GameSession session;
	
	private T msg;
	
	public MessageAction(GameSession session,T msg) {
		// TODO Auto-generated constructor stub
		this.session=session;
		this.msg=msg;
	}

	@Override
	public GameSession getSession() {
		// TODO Auto-generated method stub
		return session;
	}

	@Override
	public void setSession(GameSession session) {
		// TODO Auto-generated method stub
		this.session=session;
	}

	@Override
	public T getMsgObject() {
		// TODO Auto-generated method stub
		return msg;
	}

	@Override
	public void setMsgObject(T msg) {
		// TODO Auto-generated method stub
		this.msg=msg;
	}

}
