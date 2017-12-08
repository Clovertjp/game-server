package com.game.common.server;

import com.game.common.exception.GameException;

/**
 * @author tangjp
 *
 */
public class ShutdownHook extends Thread {

	@Override
	public void run() {
		try {
			GameMain.stop();
		} catch (GameException e) {
			e.printStackTrace();
		}
	}

}
