package com.iuni.dp.persist.datastat.dao;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallUserOrderDailyStat;

/**
 *
 * @author ZuoChangjun 2013-9-6
 * @version dp-persist-1.0.0
 */
public interface MallUserOrderDailyStatDao {

	/**
	 * 查询注册用户及订单数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallUserOrderDailyStat> queryMallUserOrderDailyStats(Map<String, Object> paramsMap);
		
	/**
	 * 查询注册用户及订单数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallUserOrderDailyStatsCount(Map<String, Object> paramsMap);
	
	/**
	 * 查询注册用户及订单数据(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public MallUserOrderDailyStat queryMallUserOrderDailyStatsSum(Map<String, Object> paramsMap);

}
