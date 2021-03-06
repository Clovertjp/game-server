package com.game.common.server.filter;

import java.util.List;

import com.game.common.server.config.Config;

/**
 * @author tangjp
 *
 */
public class DynIPFilter extends AbstractDynFilter<String> {

	@Override
	public int getForbidMax() {
		return Config.FILTER_DY_IP_MAX;
	}

	@Override
	public long getForbidMaxTime() {
		return Config.FILTER_DY_IP_TIME;
	}

	@Override
	public String getRedisKey() {
		return Config.FILTER_DY_IP_KEY;
	}
}
