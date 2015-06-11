package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.IuniIpAreaDailyStatDao;
import com.iuni.dp.persist.datastat.model.IuniIpAreaDailyStat;

/**
 * IuniIpAreaDailyStat DAO
 * @author CaiKe
 * @version dp-persist-V1.0.2
 */
@Repository("iuniIpAreaDailyStatDao")
public class IuniIpAreaDailyStatDaoImpl extends
		BaseDaoImpl<IuniIpAreaDailyStat, Serializable> implements
		IuniIpAreaDailyStatDao {

	private Logger logger = LoggerFactory.getLogger(IuniIpAreaDailyStatDaoImpl.class);
	
	@Override
	public List<Map<String, Object>> selectIidsByExample(
			Map<String, Object> params) {
		logger.debug("IuniIpAreaDailyStatDaoImpl.selectIidsByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniIpAreaDailyStat.class.getSimpleName() 
				+ ".selectIidsByExample", params);
		
		logger.debug("IuniIpAreaDailyStatDaoImpl.selectIidsByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIidsByPage(Map<String, Object> params) {
		logger.debug("IuniIpAreaDailyStatDaoImpl.selectIidsByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniIpAreaDailyStat.class.getSimpleName() 
				+ ".selectIidsByPage", params);
		
		logger.debug("IuniIpAreaDailyStatDaoImpl.selectIidsByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIidsCount(Map<String, Object> params) {
		logger.debug("IuniIpAreaDailyStatDaoImpl.selectIidsCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniIpAreaDailyStat.class.getSimpleName() 
				+ ".selectIidsCount", params);
		
		logger.debug("IuniIpAreaDailyStatDaoImpl.selectIidsCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIidsSumOnDateByExample(
			Map<String, Object> params) {
		logger.debug("IuniIpAreaDailyStatDaoImpl.selectIidsSumOnDateByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniIpAreaDailyStat.class.getSimpleName() 
				+ ".selectIidsSumOnDateByExample", params);
		
		logger.debug("IuniIpAreaDailyStatDaoImpl.selectIidsSumOnDateByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIidsSumOnDateByPage(
			Map<String, Object> params) {
		logger.debug("IuniIpAreaDailyStatDaoImpl.selectIidsSumOnDateByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniIpAreaDailyStat.class.getSimpleName() 
				+ ".selectIidsSumOnDateByPage", params);
		
		logger.debug("IuniIpAreaDailyStatDaoImpl.selectIidsSumOnDateByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIidsSumOnDateCount(Map<String, Object> params) {
		logger.debug("IuniIpAreaDailyStatDaoImpl.selectIidsSumOnDateCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniIpAreaDailyStat.class.getSimpleName() 
				+ ".selectIidsSumOnDateCount", params);
		
		logger.debug("IuniIpAreaDailyStatDaoImpl.selectIidsSumOnDateCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

}
