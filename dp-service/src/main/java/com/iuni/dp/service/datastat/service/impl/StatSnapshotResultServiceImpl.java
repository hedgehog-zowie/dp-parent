package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.StatSnapshotResultDao;
import com.iuni.dp.persist.datastat.model.StatSnapshotResult;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.StatSnapshotResultService;

/**
 * 固定间隔时间快照统计分析结果Service业务类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("statSnapshotResultService")
public class StatSnapshotResultServiceImpl implements StatSnapshotResultService {

	private final static Logger logger = LoggerFactory.getLogger(StatSnapshotResultServiceImpl.class);
	
	@Autowired
	private StatSnapshotResultDao statSnapshotResultDao;
	
	@Override
	public StatSnapshotResult getStatSnapshotResultById(Integer id) {
		StatSnapshotResult statSnapshotResult = null;
		
		try {
			statSnapshotResult = statSnapshotResultDao.selectStatSnapshotResultById(id);
			logger.debug("StatSnapshotResultServiceImpl.getStatSnapshotResultById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSnapshotResultServiceImpl.getStatSnapshotResultById found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return statSnapshotResult;
	}

	@Override
	public List<StatSnapshotResult> getAllStatSnapshotResult(
			Map<String, Object> params) {
		List<StatSnapshotResult> statSnapshotResults = null;
		
		try {
			statSnapshotResults = statSnapshotResultDao.selectAllStatSnapshotResult(params);
			logger.debug("StatSnapshotResultServiceImpl.getAllStatSnapshotResult invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSnapshotResultServiceImpl.getAllStatSnapshotResult found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return statSnapshotResults;
	}

	@Override
	public List<StatSnapshotResult> getStatSnapshotResultByPage(
			Map<String, Object> params) {
		List<StatSnapshotResult> statSnapshotResults = null;
		
		try {
			statSnapshotResults = statSnapshotResultDao.selectStatSnapshotResultByPage(params);
			logger.debug("StatSnapshotResultServiceImpl.getStatSnapshotResultByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSnapshotResultServiceImpl.getStatSnapshotResultByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return statSnapshotResults;
	}

	@Override
	public Integer getTotalCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = statSnapshotResultDao.selectTotalCount(params);
			logger.debug("StatSnapshotResultServiceImpl.getTotalCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSnapshotResultServiceImpl.getTotalCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Integer saveStatSnapshotResult(StatSnapshotResult statSnapshotResult) {
		Integer execCount = 0;
		
		try {
			execCount = statSnapshotResultDao.insertStatSnapshotResult(statSnapshotResult);
			logger.debug("StatSnapshotResultServiceImpl.saveStatSnapshotResult invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSnapshotResultServiceImpl.saveStatSnapshotResult found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeStatSnapshotResultById(Integer id) {
		Integer execCount = 0;
		
		try {
			execCount = statSnapshotResultDao.deleteStatSnapshotResultById(id);
			logger.debug("StatSnapshotResultServiceImpl.removeStatSnapshotResultById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSnapshotResultServiceImpl.removeStatSnapshotResultById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

}
