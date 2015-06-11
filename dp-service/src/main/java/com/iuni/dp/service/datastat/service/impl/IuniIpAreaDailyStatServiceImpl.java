package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.IuniIpAreaDailyStatDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.IuniIpAreaDailyStatService;

/**
 * IuniIpAreaDailyStat Service
 * @author CaiKe
 * @version dp-service-V1.0.2
 */
@Service("iuniIpAreaDailyStatService")
public class IuniIpAreaDailyStatServiceImpl implements IuniIpAreaDailyStatService {
	
	private Logger logger = LoggerFactory.getLogger(IuniIpAreaDailyStatServiceImpl.class);
	
	@Autowired
	private IuniIpAreaDailyStatDao iuniIpAreaDailyStatDao;

	@Override
	public List<Map<String, Object>> queryIidsByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniIpAreaDailyStatDao.selectIidsByExample(params);
			logger.debug("IuniIpAreaDailyStatServiceImpl.queryIidsByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniIpAreaDailyStatServiceImpl.queryIidsByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIidsByPage(Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniIpAreaDailyStatDao.selectIidsByPage(params);
			logger.debug("IuniIpAreaDailyStatServiceImpl.queryIidsByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniIpAreaDailyStatServiceImpl.queryIidsByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIidsCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniIpAreaDailyStatDao.selectIidsCount(params);
			logger.debug("IuniIpAreaDailyStatServiceImpl.queryIidsCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniIpAreaDailyStatServiceImpl.queryIidsCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryIidsSumOnDateByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniIpAreaDailyStatDao.selectIidsSumOnDateByExample(params);
			logger.debug("IuniIpAreaDailyStatServiceImpl.queryIidsSumOnDateByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniIpAreaDailyStatServiceImpl.queryIidsSumOnDateByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIidsSumOnDateByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniIpAreaDailyStatDao.selectIidsSumOnDateByPage(params);
			logger.debug("IuniIpAreaDailyStatServiceImpl.queryIidsSumOnDateByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniIpAreaDailyStatServiceImpl.queryIidsSumOnDateByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIidsSumOnDateCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniIpAreaDailyStatDao.selectIidsSumOnDateCount(params);
			logger.debug("IuniIpAreaDailyStatServiceImpl.queryIidsSumOnDateCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniIpAreaDailyStatServiceImpl.queryIidsSumOnDateCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}
	
}
