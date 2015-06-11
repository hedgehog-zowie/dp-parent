package com.iuni.dp.persist.datastat.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.common.model.IuniWmsSku;

/**
 * 
 * @author dan.wang@iuni.com
 *
 */
public interface IuniWmsSkuDao extends BaseDao<IuniWmsSku,Serializable>{

	/**
	 * 查询商品规格型号
	 */
	public List<Map<String, Object>> selectIuniWmsSku(Map<String,Object> params);

	/**
	 * 查询商品类型
	 */
	public List<Map<String, Object>> selectIuniWmsWaresCategory();

	/**
	 * 查询商品
	 * @return
	 */
	public List<Map<String, Object>> selectIuniWmsWares(Integer catId);

}
