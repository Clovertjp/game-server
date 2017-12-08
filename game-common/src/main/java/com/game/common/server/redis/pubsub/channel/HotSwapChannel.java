package com.game.common.server.redis.pubsub.channel;

import com.game.common.server.hotswap.HotSwapAgentManager;
import com.game.common.server.redis.pubsub.IPubSubChannel;
import com.game.common.server.util.CommonUtils;

/**
 * @author tangjp
 *
 */
public class HotSwapChannel implements IPubSubChannel {

	@Override
	public void handle(String serverId) {
		if(CommonUtils.containCurrentServerId(serverId)) {
			HotSwapAgentManager.reloadAgent();
		}
	}

}
