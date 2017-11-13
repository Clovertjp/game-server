package com.game.common.server.sql;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.server.config.Config;
import com.game.common.server.config.PropertiesManager;
import com.game.common.server.handler.GameHandlerManager;
import com.game.common.server.puredb.dao.Tb1Mapper;
import com.game.common.server.puredb.model.Tb1;


/**
 * @author tangjp
 *
 */
public class MySqlManager {
	
	private static final Logger logger = LogManager.getLogger(MySqlManager.class);
	
	private SqlSessionFactory sessionFactory;
	private static MySqlManager sqlManager=new MySqlManager();
	
	private List<SessionAutoClose> sqlSessionList=new ArrayList<>();
	
	private MySqlManager(){
		InputStream is = MySqlManager.class.getClassLoader().getResourceAsStream(Config.MYBATIS_XML);
		sessionFactory = new SqlSessionFactoryBuilder().build(is
				,Config.SQL_POOL
				,PropertiesManager.getSystemConfig());
	}
	
	public static MySqlManager getInstance(){
		return sqlManager;
	}
	
	public SqlSession getSqlSession(Class<?> cl){
		SqlSession session=sessionFactory.openSession();
		if(Config.SQL_AUTO_CLOSE){
			SessionAutoClose autoSession=new SessionAutoClose(session, cl.getName(),this);
			sqlSessionList.add(autoSession);
			return autoSession;
		}
		return session;
	}
	
	public void removeSession(SessionAutoClose session){
		sqlSessionList.remove(session);
	}
	
	public void schedulerRemove(){
		
		List<SessionAutoClose> leakSessions=getLeakList();
		if(leakSessions.isEmpty()){
			return;
		}
		for(SessionAutoClose ses : leakSessions){
			logger.error("sql session closed by scheduler , "+ses.getClassName()
				+"   create time "+ses.getCreateTime()
				+"   access time "+ses.getAccessTime());
			ses.close();
		}
		
	}
	
	private List<SessionAutoClose> getLeakList(){
		List<SessionAutoClose> leakSessions = new ArrayList<>();

		if(sqlSessionList.isEmpty()){
			return leakSessions;
		}
		long now=System.currentTimeMillis()/1000;
		for(SessionAutoClose ses : sqlSessionList){
			if(ses.isOperationInProgress()){
				continue;
			}
			if(now-ses.getAccessTime()>=Config.SQL_AUTO_TIME){
				leakSessions.add(ses);
			}
		}
		return leakSessions;
	}
	
	
	public static void main(String[] args) {
		 SqlSession session = MySqlManager.getInstance().getSqlSession(MySqlManager.class);
		 
	     Tb1 tb=session.getMapper(Tb1Mapper.class).selectByPrimaryKey(1);
	     System.out.println(tb);
	}
	
	

}
