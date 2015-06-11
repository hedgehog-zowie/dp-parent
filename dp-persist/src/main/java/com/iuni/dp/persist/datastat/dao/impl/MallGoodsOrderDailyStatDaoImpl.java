package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.MallGoodsOrderDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallGoodsOrderDailyStat;

/**
 * 商城订单商品基础数据日统计DAO实现类
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
@Repository("mallGoodsOrderDailyStatDao")
public class MallGoodsOrderDailyStatDaoImpl extends BaseDaoImpl<MallGoodsOrderDailyStat, Serializable> implements MallGoodsOrderDailyStatDao {

	private static final Logger logger = LoggerFactory.getLogger(MallGoodsOrderDailyStatDaoImpl.class);
	
	@Override
	public MallGoodsOrderDailyStat selectMallGoodsOrderDailyStatById(Integer id) {
		logger.debug("MallGoodsOrderDailyStatDaoImpl.selectMallGoodsOrderDailyStatById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		
		MallGoodsOrderDailyStat mallGoodsOrderDailyStat = (MallGoodsOrderDailyStat) getById(MallGoodsOrderDailyStat.class.getSimpleName()+ ".selectMallGoodsOrderDailyStatById"
				, id);
		
		logger.debug("MallGoodsOrderDailyStatDaoImpl.selectMallGoodsOrderDailyStatById(Integer) success: MallGoodsOrderDailyStat={}, costTime={}ms",
				new Object[] { mallGoodsOrderDailyStat.toString(), System.currentTimeMillis() - stime });
		return mallGoodsOrderDailyStat;
	}

	@Override
	public List<MallGoodsOrderDailyStat> selectAllByExample(
			Map<String, Object> params) {
		logger.debug("MallGoodsOrderDailyStatDaoImpl.selectAllByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<MallGoodsOrderDailyStat> mallGoodsOrderDailyStats = findAllObjectsByPage(MallGoodsOrderDailyStat.class.getSimpleName() + ".selectAllByExample", params);
		
		logger.debug("MallGoodsOrderDailyStatDaoImpl.selectAllByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return mallGoodsOrderDailyStats;
	}

	@Override
	public List<MallGoodsOrderDailyStat> selectMallGoodsOrderDailyStatByPage(
			Map<String, Object> params) {
		logger.debug("MallGoodsOrderDailyStatDaoImpl.selectMallGoodsOrderDailyStatByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<MallGoodsOrderDailyStat> mallGoodsOrderDailyStats = findAllObjectsByPage(MallGoodsOrderDailyStat.class.getSimpleName() + ".selectMallGoodsOrderDailyStatByPage", params);
		
		logger.debug("MallGoodsOrderDailyStatDaoImpl.selectMallGoodsOrderDailyStatByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return mallGoodsOrderDailyStats;
	}

	@Override
	public Integer selectTotalCountByExample(Map<String, Object> params) {
		logger.debug("MallGoodsOrderDailyStatDaoImpl.selectTotalCountByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(MallGoodsOrderDailyStat.class.getSimpleName() + ".selectTotalCountByExample", params);
		
		logger.debug("MallGoodsOrderDailyStatDaoImpl.selectTotalCountByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Integer insertMallGoodsOrderDailyStat(
			MallGoodsOrderDailyStat mallGoodsOrderDailyStat) {
		logger.debug("MallGoodsOrderDailyStatDaoImpl.insertMallGoodsOrderDailyStat(StatScheme) entry: mallPvuvDailyStat={}",
				new Object[] { mallGoodsOrderDailyStat.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) insert(mallGoodsOrderDailyStat, MallGoodsOrderDailyStat.class.getSimpleName() + ".insertMallGoodsOrderDailyStat");
		
		logger.debug("MallGoodsOrderDailyStatDaoImpl.insertMallGoodsOrderDailyStat(StatScheme) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer deleteMallGoodsOrderDailyStatById(Integer id) {
		logger.debug("MallGoodsOrderDailyStatDaoImpl.deleteMallGoodsOrderDailyStatById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, MallGoodsOrderDailyStat.class.getSimpleName() + ".deleteMallGoodsOrderDailyStatById");
		
		logger.debug("MallGoodsOrderDailyStatDaoImpl.deleteMallGoodsOrderDailyStatById(Integer) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

}
