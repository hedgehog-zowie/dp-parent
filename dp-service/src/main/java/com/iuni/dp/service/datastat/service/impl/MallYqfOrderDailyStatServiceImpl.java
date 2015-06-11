package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.MallYqfOrderDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallYqfOrderDailyStat;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.datastat.service.MallYqfOrderDailyStatService;

/**
 * 
 * @author ZuoChangjun 2013-9-6
 * @version dp-service-1.0.0
 */
@Service("mallYqfOrderDailyStatService")
public class MallYqfOrderDailyStatServiceImpl implements
		MallYqfOrderDailyStatService {

	private final static Logger logger = LoggerFactory.getLogger(MallYqfOrderDailyStatServiceImpl.class);

	@Autowired
	private MallYqfOrderDailyStatDao mallYqfOrderDailyStatDao;

	/**
	 * 查询CPS订单数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallYqfOrderDailyStat> queryMallYqfOrderDailyStats(
			Map<String, Object> paramsMap) throws ServiceException {
		return mallYqfOrderDailyStatDao.queryMallYqfOrderDailyStats(paramsMap);
	}

	/**
	 * 查询CPS订单数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallYqfOrderDailyStatsCount(Map<String, Object> paramsMap)
			throws ServiceException {
		return mallYqfOrderDailyStatDao.queryMallYqfOrderDailyStatsCount(paramsMap);
	}

	/**
	 * 查询CPS订单数据(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public MallYqfOrderDailyStat queryMallYqfOrderDailyStatsSum(
			Map<String, Object> paramsMap) throws ServiceException {
		return mallYqfOrderDailyStatDao.queryMallYqfOrderDailyStatsSum(paramsMap);
	}

}
