package com.iuni.dp.service.datastat.service.wms;

import java.util.List;
import java.util.Map;

/**
 * IuniWmsTransfer Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.5
 */
public interface IuniWmsTransferService {
	
	/**
	 * IUNI WMS调拨单明细按条件统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIuniWmsTransferStatByExample(Map<String, Object> params);
	
	/**
	 * IUNI WMS调拨单明细按条件分页统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIuniWmsTransferStatByPage(Map<String, Object> params);
	
	/**
	 * IUNI WMS调拨单明细按条件统计记录
	 * @param params
	 * @return
	 */
	public Integer queryIuniWmsTransferStatCount(Map<String, Object> params);
	
	/**
	 * IUNI WMS内部调拔明细按条件统计
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniInTransferDetailsByExample(Map<String, Object> params);
	
	/**
	 * IUNI WMS内部调拔明细按条件分页统计
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniInTransferDetailsByPage(Map<String, Object> params);
	
	/**
	 * IUNI WMS内部调拔明细按条件统计记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIuniInTransferDetailsCount(Map<String, Object> params);
	
}
