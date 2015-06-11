package com.iuni.dp.service.datastat.service.wmswl;

import java.util.List;
import java.util.Map;

public interface IuniWmsOrderEffectiveTimeService {
	
	/**
	 * 正向订单时效报表明细
	 */
	public List<Map<String, Object>> queryIuniWmsPositiveOrderWl(Map<String, Object> queryParams);
	
	public Integer queryIuniWmsPositiveOrderWlCount(Map<String, Object> queryParams);
	
	public List<Map<String, Object>> queryIuniWmsPositiveOrderWl2Excel(Map<String, Object> queryParams);
}
