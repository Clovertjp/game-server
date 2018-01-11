package com.game.common.server.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game.common.exception.GameException;

/**
 * @author tangjp
 *
 */
public abstract class AbstractStaticFilter<T> implements IFilter<T> {
	
	public boolean isInForbidList(T val) {
		if(getFilterList()==null) {
			return false;
		}
		return getFilterList().contains(val);
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
	
	@Override
	public int addCount(T val) throws GameException {
		return 0;
	}
	
	public void loadForbid() {
		
	}
	
	public void storeForbid() {
		
	}
	
}
