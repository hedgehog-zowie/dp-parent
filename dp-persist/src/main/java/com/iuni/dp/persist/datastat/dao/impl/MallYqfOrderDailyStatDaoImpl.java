package com.iuni.dp.persist.datastat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.datastat.dao.MallYqfOrderDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallYqfOrderDailyStat;

/**
 *
 * @author ZuoChangjun 2013-9-6
 * @version dp-persist-1.0.0
 */
@Repository("mallYqfOrderDailyStatDao")
public class MallYqfOrderDailyStatDaoImpl implements MallYqfOrderDailyStatDao {

	@Autowired
	@Qualifier("sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	/**
	 * 查询CPS订单数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallYqfOrderDailyStat> queryMallYqfOrderDailyStats(Map<String, Object> paramsMap) {
		return sqlMapClientTemplate.queryForList("MallYqfOrderDailyStat.queryMallYqfOrderDailyStats",paramsMap);
	}
		
	/**
	 * 查询CPS订单数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallYqfOrderDailyStatsCount(Map<String, Object> paramsMap){
		return (Integer) sqlMapClientTemplate.queryForObject("MallYqfOrderDailyStat.queryMallYqfOrderDailyStatsCount",paramsMap);
	}
	
	/**
	 * 查询CPS订单数据(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public MallYqfOrderDailyStat queryMallYqfOrderDailyStatsSum(Map<String, Object> paramsMap){
		return (MallYqfOrderDailyStat) sqlMapClientTemplate.queryForObject("MallYqfOrderDailyStat.queryMallYqfOrderDailyStatsSum",paramsMap);
	}

}
