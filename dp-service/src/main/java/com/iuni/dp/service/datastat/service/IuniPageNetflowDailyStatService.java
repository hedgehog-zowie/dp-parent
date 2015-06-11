package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

/**
 * IuniPageNetflowDailyStat Service
 * @author CaiKe
 * @version dp-service-V1.0.2
 */
public interface IuniPageNetflowDailyStatService {

	/**
	 * 按条件查询IUNI商城页面流量相关数据日统计记录
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIpndsByExample(
			Map<String, Object> params);

	/**
	 * 按条件分页查询IUNI商城页面流量相关数据日统计记录
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIpndsByPage(
			Map<String, Object> params);

	/**
	 * 按条件查询IUNI商城页面流量相关数据日统计记录条数
	 * 
	 * @param params
	 * @return
	 */
	public Integer queryIpndsCount(Map<String, Object> params);

	/**
	 * 根据时间汇总查询IUNI商城页面流量相关日统计数据
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIpndsSumOnDateByExample(
			Map<String, Object> params);

	/**
	 * 根据时间汇总分页查询IUNI商城页面流量相关日统计数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIpndsSumOnDateByPage(
			Map<String, Object> params);

	/**
	 * 根据时间汇总查询IUNI商城页面流量相关日统计数据记录数
	 * @param params
	 * @return
	 */
	public Integer queryIpndsSumOnDateCount(Map<String, Object> params);
	
}
