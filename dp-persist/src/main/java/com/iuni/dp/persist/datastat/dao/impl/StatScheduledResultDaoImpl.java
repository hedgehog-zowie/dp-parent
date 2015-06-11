package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.StatScheduledResultDao;
import com.iuni.dp.persist.datastat.model.StatScheduledResult;

/**
 * 固定时间统一调度统计分析结果DAO实现类
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
@Repository("statScheduledResultDao")
public class StatScheduledResultDaoImpl extends BaseDaoImpl<StatScheduledResult, Serializable> implements StatScheduledResultDao {

	private static final Logger logger = LoggerFactory.getLogger(StatScheduledResultDaoImpl.class);
	
	@Override
	public StatScheduledResult selectStatScheduledResultById(Integer id) {
		logger.debug("StatScheduledResultDaoImpl.selectStatScheduledResultById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		
		StatScheduledResult statScheduledResult = (StatScheduledResult) getById(StatScheduledResult.class.getSimpleName() 
				+ ".selectStatScheduledResultById", id);
		
		logger.debug("StatScheduledResultDaoImpl.selectStatScheduledResultById(Integer) success: statScheduledResult={}, costTime={}ms",
				new Object[] { statScheduledResult.toString(), System.currentTimeMillis() - stime });
		return statScheduledResult;
	}

	@Override
	public List<StatScheduledResult> selectAllStatScheduledResult(
			Map<String, Object> params) {
		logger.debug("StatScheduledResultDaoImpl.selectAllStatScheduledResult(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<StatScheduledResult> statScheduledResults = findAllObjectsByPage(StatScheduledResult.class.getSimpleName() 
				+ ".selectAllStatScheduledResult", params);
		
		logger.debug("StatScheduledResultDaoImpl.selectAllStatScheduledResult(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return statScheduledResults;
	}

	@Override
	public List<StatScheduledResult> selectAllStatScheduledResultTemp(
			Map<String, Object> params) {
		logger.debug("StatScheduledResultDaoImpl.selectAllStatScheduledResultTemp(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<StatScheduledResult> statScheduledResults = findAllObjectsByPage(StatScheduledResult.class.getSimpleName() 
				+ ".selectAllStatScheduledResultTemp", params);
		
		logger.debug("StatScheduledResultDaoImpl.selectAllStatScheduledResultTemp(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return statScheduledResults;
	}

	@Override
	public List<StatScheduledResult> selectStatScheduledResultByPage(
			Map<String, Object> params) {
		logger.debug("StatScheduledResultDaoImpl.selectStatScheduledResultByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<StatScheduledResult> statScheduledResults = findAllObjectsByPage(StatScheduledResult.class.getSimpleName() 
				+ ".selectStatScheduledResultByPage", params);
		
		logger.debug("StatScheduledResultDaoImpl.selectStatScheduledResultByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return statScheduledResults;
	}

	@Override
	public Integer selectTotalCount(Map<String, Object> params) {
		logger.debug("StatScheduledResultDaoImpl.selectTotalCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(StatScheduledResult.class.getSimpleName() + ".selectTotalCount", params);
		
		logger.debug("StatScheduledResultDaoImpl.selectTotalCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Integer insertStatScheduledResult(
			StatScheduledResult statScheduledResult) {
		logger.debug("StatScheduledResultDaoImpl.insertStatScheduledResult(StatScheduledResult) entry: statScheduledResult={}",
				new Object[] { statScheduledResult.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) insert(statScheduledResult, StatScheduledResult.class.getSimpleName() + ".insertStatScheduledResult");
		
		logger.debug("StatScheduledResultDaoImpl.insertStatScheduledResult(StatScheduledResult) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer deleteStatScheduledResultById(Integer id) {
		logger.debug("StatScheduledResultDaoImpl.deleteStatScheduledResultById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, StatScheduledResult.class.getSimpleName() + ".deleteStatScheduledResultById");
		
		logger.debug("StatScheduledResultDaoImpl.deleteStatScheduledResultById(Integer) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

	@Override
	public Integer delStatScdResultTempByCondition(Map<String, Object> params) {
		logger.debug("StatScheduledResultDaoImpl.delStatScdResultTempByCondition(Map<String, Object>) invoke");
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(params, StatScheduledResult.class.getSimpleName() + ".delStatScdResultTempByCondition");
		
		logger.debug("StatScheduledResultDaoImpl.delStatScdResultTempByCondition(Map<String, Object>) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

}
