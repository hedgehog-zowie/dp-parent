package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

/**
 * IuniReturnGoodsInfo Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.5
 */
public interface IuniReturnGoodsInfoService {

	/**
	 * IUNI订单退款明细按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniOrderRefundDetailsByExample(Map<String, Object> params);
	
	/**
	 * IUNI订单退款明细按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniOrderRefundDetailsByPage(Map<String, Object> params);
	
	/**
	 * IUNI订单退款明细按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIuniOrderRefundDetailsCount(Map<String, Object> params);
	
}
