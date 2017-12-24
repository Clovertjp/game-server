package com.game.common.server.filter;

import java.util.ArrayList;
import java.util.List;

import com.game.common.server.config.Config;
import com.game.common.server.redis.R;

/**
 * @author tangjp
 *
 */
public class IPFilter extends AbstractFilter<String> {
	
	private List<String> ipList=new ArrayList<>();

	@Override
	public List<String> getFilterList() {
		return ipList;
	}

	@Override
	public void loadFilterList() {
		ipList=R.getLocalRedis().getAllRangeList(Config.FILTER_IP_KEY);
	}

}
