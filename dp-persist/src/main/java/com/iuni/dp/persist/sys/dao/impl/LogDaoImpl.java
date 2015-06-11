package com.iuni.dp.persist.sys.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.sys.dao.LogDao;
import com.iuni.dp.persist.sys.model.Log;
 
/**
 * 操作日志
 * @author menglei
 */
@Repository("logDao")
public class LogDaoImpl extends BaseDaoImpl<Log,Serializable> implements LogDao {

	private static Logger LOGGER = LoggerFactory.getLogger(LogDaoImpl.class);	
	
	public void saveLog(Log log) {
		insert(log, LogDao.class.getSimpleName()+".saveLog");	
	}

	@SuppressWarnings("unchecked")
	public List<Log> getLogs(Map<String, Object> map) {
		return  getSqlMapClientTemplate().queryForList(LogDao.class.getSimpleName()+".getLogs", map);
	}

	public void deleteLog(String[] logIds) throws SQLException {
		getSqlMapClientTemplate().delete(LogDao.class.getSimpleName()+".deleteLogs", logIds);
	}
	
	public Integer getTotalCnt(Map<String, Object> map) {
		return  findAllObjectsCount(LogDao.class.getSimpleName()+".getTotalCnt", map);
	}
	
	@Override
	public void deleteLogforIntervalTime(String intervalTime) {
		this.getSqlMapClientTemplate().delete(LogDao.class.getSimpleName()+".deleteLogsforIntervalTime", intervalTime);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void saveBatchLog(final List<Log> logList) {
		if (CollectionUtils.isEmpty(logList)) {
			return;
		}
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() { 
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException { 
               executor.startBatch(); 
               int index=0;
               for(Log log : logList){
            	   if(log != null){
            		   index++;
            		   log.setLogindex(index);
            		   int memolength=count(log.getMemo());
            		   if(memolength>4000){
            			   log.setMemo(getMemo(log.getMemo()));
            		   }
            		   executor.insert(LogDao.class.getSimpleName()+".saveLog", log);
            	   }
               }
               executor.executeBatch(); 
               return null; 
            } 
        }); 
	}
	
	public static int count(String str) {
	    if(str == null || str.length() == 0) {
	        return 0;
	    }
	    int count = 0;
	    char[] chs = str.toCharArray();
	    for(int i = 0; i < chs.length; i++) {
	        count += (chs[i] > 0xff) ? 3 : 1;
	    }
	    return count;
	} 
	
	public static String getMemo(String str) {
	    if(str == null || str.length() == 0) {
	        return "";
	    }
	    int count = 0;
	    char[] chs = str.toCharArray();
	    int maxleng=0;
	    for(int i = 0; i < chs.length; i++) {
	        count += (chs[i] > 0xff) ? 3 : 1;
	        if(count>3900){
	        	maxleng=i;
	        	break;
	        }
	    }
	    return str.substring(0,maxleng);
	} 

	public static void main(String[] args) {
//		ApplicationContext context= new ClassPathXmlApplicationContext("classpath:config/spring/applicationContext*.xml");
//		System.out.println("it's the map:" +  SysParamCons.sysParamMap);
		//ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:config/spring/applicationContext.xml","classpath:config/spring/applicationContext-jdbc.xml","classpath:config/spring/applicationContext-service.xml"});
//		iasLogDao logDao=(iasLogDao) context.getBean("logDao");
//		
//		List<iasLog> list = new ArrayList<iasLog>();
//		iasLog iasLog = new iasLog(11L, "test1", "2", "3", "4");
//		list.add(iasLog);
//		iasLog iasLog2 = new iasLog(11L, "test2", "2", "3", "4");
//		list.add(iasLog2);
//		iasLog iasLog3 = new iasLog(11L, "test3", "2", "3", "4");
//		list.add(iasLog3);
//		
//		logDao.saveBatchiasLog(list);
		
//		logDao.saveiasLog(iasLog);
//		logDao.saveiasLog(iasLog);
//		logDao.saveiasLog(iasLog);
 
//		List<String> list = new ArrayList<String>();
//		list.add("1");
//		list.add("2");
//		list.add("3");
//		try {
//			logDao.deleteiasLog(new String[]{"4","8"});
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
//		Map map = new HashMap();
//		map.put("beginRow", 1);
//		map.put("endRow", 10);
 
//		List<iasLog> list = logDao.getLogs(map);
//		System.out.println(list);
//		for(iasLog log : list){
//			System.out.println(log.getLogId());
//		}
		
	}
 
}
