package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallGoodsPvuvDailyStat;

/**
 * 商城商品PV/UV基础数据日统计Service
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public interface MallGoodsPvuvDailyStatService {

	/**
	 * 根据ID查询商城商品PV/UV基础数据日统计
	 * @param Integer id
	 * @return MallGoodsPvuvDailyStat
	 */
	public MallGoodsPvuvDailyStat getMallGoodsPvuvDailyStatById(Integer id);
	
	/**
	 * 商城商品PV/UV基础数据日统计条件查询
	 * @param Map<String, Object> params
	 * @return List<MallGoodsPvuvDailyStat>
	 */
	public List<MallGoodsPvuvDailyStat> getAllByExample(Map<String, Object> params);
	
	/**
	 * 商城商品PV/UV基础数据日统计分页条件查询
	 * @param Map<String, Object> params
	 * @return List<MallGoodsPvuvDailyStat>
	 */
	public List<MallGoodsPvuvDailyStat> getMallGoodsPvuvDailyStatByPage(Map<String, Object> params);
	
	/**
	 * 商城商品PV/UV基础数据日统计总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer getTotalCountByExample(Map<String, Object> params);
	
	/**
	 * 商城商品PV/UV、订单相关日常基础数据日统计条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getMallGoodsDailyBaseStatByExample(Map<String, Object> params);
	
	/**
	 * 商城商品PV/UV、订单相关日常基础数据日统计分页条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getMallGoodsDailyBaseStatByPage(Map<String, Object> params);
	
	/**
	 * 商城商品PV/UV、订单相关日常基础数据日统计总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer getMallGoodsDailyBaseStatCount(Map<String, Object> params);
	
	/**
	 * 新增商城商品PV/UV基础数据日统计
	 * @param MallGoodsPvuvDailyStat mallGoodsPvuvDailyStat
	 * @return Integer
	 */
	public Integer saveMallGoodsPvuvDailyStat(MallGoodsPvuvDailyStat mallGoodsPvuvDailyStat);
	
	/**
	 * 删除商城商品PV/UV基础数据日统计
	 * @param Integer id
	 * @return Integer
	 */
	public Integer removeMallGoodsPvuvDailyStatById(Integer id);
	
}
