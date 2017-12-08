package com.game.common.server.sql;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

/**
 * @author tangjp
 *
 */
public class SessionAutoClose implements SqlSession {
	
	private SqlSession session;
	private long createTime;
	private String className;
	private long accessTime;
	private volatile boolean operationInProgress = false;
	private MySqlManager manager;
	
	public SessionAutoClose(SqlSession session,String className,MySqlManager manager){
		this.session=session;
		this.className= className;
		createTime=System.currentTimeMillis()/1000;
		accessTime=createTime;
		this.manager=manager;
	}

	public SqlSession getSession() {
		return session;
	}

	public void setSession(SqlSession session) {
		this.session = session;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public long getCreateTime() {
		return createTime;
	}
	
	public void setAccessTime() {
		accessTime=System.currentTimeMillis()/1000;
	}
	
	public long getAccessTime() {
		return accessTime;
	}
	
	private void setOperationInProgress() {
		operationInProgress = true;
	}

	private void setOperationCompleted() {
		operationInProgress = false;
	}
	
	public boolean isOperationInProgress() {
		return operationInProgress;
	}

	@Override
	public <T> T selectOne(String statement) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.selectOne(statement);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public <T> T selectOne(String statement, Object parameter) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.selectOne(statement, parameter);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public <E> List<E> selectList(String statement) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.selectList(statement);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public <E> List<E> selectList(String statement, Object parameter) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.selectList(statement, parameter);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.selectList(statement, parameter, rowBounds);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.selectMap(statement, mapKey);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.selectMap(statement, parameter, mapKey);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.selectMap(statement, parameter, mapKey, rowBounds);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public <T> Cursor<T> selectCursor(String statement) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.selectCursor(statement);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public <T> Cursor<T> selectCursor(String statement, Object parameter) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.selectCursor(statement, parameter);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.selectCursor(statement, parameter, rowBounds);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public void select(String statement, Object parameter, ResultHandler handler) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			session.select(statement, parameter, handler);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public void select(String statement, ResultHandler handler) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			session.select(statement, handler);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			session.select(statement, parameter, rowBounds, handler);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public int insert(String statement) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.insert(statement);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public int insert(String statement, Object parameter) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.insert(statement, parameter);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public int update(String statement) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.update(statement);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public int update(String statement, Object parameter) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.update(statement, parameter);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public int delete(String statement) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.delete(statement);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public int delete(String statement, Object parameter) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.delete(statement, parameter);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public void commit() {
		
		setAccessTime();
		try {
			setOperationInProgress();
			session.commit();
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public void commit(boolean force) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			session.commit(force);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public void rollback() {
		
		setAccessTime();
		try {
			setOperationInProgress();
			session.rollback();
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public void rollback(boolean force) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			session.rollback(force);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public List<BatchResult> flushStatements() {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.flushStatements();
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public void close() {
		
		session.close();
		manager.removeSession(this);
	}

	@Override
	public void clearCache() {
		
		setAccessTime();
		session.clearCache();
	}

	@Override
	public Configuration getConfiguration() {
		
		setAccessTime();
		return session.getConfiguration();
	}

	@Override
	public <T> T getMapper(Class<T> type) {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.getMapper(type);
		} finally {
			setOperationCompleted();
		}
	}

	@Override
	public Connection getConnection() {
		
		setAccessTime();
		try {
			setOperationInProgress();
			return session.getConnection();
		} finally {
			setOperationCompleted();
		}
	}
	
	

}
