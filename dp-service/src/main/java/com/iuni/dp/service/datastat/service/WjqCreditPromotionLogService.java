package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.WjqCreditPromotionLog;

/**
 * 玩机圈用户推广日志Service
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public interface WjqCreditPromotionLogService {

	/**
	 * 根据ID查询玩机圈用户推广日志
	 * @param id
	 * @return WjqCreditPromotionLog
	 */
	public WjqCreditPromotionLog fetchWjqCreditPromotionLogById(Integer id);
	
	/**
	 * 根据条件查询玩机圈用户推广日志
	 * @param params
	 * @return List<WjqCreditPromotionLog>
	 */
	public List<WjqCreditPromotionLog> fetchWjqCreditPromotionLogByExample(Map<String, Object> params);
	
	/**
	 * 根据条件分页查询玩机圈用户推广日志
	 * @param params
	 * @return List<WjqCreditPromotionLog>
	 */
	public List<WjqCreditPromotionLog> fetchWjqCreditPromotionLogByPage(Map<String, Object> params);
	
	/**
	 * 根据条件查询玩机圈用户推广日志数目
	 * @param params
	 * @return Integer
	 */
	public Integer fetchWjqCreditPromotionLogCount(Map<String, Object> params);
	
	/**
	 * 根据条件查询玩机圈用户推广情况
	 * @param params
	 * @return List<WjqUserPromotion>
	 */
	public List<Map<String, Object>> fetchWjqUserPromotionByExample(Map<String, Object> params);
	
	/**
	 * 根据条件分页查询玩机圈用户推广情况
	 * @param params
	 * @return List<WjqUserPromotion>
	 */
	public List<Map<String, Object>> fetchWjqUserPromotionByPage(Map<String, Object> params);
	
	/**
	 * 根据条件查询玩机圈用户推广情况记录数
	 * @param params
	 * @return
	 */
	public Integer fetchWjqUserPromotionCount(Map<String, Object> params);
	
	/**
	 * 根据条件查询玩机圈帖子推广情况
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchWjqPostsPromotionByExample(Map<String, Object> params);
	
	/**
	 * 根据条件分页查询玩机圈帖子推广情况
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchWjqPostsPromotionByPage(Map<String, Object> params);
	
	/**
	 * 根据条件查询玩机圈帖子推广情况记录数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer fetchWjqPostsPromotionCount(Map<String, Object> params);
	
	/**
	 * 玩机圈全站用户推广数据预览查询
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchWjqUserPromotionPreview(Map<String, Object> params);
	
	/**
	 * 根据条件查询玩机圈用户推广数据时间段分组统计
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchWjqUserPromotionSumByExample(Map<String, Object> params);
	
	/**
	 * 根据条件分页查询玩机圈用户推广数据时间段分组统计
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchWjqUserPromotionSumByPage(Map<String, Object> params);
	
	/**
	 * 根据条件查询玩机圈用户推广数据时间段分组统计记录数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer fetchWjqUserPromotionSumCount(Map<String, Object> params);
	
	/**
	 * 新增玩机圈用户推广日志记录
	 * @param wjqCreditPromotionLog
	 * @return
	 */
	public Integer saveWjqCreditPromotionLog(WjqCreditPromotionLog wjqCreditPromotionLog);
	
	/**
	 * 根据ID删除玩机圈用户推广日志记录
	 * @param id
	 * @return
	 */
	public Integer removeWjqCreditPromotionLogById(Integer id);
	
}
