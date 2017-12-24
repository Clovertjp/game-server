package com.game.common.server.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tangjp
 *
 */
public abstract class AbstractFilter<T> {
	
	public boolean isInForbidList(T val) {
		if(getFilterList()==null) {
			return true;
		}
		return !getFilterList().contains(val);
	}
	
	public Map<T,Boolean> getCheckList(List<T> checkList){
		if(checkList==null || checkList.isEmpty()) {
			return null;
		}
		Map<T,Boolean> check=new HashMap<>();
		for(int i=0;i<checkList.size();i++) {
			T checkVal=checkList.get(i);
			check.put(checkVal, !getFilterList().contains(checkVal));
		}
		return check;
	}
	
	public void reloadFilterList() {
		loadFilterList();
	}
	
	public abstract void loadFilterList();
	
	public abstract List<T> getFilterList();
	
	
}
