package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallSaleDailyStat;
import com.iuni.dp.service.common.exception.ServiceException;

/**
 *
 * @author ZuoChangjun 2013-8-20
 * @version dp-service-1.0.0
 */
public interface MallSaleDailyStatService {
	/**
	 * 查询销售数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallSaleDailyStat> queryMallSaleDailyStats(Map<String, Object> paramsMap)throws ServiceException;
		
	/**
	 * 查询销售数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallSaleDailyStatsCount(Map<String, Object> paramsMap)throws ServiceException;
	
	/**
	 * 查询销售数据(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public MallSaleDailyStat queryMallSaleDailyStatsSum(Map<String, Object> paramsMap)throws ServiceException;
}
