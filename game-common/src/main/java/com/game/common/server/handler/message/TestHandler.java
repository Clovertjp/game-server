package com.game.common.server.handler.message;

import com.game.common.exception.GameException;
import com.game.common.server.handler.AbstractGameBaseHandler;
import com.game.common.server.palyer.GamePlayer;
import com.game.pb.server.message.ResLoginOuterClass.ResLogin;
import com.google.protobuf.Message;

/**
 * @author tangjp
 *
 */
public class TestHandler extends AbstractGameBaseHandler {
	
	public static final String CMD="test";

	@Override
	public Message handlerRequest(Message msg, GamePlayer gamePlayer) throws GameException {
		// TODO Auto-generated method stub
		return ResLogin.newBuilder().setParam(gamePlayer.getUid()).build();
	}

	@Override
	public String getCmd() {
		// TODO Auto-generated method stub
		return CMD;
	}

}
