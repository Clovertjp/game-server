package com.game.common.server;

import com.game.common.exception.GameException;

/**
 * @author tangjp
 *
 */
public class ShutdownHook extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			GameMain.stop();
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
