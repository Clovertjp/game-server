package com.game.common.server.filter.bean;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import com.game.common.server.util.DateUtils;

/**
 * @author tangjp
 *
 */
public class DynFilterCount {
	
	private AtomicInteger count;
	private int minute;
	
	public DynFilterCount() {
		count=new AtomicInteger(0);
		minute=DateUtils.getTimeByField(Calendar.MINUTE);
	}
	
	public int addCount() {
		if(minute!=DateUtils.getTimeByField(Calendar.MINUTE)) {
			count.set(0);
			minute=DateUtils.getTimeByField(Calendar.MINUTE);
		}
		return count.incrementAndGet();
	}
	
	public int getCount() {
		return count.get();
	}
}
