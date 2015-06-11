package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.iuni.dp.persist.datastat.dao.IuniUcLoginLogDao;
import com.iuni.dp.persist.datastat.model.IuniUcLoginLog;
import com.iuni.dp.persist.datastat.model.IuniUcOloginLog;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.IuniUcLoginLogService;

/**
 * IUNI UC Login Log Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.4
 */
@Service("iuniUcLoginLogService")
public class IuniUcLoginLogServiceImpl implements IuniUcLoginLogService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IuniUcLoginLogDao iuniUcLoginLogDao;
	
	@Override
	public Long saveIuniUcLoginLog(IuniUcLoginLog iuniUcLoginLog) {
		Long execCount = 0l;
		
		try {
			execCount = iuniUcLoginLogDao.insertIuniUcLoginLog(iuniUcLoginLog);
			logger.debug("IuniUcLoginLogServiceImpl.saveIuniUcLoginLog invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniUcLoginLogServiceImpl.saveIuniUcLoginLog found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, timeout=30)
	@Override
	public Long batchSaveIuniUcLoginLog(List<IuniUcLoginLog> iuniUcLoginLogList) {
		long stime = System.currentTimeMillis();
		Long execCount = 0l;
		int dataSize = (iuniUcLoginLogList != null) ? iuniUcLoginLogList.size() : 0;
		logger.debug("IuniUcLoginLogServiceImpl.batchSaveIuniUcLoginLog(List<IuniUcLoginLog>) entry: dataSize={}" 
				, new Object[] { dataSize });
		
		try {
			execCount = iuniUcLoginLogDao.batchInsertIuniUcLoginLog(iuniUcLoginLogList);
			
		} catch(DataAccessException daex) {
			logger.error("IuniUcLoginLogServiceImpl.batchSaveIuniUcLoginLog(List<IuniUcLoginLog>) found DataAccessException ", daex);
			throw new DBException(daex);
		}
		
		logger.debug("IuniUcLoginLogServiceImpl.batchSaveIuniUcLoginLog(List<IuniUcLoginLog>) success: execCount={},costTime={}ms"
				, new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}

	@Override
	public List<Map<String, Object>> queryIuniUserLoginFreqByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniUcLoginLogDao.selectIuniUserLoginFreqByExample(params);
			logger.debug("IuniUcLoginLogServiceImpl.queryIuniUserLoginFreqByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniUcLoginLogServiceImpl.queryIuniUserLoginFreqByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniUserLoginFreqByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniUcLoginLogDao.selectIuniUserLoginFreqByPage(params);
			logger.debug("IuniUcLoginLogServiceImpl.queryIuniUserLoginFreqByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniUcLoginLogServiceImpl.queryIuniUserLoginFreqByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIuniUserLoginFreqCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniUcLoginLogDao.selectIuniUserLoginFreqCount(params);
			logger.debug("IuniUcLoginLogServiceImpl.queryIuniUserLoginFreqCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniUcLoginLogServiceImpl.queryIuniUserLoginFreqCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Long saveIuniUcOloginLog(IuniUcOloginLog iuniUcOloginLog) {
		Long execCount = 0l;
		
		try {
			execCount = iuniUcLoginLogDao.insertIuniUcOloginLog(iuniUcOloginLog);
			logger.debug("IuniUcLoginLogServiceImpl.saveIuniUcOloginLog invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniUcLoginLogServiceImpl.saveIuniUcOloginLog found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, timeout=30)
	@Override
	public Long batchSaveIuniUcOloginLog(
			List<IuniUcOloginLog> iuniUcOloginLogList) {
		long stime = System.currentTimeMillis();
		Long execCount = 0l;
		int dataSize = (iuniUcOloginLogList != null) ? iuniUcOloginLogList.size() : 0;
		logger.debug("IuniUcLoginLogServiceImpl.batchSaveIuniUcOloginLog(List<IuniUcLoginLog>) entry: dataSize={}" 
				, new Object[] { dataSize });
		
		try {
			execCount = iuniUcLoginLogDao.batchInsertIuniUcOloginLog(iuniUcOloginLogList);
			
		} catch(DataAccessException daex) {
			logger.error("IuniUcLoginLogServiceImpl.batchSaveIuniUcOloginLog(List<IuniUcLoginLog>) found DataAccessException ", daex);
			throw new DBException(daex);
		}
		
		logger.debug("IuniUcLoginLogServiceImpl.batchSaveIuniUcOloginLog(List<IuniUcLoginLog>) success: execCount={},costTime={}ms"
				, new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}

	@Override
	public List<Map<String, Object>> queryIuniUserLoginNumByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniUcLoginLogDao.selectIuniUserLoginNumByExample(params);
			logger.debug("IuniUcLoginLogServiceImpl.queryIuniUserLoginNumByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniUcLoginLogServiceImpl.queryIuniUserLoginNumByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniUserLoginNumByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniUcLoginLogDao.selectIuniUserLoginNumByPage(params);
			logger.debug("IuniUcLoginLogServiceImpl.queryIuniUserLoginNumByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniUcLoginLogServiceImpl.queryIuniUserLoginNumByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIuniUserLoginNumCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniUcLoginLogDao.selectIuniUserLoginNumCount(params);
			logger.debug("IuniUcLoginLogServiceImpl.queryIuniUserLoginNumCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniUcLoginLogServiceImpl.queryIuniUserLoginNumCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryIuniUserRegRetainByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniUcLoginLogDao.selectIuniUserRegRetainByExample(params);
			logger.debug("IuniUcLoginLogServiceImpl.queryIuniUserRegRetainByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniUcLoginLogServiceImpl.queryIuniUserRegRetainByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniUserRegRetainByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniUcLoginLogDao.selectIuniUserRegRetainByPage(params);
			logger.debug("IuniUcLoginLogServiceImpl.queryIuniUserRegRetainByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniUcLoginLogServiceImpl.queryIuniUserRegRetainByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIuniUserRegRetainCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniUcLoginLogDao.selectIuniUserRegRetainCount(params);
			logger.debug("IuniUcLoginLogServiceImpl.queryIuniUserRegRetainCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniUcLoginLogServiceImpl.queryIuniUserRegRetainCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

}
