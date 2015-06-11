package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallUserOrderDailyStat;
import com.iuni.dp.service.common.exception.ServiceException;

/**
 * 
 * @author ZuoChangjun 2013-9-6
 * @version dp-service-1.0.0
 */
public interface MallUserOrderDailyStatService {

	/**
	 * 查询注册用户及订单数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallUserOrderDailyStat> queryMallUserOrderDailyStats(
			Map<String, Object> paramsMap) throws ServiceException;

	/**
	 * 查询注册用户及订单数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallUserOrderDailyStatsCount(
			Map<String, Object> paramsMap) throws ServiceException;

	/**
	 * 查询注册用户及订单数据(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public MallUserOrderDailyStat queryMallUserOrderDailyStatsSum(
			Map<String, Object> paramsMap) throws ServiceException;

}
