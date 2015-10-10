package com.iuni.dp.persist.datastat.dao.wms;

import com.iuni.dp.persist.common.dao.BaseDao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniAfterSalesDao extends BaseDao<Object, Serializable> {

	/**
	 * 普通订单售后次数统计报表
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniAfterSalesNumByPage(Map<String, Object> params);
	
	/**
	 * 普通订单售后次数统计报表 记录数
	 * @param params
	 * @return
	 */
	public Integer selectIuniAfterSalesNumCount(Map<String, Object> params);
	
}
