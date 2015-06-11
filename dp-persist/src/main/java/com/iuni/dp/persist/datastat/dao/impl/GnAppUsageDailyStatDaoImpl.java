package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.GnAppUsageDailyStatDao;
import com.iuni.dp.persist.datastat.model.GnAppUsageDailyStat;

/**
 * 金立相关APP应用基本使用情况日统计 DAO
 * @author CaiKe
 * @version dp-persist-V1.0.2
 */
@Repository("gnAppUsageDailyStatDao")
public class GnAppUsageDailyStatDaoImpl extends
		BaseDaoImpl<GnAppUsageDailyStat, Serializable> implements
		GnAppUsageDailyStatDao {
	
	private Logger logger = LoggerFactory.getLogger(GnAppUsageDailyStatDaoImpl.class);

	@Override
	public Integer insertGauds(GnAppUsageDailyStat gnAppUsageDailyStat) {
		logger.debug("GnAppUsageDailyStatDaoImpl.insertGauds(GnAppUsageDailyStat) entry: gnAppUsageDailyStat={}",
				new Object[] { gnAppUsageDailyStat.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) insert(gnAppUsageDailyStat, GnAppUsageDailyStat.class.getSimpleName() 
				+ ".insertGauds");
		
		logger.debug("GnAppUsageDailyStatDaoImpl.insertGauds(GnAppUsageDailyStat) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer deleteGaudsById(Long id) {
		logger.debug("GnAppUsageDailyStatDaoImpl.deleteGaudsById(Long) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, GnAppUsageDailyStat.class.getSimpleName() + ".deleteGaudsById");
		
		logger.debug("GnAppUsageDailyStatDaoImpl.deleteGaudsById(Long) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

}
