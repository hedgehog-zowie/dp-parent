package com.iuni.dp.service.datastat.service.wms;

import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniAfterSalesService {
	
	/**
	 * IUNI支付宝交易数据按条件分页统计
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniAfterSalesNumByPage(Map<String, Object> params);
	
	/**
	 * IUNI支付宝交易数据按条件统计记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIuniAfterSalesNumCount(Map<String, Object> params);
	
}
