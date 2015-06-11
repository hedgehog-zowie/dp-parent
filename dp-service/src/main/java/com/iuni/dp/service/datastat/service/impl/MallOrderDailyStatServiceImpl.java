package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.MallOrderDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallOrderDailyStat;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.MallOrderDailyStatService;

/**
 * 商城订单基础数据日统计Service业务类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("mallOrderDailyStatService")
public class MallOrderDailyStatServiceImpl implements MallOrderDailyStatService {

	private final static Logger logger = LoggerFactory.getLogger(MallOrderDailyStatServiceImpl.class);
	
	@Autowired
	private MallOrderDailyStatDao mallOrderDailyStatDao;
	
	@Override
	public MallOrderDailyStat getMallOrderDailyStatById(Integer id) {
		MallOrderDailyStat mallOrderDailyStat = null;
		
		try {
			mallOrderDailyStat = mallOrderDailyStatDao.selectMallOrderDailyStatById(id);
			logger.debug("MallOrderDailyStatServiceImpl.getMallOrderDailyStatById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallOrderDailyStatServiceImpl.getMallOrderDailyStatById found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return mallOrderDailyStat;
	}

	@Override
	public List<MallOrderDailyStat> getAllByExample(Map<String, Object> params) {
		List<MallOrderDailyStat> mallOrderDailyStats = null;
		
		try {
			mallOrderDailyStats = mallOrderDailyStatDao.selectAllByExample(params);
			logger.debug("MallOrderDailyStatServiceImpl.getAllByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallOrderDailyStatServiceImpl.getAllByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return mallOrderDailyStats;
	}

	@Override
	public List<MallOrderDailyStat> getMallOrderDailyStatByPage(
			Map<String, Object> params) {
		List<MallOrderDailyStat> mallOrderDailyStats = null;
		
		try {
			mallOrderDailyStats = mallOrderDailyStatDao.selectMallOrderDailyStatByPage(params);
			logger.debug("MallOrderDailyStatServiceImpl.getMallOrderDailyStatByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallOrderDailyStatServiceImpl.getMallOrderDailyStatByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return mallOrderDailyStats;
	}

	@Override
	public Integer getTotalCountByExample(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = mallOrderDailyStatDao.selectTotalCountByExample(params);
			logger.debug("MallOrderDailyStatServiceImpl.getTotalCountByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallOrderDailyStatServiceImpl.getTotalCountByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Integer saveMallOrderDailyStat(MallOrderDailyStat mallOrderDailyStat) {
		Integer execCount = 0;
		
		try {
			execCount = mallOrderDailyStatDao.insertMallOrderDailyStat(mallOrderDailyStat);
			logger.debug("MallOrderDailyStatServiceImpl.saveMallOrderDailyStat invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallOrderDailyStatServiceImpl.saveMallOrderDailyStat found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeMallOrderDailyStatById(Integer id) {
		Integer execCount = 0;
		
		try {
			execCount = mallOrderDailyStatDao.deleteMallOrderDailyStatById(id);
			logger.debug("MallOrderDailyStatServiceImpl.removeMallOrderDailyStatById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallOrderDailyStatServiceImpl.removeMallOrderDailyStatById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

}
