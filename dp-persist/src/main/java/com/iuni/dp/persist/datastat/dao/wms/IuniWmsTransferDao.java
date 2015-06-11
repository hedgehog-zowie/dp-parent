package com.iuni.dp.persist.datastat.dao.wms;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.IuniWmsTransfer;

/**
 * IuniWmsTransfer DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.5
 */
public interface IuniWmsTransferDao extends BaseDao<IuniWmsTransfer, Serializable> {

	/**
	 * IUNI WMS调拨单明细按条件统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniWmsTransferStatByExample(Map<String, Object> params);
	
	/**
	 * IUNI WMS调拨单明细按条件分页统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniWmsTransferStatByPage(Map<String, Object> params);
	
	/**
	 * IUNI WMS调拨单明细按条件统计记录
	 * @param params
	 * @return
	 */
	public Integer selectIuniWmsTransferStatCount(Map<String, Object> params);
	
	/**
	 * IUNI WMS内部调拔明细按条件统计
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniInTransferDetailsByExample(Map<String, Object> params);
	
	/**
	 * IUNI WMS内部调拔明细按条件分页统计
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniInTransferDetailsByPage(Map<String, Object> params);
	
	/**
	 * IUNI WMS内部调拔明细按条件统计记录数
	 * @param params
	 * @return Integer
	 */
	public Integer selectIuniInTransferDetailsCount(Map<String, Object> params);
	
	
	public List<Map<String,Object>> selectIuniWmsTransferStatForWlByPage(Map<String, Object> queryParams);

	public Integer selectIuniWmsTransferStatForWlCount(
			Map<String, Object> params);

	public List<Map<String, Object>> selectIuniWmsTransferForWL2Excel(
			Map<String, Object> queryParams);
}
