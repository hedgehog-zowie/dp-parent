package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.MallGoodsPvuvDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallGoodsPvuvDailyStat;

/**
 * 商城商品PV/UV基础数据日统计表DAO实现类
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
@Repository("mallGoodsPvuvDailyStatDao")
public class MallGoodsPvuvDailyStatDaoImpl extends BaseDaoImpl<MallGoodsPvuvDailyStat, Serializable> implements MallGoodsPvuvDailyStatDao {

	private static final Logger logger = LoggerFactory.getLogger(MallGoodsPvuvDailyStatDaoImpl.class);
	
	private static final BigDecimal ZERO_NUM = new BigDecimal("0.00");
	
	@Override
	public MallGoodsPvuvDailyStat selectMallGoodsPvuvDailyStatById(Integer id) {
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.selectMallGoodsPvuvDailyStatById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		
		MallGoodsPvuvDailyStat mallGoodsPvuvDailyStat = (MallGoodsPvuvDailyStat) getById(MallGoodsPvuvDailyStat.class.getSimpleName()+ ".selectMallGoodsPvuvDailyStatById"
				, id);
		
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.selectMallGoodsPvuvDailyStatById(Integer) success: MallGoodsPvuvDailyStat={}, costTime={}ms",
				new Object[] { mallGoodsPvuvDailyStat.toString(), System.currentTimeMillis() - stime });
		return mallGoodsPvuvDailyStat;
	}

	@Override
	public List<MallGoodsPvuvDailyStat> selectAllByExample(
			Map<String, Object> params) {
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.selectAllByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<MallGoodsPvuvDailyStat> mallGoodsPvuvDailyStats = findAllObjectsByPage(MallGoodsPvuvDailyStat.class.getSimpleName() + ".selectAllByExample", params);
		
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.selectAllByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return mallGoodsPvuvDailyStats;
	}

	@Override
	public List<MallGoodsPvuvDailyStat> selectMallGoodsPvuvDailyStatByPage(
			Map<String, Object> params) {
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.selectMallGoodsPvuvDailyStatByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<MallGoodsPvuvDailyStat> mallGoodsPvuvDailyStats = findAllObjectsByPage(MallGoodsPvuvDailyStat.class.getSimpleName() + ".selectMallGoodsPvuvDailyStatByPage", params);
		
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.selectMallGoodsPvuvDailyStatByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return mallGoodsPvuvDailyStats;
	}

	@Override
	public Integer selectTotalCountByExample(Map<String, Object> params) {
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.selectTotalCountByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(MallGoodsPvuvDailyStat.class.getSimpleName() + ".selectTotalCountByExample", params);
		
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.selectTotalCountByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectMallGoodsDailyBaseStatByExample(
			Map<String, Object> params) {
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.selectMallGoodsDailyBaseStatByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(MallGoodsPvuvDailyStat.class.getSimpleName() + ".selectMallGoodsDailyBaseStatByExample", params);
		
		for(Map<String, Object> data : list) {
//			BigDecimal pv = (BigDecimal) data.get("pv");
			BigDecimal uv = (BigDecimal) data.get("uv");
			BigDecimal orderNum = (BigDecimal) data.get("orderNum");
			BigDecimal payNum = (BigDecimal) data.get("payNum");
			
			BigDecimal payRate = ZERO_NUM;
			BigDecimal orderRate = ZERO_NUM;
			
			if(uv.compareTo(BigDecimal.ZERO) == 1) {
				orderRate = orderNum.divide(uv, 5, BigDecimal.ROUND_HALF_UP);
			} 
			
			if(orderNum.compareTo(BigDecimal.ZERO) == 1) {
				payRate = payNum.divide(orderNum, 5, BigDecimal.ROUND_HALF_UP);
			}
			
			data.put("payRate", payRate);
			data.put("orderRate", orderRate);
		}
		
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.selectMallGoodsDailyBaseStatByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectMallGoodsDailyBaseStatByPage(
			Map<String, Object> params) {
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.selectMallGoodsDailyBaseStatByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(MallGoodsPvuvDailyStat.class.getSimpleName() + ".selectMallGoodsDailyBaseStatByPage", params);
		
		for(Map<String, Object> data : list) {
//			BigDecimal pv = (BigDecimal) data.get("pv");
			BigDecimal uv = (BigDecimal) data.get("uv");
			BigDecimal orderNum = (BigDecimal) data.get("orderNum");
			BigDecimal payNum = (BigDecimal) data.get("payNum");
			
			BigDecimal payRate = ZERO_NUM;
			BigDecimal orderRate = ZERO_NUM;
			
			if(uv.compareTo(BigDecimal.ZERO) == 1) {
				orderRate = orderNum.divide(uv, 5, BigDecimal.ROUND_HALF_UP);
			} 
			
			if(orderNum.compareTo(BigDecimal.ZERO) == 1) {
				payRate = payNum.divide(orderNum, 5, BigDecimal.ROUND_HALF_UP);
			}
			
			data.put("payRate", payRate);
			data.put("orderRate", orderRate);
		}
		
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.selectMallGoodsDailyBaseStatByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectMallGoodsDailyBaseStatCount(Map<String, Object> params) {
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.selectMallGoodsDailyBaseStatCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(MallGoodsPvuvDailyStat.class.getSimpleName() + ".selectMallGoodsDailyBaseStatCount", params);
		
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.selectMallGoodsDailyBaseStatCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Integer insertMallGoodsPvuvDailyStat(
			MallGoodsPvuvDailyStat mallGoodsPvuvDailyStat) {
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.insertMallGoodsPvuvDailyStat(StatScheme) entry: MallGoodsPvuvDailyStat={}",
				new Object[] { mallGoodsPvuvDailyStat.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) insert(mallGoodsPvuvDailyStat, MallGoodsPvuvDailyStat.class.getSimpleName() + ".insertMallGoodsPvuvDailyStat");
		
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.insertMallGoodsPvuvDailyStat(StatScheme) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer deleteMallGoodsPvuvDailyStatById(Integer id) {
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.deleteMallGoodsPvuvDailyStatById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, MallGoodsPvuvDailyStat.class.getSimpleName() + ".deleteMallGoodsPvuvDailyStatById");
		
		logger.debug("MallGoodsPvuvDailyStatDaoImpl.deleteMallGoodsPvuvDailyStatById(Integer) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

}
