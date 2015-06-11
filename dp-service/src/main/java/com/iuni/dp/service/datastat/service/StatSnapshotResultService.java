package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.StatSnapshotResult;

/**
 * 固定间隔时间快照统计分析结果Service
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public interface StatSnapshotResultService {

	/**
	 * 根据ID查询固定间隔时间快照统计分析结果
	 * @param Integer id
	 * @return StatSnapshotResult
	 */
	public StatSnapshotResult getStatSnapshotResultById(Integer id);
	
	/**
	 * 固定间隔时间快照统计分析结果条件查询
	 * @param Map<String, Object> params
	 * @return List<StatSnapshotResult>
	 */
	public List<StatSnapshotResult> getAllStatSnapshotResult(Map<String, Object> params);
	
	/**
	 * 固定间隔时间快照统计分析结果分页条件查询
	 * @param Map<String, Object> params
	 * @return List<StatSnapshotResult>
	 */
	public List<StatSnapshotResult> getStatSnapshotResultByPage(Map<String, Object> params);
	
	/**
	 * 固定间隔时间快照统计分析结果总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer getTotalCount(Map<String, Object> params);
	
	/**
	 * 新增固定间隔时间快照统计分析结果
	 * @param StatSnapshotResult statSnapshotResult
	 * @return Integer
	 */
	public Integer saveStatSnapshotResult(StatSnapshotResult statSnapshotResult);
	
	/**
	 * 删除固定间隔时间快照统计分析结果
	 * @param Integer id
	 */
	public Integer removeStatSnapshotResultById(Integer id);
	
}
