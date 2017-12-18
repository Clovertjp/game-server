package com.game.common.server.filter;

import java.util.List;

/**
 * @author tangjp
 *
 */
public abstract class AbstractFilter<T> {
	
	public abstract List<T> getFilterList();

	public boolean isInForbidList(T val) {
		if(getFilterList()==null) {
			return true;
		}
		return !getFilterList().contains(val);
	}
	
	public abstract void loadFilterList();
	
	public void reloadFilterList() {
		loadFilterList();
	}
}
