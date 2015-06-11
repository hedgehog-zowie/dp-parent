package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

/**
 * IuniNetflowDailyStat Service
 * @author CaiKe
 * @version dp-service-V1.0.2
 */
public interface IuniNetflowDailyStatService {

	/**
	 * 按条件查询IUNI商城流量相关数据日统计记录
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIndsByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI商城流量相关数据日统计记录
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIndsByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI商城流量相关数据日统计记录条数
	 * @param params
	 * @return
	 */
	public Integer queryIndsCount(Map<String, Object> params);
	
	/**
	 * 根据时间汇总查询IUNI商城流量相关日统计数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIndsSumByDate(Map<String, Object> params);
	
}
