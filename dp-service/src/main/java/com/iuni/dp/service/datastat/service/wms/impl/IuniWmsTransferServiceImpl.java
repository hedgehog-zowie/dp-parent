package com.iuni.dp.service.datastat.service.wms.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.wms.IuniWmsTransferDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.wms.IuniWmsTransferService;

/**
 * IuniWmsTransfer Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.5
 */
@Service("iuniWmsTransferService")
public class IuniWmsTransferServiceImpl implements IuniWmsTransferService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IuniWmsTransferDao iuniWmsTransferDao;

	@Override
	public List<Map<String, Object>> queryIuniWmsTransferStatByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniWmsTransferDao.selectIuniWmsTransferStatByExample(params);
			logger.debug("IuniWmsTransferServiceImpl.queryIuniWmsTransferStatByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniWmsTransferServiceImpl.queryIuniWmsTransferStatByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniWmsTransferStatByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniWmsTransferDao.selectIuniWmsTransferStatByPage(params);
			logger.debug("IuniWmsTransferServiceImpl.queryIuniWmsTransferStatByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniWmsTransferServiceImpl.queryIuniWmsTransferStatByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIuniWmsTransferStatCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniWmsTransferDao.selectIuniWmsTransferStatCount(params);
			logger.debug("IuniWmsTransferServiceImpl.queryIuniWmsTransferStatCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniWmsTransferServiceImpl.queryIuniWmsTransferStatCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryIuniInTransferDetailsByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniWmsTransferDao.selectIuniInTransferDetailsByExample(params);
			logger.debug("IuniWmsTransferServiceImpl.queryIuniInTransferDetailsByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniWmsTransferServiceImpl.queryIuniInTransferDetailsByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniInTransferDetailsByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniWmsTransferDao.selectIuniInTransferDetailsByPage(params);
			logger.debug("IuniWmsTransferServiceImpl.queryIuniInTransferDetailsByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniWmsTransferServiceImpl.queryIuniInTransferDetailsByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIuniInTransferDetailsCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniWmsTransferDao.selectIuniInTransferDetailsCount(params);
			logger.debug("IuniWmsTransferServiceImpl.queryIuniInTransferDetailsCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniWmsTransferServiceImpl.queryIuniInTransferDetailsCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}
	
}
