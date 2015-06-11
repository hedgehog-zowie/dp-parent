package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.GnAppOrderDailyStatDao;
import com.iuni.dp.persist.datastat.model.GnAppOrderDailyStat;

/**
 * 金立相关APP应用关联订单情况日统计 DAO
 * @author CaiKe
 * @version dp-persist-V1.0.2
 */
@Repository("gnAppOrderDailyStatDao")
public class GnAppOrderDailyStatDaoImpl extends
		BaseDaoImpl<GnAppOrderDailyStat, Serializable> implements
		GnAppOrderDailyStatDao {

	private Logger logger = LoggerFactory.getLogger(GnAppOrderDailyStatDaoImpl.class);
	
	@Override
	public List<Map<String, Object>> selectGnAppChannelSalesByExample(
			Map<String, Object> params) {
		logger.debug("GnAppOrderDailyStatDaoImpl.selectGnAppChannelSalesByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppOrderDailyStat.class.getSimpleName() 
				+ ".selectGnAppChannelSalesByExample", params);
		
		logger.debug("GnAppOrderDailyStatDaoImpl.selectGnAppChannelSalesByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectGnAppChannelSalesByPage(
			Map<String, Object> params) {
		logger.debug("GnAppOrderDailyStatDaoImpl.selectGnAppChannelSalesByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppOrderDailyStat.class.getSimpleName() 
				+ ".selectGnAppChannelSalesByPage", params);
		
		logger.debug("GnAppOrderDailyStatDaoImpl.selectGnAppChannelSalesByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectGnAppChannelSalesCount(Map<String, Object> params) {
		logger.debug("GnAppOrderDailyStatDaoImpl.selectGnAppChannelSalesCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(GnAppOrderDailyStat.class.getSimpleName() 
				+ ".selectGnAppChannelSalesCount", params);
		
		logger.debug("GnAppOrderDailyStatDaoImpl.selectGnAppChannelSalesCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Integer insertGaods(GnAppOrderDailyStat gnAppOrderDailyStat) {
		logger.debug("GnAppOrderDailyStatDaoImpl.insertGaods(GnAppOrderDailyStat) entry: gnAppOrderDailyStat={}",
				new Object[] { gnAppOrderDailyStat.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) insert(gnAppOrderDailyStat, GnAppOrderDailyStat.class.getSimpleName() 
				+ ".insertGaods");
		
		logger.debug("GnAppOrderDailyStatDaoImpl.insertGaods(GnAppOrderDailyStat) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer deleteGaodsById(Long id) {
		logger.debug("GnAppOrderDailyStatDaoImpl.deleteGaodsById(Long) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, GnAppOrderDailyStat.class.getSimpleName() + ".deleteGaodsById");
		
		logger.debug("GnAppOrderDailyStatDaoImpl.deleteGaodsById(Long) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

}
