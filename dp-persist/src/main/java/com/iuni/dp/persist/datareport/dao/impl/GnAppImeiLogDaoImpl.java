package com.iuni.dp.persist.datareport.dao.impl;

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
import com.iuni.dp.persist.datareport.dao.GnAppImeiLogDao;
import com.iuni.dp.persist.datareport.model.GnAppImeiLog;

/**
 * 金立相关APP客户端IMEI首次启用时间日志DAO实现类
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
@Repository("gnAppImeiLogDao")
public class GnAppImeiLogDaoImpl extends BaseDaoImpl<GnAppImeiLog, Serializable> implements GnAppImeiLogDao {

	private static final Logger logger = LoggerFactory.getLogger(GnAppImeiLogDaoImpl.class);
	
	@Override
	public List<GnAppImeiLog> selectGnAppImeiLogByMap(Map<String, Object> params) {
		logger.debug("GnAppImeiLogDaoImpl.selectGnAppImeiLogByMap(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<GnAppImeiLog> gnAppImeiLogs = findAllObjectsByPage(GnAppImeiLog.class.getSimpleName() 
				+ ".selectGnAppImeiLogByMap", params);
		
		logger.debug("GnAppImeiLogDaoImpl.selectGnAppImeiLogByMap(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return gnAppImeiLogs;
	}

	@Override
	public Integer selectGnAppImeiLogCount(Map<String, Object> params) {
		logger.debug("GnAppImeiLogDaoImpl.selectGnAppImeiLogCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(GnAppImeiLog.class.getSimpleName() 
				+ ".selectGnAppImeiLogCount", params);
		
		logger.debug("GnAppImeiLogDaoImpl.selectGnAppImeiLogCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Integer insertGnAppImeiLog(GnAppImeiLog gnAppImeiLog) {
		long stime = System.currentTimeMillis();
		Integer insertIndex = 0;
		
		logger.debug("GnAppImeiLogDaoImpl.insertGnAppImeiLog(WjqClientImeiLog) entry: wjqClientImeiLog={}"
				,new Object[] { gnAppImeiLog });
		
		insertIndex = (Integer) insert(gnAppImeiLog, GnAppImeiLog.class.getSimpleName()+".insertGnAppImeiLog");
		
		logger.debug("GnAppImeiLogDaoImpl.insertGnAppImeiLog(WjqClientImeiLog) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@SuppressWarnings({"deprecation"})
	@Override
	public Integer batchInsertGnAppImeiLog(final List<GnAppImeiLog> gnAppImeiLogs) {
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		int dataSize = (gnAppImeiLogs != null) ? gnAppImeiLogs.size() : 0;
		logger.debug("GnAppImeiLogDaoImpl.batchInsertGnAppImeiLog(List<GnAppImeiLog>) entry: dataSize={}"
				,new Object[] { dataSize });
		
		if (CollectionUtils.isEmpty(gnAppImeiLogs)) {
			return execCount;
		}
		
		execCount = getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			Integer execCount = 0;
			@Override
			public Integer doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for(GnAppImeiLog gnAppImeiLog : gnAppImeiLogs){
					if(null != gnAppImeiLog){
						executor.insert(GnAppImeiLog.class.getSimpleName()+".insertGnAppImeiLog", gnAppImeiLog);
						++execCount;
					}
	            }
	            executor.executeBatch(); 
				
				return execCount;
			}
		});
		
		logger.debug("GnAppImeiLogDaoImpl.batchInsertGnAppImeiLog(List<GnAppImeiLog>) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}

}
