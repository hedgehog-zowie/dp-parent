package com.iuni.dp.persist.datareport.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datareport.dao.GnAppAccessLogDao;
import com.iuni.dp.persist.datareport.model.GnAppAccessLog;
import com.iuni.dp.persist.datareport.model.GnAppImeiLog;

/**
 * 金立相关APP客户端登入登出记录日志DAO实现类
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
@Repository("gnAppAccessLogDao")
public class GnAppAccessLogDaoImpl extends BaseDaoImpl<GnAppAccessLog, Serializable> implements GnAppAccessLogDao {

	private static final Logger logger = LoggerFactory.getLogger(GnAppAccessLogDaoImpl.class);
	
	@Override
	public Integer insertGnAppAccessLog(GnAppAccessLog gnAppAccessLog) {
		long stime = System.currentTimeMillis();
		Integer insertIndex = 0;
		
		logger.debug("GnAppAccessLogDaoImpl.insertGnAppAccessLog(GnAppAccessLog) entry: gnAppAccessLog={}"
				,new Object[] { gnAppAccessLog });
		
		insertIndex = (Integer) insert(gnAppAccessLog, GnAppAccessLog.class.getSimpleName()+".insertGnAppAccessLog");
		
		logger.debug("GnAppAccessLogDaoImpl.insertGnAppAccessLog(GnAppAccessLog) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	/**
	 * 批量新增金立相关APP客户端登入登出记录日志
	 * 每新增一条金立相关APP客户端登入登出记录日志，
	 * 查询查询金立相关APP客户端IMEI首次启用纪录日志记录中是否存在该AppName、ChannelName、APKVersion对应的IMEI，
	 * 若金立相关APP客户端IMEI首次启用记录日志没有记录则该AppName、ChannelName、APKVersion对应的IMEI作为首次启动金立相关APP客户端的记录插入
	 */
	@SuppressWarnings({"deprecation"})
	@Override
	public Integer batchInsertGnAppAccessLog(
			final List<GnAppAccessLog> gnAppAccessLogs) {
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		int dataSize = (gnAppAccessLogs != null) ? gnAppAccessLogs.size() : 0;
		logger.debug("GnAppAccessLogDaoImpl.batchInsertGnAppAccessLog(List<GnAppAccessLog>) entry: dataSize={}"
				, new Object[] { dataSize });
		
		if (CollectionUtils.isEmpty(gnAppAccessLogs)) {
			return execCount;
		}
		
		execCount = getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			Integer execCount = 0;
			@Override
			public Integer doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for(GnAppAccessLog gnAppAccessLog : gnAppAccessLogs){
					if(null != gnAppAccessLog){
						executor.insert(GnAppAccessLog.class.getSimpleName() + ".insertGnAppAccessLog", gnAppAccessLog);
						++execCount;
						
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("appName", gnAppAccessLog.getAppName());
						params.put("channelName", gnAppAccessLog.getChannelName());
						params.put("apkVersion", gnAppAccessLog.getApkVersion());
						params.put("imei", gnAppAccessLog.getImei());
						
						Integer count = (Integer) getObjectByProperty(GnAppImeiLog.class.getSimpleName() 
								+ ".selectGnAppImeiLogCount", params);
						
						if(count <= 0) {
							GnAppImeiLog gnAppImeiLog = new GnAppImeiLog();
							// base data
							gnAppImeiLog.setAppName(gnAppAccessLog.getAppName());
							gnAppImeiLog.setChannelName(gnAppAccessLog.getChannelName());
							gnAppImeiLog.setApkVersion(gnAppAccessLog.getApkVersion());
							gnAppImeiLog.setImei(gnAppAccessLog.getImei());
							gnAppImeiLog.setFirstLaunchTime(gnAppAccessLog.getStartupTime());
							// mobile data
							gnAppImeiLog.setMobileModel(gnAppAccessLog.getMobileModel());
							gnAppImeiLog.setMobileNumber(gnAppAccessLog.getMobileNumber());
							gnAppImeiLog.setModelHeight(gnAppAccessLog.getModelHeight());
							gnAppImeiLog.setModelWidth(gnAppAccessLog.getModelWidth());
							// network data
							gnAppImeiLog.setNetworkMode(gnAppAccessLog.getNetworkMode());
							gnAppImeiLog.setNetworkIp(gnAppAccessLog.getNetworkIp());
							// location data
							gnAppImeiLog.setLocationCountry(gnAppAccessLog.getLocationCountry());
							gnAppImeiLog.setLocationProvince(gnAppAccessLog.getLocationProvince());
							gnAppImeiLog.setLocationCity(gnAppAccessLog.getLocationCity());
							gnAppImeiLog.setLocationTime(gnAppAccessLog.getLocationTime());
							// report data
							gnAppImeiLog.setReportTime(gnAppAccessLog.getReportTime());
							
							insert(gnAppImeiLog, GnAppImeiLog.class.getSimpleName()+".insertGnAppImeiLog");
							
							logger.info("GnAppAccessLogDaoImpl.batchInsertGnAppAccessLog successfully processed a fisrt launched IMEI of some version and channel"
									+ ", gnAppImeiLog={}" , new Object[]{gnAppImeiLog.toString()});
						}
					}
	            }
	            executor.executeBatch(); 
				
				return execCount;
			}
		});
		
		logger.debug("GnAppAccessLogDaoImpl.batchInsertGnAppAccessLog(List<GnAppAccessLog>) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}

	@Override
	public Integer insertGnAppAccessLogEx(GnAppAccessLog gnAppAccessLog) {
		long stime = System.currentTimeMillis();
		Integer insertIndex = 0;
		
		logger.debug("GnAppAccessLogDaoImpl.insertGnAppAccessLogEx(GnAppAccessLog) entry: gnAppAccessLog={}"
				,new Object[] { gnAppAccessLog });
		
		insertIndex = (Integer) insert(gnAppAccessLog, GnAppAccessLog.class.getSimpleName()+".insertGnAppAccessLogEx");
		
		logger.debug("GnAppAccessLogDaoImpl.insertGnAppAccessLogEx(GnAppAccessLog) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@SuppressWarnings({"deprecation"})
	@Override
	public Integer batchInsertGnAppAccessLogEx(
			final List<GnAppAccessLog> gnAppAccessLogs) {
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		int dataSize = (gnAppAccessLogs != null) ? gnAppAccessLogs.size() : 0;
		logger.debug("GnAppAccessLogDaoImpl.batchInsertGnAppAccessLog(List<GnAppAccessLog>) entry: dataSize={}"
				, new Object[] { dataSize });
		
		if (CollectionUtils.isEmpty(gnAppAccessLogs)) {
			return execCount;
		}
		
		execCount = getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			Integer execCount = 0;
			
			@Override
			public Integer doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for(GnAppAccessLog gnAppAccessLog : gnAppAccessLogs) {
					if(null != gnAppAccessLog) {
						executor.insert(GnAppAccessLog.class.getSimpleName() + ".insertGnAppAccessLogEx", gnAppAccessLog);
						++execCount;
					}
				}
				
				executor.executeBatch(); 
				
				return execCount;
			}
		});
		
		logger.debug("GnAppAccessLogDaoImpl.batchInsertGnAppAccessLogEx(List<GnAppAccessLog>) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}

}
