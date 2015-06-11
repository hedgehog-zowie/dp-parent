package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.StatSnapshotResultDao;
import com.iuni.dp.persist.datastat.model.StatSnapshotResult;

/**
 * 固定间隔时间快照统计分析结果DAO实现类
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
@Repository("statSnapshotResultDao")
public class StatSnapshotResultDaoImpl extends BaseDaoImpl<StatSnapshotResult, Serializable> implements StatSnapshotResultDao {

	private static final Logger logger = LoggerFactory.getLogger(StatSnapshotResultDaoImpl.class);
	
	@Override
	public StatSnapshotResult selectStatSnapshotResultById(Integer id) {
		logger.debug("StatSnapshotResultDaoImpl.selectStatSnapshotResultById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		
		StatSnapshotResult statSnapshotResult = (StatSnapshotResult) getById(StatSnapshotResult.class.getSimpleName() 
				+ ".selectStatSnapshotResultById", id);
		
		logger.debug("StatSnapshotResultDaoImpl.selectStatSnapshotResultById(Integer) success: statSnapshotResult={}, costTime={}ms",
				new Object[] { statSnapshotResult.toString(), System.currentTimeMillis() - stime });
		return statSnapshotResult;
	}

	@Override
	public List<StatSnapshotResult> selectAllStatSnapshotResult(
			Map<String, Object> params) {
		logger.debug("StatSnapshotResultDaoImpl.selectAllStatSnapshotResult(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<StatSnapshotResult> statSnapshotResults = findAllObjectsByPage(StatSnapshotResult.class.getSimpleName() 
				+ ".selectAllStatSnapshotResult", params);
		
		logger.debug("StatSnapshotResultDaoImpl.selectAllStatSnapshotResult(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return statSnapshotResults;
	}

	@Override
	public List<StatSnapshotResult> selectStatSnapshotResultByPage(
			Map<String, Object> params) {
		logger.debug("StatSnapshotResultDaoImpl.selectStatSnapshotResultByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<StatSnapshotResult> statSnapshotResults = findAllObjectsByPage(StatSnapshotResult.class.getSimpleName() 
				+ ".selectStatSnapshotResultByPage", params);
		
		logger.debug("StatSnapshotResultDaoImpl.selectStatSnapshotResultByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return statSnapshotResults;
	}

	@Override
	public Integer selectTotalCount(Map<String, Object> params) {
		logger.debug("StatSnapshotResultDaoImpl.selectTotalCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(StatSnapshotResult.class.getSimpleName() + ".selectTotalCount", params);
		
		logger.debug("StatSnapshotResultDaoImpl.selectTotalCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Integer insertStatSnapshotResult(StatSnapshotResult statSnapshotResult) {
		logger.debug("StatSnapshotResultDaoImpl.insertStatSnapshotResult(StatSnapshotResult) entry: statSnapshotResult={}",
				new Object[] { statSnapshotResult.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) insert(statSnapshotResult, StatSnapshotResult.class.getSimpleName() + ".insertStatSnapshotResult");
		
		logger.debug("StatSnapshotResultDaoImpl.insertStatSnapshotResult(StatSnapshotResult) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer deleteStatSnapshotResultById(Integer id) {
		logger.debug("StatSnapshotResultDaoImpl.deleteStatSnapshotResultById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, StatSnapshotResult.class.getSimpleName() + ".deleteStatSnapshotResultById");
		
		logger.debug("StatSnapshotResultDaoImpl.deleteStatSnapshotResultById(Integer) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

}
