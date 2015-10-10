package com.iuni.dp.service.datastat.service.wms;

import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniWeixinPayService {

	/**
	 * IUNI微信支付交易数据按条件分页统计
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniWeixinPayByPage(Map<String, Object> params);
	
	/**
	 * IUNI微信支付交易数据按条件统计记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIuniWeixinPayCount(Map<String, Object> params);
	
}
