package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.MallPvuvDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallPvuvDailyStat;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.MallPvuvDailyStatService;

/**
 * 商城PV/UV基础数据日统计Service业务类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("mallPvuvDailyStatService")
public class MallPvuvDailyStatServiceImpl implements MallPvuvDailyStatService {

	private final static Logger logger = LoggerFactory.getLogger(MallPvuvDailyStatServiceImpl.class);
	
	@Autowired
	private MallPvuvDailyStatDao mallPvuvDailyStatDao;
	
	@Override
	public MallPvuvDailyStat getMallPvuvDailyStatById(Integer id) {
		MallPvuvDailyStat mallPvuvDailyStat = null;
		
		try {
			mallPvuvDailyStat = mallPvuvDailyStatDao.selectMallPvuvDailyStatById(id);
			logger.debug("MallPvuvDailyStatServiceImpl.getMallPvuvDailyStatById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallPvuvDailyStatServiceImpl.getMallPvuvDailyStatById found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return mallPvuvDailyStat;
	}

	@Override
	public List<MallPvuvDailyStat> getAllByExample(Map<String, Object> params) {
		List<MallPvuvDailyStat> mallPvuvDailyStats = null;
		
		try {
			mallPvuvDailyStats = mallPvuvDailyStatDao.selectAllByExample(params);
			logger.debug("MallPvuvDailyStatServiceImpl.getAllByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallPvuvDailyStatServiceImpl.getAllByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return mallPvuvDailyStats;
	}

	@Override
	public List<MallPvuvDailyStat> getMallPvuvDailyStatByPage(
			Map<String, Object> params) {
		List<MallPvuvDailyStat> mallPvuvDailyStats = null;
		
		try {
			mallPvuvDailyStats = mallPvuvDailyStatDao.selectMallPvuvDailyStatByPage(params);
			logger.debug("MallPvuvDailyStatServiceImpl.getMallPvuvDailyStatByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallPvuvDailyStatServiceImpl.getMallPvuvDailyStatByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return mallPvuvDailyStats;
	}

	@Override
	public Integer getTotalCountByExample(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = mallPvuvDailyStatDao.selectTotalCountByExample(params);
			logger.debug("MallPvuvDailyStatServiceImpl.getTotalCountByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallPvuvDailyStatServiceImpl.getTotalCountByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> getMallDailyBaseStatByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = mallPvuvDailyStatDao.selectMallDailyBaseStatByExample(params);
			logger.debug("MallPvuvDailyStatServiceImpl.getMallDailyBaseStatByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallPvuvDailyStatServiceImpl.getMallDailyBaseStatByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> getMallDailyBaseStatByPage(
			Map<String, Object> params) {
		
		List<Map<String, Object>> list = null;
				
		try {
			list = mallPvuvDailyStatDao.selectMallDailyBaseStatByPage(params);
			logger.debug("MallPvuvDailyStatServiceImpl.getMallDailyBaseStatByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallPvuvDailyStatServiceImpl.getMallDailyBaseStatByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer getMallDailyBaseStatCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = mallPvuvDailyStatDao.selectMallDailyBaseStatCount(params);
			logger.debug("MallPvuvDailyStatServiceImpl.getMallDailyBaseStatCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallPvuvDailyStatServiceImpl.getMallDailyBaseStatCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> getWjqDailyBaseStatByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = mallPvuvDailyStatDao.selectWjqDailyBaseStatByExample(params);
			logger.debug("MallPvuvDailyStatServiceImpl.getWjqDailyBaseStatByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallPvuvDailyStatServiceImpl.getWjqDailyBaseStatByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> getWjqDailyBaseStatByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = mallPvuvDailyStatDao.selectWjqDailyBaseStatByPage(params);
			logger.debug("MallPvuvDailyStatServiceImpl.getWjqDailyBaseStatByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallPvuvDailyStatServiceImpl.getWjqDailyBaseStatByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer getWjqDailyBaseStatCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = mallPvuvDailyStatDao.selectWjqDailyBaseStatCount(params);
			logger.debug("MallPvuvDailyStatServiceImpl.getWjqDailyBaseStatCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallPvuvDailyStatServiceImpl.getWjqDailyBaseStatCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> getWjqMonthlyBaseStatByExample(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = mallPvuvDailyStatDao.selectWjqMonthlyBaseStatByExample(params);
			logger.debug("MallPvuvDailyStatServiceImpl.getWjqMonthlyBaseStatByExample invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallPvuvDailyStatServiceImpl.getWjqMonthlyBaseStatByExample found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> getWjqMonthlyBaseStatByPage(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = mallPvuvDailyStatDao.selectWjqMonthlyBaseStatByPage(params);
			logger.debug("MallPvuvDailyStatServiceImpl.getWjqMonthlyBaseStatByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallPvuvDailyStatServiceImpl.getWjqMonthlyBaseStatByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

	@Override
	public Integer getWjqMonthlyBaseStatCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = mallPvuvDailyStatDao.selectWjqMonthlyBaseStatCount(params);
			logger.debug("MallPvuvDailyStatServiceImpl.getWjqMonthlyBaseStatCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallPvuvDailyStatServiceImpl.getWjqMonthlyBaseStatCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Integer saveMallPvuvDailyStat(MallPvuvDailyStat mallPvuvDailyStat) {
		Integer execCount = 0;
		
		try {
			execCount = mallPvuvDailyStatDao.insertMallPvuvDailyStat(mallPvuvDailyStat);
			logger.debug("MallPvuvDailyStatServiceImpl.saveMallPvuvDailyStat invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallPvuvDailyStatServiceImpl.saveMallPvuvDailyStat found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeMallPvuvDailyStatById(Integer id) {
		Integer execCount = 0;
		
		try {
			execCount = mallPvuvDailyStatDao.deleteMallPvuvDailyStatById(id);
			logger.debug("MallPvuvDailyStatServiceImpl.removeMallPvuvDailyStatById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("MallPvuvDailyStatServiceImpl.removeMallPvuvDailyStatById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

}
