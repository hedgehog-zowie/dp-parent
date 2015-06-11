package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

/**
 * IuniOrderInfo Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.2
 */
public interface IuniOrderInfoService {

	/**
	 * IUNI商城订单区域分布按日统计查询
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniOrderAreaStatByExample(Map<String, Object> params);
	
	/**
	 * IUNI商城订单区域分布按日统计分页查询
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniOrderAreaStatByPage(Map<String, Object> params);
	
	/**
	 * IUNI商城订单区域分布按日统计查询记录数
	 * @return List
	 */
	public Integer queryIuniOrderAreaStatCount(Map<String, Object> params);
	
	/**
	 * IUNI商城订单区域分布汇总统计查询
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniOrderAreaSumByExample(Map<String, Object> params);
	
	/**
	 * IUNI商城订单区域分布汇总分页查询
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniOrderAreaSumByPage(Map<String, Object> params);
	
	/**
	 * IUNI商城订单区域分布汇总查询记录数
	 * @return List
	 */
	public Integer queryIuniOrderAreaSumCount(Map<String, Object> params);
	
	/**
	 * 订单相关客服指标统计数据查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryOrderStat4Cs(Map<String, Object> params);

    /**
     * 在线客服销售数据
     * @param params
     * @return
     */
    public List<Map<String, Object>> queryOrderStat4CsByUser(Map<String, Object> params);
	
}
