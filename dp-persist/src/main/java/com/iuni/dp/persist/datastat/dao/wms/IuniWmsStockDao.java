package com.iuni.dp.persist.datastat.dao.wms;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.IuniWmsDailyStock;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * IuniWmsStock DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.5
 */
public interface IuniWmsStockDao extends BaseDao<IuniWmsDailyStock, Serializable> {

	/**
	 * IUNI WMS库存明细按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniWmsStockDetailsByExample(Map<String, Object> params);
	
	/**
	 * IUNI WMS库存明细按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniWmsStockDetailsByPage(Map<String, Object> params);
	
	/**
	 * IUNI WMS库存明细按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer selectIuniWmsStockDetailsCount(Map<String, Object> params);
	
}
