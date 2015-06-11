package com.iuni.dp.service.datastat.service.wms;

import java.util.List;
import java.util.Map;

/**
 * IuniWmsTransfer Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.5
 */
public interface IuniAlipayService {
	
	/**
	 * IUNI支付宝交易数据按条件分页统计
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniAlipayByPage(Map<String, Object> params);
	
	/**
	 * IUNI支付宝交易数据按条件统计记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIuniAlipayCount(Map<String, Object> params);
	
}
