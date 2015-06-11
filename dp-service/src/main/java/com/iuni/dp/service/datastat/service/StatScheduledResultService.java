package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.StatScheduledResult;

/**
 * 固定时间统一调度统计分析结果Service
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public interface StatScheduledResultService {

	/**
	 * 根据ID查询固定时间统一调度统计分析结果
	 * @param Integer id
	 * @return StatScheduledResult
	 */
	public StatScheduledResult getStatScheduledResultById(Integer id);
	
	/**
	 * 固定时间统一调度统计分析结果条件查询
	 * @param Map<String, Object> params
	 * @return List<StatScheduledResult>
	 */
	public List<StatScheduledResult> getAllStatScheduledResult(Map<String, Object> params);
	
	/**
	 * 固定时间统一调度统计分析结果临时表条件查询
	 * @param Map<String, Object> params
	 * @return List<StatScheduledResult>
	 */
	public List<StatScheduledResult> getAllStatScheduledResultTemp(Map<String, Object> params);
	
	/**
	 * 根据条件获取固定时间统一调度统计分析结果
	 * @param Map<String, Object> params
	 * @return List<StatScheduledResult>
	 */
	public List<StatScheduledResult> fetchStatScheduledResultByCondition(Map<String, Object> params);
	
	/**
	 * 固定时间统一调度统计分析结果分页条件查询
	 * @param Map<String, Object> params
	 * @return List<StatScheduledResult>
	 */
	public List<StatScheduledResult> getStatScheduledResultByPage(Map<String, Object> params);
	
	/**
	 * 固定时间统一调度统计分析结果总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer getTotalCount(Map<String, Object> params);
	
	/**
	 * 新增固定时间统一调度统计分析结果
	 * @param StatScheduledResult statScheduledResult
	 * @return Integer
	 */
	public Integer saveStatScheduledResult(StatScheduledResult statScheduledResult);
	
	/**
	 * 删除固定时间统一调度统计分析结果
	 * @param Integer
	 */
	public Integer removeStatScheduledResultById(Integer id);
	
	/**
	 * 按条件删除固定时间统一调度统计分析结果临时表数据
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer removeStatScdResultTempByCondition(Map<String, Object> params);
	
}
