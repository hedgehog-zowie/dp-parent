package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

/**
 * IuniIpAreaDailyStat Service
 * @author CaiKe
 * @version dp-service-V1.0.2
 */
public interface IuniIpAreaDailyStatService {

	/**
	 * 按条件查询IUNI商城独立IP数区域分布
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIidsByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI商城独立IP数区域分布
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIidsByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI商城独立IP数区域分布记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIidsCount(Map<String, Object> params);
	
	/**
	 * 按条件查询时间段范围内IUNI商城独立IP汇总数区域分布
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIidsSumOnDateByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询时间段范围内IUNI商城独立IP汇总数区域分布
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIidsSumOnDateByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询时间段范围内IUNI商城独立IP汇总数区域分布记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIidsSumOnDateCount(Map<String, Object> params);
	
}
