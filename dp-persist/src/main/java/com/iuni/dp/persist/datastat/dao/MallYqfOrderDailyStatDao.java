package com.iuni.dp.persist.datastat.dao;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallYqfOrderDailyStat;

/**
 *
 * @author ZuoChangjun 2013-9-6
 * @version dp-persist-1.0.0
 */
public interface MallYqfOrderDailyStatDao {

	/**
	 * 查询CPS订单数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallYqfOrderDailyStat> queryMallYqfOrderDailyStats(Map<String, Object> paramsMap);
		
	/**
	 * 查询CPS订单数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallYqfOrderDailyStatsCount(Map<String, Object> paramsMap);
	
	/**
	 * 查询CPS订单数据(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public MallYqfOrderDailyStat queryMallYqfOrderDailyStatsSum(Map<String, Object> paramsMap);

}
