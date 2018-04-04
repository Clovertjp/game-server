package com.game.common.server.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.game.common.exception.GameException;

/**
 * @author tangjp
 *
 */
public class FilterManager {
	
	public static interface IFilterType{
		public int getType();
		public IFilterType toOtherFilterType();
	}
	
	public static class StaticFilterType implements IFilterType{
		public static IFilterType STATIC_IP=new StaticFilterType(0);
		public static IFilterType STATIC_UID=new StaticFilterType(1);
		private int type;
		private StaticFilterType(int type) {
			this.type=type;
		}
		
		@Override
		public int getType() {
			return type;
		}

		@Override
		public IFilterType toOtherFilterType() {
			switch(type) {
			case 0:
				return DynFilterType.DYN_IP;
			case 1:
				return DynFilterType.DYN_UID;
			default:
				return null;
			}
		}
		
	}
	
	public static class DynFilterType implements IFilterType{
		public static IFilterType DYN_IP=new DynFilterType(0);
		public static IFilterType DYN_UID=new DynFilterType(1);
		private int type;
		private DynFilterType(int type) {
			this.type=type;
		}
		
		@Override
		public int getType() {
			return type;
		}

		@Override
		public IFilterType toOtherFilterType() {
			switch(type) {
			case 0:
				return StaticFilterType.STATIC_IP;
			case 1:
				return StaticFilterType.STATIC_UID;
			default:
				return null;
			}
		}
	}
	
	private Map<IFilterType,IFilter> filterMap;
	
	private static FilterManager filterManager=new FilterManager();
	
	public static FilterManager getInstance() {
		return filterManager;
	}
	
	private FilterManager() {
		
		filterMap=new HashMap<>();
		filterMap.put(StaticFilterType.STATIC_IP, new IPFilter());
		filterMap.put(StaticFilterType.STATIC_UID, new UidFilter());
		filterMap.put(DynFilterType.DYN_IP, new DynIPFilter());
		filterMap.put(DynFilterType.DYN_UID, new DynUidFilter());
		reloadAll();
		loadDyn();
	}
	
	public void loadDyn() {
		for(IFilter filter : filterMap.values()) {
			filter.loadForbid();
		}
	}
	
	public void reloadAll() {
		for(IFilter filter : filterMap.values()) {
			filter.loadFilterList();
		}
	}
	
	public boolean check(String val,IFilterType type) {
		return filterMap.get(type).isInForbidList(val);
	}
	
	public Map<String,Boolean> checkList(List<String> list,IFilterType type){
		return filterMap.get(type).getCheckList(list);
	}
	
	public void addMsgFilterCount(String val,DynFilterType type) throws GameException {
		if(StringUtils.isBlank(val)) {
			return;
		}
		IFilterType typ=type.toOtherFilterType();
		if(typ!=null) {
			if(check(val,typ)) {
				throw new GameException(val+" has in static forbid ");
			}
		}
		filterMap.get(type).addCount(val);
	}
	
	public void restoreInfo() {
		for(IFilter filter : filterMap.values()) {
			filter.storeForbid();
		}
	}

}
