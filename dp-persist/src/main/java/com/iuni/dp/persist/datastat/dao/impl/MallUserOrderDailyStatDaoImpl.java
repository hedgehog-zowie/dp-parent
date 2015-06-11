package com.iuni.dp.persist.datastat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.datastat.dao.MallUserOrderDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallUserOrderDailyStat;

/**
 *
 * @author ZuoChangjun 2013-9-6
 * @version dp-persist-1.0.0
 */
@Repository("mallUserOrderDailyStatDao")
public class MallUserOrderDailyStatDaoImpl implements MallUserOrderDailyStatDao {

	@Autowired
	@Qualifier("sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	/**
	 * 查询注册用户及订单数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallUserOrderDailyStat> queryMallUserOrderDailyStats(Map<String, Object> paramsMap) {
		return sqlMapClientTemplate.queryForList("MallUserOrderDailyStat.queryMallUserOrderDailyStats",paramsMap);
	}
		
	/**
	 * 查询注册用户及订单数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallUserOrderDailyStatsCount(Map<String, Object> paramsMap){
		return (Integer) sqlMapClientTemplate.queryForObject("MallUserOrderDailyStat.queryMallUserOrderDailyStatsCount",paramsMap);
	}
	
	/**
	 * 查询注册用户及订单数据(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public MallUserOrderDailyStat queryMallUserOrderDailyStatsSum(Map<String, Object> paramsMap){
		return (MallUserOrderDailyStat) sqlMapClientTemplate.queryForObject("MallUserOrderDailyStat.queryMallUserOrderDailyStatsSum",paramsMap);
	}

}
