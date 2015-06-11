package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.WxAccessLogDao;
import com.iuni.dp.persist.datastat.model.WxAccessLog;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.datastat.service.WxAccessLogService;

/**
 * 微信公众帐号访问日志Service业务类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("wxAccessLogService")
public class WxAccessLogServiceImpl implements WxAccessLogService {

	private static final Logger logger = LoggerFactory.getLogger(WxAccessLogServiceImpl.class);
	
	@Autowired
	private WxAccessLogDao wxAccessLogDao;
	
	@Override
	public WxAccessLog fetchWxAccessLogById(Integer id) {
		WxAccessLog wxAccessLog = null;
		
		try {
			wxAccessLog = wxAccessLogDao.selectWxAccessLogById(id);
			logger.debug("WxAccessLogServiceImpl.fetchWxAccessLogById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchWxAccessLogById found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return wxAccessLog;
	}

	@Override
	public List<WxAccessLog> fetchWxAccessLogByExample(Map<String, Object> params) {
		List<WxAccessLog> wxAccessLogs = null;
		
		try {
			wxAccessLogs = wxAccessLogDao.selectWxAccessLogByExample(params);
			logger.debug("WxAccessLogServiceImpl.fetchWxAccessLogByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchWxAccessLogByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return wxAccessLogs;
	}

	@Override
	public List<WxAccessLog> fetchWxAccessLogByPage(Map<String, Object> params) {
		List<WxAccessLog> wxAccessLogs = null;
		
		try {
			wxAccessLogs = wxAccessLogDao.selectWxAccessLogByPage(params);
			logger.debug("WxAccessLogServiceImpl.fetchWxAccessLogByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchWxAccessLogByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return wxAccessLogs;
	}

	@Override
	public Integer fetchWxAccessLogCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = wxAccessLogDao.selectWxAccessLogCount(params);
			logger.debug("WxAccessLogServiceImpl.fetchWxAccessLogCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchWxAccessLogCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> fetchNewFansStatByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = wxAccessLogDao.selectNewFansStatByExample(params);
			logger.debug("WxAccessLogServiceImpl.fetchNewFansStatByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchNewFansStatByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> fetchNewFansStatByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = wxAccessLogDao.selectNewFansStatByPage(params);
			logger.debug("WxAccessLogServiceImpl.fetchNewFansStatByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchNewFansStatByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer fetchNewFansStatCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = wxAccessLogDao.selectNewFansStatCount(params);
			logger.debug("WxAccessLogServiceImpl.fetchNewFansStatCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchNewFansStatCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> fetchActiveUserStatDailyByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = wxAccessLogDao.selectActiveUserStatDailyByExample(params);
			logger.debug("WxAccessLogServiceImpl.fetchActiveUserStatDailyByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchActiveUserStatDailyByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> fetchActiveUserStatDailyByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = wxAccessLogDao.selectActiveUserStatDailyByPage(params);
			logger.debug("WxAccessLogServiceImpl.fetchActiveUserStatDailyByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchActiveUserStatDailyByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer fetchActiveUserStatDailyCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = wxAccessLogDao.selectActiveUserStatDailyCount(params);
			logger.debug("WxAccessLogServiceImpl.fetchActiveUserStatDailyCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchActiveUserStatDailyCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> fetchActiveUserStatMonthlyByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = wxAccessLogDao.selectActiveUserStatMonthlyByExample(params);
			logger.debug("WxAccessLogServiceImpl.fetchActiveUserStatMonthlyByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchActiveUserStatMonthlyByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> fetchActiveUserStatMonthlyByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = wxAccessLogDao.selectActiveUserStatMonthlyByPage(params);
			logger.debug("WxAccessLogServiceImpl.fetchActiveUserStatMonthlyByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchActiveUserStatMonthlyByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer fetchActiveUserStatMonthlyCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = wxAccessLogDao.selectActiveUserStatMonthlyCount(params);
			logger.debug("WxAccessLogServiceImpl.fetchActiveUserStatMonthlyCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchActiveUserStatMonthlyCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> fetchUpwardTextStatByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = wxAccessLogDao.selectUpwardTextStatByExample(params);
			logger.debug("WxAccessLogServiceImpl.fetchUpwardTextStatByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchUpwardTextStatByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> fetchUpwardTextStatByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = wxAccessLogDao.selectUpwardTextStatByPage(params);
			logger.debug("WxAccessLogServiceImpl.fetchUpwardTextStatByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchUpwardTextStatByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer fetchUpwardTextStatCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = wxAccessLogDao.selectUpwardTextStatCount(params);
			logger.debug("WxAccessLogServiceImpl.fetchUpwardTextStatCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.fetchUpwardTextStatCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	/**
	 * 按条件查询微信公众帐号图片下载排行
	 * @param params 
	 * @return List<WxAccessLog>
	 */
	public List<Map<String, Object>> selectWxImageDownloadRanks(Map<String, Object> paramMap) throws ServiceException{	
		return wxAccessLogDao.selectWxImageDownloadRanks(paramMap);
	}
	
	/**
	 * 按条件查询微信公众帐号图片下载排行-记录总数
	 * @param params 
	 * @return List<WxAccessLog>
	 */
	public Integer selectWxImageDownloadRanksCount(Map<String, Object> paramMap)throws ServiceException{
		return wxAccessLogDao.selectWxImageDownloadRanksCount(paramMap);
	}
	
	/**
	 * 按条件查询微信公众帐号图片下载总次数
	 * @param params 
	 * @return List<WxAccessLog>
	 */
	public Integer selectWxImageDownloadTotalCount(Map<String, Object> paramMap)throws ServiceException{
		return wxAccessLogDao.selectWxImageDownloadTotalCount(paramMap);
	}
	
	@Override
	public Integer saveWxAccessLog(WxAccessLog wxAccessLog) {
		Integer execCount = 0;
		
		try {
			execCount = wxAccessLogDao.insertWxAccessLog(wxAccessLog);
			logger.debug("WxAccessLogServiceImpl.saveWxAccessLog invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.saveWxAccessLog found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeWxAccessLogById(Integer id) {
		Integer execCount = 0;
		
		try {
			execCount = wxAccessLogDao.deleteWxAccessLogById(id);
			logger.debug("WxAccessLogServiceImpl.removeWxAccessLogById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("WxAccessLogServiceImpl.removeWxAccessLogById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}
	
}
