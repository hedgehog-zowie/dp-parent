package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.IuniOrderInfoDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.IuniOrderInfoService;

/**
 * IuniOrderInfo Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.2
 */
@Service("iuniOrderInfoService")
public class IuniOrderInfoServiceImpl implements IuniOrderInfoService {

	private final Logger logger = LoggerFactory.getLogger(IuniOrderInfoServiceImpl.class);
	
	@Autowired
	private IuniOrderInfoDao iuniOrderInfoDao; 
	
	@Override
	public List<Map<String, Object>> queryIuniOrderAreaStatByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniOrderInfoDao.selectIuniOrderAreaStatByExample(params);
			logger.debug("IuniOrderInfoServiceImpl.queryIuniOrderAreaStatByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniOrderInfoServiceImpl.queryIuniOrderAreaStatByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniOrderAreaStatByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniOrderInfoDao.selectIuniOrderAreaStatByPage(params);
			logger.debug("IuniOrderInfoServiceImpl.queryIuniOrderAreaStatByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniOrderInfoServiceImpl.queryIuniOrderAreaStatByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIuniOrderAreaStatCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniOrderInfoDao.selectIuniOrderAreaStatCount(params);
			logger.debug("IuniOrderInfoServiceImpl.queryIuniOrderAreaStatCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniOrderInfoServiceImpl.queryIuniOrderAreaStatCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryIuniOrderAreaSumByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniOrderInfoDao.selectIuniOrderAreaSumByExample(params);
			logger.debug("IuniOrderInfoServiceImpl.queryIuniOrderAreaSumByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniOrderInfoServiceImpl.queryIuniOrderAreaSumByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIuniOrderAreaSumByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniOrderInfoDao.selectIuniOrderAreaSumByPage(params);
			logger.debug("IuniOrderInfoServiceImpl.queryIuniOrderAreaSumByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniOrderInfoServiceImpl.queryIuniOrderAreaSumByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIuniOrderAreaSumCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniOrderInfoDao.selectIuniOrderAreaSumCount(params);
			logger.debug("IuniOrderInfoServiceImpl.queryIuniOrderAreaSumCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniOrderInfoServiceImpl.queryIuniOrderAreaSumCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryOrderStat4Cs(Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = iuniOrderInfoDao.selectOrderStat4Cs(params);
			logger.debug("IuniOrderInfoServiceImpl.queryOrderStat4Cs invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniOrderInfoServiceImpl.queryOrderStat4Cs found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

    @Override
    public List<Map<String, Object>> queryOrderStat4CsByUser(Map<String, Object> params) {
        List<Map<String, Object>> list = null;

        try {
            list = iuniOrderInfoDao.selectOrderStat4CsByUser(params);
            logger.debug("IuniOrderInfoServiceImpl.queryOrderStat4CsByUser invoke success");

        } catch(DataAccessException daex) {
            logger.error("IuniOrderInfoServiceImpl.queryOrderStat4CsByUser found DataAccessException", daex);
            throw new DBException(daex);
        }

        return list;
    }

}
