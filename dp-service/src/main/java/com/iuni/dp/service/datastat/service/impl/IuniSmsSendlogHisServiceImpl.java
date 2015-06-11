package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.IuniSmsSendlogHisDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.IuniSmsSendlogHisService;

/**
 * IuniSmsSendlogHis Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.2
 */
@Service("iuniSmsSendlogHisService")
public class IuniSmsSendlogHisServiceImpl implements IuniSmsSendlogHisService {

	private static final Logger logger = LoggerFactory.getLogger(IuniSmsSendlogHisServiceImpl.class);
	
	@Autowired
	private IuniSmsSendlogHisDao iuniSmsSendlogHisDao;
	
	@Override
	public List<Map<String, Object>> querySendNumDailyByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniSmsSendlogHisDao.selectSendNumDailyByExample(params);
			logger.debug("IuniSmsSendlogHisServiceImpl.querySendNumDailyByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniSmsSendlogHisServiceImpl.querySendNumDailyByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> querySendNumDailyByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniSmsSendlogHisDao.selectSendNumDailyByPage(params);
			logger.debug("IuniSmsSendlogHisServiceImpl.querySendNumDailyByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniSmsSendlogHisServiceImpl.querySendNumDailyByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer querySendNumDailyCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniSmsSendlogHisDao.selectSendNumDailyCount(params);
			logger.debug("IuniSmsSendlogHisServiceImpl.querySendNumDailyCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniSmsSendlogHisServiceImpl.querySendNumDailyCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> querySendNumSumByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniSmsSendlogHisDao.selectSendNumSumByExample(params);
			logger.debug("IuniSmsSendlogHisServiceImpl.querySendNumSumByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniSmsSendlogHisServiceImpl.querySendNumSumByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}
	
}
