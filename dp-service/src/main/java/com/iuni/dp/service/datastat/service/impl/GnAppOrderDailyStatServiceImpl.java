package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.GnAppOrderDailyStatDao;
import com.iuni.dp.persist.datastat.model.GnAppOrderDailyStat;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.GnAppOrderDailyStatService;

/**
 * 金立相关APP应用关联订单情况日统计 Service
 * @author CaiKe
 * @version dp-service-V1.0.2
 */
@Service("gnAppOrderDailyStatService")
public class GnAppOrderDailyStatServiceImpl implements GnAppOrderDailyStatService {

	private Logger logger = LoggerFactory.getLogger(GnAppOrderDailyStatServiceImpl.class);
	
	@Autowired
	private GnAppOrderDailyStatDao gnAppOrderDailyStatDao;
	
	@Override
	public List<Map<String, Object>> queryGnAppChannelSalesByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppOrderDailyStatDao.selectGnAppChannelSalesByExample(params);
			logger.debug("GnAppOrderDailyStatServiceImpl.queryGnAppChannelSalesByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppOrderDailyStatServiceImpl.queryGnAppChannelSalesByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryGnAppChannelSalesByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = gnAppOrderDailyStatDao.selectGnAppChannelSalesByPage(params);
			logger.debug("GnAppOrderDailyStatServiceImpl.queryGnAppChannelSalesByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppOrderDailyStatServiceImpl.queryGnAppChannelSalesByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryGnAppChannelSalesCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = gnAppOrderDailyStatDao.selectGnAppChannelSalesCount(params);
			logger.debug("GnAppOrderDailyStatServiceImpl.queryGnAppChannelSalesCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppOrderDailyStatServiceImpl.queryGnAppChannelSalesCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Integer saveGaods(GnAppOrderDailyStat gnAppOrderDailyStat) {
		Integer execCount = 0;
		
		try {
			execCount = gnAppOrderDailyStatDao.insertGaods(gnAppOrderDailyStat);
			logger.debug("GnAppOrderDailyStatServiceImpl.saveGaods invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppOrderDailyStatServiceImpl.saveGaods found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeGaodsById(Long id) {
		Integer execCount = 0;
		
		try {
			execCount = gnAppOrderDailyStatDao.deleteGaodsById(id);
			logger.debug("GnAppOrderDailyStatServiceImpl.removeGaodsById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppOrderDailyStatServiceImpl.removeGaodsById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

}
