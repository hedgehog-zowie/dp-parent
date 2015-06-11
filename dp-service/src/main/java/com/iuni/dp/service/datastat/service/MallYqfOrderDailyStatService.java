package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallYqfOrderDailyStat;
import com.iuni.dp.service.common.exception.ServiceException;

/**
 * 
 * @author ZuoChangjun 2013-9-6
 * @version dp-service-1.0.0
 */
public interface MallYqfOrderDailyStatService {

	/**
	 * 查询CPS订单数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallYqfOrderDailyStat> queryMallYqfOrderDailyStats(
			Map<String, Object> paramsMap) throws ServiceException;

	/**
	 * 查询CPS订单数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallYqfOrderDailyStatsCount(
			Map<String, Object> paramsMap) throws ServiceException;

	/**
	 * 查询CPS订单数据(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public MallYqfOrderDailyStat queryMallYqfOrderDailyStatsSum(
			Map<String, Object> paramsMap) throws ServiceException;

}
