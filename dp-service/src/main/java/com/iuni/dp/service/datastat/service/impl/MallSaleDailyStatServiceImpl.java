package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.MallSaleDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallSaleDailyStat;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.datastat.service.MallSaleDailyStatService;

/**
 *
 * @author ZuoChangjun 2013-8-20
 * @version dp-service-1.0.0
 */
@Service("mallSaleDailyStatService")
public class MallSaleDailyStatServiceImpl implements MallSaleDailyStatService {
	private final static Logger logger = LoggerFactory.getLogger(MallSaleDailyStatServiceImpl.class);
	
	@Autowired
	private MallSaleDailyStatDao mallSaleDailyStatDao;
	/**
	 * 查询销售数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallSaleDailyStat> queryMallSaleDailyStats(Map<String, Object> paramsMap)throws ServiceException{
		return mallSaleDailyStatDao.queryMallSaleDailyStats(paramsMap);
	}
		
	/**
	 * 查询销售数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallSaleDailyStatsCount(Map<String, Object> paramsMap)throws ServiceException{
		return mallSaleDailyStatDao.queryMallSaleDailyStatsCount(paramsMap);
	}
	
	/**
	 * 查询销售数据(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public MallSaleDailyStat queryMallSaleDailyStatsSum(Map<String, Object> paramsMap)throws ServiceException{
		return mallSaleDailyStatDao.queryMallSaleDailyStatsSum(paramsMap);
	}

}
