package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.IuniBuriedPointSiteDao;
import com.iuni.dp.persist.datastat.model.IuniBuriedPointSite;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.IuniBuriedPointSiteService;

/**
 * IuniBuriedPointSite Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-V1.1.0
 */
@Service("iuniBuriedPointSiteService")
public class IuniBuriedPointSiteServiceImpl implements IuniBuriedPointSiteService {

	private final Logger logger = LoggerFactory.getLogger(IuniBuriedPointSiteServiceImpl.class);
	
	@Autowired
	private IuniBuriedPointSiteDao iuniBuriedPointSiteDao; 
	
	@Override
	public List<IuniBuriedPointSite> queryIbpsByExample(
			Map<String, Object> params) {
		List<IuniBuriedPointSite> list = null;
		
		try {
			list = iuniBuriedPointSiteDao.selectIbpsByExample(params);
			logger.debug("IuniBuriedPointSiteServiceImpl.queryIbpsByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniBuriedPointSiteServiceImpl.queryIbpsByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<IuniBuriedPointSite> queryIbpsByPage(Map<String, Object> params) {
		List<IuniBuriedPointSite> list = null;
		
		try {
			list = iuniBuriedPointSiteDao.selectIbpsByPage(params);
			logger.debug("IuniBuriedPointSiteServiceImpl.queryIbpsByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniBuriedPointSiteServiceImpl.queryIbpsByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer queryIbpsCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = iuniBuriedPointSiteDao.selectIbpsCount(params);
			logger.debug("IuniBuriedPointSiteServiceImpl.queryIbpsCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniBuriedPointSiteServiceImpl.queryIbpsCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Long saveIbps(IuniBuriedPointSite iuniBuriedPointSite) {
		Long execCount = 0l;
		
		try {
			execCount = iuniBuriedPointSiteDao.insertIbps(iuniBuriedPointSite);
			logger.debug("IuniBuriedPointSiteServiceImpl.saveIbps invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniBuriedPointSiteServiceImpl.saveIbps found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer updateIbps(IuniBuriedPointSite iuniBuriedPointSite) {
		Integer execCount = 0;
		
		try {
			execCount = iuniBuriedPointSiteDao.updateIbps(iuniBuriedPointSite);
			logger.debug("IuniBuriedPointSiteServiceImpl.updateIbps invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniBuriedPointSiteServiceImpl.updateIbps found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeIbpsById(Long id) {
		Integer execCount = 0;
		
		try {
			execCount = iuniBuriedPointSiteDao.deleteIbpsById(id);
			logger.debug("IuniBuriedPointSiteServiceImpl.removeIbpsById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniBuriedPointSiteServiceImpl.removeIbpsById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

}
