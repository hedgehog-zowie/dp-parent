package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.MallGoodsPvuvDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallGoodsPvuvDailyStat;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.MallGoodsPvuvDailyStatService;

/**
 * 商城商品PV/UV基础数据日统计Service业务类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("mallGoodsPvuvDailyStatService")
public class MallGoodsPvuvDailyStatServiceImpl implements MallGoodsPvuvDailyStatService {

	private final static Logger logger = LoggerFactory.getLogger(MallGoodsPvuvDailyStatServiceImpl.class);
	
	@Autowired
	private MallGoodsPvuvDailyStatDao mallGoodsPvuvDailyStatDao;
	
	@Override
	public MallGoodsPvuvDailyStat getMallGoodsPvuvDailyStatById(Integer id) {
		MallGoodsPvuvDailyStat mallGoodsPvuvDailyStat = null;
		
		try {
			mallGoodsPvuvDailyStat = mallGoodsPvuvDailyStatDao.selectMallGoodsPvuvDailyStatById(id);
			logger.debug("MallGoodsPvuvDailyStatServiceImpl.getMallGoodsPvuvDailyStatById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallGoodsPvuvDailyStatServiceImpl.getMallGoodsPvuvDailyStatById found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return mallGoodsPvuvDailyStat;
	}

	@Override
	public List<MallGoodsPvuvDailyStat> getAllByExample(
			Map<String, Object> params) {
		List<MallGoodsPvuvDailyStat> mallGoodsPvuvDailyStats = null;
		
		try {
			mallGoodsPvuvDailyStats = mallGoodsPvuvDailyStatDao.selectAllByExample(params);
			logger.debug("MallGoodsPvuvDailyStatServiceImpl.getAllByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallGoodsPvuvDailyStatServiceImpl.getAllByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return mallGoodsPvuvDailyStats;
	}

	@Override
	public List<MallGoodsPvuvDailyStat> getMallGoodsPvuvDailyStatByPage(
			Map<String, Object> params) {
		List<MallGoodsPvuvDailyStat> mallGoodsPvuvDailyStats = null;
		
		try {
			mallGoodsPvuvDailyStats = mallGoodsPvuvDailyStatDao.selectMallGoodsPvuvDailyStatByPage(params);
			logger.debug("MallGoodsPvuvDailyStatServiceImpl.getMallGoodsPvuvDailyStatByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallGoodsPvuvDailyStatServiceImpl.getMallGoodsPvuvDailyStatByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return mallGoodsPvuvDailyStats;
	}

	@Override
	public Integer getTotalCountByExample(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = mallGoodsPvuvDailyStatDao.selectTotalCountByExample(params);
			logger.debug("MallGoodsPvuvDailyStatServiceImpl.getTotalCountByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallGoodsPvuvDailyStatServiceImpl.getTotalCountByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> getMallGoodsDailyBaseStatByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = mallGoodsPvuvDailyStatDao.selectMallGoodsDailyBaseStatByExample(params);
			logger.debug("MallGoodsPvuvDailyStatServiceImpl.getMallGoodsDailyBaseStatByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallGoodsPvuvDailyStatServiceImpl.getMallGoodsDailyBaseStatByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> getMallGoodsDailyBaseStatByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = mallGoodsPvuvDailyStatDao.selectMallGoodsDailyBaseStatByPage(params);
			logger.debug("MallGoodsPvuvDailyStatServiceImpl.getMallGoodsDailyBaseStatByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallGoodsPvuvDailyStatServiceImpl.getMallGoodsDailyBaseStatByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer getMallGoodsDailyBaseStatCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = mallGoodsPvuvDailyStatDao.selectMallGoodsDailyBaseStatCount(params);
			logger.debug("MallGoodsPvuvDailyStatServiceImpl.getMallGoodsDailyBaseStatCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallGoodsPvuvDailyStatServiceImpl.getMallGoodsDailyBaseStatCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Integer saveMallGoodsPvuvDailyStat(
			MallGoodsPvuvDailyStat mallGoodsPvuvDailyStat) {
		Integer execCount = 0;
		
		try {
			execCount = mallGoodsPvuvDailyStatDao.insertMallGoodsPvuvDailyStat(mallGoodsPvuvDailyStat);
			logger.debug("MallGoodsPvuvDailyStatServiceImpl.saveMallGoodsPvuvDailyStat invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallGoodsPvuvDailyStatServiceImpl.saveMallGoodsPvuvDailyStat found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeMallGoodsPvuvDailyStatById(Integer id) {
		Integer execCount = 0;
		
		try {
			execCount = mallGoodsPvuvDailyStatDao.deleteMallGoodsPvuvDailyStatById(id);
			logger.debug("MallGoodsPvuvDailyStatServiceImpl.removeMallGoodsPvuvDailyStatById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallGoodsPvuvDailyStatServiceImpl.removeMallGoodsPvuvDailyStatById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

}
