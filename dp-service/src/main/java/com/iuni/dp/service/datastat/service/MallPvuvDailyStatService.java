package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallPvuvDailyStat;

/**
 * 商城PV/UV基础数据日统计Service
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public interface MallPvuvDailyStatService {

	/**
	 * 根据ID查询商城PV/UV基础数据日统计
	 * @param Integer id
	 * @return MallPvuvDailyStat
	 */
	public MallPvuvDailyStat getMallPvuvDailyStatById(Integer id);
	
	/**
	 * 商城PV/UV基础数据日统计条件查询
	 * @param Map<String, Object> params
	 * @return List<MallPvuvDailyStat>
	 */
	public List<MallPvuvDailyStat> getAllByExample(Map<String, Object> params);
	
	/**
	 * 商城PV/UV基础数据日统计分页条件查询
	 * @param Map<String, Object> params
	 * @return List<MallPvuvDailyStat>
	 */
	public List<MallPvuvDailyStat> getMallPvuvDailyStatByPage(Map<String, Object> params);
	
	/**
	 * 商城PV/UV基础数据日统计总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer getTotalCountByExample(Map<String, Object> params);
	
	/**
	 * 商城PV/UV、订单相关日常基础数据日统计条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getMallDailyBaseStatByExample(Map<String, Object> params);
	
	/**
	 * 商城PV/UV、订单相关日常基础数据日统计分页条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getMallDailyBaseStatByPage(Map<String, Object> params);
	
	/**
	 * 商城PV/UV、订单相关日常基础数据日统计总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer getMallDailyBaseStatCount(Map<String, Object> params);
	
	/**
	 * 玩机圈PV/UV相关日常基础数据日统计条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getWjqDailyBaseStatByExample(Map<String, Object> params);
	
	/**
	 * 玩机圈PV/UV相关日常基础数据日统计分页条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getWjqDailyBaseStatByPage(Map<String, Object> params);
	
	/**
	 * 玩机圈PV/UV相关日常基础数据日统计总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer getWjqDailyBaseStatCount(Map<String, Object> params);
	
	/**
	 * 玩机圈PV/UV相关日常基础数据日统计条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getWjqMonthlyBaseStatByExample(Map<String, Object> params);
	
	/**
	 * 玩机圈PV/UV相关日常基础数据日统计分页条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getWjqMonthlyBaseStatByPage(Map<String, Object> params);
	
	/**
	 * 玩机圈PV/UV相关日常基础数据日统计总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer getWjqMonthlyBaseStatCount(Map<String, Object> params);
	
	/**
	 * 新增商城PV/UV基础数据日统计
	 * @param MallPvuvDailyStat mallPvuvDailyStat
	 * @return Integer
	 */
	public Integer saveMallPvuvDailyStat(MallPvuvDailyStat mallPvuvDailyStat);
	
	/**
	 * 删除商城PV/UV基础数据日统计
	 * @param Integer id
	 * @return Integer
	 */
	public Integer removeMallPvuvDailyStatById(Integer id);
	
}
