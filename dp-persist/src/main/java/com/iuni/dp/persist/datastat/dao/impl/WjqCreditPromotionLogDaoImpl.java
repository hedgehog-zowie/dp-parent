package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.WjqCreditPromotionLogDao;
import com.iuni.dp.persist.datastat.model.WjqCreditPromotionLog;

/**
 * 玩机圈用户推广日志DAO实现类
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
@Repository("wjqCreditPromotionLogDao")
public class WjqCreditPromotionLogDaoImpl extends
		BaseDaoImpl<WjqCreditPromotionLog, Serializable> implements
		WjqCreditPromotionLogDao {

	private static final Logger logger = LoggerFactory.getLogger(WjqCreditPromotionLogDaoImpl.class);
	
	@Override
	public WjqCreditPromotionLog selectWjqCreditPromotionLogById(Integer id) {
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqCreditPromotionLogById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		
		WjqCreditPromotionLog wjqCreditPromotionLog = (WjqCreditPromotionLog) getById(WjqCreditPromotionLog.class.getSimpleName() 
				+ ".selectWjqCreditPromotionLogById", id);
		
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqCreditPromotionLogById(Integer) success: WjqCreditPromotionLog={}, costTime={}ms",
				new Object[] { wjqCreditPromotionLog.toString(), System.currentTimeMillis() - stime });
		return wjqCreditPromotionLog;
	}

	@Override
	public List<WjqCreditPromotionLog> selectWjqCreditPromotionLogByExample(
			Map<String, Object> params) {
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqCreditPromotionLogByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<WjqCreditPromotionLog> wjqCreditPromotionLogs = findAllObjectsByPage(WjqCreditPromotionLog.class.getSimpleName() 
				+ ".selectWjqCreditPromotionLogByExample", params);
		
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqCreditPromotionLogByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return wjqCreditPromotionLogs;
	}

	@Override
	public List<WjqCreditPromotionLog> selectWjqCreditPromotionLogByPage(
			Map<String, Object> params) {
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqCreditPromotionLogByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<WjqCreditPromotionLog> wjqCreditPromotionLogs = findAllObjectsByPage(WjqCreditPromotionLog.class.getSimpleName() 
				+ ".selectWjqCreditPromotionLogByPage", params);
		
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqCreditPromotionLogByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return wjqCreditPromotionLogs;
	}

	@Override
	public Integer selectWjqCreditPromotionLogCount(Map<String, Object> params) {
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqCreditPromotionLogCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(WjqCreditPromotionLog.class.getSimpleName() 
				+ ".selectWjqCreditPromotionLogCount", params);
		
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqCreditPromotionLogCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectWjqUserPromotionByExample(
			Map<String, Object> params) {
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqUserPromotionByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> wjqUserPromotions = findAllObjectsByPage2(WjqCreditPromotionLog.class.getSimpleName() 
				+ ".selectWjqUserPromotionByExample", params);
		
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqUserPromotionByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return wjqUserPromotions;
	}

	@Override
	public List<Map<String, Object>> selectWjqUserPromotionByPage(
			Map<String, Object> params) {
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqUserPromotionByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> wjqUserPromotions = findAllObjectsByPage2(WjqCreditPromotionLog.class.getSimpleName() 
				+ ".selectWjqUserPromotionByPage", params);
		
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqUserPromotionByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return wjqUserPromotions;
	}

	@Override
	public Integer selectWjqUserPromotionCount(Map<String, Object> params) {
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqUserPromotionCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(WjqCreditPromotionLog.class.getSimpleName() 
				+ ".selectWjqUserPromotionCount", params);
		
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqUserPromotionCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectWjqPostsPromotionByExample(
			Map<String, Object> params) {
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqPostsPromotionByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> wjqPostsPromotions = findAllObjectsByPage2(WjqCreditPromotionLog.class.getSimpleName() 
				+ ".selectWjqPostsPromotionByExample", params);
		
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqPostsPromotionByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return wjqPostsPromotions;
	}

	@Override
	public List<Map<String, Object>> selectWjqPostsPromotionByPage(
			Map<String, Object> params) {
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqPostsPromotionByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> wjqPostsPromotions = findAllObjectsByPage2(WjqCreditPromotionLog.class.getSimpleName() 
				+ ".selectWjqPostsPromotionByPage", params);
		
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqPostsPromotionByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return wjqPostsPromotions;
	}

	@Override
	public Integer selectWjqPostsPromotionCount(Map<String, Object> params) {
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqPostsPromotionCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(WjqCreditPromotionLog.class.getSimpleName() 
				+ ".selectWjqPostsPromotionCount", params);
		
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqPostsPromotionCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectWjqUserPromotionPreview(
			Map<String, Object> params) {
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqUserPromotionPreview(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(WjqCreditPromotionLog.class.getSimpleName() 
				+ ".selectWjqUserPromotionPreview", params);
		
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqUserPromotionPreview(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectWjqUserPromotionSumByExample(
			Map<String, Object> params) {
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqUserPromotionSumByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(WjqCreditPromotionLog.class.getSimpleName() 
				+ ".selectWjqUserPromotionSumByExample", params);
		
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqUserPromotionSumByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectWjqUserPromotionSumByPage(
			Map<String, Object> params) {
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqUserPromotionSumByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(WjqCreditPromotionLog.class.getSimpleName() 
				+ ".selectWjqUserPromotionSumByPage", params);
		
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqUserPromotionSumByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectWjqUserPromotionSumCount(Map<String, Object> params) {
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqUserPromotionSumCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(WjqCreditPromotionLog.class.getSimpleName() 
				+ ".selectWjqUserPromotionSumCount", params);
		
		logger.debug("WjqCreditPromotionLogDaoImpl.selectWjqUserPromotionSumCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Integer insertWjqCreditPromotionLog(
			WjqCreditPromotionLog wjqCreditPromotionLog) {
		logger.debug("WjqCreditPromotionLogDaoImpl.insertWjqCreditPromotionLog(WjqCreditPromotionLog) entry: WjqCreditPromotionLog={}",
				new Object[] { wjqCreditPromotionLog.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) insert(wjqCreditPromotionLog, WjqCreditPromotionLog.class.getSimpleName() 
				+ ".insertWjqCreditPromotionLog");
		
		logger.debug("WjqCreditPromotionLogDaoImpl.insertWjqCreditPromotionLog(WjqCreditPromotionLog) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer deleteWjqCreditPromotionLogById(Integer id) {
		logger.debug("WjqCreditPromotionLogDaoImpl.deleteWjqCreditPromotionLogById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, WjqCreditPromotionLog.class.getSimpleName() + ".deleteWjqCreditPromotionLogById");
		
		logger.debug("WjqCreditPromotionLogDaoImpl.deleteWjqCreditPromotionLogById(Integer) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

}
