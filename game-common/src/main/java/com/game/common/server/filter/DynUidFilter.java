package com.game.common.server.filter;

import com.game.common.server.config.Config;

/**
 * @author tangjp
 *
 */
public class DynUidFilter extends AbstractDynFilter<String> {

	@Override
	public int getForbidMax() {
		return Config.FILTER_DY_UID_MAX;
	}

	@Override
	public long getForbidMaxTime() {
		return Config.FILTER_DY_UID_TIME;
	}

	@Override
	public String getRedisKey() {
		return Config.FILTER_DY_UID_KEY;
	}

}
