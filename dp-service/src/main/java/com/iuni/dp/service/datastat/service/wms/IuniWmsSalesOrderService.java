package com.iuni.dp.service.datastat.service.wms;

import com.iuni.dp.service.common.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * IuniWmsSalesOrder Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.5
 */
public interface IuniWmsSalesOrderService {

	/**
	 * IUNI WMS销售单相关统计按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniWmsSalesOrderStatByExample(Map<String, Object> params) throws ServiceException;

	/**
	 * IUNI WMS销售单相关统计按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniWmsSalesOrderStatByPage(Map<String, Object> params) throws ServiceException;

	/**
	 * IUNI WMS销售单相关统计按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIuniWmsSalesOrderStatCount(Map<String, Object> params) throws ServiceException;
	
	/**
	 * IUNI WMS销售出库明细按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniStockMovDetailsByExample(Map<String, Object> params) throws ServiceException;
	
	/**
	 * IUNI WMS销售出库明细按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniStockMovDetailsByPage(Map<String, Object> params) throws ServiceException;
	
	/**
	 * IUNI WMS销售出库明细按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIuniStockMovDetailsCount(Map<String, Object> params) throws ServiceException;
	
	/**
	 * IUNI WMS未开票销售明细按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniNoInvoiceSalesDetailsByExample(Map<String, Object> params) throws ServiceException;
	
	/**
	 * IUNI WMS未开票销售明细按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniNoInvoiceSalesDetailsByPage(Map<String, Object> params) throws ServiceException;
	
	/**
	 * IUNI WMS未开票销售明细按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIuniNoInvoiceSalesDetailsCount(Map<String, Object> params) throws ServiceException;
	
	/**
	 * IUNI WMS收款发货发票金额核对明细按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniWmsPayAmountCheckByExample(Map<String, Object> params) throws ServiceException;
	
	/**
	 * IUNI WMS收款发货发票金额核对明细按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniWmsPayAmountCheckByPage(Map<String, Object> params) throws ServiceException;
	
	/**
	 * IUNI WMS收款发货发票金额核对明细按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIuniWmsPayAmountCheckCount(Map<String, Object> params) throws ServiceException;

	/**
	 * 返利报表分页查询
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> 	queryIuniRebatesDetailByPage(Map<String, Object> params) throws ServiceException;

	/**
	 * 返利报表查询记录数
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public Integer queryIuniRebatesDetailCount(Map<String, Object> params) throws ServiceException;
}
