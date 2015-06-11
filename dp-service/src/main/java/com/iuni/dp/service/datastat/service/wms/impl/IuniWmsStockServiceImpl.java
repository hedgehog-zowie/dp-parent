package com.iuni.dp.service.datastat.service.wms.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.wms.IuniWmsStockDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.wms.IuniWmsStockService;

/**
 * IuniWmsStock Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.5
 */
@Service("iuniWmsStockService")
public class IuniWmsStockServiceImpl implements IuniWmsStockService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IuniWmsStockDao iuniWmsStockDao;
	
	@Override
	public List<Map<String, Object>> queryIuniWmsStockDetailsByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniWmsStockDao.selectIuniWmsStockDetailsByExample(params);
			logger.debug("IuniWmsStockServiceImpl.queryIuniWmsStockDetailsByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniWmsStockServiceImpl.queryIuniWmsStockDetailsByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniWmsStockDetailsByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniWmsStockDao.selectIuniWmsStockDetailsByPage(params);
			logger.debug("IuniWmsStockServiceImpl.queryIuniWmsStockDetailsByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniWmsStockServiceImpl.queryIuniWmsStockDetailsByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIuniWmsStockDetailsCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniWmsStockDao.selectIuniWmsStockDetailsCount(params);
			logger.debug("IuniWmsStockServiceImpl.queryIuniWmsStockDetailsCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniWmsStockServiceImpl.queryIuniWmsStockDetailsCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

}
