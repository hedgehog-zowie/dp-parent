package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.WxClickLogDao;
import com.iuni.dp.persist.datastat.model.WxClickLog;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.WxClickLogService;

/**
 * 微信公众帐号点击日志Service业务类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("wxClickLogService")
public class WxClickLogServiceImpl implements WxClickLogService {

	private static final Logger logger = LoggerFactory.getLogger(WxClickLogServiceImpl.class);
	
	@Autowired
	private WxClickLogDao wxClickLogDao;
	
	@Override
	public WxClickLog fetchWxClickLogById(Integer id) {
		WxClickLog wxClickLog = null;
		
		try {
			wxClickLog = wxClickLogDao.selectWxClickLogById(id);
			logger.debug("WxClickLogServiceImpl.fetchWxClickLogById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxClickLogServiceImpl.fetchWxClickLogById found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return wxClickLog;
	}

	@Override
	public List<WxClickLog> fetchWxClickLogByExample(Map<String, Object> params) {
		List<WxClickLog> wxClickLogs = null;
		
		try {
			wxClickLogs = wxClickLogDao.selectWxClickLogByExample(params);
			logger.debug("WxClickLogServiceImpl.fetchWxClickLogByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxClickLogServiceImpl.fetchWxClickLogByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return wxClickLogs;
	}

	@Override
	public List<WxClickLog> fetchWxClickLogByPage(Map<String, Object> params) {
		List<WxClickLog> wxClickLogs = null;
		
		try {
			wxClickLogs = wxClickLogDao.selectWxClickLogByPage(params);
			logger.debug("WxClickLogServiceImpl.fetchWxClickLogByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxClickLogServiceImpl.fetchWxClickLogByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return wxClickLogs;
	}

	@Override
	public Integer fetchWxClickLogCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = wxClickLogDao.selectWxClickLogCount(params);
			logger.debug("WxClickLogServiceImpl.fetchWxClickLogCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxClickLogServiceImpl.fetchWxClickLogCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Integer saveWxClickLog(WxClickLog wxClickLog) {
		Integer execCount = 0;
		
		try {
			execCount = wxClickLogDao.insertWxClickLog(wxClickLog);
			logger.debug("WxClickLogServiceImpl.saveWxClickLog invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxClickLogServiceImpl.saveWxClickLog found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeWxClickLogById(Integer id) {
		Integer execCount = 0;
		
		try {
			execCount = wxClickLogDao.deleteWxClickLogById(id);
			logger.debug("WxClickLogServiceImpl.removeWxClickLogById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxClickLogServiceImpl.removeWxClickLogById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer fetchActiveUserCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = wxClickLogDao.selectActiveUserCount(params);
			logger.debug("WxClickLogServiceImpl.fetchActiveUserCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxClickLogServiceImpl.fetchActiveUserCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}
	
}
