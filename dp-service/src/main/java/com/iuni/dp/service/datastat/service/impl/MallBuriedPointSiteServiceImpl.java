package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.MallBuriedPointSiteDao;
import com.iuni.dp.persist.datastat.model.MallBuriedPointSite;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.MallBuriedPointSiteService;

/**
 * MallBuriedPointSite Service
 * @author CaiKe
 * @version dp-service-V1.0.1
 */
@Service("mallBuriedPointSiteService")
public class MallBuriedPointSiteServiceImpl implements MallBuriedPointSiteService {

	private Logger logger = LoggerFactory.getLogger(MallBuriedPointSiteServiceImpl.class);
	
	@Autowired
	private MallBuriedPointSiteDao mallBuriedPointSiteDao;
	
	@Override
	public List<MallBuriedPointSite> queryMbpsByExample(
			Map<String, Object> params) {
		List<MallBuriedPointSite> list = null;
		
		try {
			list = mallBuriedPointSiteDao.selectMbpsByExample(params);
			logger.debug("MallBuriedPointSiteServiceImpl.queryMbpsByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallBuriedPointSiteServiceImpl.queryMbpsByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<MallBuriedPointSite> queryMbpsByPage(Map<String, Object> params) {
		List<MallBuriedPointSite> list = null;
		
		try {
			list = mallBuriedPointSiteDao.selectMbpsByPage(params);
			logger.debug("MallBuriedPointSiteServiceImpl.queryMbpsByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallBuriedPointSiteServiceImpl.queryMbpsByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryMbpsCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = mallBuriedPointSiteDao.selectMbpsCount(params);
			logger.debug("MallBuriedPointSiteServiceImpl.queryMbpsCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallBuriedPointSiteServiceImpl.queryMbpsCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> queryClickRateByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = mallBuriedPointSiteDao.selectClickRateByExample(params);
			logger.debug("MallBuriedPointSiteServiceImpl.queryClickRateByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallBuriedPointSiteServiceImpl.queryClickRateByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryClickRateByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = mallBuriedPointSiteDao.selectClickRateByPage(params);
			logger.debug("MallBuriedPointSiteServiceImpl.queryClickRateByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallBuriedPointSiteServiceImpl.queryClickRateByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryClickRateCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = mallBuriedPointSiteDao.selectClickRateCount(params);
			logger.debug("MallBuriedPointSiteServiceImpl.queryClickRateCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallBuriedPointSiteServiceImpl.queryClickRateCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Long saveMbps(MallBuriedPointSite mallBuriedPointSite) {
		Long execCount = 0l;
		
		try {
			execCount = mallBuriedPointSiteDao.insertMbps(mallBuriedPointSite);
			logger.debug("MallBuriedPointSiteServiceImpl.saveMbps invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallBuriedPointSiteServiceImpl.saveMbps found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer updateMbps(MallBuriedPointSite mallBuriedPointSite) {
		Integer execCount = 0;
		
		try {
			execCount = mallBuriedPointSiteDao.updateMbps(mallBuriedPointSite);
			logger.debug("MallBuriedPointSiteServiceImpl.updateMbps invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallBuriedPointSiteServiceImpl.updateMbps found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeMbpsById(Long id) {
		Integer execCount = 0;
		
		try {
			execCount = mallBuriedPointSiteDao.deleteMbpsById(id);
			logger.debug("MallBuriedPointSiteServiceImpl.removeMbpsById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallBuriedPointSiteServiceImpl.removeMbpsById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

}
