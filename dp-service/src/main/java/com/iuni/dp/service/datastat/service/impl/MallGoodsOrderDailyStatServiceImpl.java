package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.MallGoodsOrderDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallGoodsOrderDailyStat;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.MallGoodsOrderDailyStatService;

/**
 * 商城订单商品基础数据日统计Service业务类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("mallGoodsOrderDailyStatService")
public class MallGoodsOrderDailyStatServiceImpl implements MallGoodsOrderDailyStatService {

	private final static Logger logger = LoggerFactory.getLogger(MallGoodsOrderDailyStatServiceImpl.class);
	
	@Autowired
	private MallGoodsOrderDailyStatDao mallGoodsOrderDailyStatDao;
	
	@Override
	public MallGoodsOrderDailyStat getMallGoodsOrderDailyStatById(Integer id) {
		MallGoodsOrderDailyStat mallGoodsOrderDailyStat = null;
		
		try {
			mallGoodsOrderDailyStat = mallGoodsOrderDailyStatDao.selectMallGoodsOrderDailyStatById(id);
			logger.debug("MallGoodsOrderDailyStatServiceImpl.getMallGoodsOrderDailyStatById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallGoodsOrderDailyStatServiceImpl.getMallGoodsOrderDailyStatById found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return mallGoodsOrderDailyStat;
	}

	@Override
	public List<MallGoodsOrderDailyStat> getAllByExample(
			Map<String, Object> params) {
		List<MallGoodsOrderDailyStat> mallGoodsOrderDailyStats = null;
		
		try {
			mallGoodsOrderDailyStats = mallGoodsOrderDailyStatDao.selectAllByExample(params);
			logger.debug("MallGoodsOrderDailyStatServiceImpl.getAllByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallGoodsOrderDailyStatServiceImpl.getAllByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return mallGoodsOrderDailyStats;
	}

	@Override
	public List<MallGoodsOrderDailyStat> getMallGoodsOrderDailyStatByPage(
			Map<String, Object> params) {
		List<MallGoodsOrderDailyStat> mallGoodsOrderDailyStats = null;
		
		try {
			mallGoodsOrderDailyStats = mallGoodsOrderDailyStatDao.selectMallGoodsOrderDailyStatByPage(params);
			logger.debug("MallGoodsOrderDailyStatServiceImpl.getMallGoodsOrderDailyStatByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallGoodsOrderDailyStatServiceImpl.getMallGoodsOrderDailyStatByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return mallGoodsOrderDailyStats;
	}

	@Override
	public Integer getTotalCountByExample(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = mallGoodsOrderDailyStatDao.selectTotalCountByExample(params);
			logger.debug("MallGoodsOrderDailyStatServiceImpl.getTotalCountByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallGoodsOrderDailyStatServiceImpl.getTotalCountByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Integer saveMallGoodsOrderDailyStat(
			MallGoodsOrderDailyStat mallGoodsOrderDailyStat) {
		Integer execCount = 0;
		
		try {
			execCount = mallGoodsOrderDailyStatDao.insertMallGoodsOrderDailyStat(mallGoodsOrderDailyStat);
			logger.debug("MallGoodsOrderDailyStatServiceImpl.saveMallGoodsOrderDailyStat invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallGoodsOrderDailyStatServiceImpl.saveMallGoodsOrderDailyStat found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeMallGoodsOrderDailyStatById(Integer id) {
		Integer execCount = 0;
		
		try {
			execCount = mallGoodsOrderDailyStatDao.deleteMallGoodsOrderDailyStatById(id);
			logger.debug("MallGoodsOrderDailyStatServiceImpl.removeMallGoodsOrderDailyStatById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallGoodsOrderDailyStatServiceImpl.removeMallGoodsOrderDailyStatById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

}
