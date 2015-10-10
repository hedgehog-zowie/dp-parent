package com.iuni.dp.persist.datastat.dao.wms;

import com.iuni.dp.persist.common.dao.BaseDao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public interface IuniWeixinPayDao extends BaseDao<Object, Serializable> {

	/**
	 * IUNI微信支付交易数据按条件分页统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniWeixinPayByPage(Map<String, Object> params);
	
	/**
	 * IUNI微信支付交易数据按条件统计记录数
	 * @param params
	 * @return
	 */
	public Integer selectIuniWeixinPayCount(Map<String, Object> params);
	
}
