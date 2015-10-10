package com.iuni.dp.persist.datastat.dao.wms;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.IuniWmsSalesOrder;

/**
 * IuniWmsSalesOrder DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.5
 */
public interface IuniWmsSalesOrderDao extends
		BaseDao<IuniWmsSalesOrder, Serializable> {

	/**
	 * IUNI WMS销售单相关统计按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniWmsSalesOrderStatByExample(
			Map<String, Object> params);

	/**
	 * IUNI WMS销售单相关统计按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniWmsSalesOrderStatByPage(
			Map<String, Object> params);

	/**
	 * IUNI WMS销售单相关统计按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer selectIuniWmsSalesOrderStatCount(Map<String, Object> params);
	
	/**
	 * IUNI WMS销售出库明细按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniStockMovDetailsByExample(Map<String, Object> params);
	
	/**
	 * IUNI WMS销售出库明细按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniStockMovDetailsByPage(Map<String, Object> params);
	
	/**
	 * IUNI WMS销售出库明细按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer selectIuniStockMovDetailsCount(Map<String, Object> params);
	
	/**
	 * IUNI WMS未开票销售明细按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniNoInvoiceSalesDetailsByExample(Map<String, Object> params);
	
	/**
	 * IUNI WMS未开票销售明细按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniNoInvoiceSalesDetailsByPage(Map<String, Object> params);
	
	/**
	 * IUNI WMS未开票销售明细按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer selectIuniNoInvoiceSalesDetailsCount(Map<String, Object> params);
	
	/**
	 * IUNI WMS收款发货发票金额核对明细按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniWmsPayAmountCheckByExample(Map<String, Object> params);
	
	/**
	 * IUNI WMS收款发货发票金额核对明细按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniWmsPayAmountCheckByPage(Map<String, Object> params);
	
	/**
	 * IUNI WMS收款发货发票金额核对明细按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer selectIuniWmsPayAmountCheckCount(Map<String, Object> params);

	/**
	 * 返利明细分页查询
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> selectIuniRebatesDetailByPage(Map<String, Object> params);

	/**
	 * 返利明细记录数
	 * @param params
	 * @return
	 */
	Integer selectIuniRebatesDetailCount(Map<String, Object> params);
}
