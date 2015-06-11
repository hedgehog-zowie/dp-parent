package com.iuni.dp.persist.dataalarm.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.dataalarm.dao.BusLogDAO;
import com.iuni.dp.persist.dataalarm.model.BusLog;

@Repository("busLogDao")
public class BusLogDAOImpl extends BaseDaoImpl<BusLog, Serializable> implements BusLogDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(BusLogDAOImpl.class);
	
	@Override
	public List<BusLog> getAllBusLog() {
		logger.debug("BusLogDAOImpl.getAllBusLog() invoke");
		long stime = System.currentTimeMillis();
		
		List<BusLog> busLogs = getAll(BusLog.class.getSimpleName() + ".getAllBusLog");
		
		logger.debug("BusLogDAOImpl.getAllBusLog() success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return busLogs;
	}
	
	@Override
	public List<BusLog> getBusLogByPage(Map<String,Object> params) {
		logger.debug("BusLogDAOImpl.getBusLogByPage(Map<String, Object>) invoke");
		long stime = System.currentTimeMillis();
		
		List<BusLog> busLogs = findAllObjectsByPage(BusLog.class.getSimpleName() + ".getBusLogByPage", params);
		
		logger.debug("BusLogDAOImpl.getBusLogByPage() success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return busLogs;
	}

	@Override
	public Integer getTotalCount(Map<String,Object> params) {
		logger.debug("BusLogDAOImpl.getTotalCount(Map<String, Object>) invoke");
		long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(BusLog.class.getSimpleName() + ".getTotalCount", params);
		
		logger.debug("BusLogDAOImpl.getTotalCount() success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Integer> batchSaveBusLog(final List<BusLog> busLogList){
		final List<Integer> busLogIdList = new ArrayList<Integer>();
		int busLogCountParm = (busLogList != null) ? busLogList.size() : 0;
		logger.debug("BusLogDAOImpl.batchSaveBusLog(List<BusLog>) entry: busLogCountParm={}",new Object[] { busLogCountParm });
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			int busLogListCount = (busLogList != null) ? busLogList.size() : 0;
			long stime = System.currentTimeMillis();
			public Object doInSqlMapClient(SqlMapExecutor executor)throws SQLException {
				executor.startBatch();
				if (busLogListCount > 0) {
					for (BusLog busLog : busLogList) {
						if (busLog != null) {
							Integer busLogId = (Integer) executor.insert(BusLog.class.getSimpleName()+ ".saveBusLog", busLog);
							busLogIdList.add(busLogId);
						}
					}
				}
				executor.executeBatch();
				logger.debug("BusLogDAOImpl.batchSaveBusLog(List<BusLog>) success: busLogCountSaveDB={},costTime={}ms",
						new Object[] { busLogListCount,System.currentTimeMillis() - stime });
				return executor;
			} 
        });
		return busLogIdList; 
	}
	
    @Override
    public int saveBusLog(BusLog busLog) {
		logger.debug("BusLogDAOImpl.saveBusLog(BusLog) entry: busLog={}",new Object[] { busLog.toString() });
		long stime = System.currentTimeMillis();
		int busLogId = (Integer)insert(busLog, BusLog.class.getSimpleName() + ".saveBusLog");
		logger.debug("BusLogDAOImpl.saveBusLog(BusLog) success: costTime={}ms",
					new Object[] { System.currentTimeMillis() - stime });
		return busLogId;
	}
	
}
