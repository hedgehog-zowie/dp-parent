package com.iuni.dp.persist.datastat.dao;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.IuniDayOrderConvertRate;
import com.iuni.dp.persist.datastat.model.IuniDayPvuvUserReg;
import com.iuni.dp.persist.datastat.model.IuniSaleDailyStat;

/**
 *
 * @author ZuoChangjun 2013-8-20
 * @version dp-persist-1.0.0
 */
public interface IuniSaleDailyStatDao {
	/**
	 * 查询销售数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<IuniSaleDailyStat> queryIuniSaleDailyStats(Map<String, Object> paramsMap);
		
	/**
	 * 查询销售数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryIuniSaleDailyStatsCount(Map<String, Object> paramsMap);
	
	/**
	 * 查询销售数据(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public IuniSaleDailyStat queryIuniSaleDailyStatsSum(Map<String, Object> paramsMap);
	
	/**
	 * 查询订单转化率
	 * 
	 * @author ZuoChangjun
	 */
	public List<IuniDayOrderConvertRate> queryIuniDayOrderConvertRates(Map<String, Object> paramsMap);
		
	/**
	 * 查询订单转化率:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryIuniDayOrderConvertRatesCount(Map<String, Object> paramsMap);
	
	/**
	 * 查询订单转化率(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public IuniDayOrderConvertRate queryIuniDayOrderConvertRatesSum(Map<String, Object> paramsMap);
	
	/**
	 * 用户注册：列表
	 * 
	 * @author ZuoChangjun
	 */
	public List<IuniDayPvuvUserReg> queryIuniUserRegDailyStats(Map<String, Object> paramsMap);
		
	/**
	 * 用户注册:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryIuniUserRegDailyStatsCount(Map<String, Object> paramsMap);
	
	/**
	 * 用户注册：总计
	 * 
	 * @author ZuoChangjun
	 */
	public IuniDayPvuvUserReg queryIuniUserRegDailyStatsSum(Map<String, Object> paramsMap);
}
