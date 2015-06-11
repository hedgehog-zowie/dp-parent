package com.iuni.dp.service.datastat.service.wms.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsReceiveDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.wms.IuniWmsProcurementDetailService;

@Service("iuniWmsProcurementDetailService")
public class IuniWmsProcurementDetailServiceImpl implements
		IuniWmsProcurementDetailService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IuniWmsReceiveDao iuniWmsReceiveDao;
	
	@Override
	public Integer queryIuniWmsProcurementDetailCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniWmsReceiveDao.selectIuniWmsProcurementDetailCount(params);
			logger.debug("IuniWmsProcurementDetailServiceImpl.queryIuniWmsSalesOrderStatCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniWmsProcurementDetailServiceImpl.queryIuniWmsSalesOrderStatCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
		
	}

	@Override
	public List<Map<String, Object>> queryIuniWmsProcurementDetailByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = 	iuniWmsReceiveDao.selectIuniWmsProcurementDetailByPage(params);
			logger.debug("IuniWmsProcurementDetailServiceImpl.queryIuniWmsProcurementDetailByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniWmsProcurementDetailServiceImpl.queryIuniWmsProcurementDetailByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;

	}

	@Override
	public List<Map<String, Object>> queryIuniWmsProcurementDetail2Excel(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniWmsReceiveDao.selectIuniWmsProcurementDetail2Excel(params);
			logger.debug("IuniWmsProcurementDetailServiceImpl.queryIuniWmsProcurementDetail2Excel invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniWmsProcurementDetailServiceImpl.queryIuniWmsProcurementDetail2Excel found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}


}
