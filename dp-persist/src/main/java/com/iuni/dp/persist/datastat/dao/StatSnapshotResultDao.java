package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.StatSnapshotResult;

/**
 * 固定间隔时间快照统计分析结果DAO
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public interface StatSnapshotResultDao extends BaseDao<StatSnapshotResult, Serializable> {

	/**
	 * 根据ID查询固定间隔时间快照统计分析结果
	 * @param Integer id
	 * @return StatSnapshotResult
	 */
	public StatSnapshotResult selectStatSnapshotResultById(Integer id);
	
	/**
	 * 固定间隔时间快照统计分析结果条件查询
	 * @param Map<String, Object> params
	 * @return List<StatSnapshotResult>
	 */
	public List<StatSnapshotResult> selectAllStatSnapshotResult(Map<String, Object> params);
	
	/**
	 * 固定间隔时间快照统计分析结果分页条件查询
	 * @param Map<String, Object> params
	 * @return List<StatSnapshotResult>
	 */
	public List<StatSnapshotResult> selectStatSnapshotResultByPage(Map<String, Object> params);
	
	/**
	 * 固定间隔时间快照统计分析结果总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectTotalCount(Map<String, Object> params);
	
	/**
	 * 新增固定间隔时间快照统计分析结果
	 * @param StatSnapshotResult statSnapshotResult
	 * @return Integer
	 */
	public Integer insertStatSnapshotResult(StatSnapshotResult statSnapshotResult);
	
	/**
	 * 删除固定间隔时间快照统计分析结果
	 * @param Integer id
	 */
	public Integer deleteStatSnapshotResultById(Integer id);
	
}
