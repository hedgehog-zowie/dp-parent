package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.IuniPageNetflowDailyStatDao;
import com.iuni.dp.persist.datastat.model.IuniPageNetflowDailyStat;

/**
 * IuniPageNetflowDailyStat DAO
 * @author CaiKe
 * @version dp-persist-V1.0.2
 */
@Repository("iuniPageNetflowDailyStatDao")
public class IuniPageNetflowDailyStatDaoImpl extends
		BaseDaoImpl<IuniPageNetflowDailyStat, Serializable> implements
		IuniPageNetflowDailyStatDao {
	
	private Logger logger = LoggerFactory.getLogger(IuniPageNetflowDailyStatDaoImpl.class);

	@Override
	public List<Map<String, Object>> selectIpndsByExample(
			Map<String, Object> params) {
		logger.debug("IuniPageNetflowDailyStatDaoImpl.selectIpndsByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniPageNetflowDailyStat.class.getSimpleName() 
				+ ".selectIpndsByExample", params);
		
		logger.debug("IuniPageNetflowDailyStatDaoImpl.selectIpndsByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIpndsByPage(
			Map<String, Object> params) {
		logger.debug("IuniPageNetflowDailyStatDaoImpl.selectIpndsByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniPageNetflowDailyStat.class.getSimpleName() 
				+ ".selectIpndsByPage", params);
		
		logger.debug("IuniPageNetflowDailyStatDaoImpl.selectIpndsByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIpndsCount(Map<String, Object> params) {
		logger.debug("IuniPageNetflowDailyStatDaoImpl.selectIpndsCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniPageNetflowDailyStat.class.getSimpleName() 
				+ ".selectIpndsCount", params);
		
		logger.debug("IuniPageNetflowDailyStatDaoImpl.selectIpndsCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIpndsSumOnDateByExample(
			Map<String, Object> params) {
		logger.debug("IuniPageNetflowDailyStatDaoImpl.selectIpndsSumOnDateByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniPageNetflowDailyStat.class.getSimpleName() 
				+ ".selectIpndsSumOnDateByExample", params);
		
		logger.debug("IuniPageNetflowDailyStatDaoImpl.selectIpndsSumOnDateByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIpndsSumOnDateByPage(
			Map<String, Object> params) {
		logger.debug("IuniPageNetflowDailyStatDaoImpl.selectIpndsSumOnDateByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniPageNetflowDailyStat.class.getSimpleName() 
				+ ".selectIpndsSumOnDateByPage", params);
		
		logger.debug("IuniPageNetflowDailyStatDaoImpl.selectIpndsSumOnDateByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIpndsSumOnDateCount(Map<String, Object> params) {
		logger.debug("IuniPageNetflowDailyStatDaoImpl.selectIpndsSumOnDateCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniPageNetflowDailyStat.class.getSimpleName() 
				+ ".selectIpndsSumOnDateCount", params);
		
		logger.debug("IuniPageNetflowDailyStatDaoImpl.selectIpndsSumOnDateCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

}
