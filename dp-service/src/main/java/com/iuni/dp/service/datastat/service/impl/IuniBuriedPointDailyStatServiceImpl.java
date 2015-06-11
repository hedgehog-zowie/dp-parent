package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.IuniBuriedPointDailyStatDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.IuniBuriedPointDailyStatService;

/**
 * IuniBuriedPointDailyStat Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.3
 */
@Service("iuniBuriedPointDailyStatService")
public class IuniBuriedPointDailyStatServiceImpl implements IuniBuriedPointDailyStatService {

	private final Logger logger = LoggerFactory.getLogger(IuniBuriedPointDailyStatServiceImpl.class);
	
	@Autowired
	private IuniBuriedPointDailyStatDao iuniBuriedPointDailyStatDao;
	
	@Override
	public List<Map<String, Object>> queryIbpdByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniBuriedPointDailyStatDao.selectIbpdByExample(params);
			logger.debug("IuniBuriedPointDailyStatServiceImpl.queryIbpdByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniBuriedPointDailyStatServiceImpl.queryIbpdByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIbpdByPage(Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniBuriedPointDailyStatDao.selectIbpdByPage(params);
			logger.debug("IuniBuriedPointDailyStatServiceImpl.queryIbpdByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniBuriedPointDailyStatServiceImpl.queryIbpdByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIbpdCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniBuriedPointDailyStatDao.selectIbpdCount(params);
			logger.debug("IuniBuriedPointDailyStatServiceImpl.queryIbpdCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniBuriedPointDailyStatServiceImpl.queryIbpdCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryClickRateTodayByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniBuriedPointDailyStatDao.selectClickRateTodayByExample(params);
			logger.debug("IuniBuriedPointDailyStatServiceImpl.queryClickRateTodayByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniBuriedPointDailyStatServiceImpl.queryClickRateTodayByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryClickRateTodayByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniBuriedPointDailyStatDao.selectClickRateTodayByPage(params);
			logger.debug("IuniBuriedPointDailyStatServiceImpl.queryClickRateTodayByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniBuriedPointDailyStatServiceImpl.queryClickRateTodayByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryClickRateTodayCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniBuriedPointDailyStatDao.selectClickRateTodayCount(params);
			logger.debug("IuniBuriedPointDailyStatServiceImpl.queryClickRateTodayCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniBuriedPointDailyStatServiceImpl.queryClickRateTodayCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

}
