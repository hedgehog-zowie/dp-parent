package com.iuni.dp.service.datastat.service.wms;

import java.util.List;
import java.util.Map;

/**
 * IuniWmsStock Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.5
 */
public interface IuniWmsStockService {

	/**
	 * IUNI WMS库存明细按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniWmsStockDetailsByExample(Map<String, Object> params);
	
	/**
	 * IUNI WMS库存明细按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniWmsStockDetailsByPage(Map<String, Object> params);
	
	/**
	 * IUNI WMS库存明细按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIuniWmsStockDetailsCount(Map<String, Object> params);
	
}
