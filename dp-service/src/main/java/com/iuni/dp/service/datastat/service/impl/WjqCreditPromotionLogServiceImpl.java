package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.WjqCreditPromotionLogDao;
import com.iuni.dp.persist.datastat.model.WjqCreditPromotionLog;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.WjqCreditPromotionLogService;

/**
 * 玩机圈用户推广日志Service业务类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("wjqCreditPromotionLogService")
public class WjqCreditPromotionLogServiceImpl implements WjqCreditPromotionLogService {

	private static final Logger logger = LoggerFactory.getLogger(WjqCreditPromotionLogServiceImpl.class);
	
	@Autowired
	private WjqCreditPromotionLogDao wjqCreditPromotionLogDao;
	
	@Override
	public WjqCreditPromotionLog fetchWjqCreditPromotionLogById(Integer id) {
		WjqCreditPromotionLog wjqCreditPromotionLog = null;
		
		try {
			wjqCreditPromotionLog = wjqCreditPromotionLogDao.selectWjqCreditPromotionLogById(id);
			logger.debug("WjqCreditPromotionLogServiceImpl.fetchWjqCreditPromotionLogById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.fetchWjqCreditPromotionLogById found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return wjqCreditPromotionLog;
	}

	@Override
	public List<WjqCreditPromotionLog> fetchWjqCreditPromotionLogByExample(
			Map<String, Object> params) {
		List<WjqCreditPromotionLog> wjqCreditPromotionLogs = null;
		
		try {
			wjqCreditPromotionLogs = wjqCreditPromotionLogDao.selectWjqCreditPromotionLogByExample(params);
			logger.debug("WjqCreditPromotionLogServiceImpl.fetchWjqCreditPromotionLogByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.fetchWjqCreditPromotionLogByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return wjqCreditPromotionLogs;
	}

	@Override
	public List<WjqCreditPromotionLog> fetchWjqCreditPromotionLogByPage(
			Map<String, Object> params) {
		List<WjqCreditPromotionLog> wjqCreditPromotionLogs = null;
		
		try {
			wjqCreditPromotionLogs = wjqCreditPromotionLogDao.selectWjqCreditPromotionLogByPage(params);
			logger.debug("WjqCreditPromotionLogServiceImpl.fetchWjqCreditPromotionLogByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.fetchWjqCreditPromotionLogByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return wjqCreditPromotionLogs;
	}

	@Override
	public Integer fetchWjqCreditPromotionLogCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = wjqCreditPromotionLogDao.selectWjqCreditPromotionLogCount(params);
			logger.debug("WjqCreditPromotionLogServiceImpl.fetchWjqCreditPromotionLogCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.fetchWjqCreditPromotionLogCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> fetchWjqUserPromotionByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> wjqUserPromotions = null;
		
		try {
			wjqUserPromotions = wjqCreditPromotionLogDao.selectWjqUserPromotionByExample(params);
			logger.debug("WjqCreditPromotionLogServiceImpl.fetchWjqUserPromotionByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.fetchWjqUserPromotionByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return wjqUserPromotions;
	}

	@Override
	public List<Map<String, Object>> fetchWjqUserPromotionByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> wjqUserPromotions = null;
		
		try {
			wjqUserPromotions = wjqCreditPromotionLogDao.selectWjqUserPromotionByPage(params);
			logger.debug("WjqCreditPromotionLogServiceImpl.fetchWjqUserPromotionByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.fetchWjqUserPromotionByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return wjqUserPromotions;
	}

	@Override
	public Integer fetchWjqUserPromotionCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = wjqCreditPromotionLogDao.selectWjqUserPromotionCount(params);
			logger.debug("WjqCreditPromotionLogServiceImpl.fetchWjqUserPromotionCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.fetchWjqUserPromotionCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> fetchWjqPostsPromotionByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> wjqPostsPromotions = null;
		
		try {
			wjqPostsPromotions = wjqCreditPromotionLogDao.selectWjqPostsPromotionByExample(params);
			logger.debug("WjqCreditPromotionLogServiceImpl.fetchWjqPostsPromotionByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.fetchWjqPostsPromotionByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return wjqPostsPromotions;
	}

	@Override
	public List<Map<String, Object>> fetchWjqPostsPromotionByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> wjqPostsPromotions = null;
		
		try {
			wjqPostsPromotions = wjqCreditPromotionLogDao.selectWjqPostsPromotionByPage(params);
			logger.debug("WjqCreditPromotionLogServiceImpl.fetchWjqPostsPromotionByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.fetchWjqPostsPromotionByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return wjqPostsPromotions;
	}

	@Override
	public Integer fetchWjqPostsPromotionCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = wjqCreditPromotionLogDao.selectWjqPostsPromotionCount(params);
			logger.debug("WjqCreditPromotionLogServiceImpl.fetchWjqPostsPromotionCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.fetchWjqPostsPromotionCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> fetchWjqUserPromotionPreview(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = wjqCreditPromotionLogDao.selectWjqUserPromotionPreview(params);
			logger.debug("WjqCreditPromotionLogServiceImpl.fetchWjqUserPromotionPreview invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.fetchWjqUserPromotionPreview found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> fetchWjqUserPromotionSumByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = wjqCreditPromotionLogDao.selectWjqUserPromotionSumByExample(params);
			logger.debug("WjqCreditPromotionLogServiceImpl.fetchWjqUserPromotionSumByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.fetchWjqUserPromotionSumByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> fetchWjqUserPromotionSumByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = wjqCreditPromotionLogDao.selectWjqUserPromotionSumByPage(params);
			logger.debug("WjqCreditPromotionLogServiceImpl.fetchWjqUserPromotionSumByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.fetchWjqUserPromotionSumByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer fetchWjqUserPromotionSumCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = wjqCreditPromotionLogDao.selectWjqUserPromotionSumCount(params);
			logger.debug("WjqCreditPromotionLogServiceImpl.fetchWjqUserPromotionSumCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.fetchWjqUserPromotionSumCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Integer saveWjqCreditPromotionLog(
			WjqCreditPromotionLog wjqCreditPromotionLog) {
		Integer execCount = 0;
		
		try {
			execCount = wjqCreditPromotionLogDao.insertWjqCreditPromotionLog(wjqCreditPromotionLog);
			logger.debug("WjqCreditPromotionLogServiceImpl.saveWjqCreditPromotionLog invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.saveWjqCreditPromotionLog found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeWjqCreditPromotionLogById(Integer id) {
		Integer execCount = 0;
		
		try {
			execCount = wjqCreditPromotionLogDao.deleteWjqCreditPromotionLogById(id);
			logger.debug("WjqCreditPromotionLogServiceImpl.removeWjqCreditPromotionLogById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WjqCreditPromotionLogServiceImpl.removeWjqCreditPromotionLogById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}
	
}
