package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

/**
 * 商品销售报表
 * @author dan.wang@iuni.com
 *
 */
public interface IuniSalesOfGoodsService {
	/**
	 * 查询商品销售
	 */
	public List<Map<String,Object>> queryIuniSalesOfGoodsBypage(Map<String,Object> paramsMap);
	
	/**
	 * 数量
	 */
	public Integer queryIuniSalesOfGoodsCount(Map<String,Object> paramsMap);
	/**
	 * 导出到Excel的数据
	 */
	public List<Map<String,Object>> queryIuniSalesOfGoods2Excel(Map<String,Object> paramsMap);
}
