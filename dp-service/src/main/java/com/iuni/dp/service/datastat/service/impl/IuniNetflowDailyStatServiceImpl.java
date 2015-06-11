package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.IuniNetflowDailyStatDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.IuniNetflowDailyStatService;

/**
 * IuniNetflowDailyStat Service
 * @author CaiKe
 * @version dp-service-V1.0.2
 */
@Service("iuniNetflowDailyStatService")
public class IuniNetflowDailyStatServiceImpl implements IuniNetflowDailyStatService {

	private Logger logger = LoggerFactory.getLogger(IuniNetflowDailyStatServiceImpl.class);
	
	@Autowired
	private IuniNetflowDailyStatDao iuniNetflowDailyStatDao; 
	
	@Override
	public List<Map<String, Object>> queryIndsByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniNetflowDailyStatDao.selectIndsByExample(params);
			logger.debug("IuniNetflowDailyStatServiceImpl.queryIndsByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniNetflowDailyStatServiceImpl.queryIndsByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIndsByPage(Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniNetflowDailyStatDao.selectIndsByPage(params);
			logger.debug("IuniNetflowDailyStatServiceImpl.queryIndsByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniNetflowDailyStatServiceImpl.queryIndsByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIndsCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniNetflowDailyStatDao.selectIndsCount(params);
			logger.debug("IuniNetflowDailyStatServiceImpl.queryIndsCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniNetflowDailyStatServiceImpl.queryIndsCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryIndsSumByDate(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniNetflowDailyStatDao.selectIndsSumByDate(params);
			logger.debug("IuniNetflowDailyStatServiceImpl.queryIndsSumByDate invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniNetflowDailyStatServiceImpl.queryIndsSumByDate found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

}
