package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.MallOrderDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallOrderDailyStat;

/**
 * 商城订单基础数据日统计DAO实现类
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
@Repository("mallOrderDailyStatDao")
public class MallOrderDailyStatDaoImpl extends BaseDaoImpl<MallOrderDailyStat, Serializable> implements MallOrderDailyStatDao {

	private static final Logger logger = LoggerFactory.getLogger(MallOrderDailyStatDaoImpl.class);
	
	@Override
	public MallOrderDailyStat selectMallOrderDailyStatById(Integer id) {
		logger.debug("MallOrderDailyStatDaoImpl.selectMallOrderDailyStatById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		
		MallOrderDailyStat mallOrderDailyStat = (MallOrderDailyStat) getById(MallOrderDailyStat.class.getSimpleName()+ ".selectMallOrderDailyStatById"
				, id);
		
		logger.debug("MallOrderDailyStatDaoImpl.selectMallOrderDailyStatById(Integer) success: mallOrderDailyStat={}, costTime={}ms",
				new Object[] { mallOrderDailyStat.toString(), System.currentTimeMillis() - stime });
		return mallOrderDailyStat;
	}

	@Override
	public List<MallOrderDailyStat> selectAllByExample(
			Map<String, Object> params) {
		logger.debug("MallOrderDailyStatDaoImpl.selectAllByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<MallOrderDailyStat> mallOrderDailyStats = findAllObjectsByPage(MallOrderDailyStat.class.getSimpleName() + ".selectAllByExample", params);
		
		logger.debug("MallOrderDailyStatDaoImpl.selectAllByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return mallOrderDailyStats;
	}

	@Override
	public List<MallOrderDailyStat> selectMallOrderDailyStatByPage(
			Map<String, Object> params) {
		logger.debug("MallOrderDailyStatDaoImpl.selectMallOrderDailyStatByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<MallOrderDailyStat> mallOrderDailyStats = findAllObjectsByPage(MallOrderDailyStat.class.getSimpleName() + ".selectMallOrderDailyStatByPage", params);
		
		logger.debug("MallOrderDailyStatDaoImpl.selectMallOrderDailyStatByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return mallOrderDailyStats;
	}

	@Override
	public Integer selectTotalCountByExample(Map<String, Object> params) {
		logger.debug("MallOrderDailyStatDaoImpl.selectTotalCountByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(MallOrderDailyStat.class.getSimpleName() + ".selectTotalCountByExample", params);
		
		logger.debug("MallOrderDailyStatDaoImpl.selectTotalCountByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Integer insertMallOrderDailyStat(
			MallOrderDailyStat mallOrderDailyStat) {
		logger.debug("MallOrderDailyStatDaoImpl.insertMallOrderDailyStat(StatScheme) entry: mallPvuvDailyStat={}",
				new Object[] { mallOrderDailyStat.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) insert(mallOrderDailyStat, MallOrderDailyStat.class.getSimpleName() + ".insertMallOrderDailyStat");
		
		logger.debug("MallOrderDailyStatDaoImpl.insertMallOrderDailyStat(StatScheme) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer deleteMallOrderDailyStatById(Integer id) {
		logger.debug("MallOrderDailyStatDaoImpl.deleteMallOrderDailyStatById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, MallOrderDailyStat.class.getSimpleName() + ".deleteMallOrderDailyStatById");
		
		logger.debug("MallOrderDailyStatDaoImpl.deleteMallOrderDailyStatById(Integer) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}
	
}
