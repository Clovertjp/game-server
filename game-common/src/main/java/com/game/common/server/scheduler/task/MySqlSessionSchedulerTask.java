package com.game.common.server.scheduler.task;

import com.game.common.server.sql.MySqlManager;

/**
 * @author tangjp
 *
 */
public class MySqlSessionSchedulerTask implements Runnable {

	@Override
	public void run() {
		MySqlManager.getInstance().schedulerRemove();
	}

}
