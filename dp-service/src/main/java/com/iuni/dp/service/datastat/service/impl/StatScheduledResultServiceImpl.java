package com.iuni.dp.service.datastat.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.iuni.dp.persist.datastat.dao.StatScheduledResultDao;
import com.iuni.dp.persist.datastat.dao.StatSchemeDao;
import com.iuni.dp.persist.datastat.model.StatScheduledResult;
import com.iuni.dp.persist.datastat.model.StatScheme;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.constants.PfRptDataStatField;
import com.iuni.dp.service.datastat.constants.RptDataContentType;
import com.iuni.dp.service.datastat.service.StatScheduledResultService;

/**
 * 固定时间统一调度统计分析结果Service业务类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("statScheduledResultService")
public class StatScheduledResultServiceImpl implements StatScheduledResultService {

	private final static Logger logger = LoggerFactory.getLogger(StatScheduledResultServiceImpl.class);
	
	private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private StatScheduledResultDao statScheduledResultDao;
	
	@Autowired
	private StatSchemeDao statSchemeDao;
	
	@Override
	public StatScheduledResult getStatScheduledResultById(Integer id) {
		StatScheduledResult statScheduledResult = null;
		
		try {
			statScheduledResult = statScheduledResultDao.selectStatScheduledResultById(id);
			logger.debug("StatScheduledResultServiceImpl.getStatScheduledResultById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatScheduledResultServiceImpl.getStatScheduledResultById found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return statScheduledResult;
	}
	
	@Override
	public List<StatScheduledResult> getAllStatScheduledResult(
			Map<String, Object> params) {
		List<StatScheduledResult> statScheduledResults = null;
		
		try {
			statScheduledResults = statScheduledResultDao.selectAllStatScheduledResult(params);
			logger.debug("StatScheduledResultServiceImpl.getAllStatScheduledResult invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatScheduledResultServiceImpl.getAllStatScheduledResult found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return statScheduledResults;
	}

	@Override
	public List<StatScheduledResult> getAllStatScheduledResultTemp(
			Map<String, Object> params) {
		List<StatScheduledResult> statScheduledResults = null;
		
		try {
			statScheduledResults = statScheduledResultDao.selectAllStatScheduledResultTemp(params);
			logger.debug("StatScheduledResultServiceImpl.getAllStatScheduledResultTemp invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatScheduledResultServiceImpl.getAllStatScheduledResultTemp found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return statScheduledResults;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 30)
	@Override
	public List<StatScheduledResult> fetchStatScheduledResultByCondition(
			Map<String, Object> params) {
		List<StatScheduledResult> statScheduledResults = null;
		
		// 解析params
		String statSchemeId = (String) params.get("statSchemeId");
		String sourceId = (String) params.get("sourceId");
		String scheduledTime = (String) params.get("scheduledTime");
		
		// 解析params关联StatScheme
		StatScheme statScheme = statSchemeDao.selectStatSchemeById(Integer.valueOf(statSchemeId));
		String rptDatatype = statScheme.getRptDataType();
		String statField = statScheme.getStatField();
		
		// 获取当前日期
		String curTime = dateFormat.format(new Date());
		
		if(curTime.equals(scheduledTime)) {
			// 处理当天查询PF类型上报数据ResponseEnd维度统计分析结果
			if(RptDataContentType.PF.getValue().equals(rptDatatype) 
					&& PfRptDataStatField.ResponseEnd.getValue().equals(statField)) {
				Map<String, Object> procParams = new HashMap<String, Object>();
				procParams.put("STAT_SCHEME_ID", statScheme.getId());
				procParams.put("STAT_SOURCE_ID", sourceId);
				procParams.put("STAT_DATE",curTime);
				// 10：获取上一天的正式统计数据; 11：获取当天的临时统计数据
				procParams.put("STAT_FLAG", 11);
				procParams.put("EXEC_STATUS", null);
				
				try {
					// 调用相关存储过程计算当天的临时统计数据
					statSchemeDao.callProcStatPfResponse(procParams);
					
					// 固定时间统一调度统计分析结果临时表条件查询
					statScheduledResults = getAllStatScheduledResultTemp(params);
					
					Map<String, Object> delParams = new HashMap<String, Object>();
					delParams.put("sourceId", sourceId);
					delParams.put("statSchemeId", statScheme.getId());
					
					statScheduledResultDao.delStatScdResultTempByCondition(delParams);
					
					logger.debug("StatScheduledResultServiceImpl.fetchStatScheduledResultByCondition calc current Date Data invoke success");
					
				} catch(DataAccessException daex) {
					logger.error("StatScheduledResultServiceImpl.fetchStatScheduledResultByCondition found DataAccessException", daex);
					throw new DBException(daex);
				}
			}
		} else {
			statScheduledResults = getAllStatScheduledResult(params);
		}
		
		return statScheduledResults;
	}

	@Override
	public List<StatScheduledResult> getStatScheduledResultByPage(
			Map<String, Object> params) {
		List<StatScheduledResult> statScheduledResults = null;
		
		try {
			statScheduledResults = statScheduledResultDao.selectStatScheduledResultByPage(params);
			logger.debug("StatScheduledResultServiceImpl.getStatScheduledResultByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatScheduledResultServiceImpl.getStatScheduledResultByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return statScheduledResults;
	}

	@Override
	public Integer getTotalCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = statScheduledResultDao.selectTotalCount(params);
			logger.debug("StatScheduledResultServiceImpl.getTotalCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatScheduledResultServiceImpl.getTotalCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Integer saveStatScheduledResult(StatScheduledResult statScheduledResult) {
		Integer execCount = 0;
		
		try {
			execCount = statScheduledResultDao.insertStatScheduledResult(statScheduledResult);
			logger.debug("StatScheduledResultServiceImpl.saveStatScheduledResult invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatScheduledResultServiceImpl.saveStatScheduledResult found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeStatScheduledResultById(Integer id) {
		Integer execCount = 0;
		
		try {
			execCount = statScheduledResultDao.deleteStatScheduledResultById(id);
			logger.debug("StatScheduledResultServiceImpl.removeStatScheduledResultById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatScheduledResultServiceImpl.removeStatScheduledResultById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeStatScdResultTempByCondition(Map<String, Object> params) {
		Integer execCount = 0;
		
		try {
			execCount = statScheduledResultDao.delStatScdResultTempByCondition(params);
			logger.debug("StatScheduledResultServiceImpl.removeStatScdResultTempByCondition invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatScheduledResultServiceImpl.removeStatScdResultTempByCondition found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

}
