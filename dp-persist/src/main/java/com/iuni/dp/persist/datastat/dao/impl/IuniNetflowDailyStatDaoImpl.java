package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.IuniNetflowDailyStatDao;
import com.iuni.dp.persist.datastat.model.IuniNetflowDailyStat;

/**
 * IuniNetflowDailyStat DAO
 * @author CaiKe
 * @version dp-persist-V1.0.2
 */
@Repository("iuniNetflowDailyStatDao")
public class IuniNetflowDailyStatDaoImpl extends
		BaseDaoImpl<IuniNetflowDailyStat, Serializable> implements
		IuniNetflowDailyStatDao {

	private Logger logger = LoggerFactory.getLogger(IuniNetflowDailyStatDaoImpl.class);
	
	@Override
	public List<Map<String, Object>> selectIndsByExample(
			Map<String, Object> params) {
		logger.debug("IuniNetflowDailyStatDaoImpl.selectIndsByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniNetflowDailyStat.class.getSimpleName() 
				+ ".selectIndsByExample", params);
		
		logger.debug("IuniNetflowDailyStatDaoImpl.selectIndsByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIndsByPage(Map<String, Object> params) {
		logger.debug("IuniNetflowDailyStatDaoImpl.selectIndsByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniNetflowDailyStat.class.getSimpleName() 
				+ ".selectIndsByPage", params);
		
		logger.debug("IuniNetflowDailyStatDaoImpl.selectIndsByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIndsCount(Map<String, Object> params) {
		logger.debug("IuniNetflowDailyStatDaoImpl.selectIndsCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniNetflowDailyStat.class.getSimpleName() 
				+ ".selectIndsCount", params);
		
		logger.debug("IuniNetflowDailyStatDaoImpl.selectIndsCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIndsSumByDate(
			Map<String, Object> params) {
		logger.debug("IuniNetflowDailyStatDaoImpl.selectIndsSumByDate(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniNetflowDailyStat.class.getSimpleName() 
				+ ".selectIndsSumByDate", params);
		
		logger.debug("IuniNetflowDailyStatDaoImpl.selectIndsSumByDate(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

}
