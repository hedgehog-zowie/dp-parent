package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.WjqCreditPromotionLog;

/**
 * 玩机圈用户推广日志DAO
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public interface WjqCreditPromotionLogDao extends BaseDao<WjqCreditPromotionLog, Serializable> {

	/**
	 * 根据ID查询玩机圈用户推广日志
	 * @param Integer id
	 * @return WjqCreditPromotionLog
	 */
	public WjqCreditPromotionLog selectWjqCreditPromotionLogById(Integer id);
	
	/**
	 * 根据条件查询玩机圈用户推广日志
	 * @param Map<String, Object> params
	 * @return List<WjqCreditPromotionLog>
	 */
	public List<WjqCreditPromotionLog> selectWjqCreditPromotionLogByExample(Map<String, Object> params);
	
	/**
	 * 根据条件分页查询玩机圈用户推广日志
	 * @param Map<String, Object> params
	 * @return List<WjqCreditPromotionLog>
	 */
	public List<WjqCreditPromotionLog> selectWjqCreditPromotionLogByPage(Map<String, Object> params);
	
	/**
	 * 根据条件查询玩机圈用户推广日志数目
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectWjqCreditPromotionLogCount(Map<String, Object> params);
	
	/**
	 * 根据条件查询玩机圈用户推广情况
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectWjqUserPromotionByExample(Map<String, Object> params);
	
	/**
	 * 根据条件分页查询玩机圈用户推广情况
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectWjqUserPromotionByPage(Map<String, Object> params);
	
	/**
	 * 根据条件查询玩机圈用户推广情况记录数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectWjqUserPromotionCount(Map<String, Object> params);
	
	/**
	 * 根据条件查询玩机圈帖子推广情况
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectWjqPostsPromotionByExample(Map<String, Object> params);
	
	/**
	 * 根据条件分页查询玩机圈帖子推广情况
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectWjqPostsPromotionByPage(Map<String, Object> params);
	
	/**
	 * 根据条件查询玩机圈帖子推广情况记录数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectWjqPostsPromotionCount(Map<String, Object> params);
	
	/**
	 * 玩机圈全站用户推广数据预览查询
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectWjqUserPromotionPreview(Map<String, Object> params);
	
	/**
	 * 根据条件查询玩机圈用户推广数据时间段分组统计
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectWjqUserPromotionSumByExample(Map<String, Object> params);
	
	/**
	 * 根据条件分页查询玩机圈用户推广数据时间段分组统计
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectWjqUserPromotionSumByPage(Map<String, Object> params);
	
	/**
	 * 根据条件查询玩机圈用户推广数据时间段分组统计记录数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectWjqUserPromotionSumCount(Map<String, Object> params);
	
	/**
	 * 新增玩机圈用户推广日志记录
	 * @param WjqCreditPromotionLog wjqCreditPromotionLog
	 * @return Integer
	 */
	public Integer insertWjqCreditPromotionLog(WjqCreditPromotionLog wjqCreditPromotionLog);
	
	/**
	 * 根据ID删除玩机圈用户推广日志记录
	 * @param Integer id
	 * @return Integer
	 */
	public Integer deleteWjqCreditPromotionLogById(Integer id);
	
}
