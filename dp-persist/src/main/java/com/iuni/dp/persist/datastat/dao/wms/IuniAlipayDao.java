package com.iuni.dp.persist.datastat.dao.wms;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.IuniWmsTransfer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniAlipayDao extends BaseDao<Object, Serializable> {

	/**
	 * IUNI支付宝交易数据按条件分页统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniAlipayByPage(Map<String, Object> params);
	
	/**
	 * IUNI支付宝交易数据按条件统计记录数
	 * @param params
	 * @return
	 */
	public Integer selectIuniAlipayCount(Map<String, Object> params);
	
}
