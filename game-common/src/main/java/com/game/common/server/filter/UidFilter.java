package com.game.common.server.filter;

import java.util.ArrayList;
import java.util.List;

import com.game.common.server.config.Config;
import com.game.common.server.redis.R;

/**
 * @author tangjp
 *
 */
public class UidFilter extends AbstractFilter<String> {
	
	private List<String> uidList=new ArrayList<>();

	@Override
	public void loadFilterList() {
		uidList=R.getLocalRedis().getAllRangeList(Config.FILTER_UID_KEY);
	}

	@Override
	public List<String> getFilterList() {
		return uidList;
	}

}
