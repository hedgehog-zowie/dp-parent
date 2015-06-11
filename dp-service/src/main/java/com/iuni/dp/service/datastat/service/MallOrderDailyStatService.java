package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallOrderDailyStat;

/**
 * 商城订单基础数据日统计Service
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public interface MallOrderDailyStatService {

	/**
	 * 根据ID查询商城订单基础数据日统计
	 * @param Integer id
	 * @return MallOrderDailyStat
	 */
	public MallOrderDailyStat getMallOrderDailyStatById(Integer id);
	
	/**
	 * 商城订单基础数据日统计条件查询
	 * @param Map<String, Object> params
	 * @return List<MallOrderDailyStat>
	 */
	public List<MallOrderDailyStat> getAllByExample(Map<String, Object> params);
	
	/**
	 * 商城订单基础数据日统计分页条件查询
	 * @param Map<String, Object> params
	 * @return List<MallOrderDailyStat>
	 */
	public List<MallOrderDailyStat> getMallOrderDailyStatByPage(Map<String, Object> params);
	
	/**
	 * 商城订单基础数据日统计总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer getTotalCountByExample(Map<String, Object> params);
	
	/**
	 * 新增商城订单基础数据日统计
	 * @param MallOrderDailyStat mallOrderDailyStat
	 * @return Integer
	 */
	public Integer saveMallOrderDailyStat(MallOrderDailyStat mallOrderDailyStat);
	
	/**
	 * 删除商城订单基础数据日统计
	 * @param Integer id
	 * @return Integer
	 */
	public Integer removeMallOrderDailyStatById(Integer id);
	
}
