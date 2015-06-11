package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallGoodsOrderDailyStat;

/**
 * 商城订单商品基础数据日统计Service
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public interface MallGoodsOrderDailyStatService {

	/**
	 * 根据ID查询商城订单商品基础数据日统计
	 * @param Integer id
	 * @return MallGoodsOrderDailyStat
	 */
	public MallGoodsOrderDailyStat getMallGoodsOrderDailyStatById(Integer id);
	
	/**
	 * 商城订单商品基础数据日统计条件查询
	 * @param Map<String, Object> params
	 * @return List<MallGoodsOrderDailyStat>
	 */
	public List<MallGoodsOrderDailyStat> getAllByExample(Map<String, Object> params);
	
	/**
	 * 商城订单商品基础数据日统计分页条件查询
	 * @param Map<String, Object> params
	 * @return List<MallGoodsOrderDailyStat>
	 */
	public List<MallGoodsOrderDailyStat> getMallGoodsOrderDailyStatByPage(Map<String, Object> params);
	
	/**
	 * 商城订单商品基础数据日统计总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer getTotalCountByExample(Map<String, Object> params);
	
	/**
	 * 新增商城订单商品基础数据日统计
	 * @param MallGoodsOrderDailyStat mallGoodsOrderDailyStat
	 * @return Integer
	 */
	public Integer saveMallGoodsOrderDailyStat(MallGoodsOrderDailyStat mallGoodsOrderDailyStat);
	
	/**
	 * 删除商城订单商品基础数据日统计
	 * @param Integer id
	 * @return Integer
	 */
	public Integer removeMallGoodsOrderDailyStatById(Integer id);
	
}
