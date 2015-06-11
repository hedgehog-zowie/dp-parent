package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;

/**
 * 
 * @author dan.wang@iuni.com
 *
 */
public interface IuniSalesOfGoodsDao extends BaseDao<Object, Serializable>{
	
	/**
	 * 商品销售统计
	 */
	public List<Map<String,Object>> selectSalesOfGoodsByPage(Map<String,Object> paramsMap);
	
	/**
	 * 数量
	 * */
	public Integer selectSalesOfGoodsCount(Map<String,Object> paramsMap);
	
	/**
	 * 导出到Excel
	 */
	public List<Map<String,Object>> selectSalesOfGoods2Excel(Map<String,Object> paramsMap);
}
