package com.game.common.server.handler.message;

import com.game.common.server.handler.AbstractLoginHandler;
import com.game.common.server.palyer.GamePlayer;
import com.game.pb.server.message.ReqLoginOuterClass.ReqLogin;
import com.game.pb.server.message.ResLoginOuterClass.ResLogin;
import com.google.protobuf.Message;

/**
 * @author tangjp
 *
 */
public class LoginHandler extends AbstractLoginHandler<Message,Message> {

	@Override
	public Message onLogin(GamePlayer gamePlayer,Message msg) {
		ReqLogin login=(ReqLogin) msg;
		return ResLogin.newBuilder().setParam("1").build();
	}
}
