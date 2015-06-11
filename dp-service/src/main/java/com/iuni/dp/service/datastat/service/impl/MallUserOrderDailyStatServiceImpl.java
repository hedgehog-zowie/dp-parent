package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.MallUserOrderDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallUserOrderDailyStat;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.datastat.service.MallUserOrderDailyStatService;

/**
 * 
 * @author ZuoChangjun 2013-9-6
 * @version dp-service-1.0.0
 */
@Service("mallUserOrderDailyStatService")
public class MallUserOrderDailyStatServiceImpl implements
		MallUserOrderDailyStatService {

	private final static Logger logger = LoggerFactory.getLogger(MallUserOrderDailyStatServiceImpl.class);

	@Autowired
	private MallUserOrderDailyStatDao mallUserOrderDailyStatDao;

	/**
	 * 查询注册用户及订单数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallUserOrderDailyStat> queryMallUserOrderDailyStats(
			Map<String, Object> paramsMap) throws ServiceException {
		return mallUserOrderDailyStatDao.queryMallUserOrderDailyStats(paramsMap);
	}

	/**
	 * 查询注册用户及订单数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallUserOrderDailyStatsCount(Map<String, Object> paramsMap)
			throws ServiceException {
		return mallUserOrderDailyStatDao.queryMallUserOrderDailyStatsCount(paramsMap);
	}

	/**
	 * 查询注册用户及订单数据(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public MallUserOrderDailyStat queryMallUserOrderDailyStatsSum(
			Map<String, Object> paramsMap) throws ServiceException {
		return mallUserOrderDailyStatDao.queryMallUserOrderDailyStatsSum(paramsMap);
	}

}
