package com.iuni.dp.service.datareport.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datareport.dao.GnAppImeiLogDao;
import com.iuni.dp.persist.datareport.model.GnAppImeiLog;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datareport.service.GnAppImeiLogService;

/**
 * 金立相关APP客户端IMEI首次启用记录日志Service业务类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("gnAppImeiLogService")
public class GnAppImeiLogServiceImpl implements GnAppImeiLogService {

	private static final Logger logger = LoggerFactory.getLogger(GnAppImeiLogServiceImpl.class);
	
	@Autowired
	private GnAppImeiLogDao GnAppImeiLogDao;
	
	@Override
	public List<GnAppImeiLog> fetchGnAppImeiLogByMap(Map<String, Object> params) {
		List<GnAppImeiLog> gnAppImeiLogs = null;
		
		try {
			gnAppImeiLogs = GnAppImeiLogDao.selectGnAppImeiLogByMap(params);
			logger.debug("GnAppImeiLogServiceImpl.fectchGnAppImeiLogByMap invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppImeiLogServiceImpl.fectchGnAppImeiLogByMap found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return gnAppImeiLogs;
	}

	@Override
	public Integer fetchGnAppImeiLogCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = GnAppImeiLogDao.selectGnAppImeiLogCount(params);
			logger.debug("GnAppImeiLogServiceImpl.fetchGnAppImeiLogCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppImeiLogServiceImpl.fetchGnAppImeiLogCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Integer saveGnAppImeiLog(GnAppImeiLog gnAppImeiLog) {
		long stime = System.currentTimeMillis();
		Integer insertIndex = 0;
		logger.debug("GnAppImeiLogServiceImpl.saveGnAppImeiLog(GnAppImeiLog) entry: wjqClientAccessLog={}"
				, new Object[] { gnAppImeiLog });
		
		try {
			insertIndex = GnAppImeiLogDao.insertGnAppImeiLog(gnAppImeiLog);
			
		} catch(DataAccessException daex) {
			logger.error("GnAppImeiLogServiceImpl.saveGnAppImeiLog(GnAppImeiLog) found DataAccessException ", daex);
			throw new DBException(daex);
		}
		
		logger.debug("GnAppImeiLogServiceImpl.saveGnAppImeiLog(GnAppImeiLog) success: insertIndex={},costTime={}ms"
				, new Object[] { insertIndex, System.currentTimeMillis() - stime });
		
		return insertIndex;
	}

	@Override
	public Integer batchSaveGnAppImeiLog(List<GnAppImeiLog> gnAppImeiLogs) {
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		int dataSize = (gnAppImeiLogs != null) ? gnAppImeiLogs.size() : 0;
		logger.debug("GnAppImeiLogServiceImpl.batchSaveGnAppImeiLog(List<GnAppImeiLog>) entry: dataSize={}" 
				, new Object[] { dataSize });
		
		try {
			execCount = GnAppImeiLogDao.batchInsertGnAppImeiLog(gnAppImeiLogs);
			
		} catch(DataAccessException daex) {
			logger.error("GnAppImeiLogServiceImpl.batchSaveGnAppImeiLog(List<GnAppImeiLog>) found DataAccessException ", daex);
			throw new DBException(daex);
		}
		
		logger.debug("GnAppImeiLogServiceImpl.batchSaveGnAppImeiLog(List<GnAppImeiLog>) success: execCount={},costTime={}ms"
				, new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}

}
