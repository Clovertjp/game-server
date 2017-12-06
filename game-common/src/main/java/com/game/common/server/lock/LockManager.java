package com.game.common.server.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.game.common.exception.GameException;

/**
 * @author tangjp
 *
 */
public class LockManager {
	
	private static class LazyHolder{
        final private static LockManager INSTANCE = new LockManager();
    }
	
	public static LockManager getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	private Map<Object,Object> lockMap=new ConcurrentHashMap<>();
	
	private boolean lock(List<Object> lockList) {
		if(lockList==null || lockList.isEmpty()) {
			return true;
		}
		boolean ret=true;
		
		List<Object> lockedList=new ArrayList<>();
		for(Object obj : lockList) {
			if(lockMap.putIfAbsent(obj, new Object())!=null) {
				ret=false;
				break;
			}
			lockedList.add(obj);
		}
		if(!ret) {
			unlock(lockedList);
		}
		
		return ret;
	}
	
	private void unlock(List<Object> lockedList) {
		if(lockedList==null || lockedList.isEmpty()) {
			return ;
		}
		try {
			for(Object obj : lockedList) {
				lockMap.remove(obj);
			}
		}finally {
			
		}
	}
	
	public Object gameLock(ILockBatch batch) throws GameException {
		if(batch==null) {
			return null;
		}
		List<Object> locklist=null;
		if(batch.getLockList()!=null) {
			locklist=new ArrayList<>(batch.getLockList());
		}
		
		if(lock(locklist)) {
			try {
				return batch.run();
			}finally {
				unlock(locklist);
			}
		}else {
			batch.lockFalse();
		}
		return null;
		
	}

}
