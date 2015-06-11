package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.IuniPageNetflowDailyStatDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.IuniPageNetflowDailyStatService;

/**
 * IuniPageNetflowDailyStat Service
 * @author CaiKe
 * @version dp-service-V1.0.2
 */
@Service("iuniPageNetflowDailyStatService")
public class IuniPageNetflowDailyStatServiceImpl implements IuniPageNetflowDailyStatService {

	private Logger logger = LoggerFactory.getLogger(IuniPageNetflowDailyStatServiceImpl.class);
	
	@Autowired
	private IuniPageNetflowDailyStatDao iuniPageNetflowDailyStatDao; 
	
	@Override
	public List<Map<String, Object>> queryIpndsByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniPageNetflowDailyStatDao.selectIpndsByExample(params);
			logger.debug("IuniPageNetflowDailyStatServiceImpl.queryIpndsByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniPageNetflowDailyStatServiceImpl.queryIpndsByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIpndsByPage(Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniPageNetflowDailyStatDao.selectIpndsByPage(params);
			logger.debug("IuniPageNetflowDailyStatServiceImpl.queryIpndsByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniPageNetflowDailyStatServiceImpl.queryIpndsByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIpndsCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniPageNetflowDailyStatDao.selectIpndsCount(params);
			logger.debug("IuniPageNetflowDailyStatServiceImpl.queryIpndsCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniPageNetflowDailyStatServiceImpl.queryIpndsCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryIpndsSumOnDateByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniPageNetflowDailyStatDao.selectIpndsSumOnDateByExample(params);
			logger.debug("IuniPageNetflowDailyStatServiceImpl.queryIpndsSumOnDateByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniPageNetflowDailyStatServiceImpl.queryIpndsSumOnDateByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIpndsSumOnDateByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniPageNetflowDailyStatDao.selectIpndsSumOnDateByPage(params);
			logger.debug("IuniPageNetflowDailyStatServiceImpl.queryIpndsSumOnDateByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniPageNetflowDailyStatServiceImpl.queryIpndsSumOnDateByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIpndsSumOnDateCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniPageNetflowDailyStatDao.selectIpndsSumOnDateCount(params);
			logger.debug("IuniPageNetflowDailyStatServiceImpl.queryIpndsSumOnDateCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniPageNetflowDailyStatServiceImpl.queryIpndsSumOnDateCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

}
