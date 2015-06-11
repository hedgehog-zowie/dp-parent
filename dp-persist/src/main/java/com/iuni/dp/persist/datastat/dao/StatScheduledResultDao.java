package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.StatScheduledResult;

/**
 * 固定时间统一调度统计分析结果DAO
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public interface StatScheduledResultDao extends BaseDao<StatScheduledResult, Serializable> {

	/**
	 * 根据ID查询固定时间统一调度统计分析结果
	 * @param Integer id
	 * @return StatScheduledResult
	 */
	public StatScheduledResult selectStatScheduledResultById(Integer id);
	
	/**
	 * 固定时间统一调度统计分析结果条件查询
	 * @param Map<String, Object> params
	 * @return List<StatScheduledResult>
	 */
	public List<StatScheduledResult> selectAllStatScheduledResult(Map<String, Object> params);
	
	/**
	 * 固定时间统一调度统计分析结果临时表条件查询
	 * @param Map<String, Object> params
	 * @return List<StatScheduledResult>
	 */
	public List<StatScheduledResult> selectAllStatScheduledResultTemp(Map<String, Object> params);
	
	/**
	 * 固定时间统一调度统计分析结果分页条件查询
	 * @param Map<String, Object> params
	 * @return List<StatScheduledResult>
	 */
	public List<StatScheduledResult> selectStatScheduledResultByPage(Map<String, Object> params);
	
	/**
	 * 固定时间统一调度统计分析结果总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectTotalCount(Map<String, Object> params);
	
	/**
	 * 新增固定时间统一调度统计分析结果
	 * @param StatScheduledResult statScheduledResult
	 * @return Integer
	 */
	public Integer insertStatScheduledResult(StatScheduledResult statScheduledResult);
	
	/**
	 * 删除固定时间统一调度统计分析结果
	 * @param Integer
	 */
	public Integer deleteStatScheduledResultById(Integer id);
	
	/**
	 * 按条件删除固定时间统一调度统计分析结果临时表数据
	 * @param Map<String, Object> params
	 * @return Integer 
	 */
	public Integer delStatScdResultTempByCondition(Map<String, Object> params);
	
}
