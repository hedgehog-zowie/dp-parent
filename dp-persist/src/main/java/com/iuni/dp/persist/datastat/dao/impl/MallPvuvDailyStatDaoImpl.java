package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.MallPvuvDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallPvuvDailyStat;

/**
 * 商城PV/UV基础数据日统计DAO实现类
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
@Repository("mallPvuvDailyStatDao")
public class MallPvuvDailyStatDaoImpl extends BaseDaoImpl<MallPvuvDailyStat, Serializable> implements MallPvuvDailyStatDao {

	private static final Logger logger = LoggerFactory.getLogger(MallPvuvDailyStatDaoImpl.class);
	
	private static final BigDecimal ZERO_NUM = new BigDecimal("0.00");
	
	@Override
	public MallPvuvDailyStat selectMallPvuvDailyStatById(Integer id) {
		logger.debug("MallPvuvDailyStatDaoImpl.selectMallPvuvDailyStatById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		
		MallPvuvDailyStat mallPvuvDailyStat = (MallPvuvDailyStat) getById(MallPvuvDailyStat.class.getSimpleName()+ ".selectMallPvuvDailyStatById"
				, id);
		
		logger.debug("MallPvuvDailyStatDaoImpl.selectMallPvuvDailyStatById(Integer) success: mallPvuvDailyStat={}, costTime={}ms",
				new Object[] { mallPvuvDailyStat.toString(), System.currentTimeMillis() - stime });
		return mallPvuvDailyStat;
	}

	@Override
	public List<MallPvuvDailyStat> selectAllByExample(Map<String, Object> params) {
		logger.debug("MallPvuvDailyStatDaoImpl.selectAllByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<MallPvuvDailyStat> mallPvuvDailyStats = findAllObjectsByPage(MallPvuvDailyStat.class.getSimpleName() + ".selectAllByExample", params);
		
		logger.debug("MallPvuvDailyStatDaoImpl.selectAllByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return mallPvuvDailyStats;
	}

	@Override
	public List<MallPvuvDailyStat> selectMallPvuvDailyStatByPage(
			Map<String, Object> params) {
		logger.debug("MallPvuvDailyStatDaoImpl.selectMallPvuvDailyStatByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<MallPvuvDailyStat> mallPvuvDailyStats = findAllObjectsByPage(MallPvuvDailyStat.class.getSimpleName() + ".selectMallPvuvDailyStatByPage", params);
		
		logger.debug("MallPvuvDailyStatDaoImpl.selectMallPvuvDailyStatByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return mallPvuvDailyStats;
	}

	@Override
	public Integer selectTotalCountByExample(Map<String, Object> params) {
		logger.debug("MallPvuvDailyStatDaoImpl.selectTotalCountByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(MallPvuvDailyStat.class.getSimpleName() + ".selectTotalCountByExample", params);
		
		logger.debug("MallPvuvDailyStatDaoImpl.selectTotalCountByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectMallDailyBaseStatByExample(
			Map<String, Object> params) {
		logger.debug("MallPvuvDailyStatDaoImpl.selectMallDailyBaseStatByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(MallPvuvDailyStat.class.getSimpleName() + ".selectMallDailyBaseStatByExample", params);
		
		if(CollectionUtils.isNotEmpty(list)) {
			for(Map<String, Object> data : list) {
				BigDecimal pv = (BigDecimal) data.get("pv");
				BigDecimal uv = (BigDecimal) data.get("uv");
				BigDecimal orderNum = (BigDecimal) data.get("orderNum");
				BigDecimal payNum = (BigDecimal) data.get("payNum");
				
				BigDecimal visitDepth = ZERO_NUM;
				BigDecimal payRate = ZERO_NUM;
				BigDecimal orderRate = ZERO_NUM;
				
				if(uv.compareTo(BigDecimal.ZERO) == 1) {
					visitDepth = pv.divide(uv, 5, BigDecimal.ROUND_HALF_UP);
					orderRate = orderNum.divide(uv, 5, BigDecimal.ROUND_HALF_UP);
				} 
				
				if(orderNum.compareTo(BigDecimal.ZERO) == 1) {
					payRate = payNum.divide(orderNum, 5, BigDecimal.ROUND_HALF_UP);
				}
				
				data.put("visitDepth", visitDepth);
				data.put("payRate", payRate);
				data.put("orderRate", orderRate);
			}
		}
		
		logger.debug("MallPvuvDailyStatDaoImpl.selectMallDailyBaseStatByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectMallDailyBaseStatByPage(
			Map<String, Object> params) {
		logger.debug("MallPvuvDailyStatDaoImpl.selectMallDailyBaseStatByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(MallPvuvDailyStat.class.getSimpleName() + ".selectMallDailyBaseStatByPage", params);
		
		if(CollectionUtils.isNotEmpty(list)) {
			for(Map<String, Object> data : list) {
				BigDecimal pv = (BigDecimal) data.get("pv");
				BigDecimal uv = (BigDecimal) data.get("uv");
				BigDecimal orderNum = (BigDecimal) data.get("orderNum");
				BigDecimal payNum = (BigDecimal) data.get("payNum");
				
				BigDecimal visitDepth = ZERO_NUM;
				BigDecimal payRate = ZERO_NUM;
				BigDecimal orderRate = ZERO_NUM;
				
				if(uv.compareTo(BigDecimal.ZERO) == 1) {
					visitDepth = pv.divide(uv, 5, BigDecimal.ROUND_HALF_UP);
					orderRate = orderNum.divide(uv, 5, BigDecimal.ROUND_HALF_UP);
				} 
				
				if(orderNum.compareTo(BigDecimal.ZERO) == 1) {
					payRate = payNum.divide(orderNum, 5, BigDecimal.ROUND_HALF_UP);
				}
				
				data.put("visitDepth", visitDepth);
				data.put("payRate", payRate);
				data.put("orderRate", orderRate);
			}
		}
		
		logger.debug("MallPvuvDailyStatDaoImpl.selectMallDailyBaseStatByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectMallDailyBaseStatCount(Map<String, Object> params) {
		logger.debug("MallPvuvDailyStatDaoImpl.selectMallDailyBaseStatCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(MallPvuvDailyStat.class.getSimpleName() + ".selectMallDailyBaseStatCount", params);
		
		logger.debug("MallPvuvDailyStatDaoImpl.selectMallDailyBaseStatCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectWjqDailyBaseStatByExample(
			Map<String, Object> params) {
		logger.debug("MallPvuvDailyStatDaoImpl.selectWjqDailyBaseStatByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(MallPvuvDailyStat.class.getSimpleName() + ".selectWjqDailyBaseStatByExample", params);
		
		if(CollectionUtils.isNotEmpty(list)) {
			for(Map<String, Object> data : list) {
				BigDecimal pv = (BigDecimal) data.get("pv");
				BigDecimal uv = (BigDecimal) data.get("uv");
				
				BigDecimal visitDepth = ZERO_NUM;
				
				if(uv.compareTo(BigDecimal.ZERO) == 1) {
					visitDepth = pv.divide(uv, 5, BigDecimal.ROUND_HALF_UP);
				} 
				
				data.put("visitDepth", visitDepth);
			}
		}
		
		logger.debug("MallPvuvDailyStatDaoImpl.selectWjqDailyBaseStatByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectWjqDailyBaseStatByPage(
			Map<String, Object> params) {
		logger.debug("MallPvuvDailyStatDaoImpl.selectWjqDailyBaseStatByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(MallPvuvDailyStat.class.getSimpleName() + ".selectWjqDailyBaseStatByPage", params);
		
		if(CollectionUtils.isNotEmpty(list)) {
			for(Map<String, Object> data : list) {
				BigDecimal pv = (BigDecimal) data.get("pv");
				BigDecimal uv = (BigDecimal) data.get("uv");
				
				BigDecimal visitDepth = ZERO_NUM;
				
				if(uv.compareTo(BigDecimal.ZERO) == 1) {
					visitDepth = pv.divide(uv, 5, BigDecimal.ROUND_HALF_UP);
				} 
				
				data.put("visitDepth", visitDepth);
			}
		}
		
		logger.debug("MallPvuvDailyStatDaoImpl.selectWjqDailyBaseStatByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectWjqDailyBaseStatCount(Map<String, Object> params) {
		logger.debug("MallPvuvDailyStatDaoImpl.selectWjqDailyBaseStatCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(MallPvuvDailyStat.class.getSimpleName() + ".selectWjqDailyBaseStatCount", params);
		
		logger.debug("MallPvuvDailyStatDaoImpl.selectWjqDailyBaseStatCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectWjqMonthlyBaseStatByExample(
			Map<String, Object> params) {
		logger.debug("MallPvuvDailyStatDaoImpl.selectWjqMonthlyBaseStatByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(MallPvuvDailyStat.class.getSimpleName() + ".selectWjqMonthlyBaseStatByExample", params);
		
		if(CollectionUtils.isNotEmpty(list)) {
			for(Map<String, Object> data : list) {
				BigDecimal pv = (BigDecimal) data.get("pv");
				BigDecimal uv = (BigDecimal) data.get("uv");
				
				BigDecimal visitDepth = ZERO_NUM;
				
				if(uv.compareTo(BigDecimal.ZERO) == 1) {
					visitDepth = pv.divide(uv, 5, BigDecimal.ROUND_HALF_UP);
				} 
				
				data.put("visitDepth", visitDepth);
			}
		}
		
		logger.debug("MallPvuvDailyStatDaoImpl.selectWjqMonthlyBaseStatByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectWjqMonthlyBaseStatByPage(
			Map<String, Object> params) {
		logger.debug("MallPvuvDailyStatDaoImpl.selectWjqMonthlyBaseStatByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(MallPvuvDailyStat.class.getSimpleName() + ".selectWjqMonthlyBaseStatByPage", params);
		
		if(CollectionUtils.isNotEmpty(list)) {
			for(Map<String, Object> data : list) {
				BigDecimal pv = (BigDecimal) data.get("pv");
				BigDecimal uv = (BigDecimal) data.get("uv");
				
				BigDecimal visitDepth = ZERO_NUM;
				
				if(uv.compareTo(BigDecimal.ZERO) == 1) {
					visitDepth = pv.divide(uv, 5, BigDecimal.ROUND_HALF_UP);
				} 
				
				data.put("visitDepth", visitDepth);
			}
		}
		
		logger.debug("MallPvuvDailyStatDaoImpl.selectWjqMonthlyBaseStatByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectWjqMonthlyBaseStatCount(Map<String, Object> params) {
		logger.debug("MallPvuvDailyStatDaoImpl.selectWjqMonthlyBaseStatCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(MallPvuvDailyStat.class.getSimpleName() + ".selectWjqMonthlyBaseStatCount", params);
		
		logger.debug("MallPvuvDailyStatDaoImpl.selectWjqMonthlyBaseStatCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Integer insertMallPvuvDailyStat(MallPvuvDailyStat mallPvuvDailyStat) {
		logger.debug("MallPvuvDailyStatDaoImpl.insertMallPvuvDailyStat(StatScheme) entry: mallPvuvDailyStat={}",
				new Object[] { mallPvuvDailyStat.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) insert(mallPvuvDailyStat, MallPvuvDailyStat.class.getSimpleName() + ".insertMallPvuvDailyStat");
		
		logger.debug("MallPvuvDailyStatDaoImpl.insertMallPvuvDailyStat(StatScheme) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer deleteMallPvuvDailyStatById(Integer id) {
		logger.debug("MallPvuvDailyStatDaoImpl.deleteMallPvuvDailyStatById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, MallPvuvDailyStat.class.getSimpleName() + ".deleteMallPvuvDailyStatById");
		
		logger.debug("MallPvuvDailyStatDaoImpl.deleteMallPvuvDailyStatById(Integer) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

}
