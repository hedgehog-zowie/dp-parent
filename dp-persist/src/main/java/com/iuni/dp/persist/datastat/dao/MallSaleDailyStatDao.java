package com.iuni.dp.persist.datastat.dao;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallSaleDailyStat;

/**
 *
 * @author ZuoChangjun 2013-8-20
 * @version dp-persist-1.0.0
 */
public interface MallSaleDailyStatDao {
	/**
	 * 查询销售数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallSaleDailyStat> queryMallSaleDailyStats(Map<String, Object> paramsMap);
		
	/**
	 * 查询销售数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallSaleDailyStatsCount(Map<String, Object> paramsMap);
	
	/**
	 * 查询销售数据(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public MallSaleDailyStat queryMallSaleDailyStatsSum(Map<String, Object> paramsMap);
}
