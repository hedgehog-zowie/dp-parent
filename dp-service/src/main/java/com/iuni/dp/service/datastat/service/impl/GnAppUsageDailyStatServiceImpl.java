package com.iuni.dp.service.datastat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.GnAppUsageDailyStatDao;
import com.iuni.dp.persist.datastat.model.GnAppUsageDailyStat;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.GnAppUsageDailyStatService;

/**
 * 金立相关APP应用基本使用情况日统计 Service
 * @author CaiKe
 * @version dp-serive-V1.0.2
 */
@Service("gnAppUsageDailyStatService")
public class GnAppUsageDailyStatServiceImpl implements GnAppUsageDailyStatService {

	private Logger logger = LoggerFactory.getLogger(GnAppUsageDailyStatServiceImpl.class);
	
	@Autowired
	private GnAppUsageDailyStatDao gnAppUsageDailyStatDao; 
	
	@Override
	public Integer saveGauds(GnAppUsageDailyStat gnAppUsageDailyStat) {
		Integer execCount = 0;
		
		try {
			execCount = gnAppUsageDailyStatDao.insertGauds(gnAppUsageDailyStat);
			logger.debug("GnAppUsageDailyStatServiceImpl.saveGauds invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppUsageDailyStatServiceImpl.saveGauds found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeGaudsById(Long id) {
		Integer execCount = 0;
		
		try {
			execCount = gnAppUsageDailyStatDao.deleteGaudsById(id);
			logger.debug("GnAppUsageDailyStatServiceImpl.removeGaudsById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("GnAppUsageDailyStatServiceImpl.removeGaudsById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

}
