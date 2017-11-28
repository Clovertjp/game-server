package com.game.common.server.scheduler.task;

import com.game.common.server.manager.GameSessionManager;

/**
 * @author tangjp
 *
 */
public class SessionSchedulerTask implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		GameSessionManager.getInstance().checkSession();
	}

}
