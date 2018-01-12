package com.game.common.server.action;

import com.game.common.server.session.GameSession;

/**
 * @author tangjp
 *
 */
public interface IAction<T> {
	
	public GameSession getSession();
	
	public void setSession(GameSession session);
	
	public T getMsgObject();
	
	public void setMsgObject(T msg);
	
	public long getCreateTime();

}
