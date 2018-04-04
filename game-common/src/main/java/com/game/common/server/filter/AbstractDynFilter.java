package com.game.common.server.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.game.common.exception.ErrorCode;
import com.game.common.exception.GameException;
import com.game.common.server.filter.bean.DynFilterCount;
import com.game.common.server.redis.R;

/**
 * @author tangjp
 *
 */
public abstract class AbstractDynFilter<T> extends AbstractStaticFilter<T> {
	
	private Map<T,DynFilterCount> countMap=new ConcurrentHashMap<>();
	
	private Map<T,Long> forbidMap=new ConcurrentHashMap<>();
	
	public int addCount(T val) throws GameException {
		if(!checkForbidTime(val)) {
			throw new GameException(val+" 已经被封禁", ErrorCode.FORBID);
		}
		if(!countMap.containsKey(val)) {
			countMap.put(val, new DynFilterCount());
		}
		int count= countMap.get(val).addCount();
		if(!check(count)) {
			forbidMap.put(val, System.currentTimeMillis()/1000);
			throw new GameException(val+" 刚被封禁,请求次数为："+count, ErrorCode.FORBID);
		}
		return count;
	}
	
	public boolean check(int count) {
		return count<getForbidMax();
	}
	
	public abstract int getForbidMax() ;
	
	public abstract long getForbidMaxTime() ;
	
	public abstract String getRedisKey() ;
	
	public boolean checkForbidTime(T val) {
		Long time=forbidMap.get(val);
		if(time==null) {
			return true;
		}
		if(System.currentTimeMillis()/1000-time<getForbidMaxTime()) {
			return false;
		}
		forbidMap.remove(val);
		return true;
	}
	
	public void storeForbid() {
		Map<String,String> storeMap=new HashMap<>();
		long nowTime=System.currentTimeMillis()/1000;
		for(Map.Entry<T, Long> entry : forbidMap.entrySet()) {
			if(nowTime-entry.getValue()<getForbidMaxTime()) {
				storeMap.put(entry.getKey().toString(), entry.getValue()+"");
			}
		}
		if(storeMap.size()<=0) {
			return;
		}
		R.getLocalRedis().hMSet(getRedisKey(), storeMap);
	}
	
	public void loadForbid() {
		Map<String, String> map=R.getLocalRedis().hMGetAll(getRedisKey());
		forbidMap.clear();
		long nowTime=System.currentTimeMillis()/1000;
		for(Map.Entry<String,String> entry : map.entrySet()) {
			long time=Long.parseLong(entry.getValue());
			if(nowTime-time < getForbidMaxTime()) {
				forbidMap.put((T)entry.getKey(), time);
			}
		}
	}
	
	@Override
	public void loadFilterList() {		
	}

	@Override
	public List<T> getFilterList() {
		return new ArrayList<>(forbidMap.keySet());
	}

}
