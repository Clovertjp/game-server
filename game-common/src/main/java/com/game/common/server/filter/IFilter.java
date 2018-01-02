package com.game.common.server.filter;

import java.util.List;
import java.util.Map;

import com.game.common.exception.GameException;

/**
 * @author tangjp
 *
 */
public interface IFilter<T> {

	public void loadFilterList();
	
	public List<T> getFilterList();
	
	public int addCount(T val) throws GameException;
	
	public boolean isInForbidList(T val) ;
	
	public Map<T,Boolean> getCheckList(List<T> checkList);
	
	public void loadForbid();
	
	public void storeForbid();
}
