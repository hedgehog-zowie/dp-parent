package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

/**
 * IuniBuriedPointDailyStat Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.3
 */
public interface IuniBuriedPointDailyStatService {

	/**
	 * IUNI前端埋点位置每日统计查询
	 * 
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIbpdByExample(
			Map<String, Object> params);

	/**
	 * IUNI前端埋点位置每日统计分页查询
	 * 
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIbpdByPage(Map<String, Object> params);

	/**
	 * IUNI前端埋点位置每日统计查询记录数
	 * 
	 * @param params
	 * @return Integer
	 */
	public Integer queryIbpdCount(Map<String, Object> params);
	
	/**
	 * IUNI前端埋点页面当日实时点击率相关统计查询
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryClickRateTodayByExample(Map<String, Object> params);
	
	/**
	 * IUNI前端埋点页面当日实时点击率相关统计分页查询
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryClickRateTodayByPage(Map<String, Object> params);
	
	/**
	 * IUNI前端埋点页面当日实时点击率相关统计数目查询
	 * @param params
	 * @return
	 */
	public Integer queryClickRateTodayCount(Map<String, Object> params);
	
}
