package com.iuni.dp.service.datareport.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.iuni.dp.persist.datareport.dao.GnAppAccessLogDao;
import com.iuni.dp.persist.datareport.model.GnAppAccessLog;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datareport.service.GnAppAccessLogService;

/**
 * 金立相关APP客户端登入登出记录日志Service业务类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("gnAppAccessLogService")
public class GnAppAccessLogServiceImpl implements GnAppAccessLogService {

	private static final Logger logger = LoggerFactory.getLogger(GnAppAccessLogServiceImpl.class);
	
	@Autowired
	private GnAppAccessLogDao gnAppAccessLogDao;
	
	@Override
	public Integer saveGnAppAccessLog(GnAppAccessLog gnAppAccessLog) {
		long stime = System.currentTimeMillis();
		Integer insertIndex = 0;
		logger.debug("GnAppAccessLogServiceImpl.saveGnAppAccessLog(GnAppAccessLog) entry: gnAppAccessLog={}"
				, new Object[] { gnAppAccessLog });
		
		try {
			insertIndex = gnAppAccessLogDao.insertGnAppAccessLog(gnAppAccessLog);
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.saveGnAppAccessLog(GnAppAccessLog) found DataAccessException ", daex);
			throw new DBException(daex);
		}
		
		logger.debug("GnAppAccessLogServiceImpl.saveGnAppAccessLog(GnAppAccessLog) success: insertIndex={},costTime={}ms"
				, new Object[] { insertIndex, System.currentTimeMillis() - stime });
		
		return insertIndex;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 30)
	@Override
	public Integer batchSaveGnAppAccessLog(List<GnAppAccessLog> gnAppAccessLogs) {
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		int dataSize = (gnAppAccessLogs != null) ? gnAppAccessLogs.size() : 0;
		logger.debug("GnAppAccessLogServiceImpl.batchSaveGnAppAccessLog(List<GnAppAccessLog>) entry: dataSize={}" 
				, new Object[] { dataSize });
		
		try {
			execCount = gnAppAccessLogDao.batchInsertGnAppAccessLog(gnAppAccessLogs);
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.batchSaveGnAppAccessLog(List<GnAppAccessLog>) found DataAccessException ", daex);
			throw new DBException(daex);
		}
		
		logger.debug("GnAppAccessLogServiceImpl.batchSaveGnAppAccessLog(List<GnAppAccessLog>) success: execCount={},costTime={}ms"
				, new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 30)
	@Override
	public Integer saveGnAppAccessLogEx(GnAppAccessLog gnAppAccessLog) {
		long stime = System.currentTimeMillis();
		Integer insertIndex = 0;
		logger.debug("GnAppAccessLogServiceImpl.saveGnAppAccessLogEx(GnAppAccessLog) entry: gnAppAccessLog={}"
				, new Object[] { gnAppAccessLog });
		
		try {
			insertIndex = gnAppAccessLogDao.insertGnAppAccessLogEx(gnAppAccessLog);
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.saveGnAppAccessLogEx(GnAppAccessLog) found DataAccessException ", daex);
			throw new DBException(daex);
		}
		
		logger.debug("GnAppAccessLogServiceImpl.saveGnAppAccessLogEx(GnAppAccessLog) success: insertIndex={},costTime={}ms"
				, new Object[] { insertIndex, System.currentTimeMillis() - stime });
		
		return insertIndex;
	}

	@Override
	public Integer batchSaveGnAppAccessLogEx(
			List<GnAppAccessLog> gnAppAccessLogs) {
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		int dataSize = (gnAppAccessLogs != null) ? gnAppAccessLogs.size() : 0;
		logger.debug("GnAppAccessLogServiceImpl.batchSaveGnAppAccessLogEx(List<GnAppAccessLog>) entry: dataSize={}" 
				, new Object[] { dataSize });
		
		try {
			execCount = gnAppAccessLogDao.batchInsertGnAppAccessLogEx(gnAppAccessLogs);
			
		} catch(DataAccessException daex) {
			logger.error("GnAppAccessLogServiceImpl.batchSaveGnAppAccessLogEx(List<GnAppAccessLog>) found DataAccessException ", daex);
			throw new DBException(daex);
		}
		
		logger.debug("GnAppAccessLogServiceImpl.batchSaveGnAppAccessLogEx(List<GnAppAccessLog>) success: execCount={},costTime={}ms"
				, new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}

}
