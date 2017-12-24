package com.game.common.server.filter;

import java.util.List;
import java.util.Map;

/**
 * @author tangjp
 *
 */
public class FilterManager {
	
	private static IPFilter ipFilter;
	private static UidFilter uidFilter;
	
	private static FilterManager filterManager=new FilterManager();
	
	public static FilterManager getInstance() {
		return filterManager;
	}
	
	private FilterManager() {
		ipFilter=new IPFilter();
		uidFilter=new UidFilter();
		reloadAll();
	}
	
	public void reloadIp() {
		ipFilter.reloadFilterList();
	}
	
	public void reloadUid() {
		uidFilter.reloadFilterList();
	}
	
	public void reloadAll() {
		reloadIp();
		reloadUid();
	}
	
	public boolean checkIp(String ip) {
		return ipFilter.isInForbidList(ip);
	}
	
	public boolean checkUid(String uid) {
		return uidFilter.isInForbidList(uid);
	}
	
	public Map<String,Boolean> checkIpList(List<String> ipList){
		return ipFilter.getCheckList(ipList);
	}
	
	public Map<String,Boolean> checkUidList(List<String> uidList){
		return uidFilter.getCheckList(uidList);
	}

}
