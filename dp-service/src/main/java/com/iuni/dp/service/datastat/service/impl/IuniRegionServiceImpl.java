package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.IuniRegionDao;
import com.iuni.dp.persist.datastat.model.IuniRegion;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.IuniRegionService;

/**
 * IuniRegion Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.2
 */
@Service("iuniRegionService")
public class IuniRegionServiceImpl implements IuniRegionService {

	private Logger logger = LoggerFactory.getLogger(IuniRegionServiceImpl.class);
	
	@Autowired
	private IuniRegionDao iuniRegionDao;
	
	@Override
	public List<IuniRegion> queryIuniRegionMapByParent(
			Map<String, Object> params) {
		List<IuniRegion> list = null;
		
		try {
			list = iuniRegionDao.selectIuniRegionMapByParent(params);
			logger.debug("IuniOrderInfoServiceImpl.queryIuniRegionMapByParent invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("IuniOrderInfoServiceImpl.queryIuniRegionMapByParent found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	
	
}
