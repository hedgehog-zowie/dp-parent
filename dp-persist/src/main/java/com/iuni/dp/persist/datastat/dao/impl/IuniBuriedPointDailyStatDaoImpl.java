package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.IuniBuriedPointDailyStatDao;
import com.iuni.dp.persist.datastat.model.IuniBuriedPointDailyStat;
import com.iuni.dp.persist.datastat.model.IuniBuriedPointSite;

/**
 * IuniBuriedPointDailyStat DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.3
 */
@Repository("iuniBuriedPointDailyStatDao")
public class IuniBuriedPointDailyStatDaoImpl extends
		BaseDaoImpl<IuniBuriedPointDailyStat, Serializable> implements
		IuniBuriedPointDailyStatDao {

	private final Logger logger = LoggerFactory.getLogger(IuniBuriedPointDailyStatDaoImpl.class);
	
	@Override
	public List<Map<String, Object>> selectIbpdByExample(
			Map<String, Object> params) {
		logger.debug("IuniBuriedPointDailyStatDaoImpl.selectIbpdByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String,Object>> list = findAllObjectsByPage2(IuniBuriedPointDailyStat.class.getSimpleName() 
				+ ".selectIbpdByExample", params);
		
		logger.debug("IuniBuriedPointDailyStatDaoImpl.selectIbpdByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIbpdByPage(Map<String, Object> params) {
		logger.debug("IuniBuriedPointDailyStatDaoImpl.selectIbpdByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String,Object>> list = findAllObjectsByPage2(IuniBuriedPointDailyStat.class.getSimpleName() 
				+ ".selectIbpdByPage", params);
		
		logger.debug("IuniBuriedPointDailyStatDaoImpl.selectIbpdByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		
		return list;
	}

	@Override
	public Integer selectIbpdCount(Map<String, Object> params) {
		logger.debug("IuniBuriedPointDailyStatDaoImpl.selectIbpdCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniBuriedPointDailyStat.class.getSimpleName() 
				+ ".selectIbpdCount", params);
		
		logger.debug("IuniBuriedPointDailyStatDaoImpl.selectIbpdCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectClickRateTodayByExample(
			Map<String, Object> params) {
		logger.debug("IuniBuriedPointDailyStatDaoImpl.selectClickRateTodayByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String,Object>> list = findAllObjectsByPage2(IuniBuriedPointDailyStat.class.getSimpleName() 
				+ ".selectClickRateTodayByExample", params);
		
		logger.debug("IuniBuriedPointDailyStatDaoImpl.selectClickRateTodayByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		
		return list;
	}

	@Override
	public List<Map<String, Object>> selectClickRateTodayByPage(
			Map<String, Object> params) {
		logger.debug("IuniBuriedPointDailyStatDaoImpl.selectClickRateTodayByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String,Object>> list = findAllObjectsByPage2(IuniBuriedPointDailyStat.class.getSimpleName() 
				+ ".selectClickRateTodayByPage", params);
		
		logger.debug("IuniBuriedPointDailyStatDaoImpl.selectClickRateTodayByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		
		return list;
	}

	@Override
	public Integer selectClickRateTodayCount(Map<String, Object> params) {
		logger.debug("IuniBuriedPointDailyStatDaoImpl.selectClickRateTodayCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniBuriedPointDailyStat.class.getSimpleName() 
				+ ".selectClickRateTodayCount", params);
		
		logger.debug("IuniBuriedPointDailyStatDaoImpl.selectClickRateTodayCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

}
