package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.IuniReturnGoodsInfo;

/**
 * IuniReturnGoodsInfo DAO
 * 
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.5
 */
public interface IuniReturnGoodsInfoDao extends
		BaseDao<IuniReturnGoodsInfo, Serializable> {

	/**
	 * IUNI订单退款明细按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniOrderRefundDetailsByExample(Map<String, Object> params);
	
	/**
	 * IUNI订单退款明细按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniOrderRefundDetailsByPage(Map<String, Object> params);
	
	/**
	 * IUNI订单退款明细按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer selectIuniOrderRefundDetailsCount(Map<String, Object> params);
	
}
