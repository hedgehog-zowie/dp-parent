package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.IuniSaleDailyStatDao;
import com.iuni.dp.persist.datastat.model.IuniDayOrderConvertRate;
import com.iuni.dp.persist.datastat.model.IuniDayPvuvUserReg;
import com.iuni.dp.persist.datastat.model.IuniSaleDailyStat;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.datastat.service.IuniSaleDailyStatService;

/**
 *
 * @author ZuoChangjun 2013-8-20
 * @version dp-service-1.0.0
 */
@Service("iuniSaleDailyStatService")
public class IuniSaleDailyStatServiceImpl implements IuniSaleDailyStatService {
	private final static Logger logger = LoggerFactory.getLogger(IuniSaleDailyStatServiceImpl.class);
	
	@Autowired
	private IuniSaleDailyStatDao iuniSaleDailyStatDao;
	
	/**
	 * 查询销售数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<IuniSaleDailyStat> queryIuniSaleDailyStats(Map<String, Object> paramsMap) throws ServiceException{
		return iuniSaleDailyStatDao.queryIuniSaleDailyStats(paramsMap);
	}
		
	/**
	 * 查询销售数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryIuniSaleDailyStatsCount(Map<String, Object> paramsMap) throws ServiceException{
		return iuniSaleDailyStatDao.queryIuniSaleDailyStatsCount(paramsMap);
	}
	
	/**
	 * 查询销售数据(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public IuniSaleDailyStat queryIuniSaleDailyStatsSum(Map<String, Object> paramsMap) throws ServiceException{
		return iuniSaleDailyStatDao.queryIuniSaleDailyStatsSum(paramsMap);
	}

	/**
	 * 查询订单转化率
	 * 
	 * @author ZuoChangjun
	 */
	public List<IuniDayOrderConvertRate> queryIuniDayOrderConvertRates(Map<String, Object> paramsMap) throws ServiceException{
		return iuniSaleDailyStatDao.queryIuniDayOrderConvertRates(paramsMap);
	}
		
	/**
	 * 查询订单转化率:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryIuniDayOrderConvertRatesCount(Map<String, Object> paramsMap) throws ServiceException{
		return iuniSaleDailyStatDao.queryIuniDayOrderConvertRatesCount(paramsMap);
	}
	
	/**
	 * 查询订单转化率(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public IuniDayOrderConvertRate queryIuniDayOrderConvertRatesSum(Map<String, Object> paramsMap) throws ServiceException{
		return iuniSaleDailyStatDao.queryIuniDayOrderConvertRatesSum(paramsMap);
	}
	
	/**
	 * 用户注册：列表
	 * 
	 * @author ZuoChangjun
	 */
	public List<IuniDayPvuvUserReg> queryIuniUserRegDailyStats(Map<String, Object> paramsMap) throws ServiceException{
		return iuniSaleDailyStatDao.queryIuniUserRegDailyStats(paramsMap);
	}
		
	/**
	 * 用户注册:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryIuniUserRegDailyStatsCount(Map<String, Object> paramsMap) throws ServiceException{
		return iuniSaleDailyStatDao.queryIuniUserRegDailyStatsCount(paramsMap);
	}
	
	/**
	 * 用户注册：总计
	 * 
	 * @author ZuoChangjun
	 */
	public IuniDayPvuvUserReg queryIuniUserRegDailyStatsSum(Map<String, Object> paramsMap) throws ServiceException{
		return iuniSaleDailyStatDao.queryIuniUserRegDailyStatsSum(paramsMap);
	}
}
