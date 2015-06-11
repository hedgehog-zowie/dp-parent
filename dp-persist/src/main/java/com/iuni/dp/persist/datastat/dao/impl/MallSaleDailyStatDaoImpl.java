package com.iuni.dp.persist.datastat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.datastat.dao.MallSaleDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallSaleDailyStat;

/**
 *
 * @author ZuoChangjun 2013-8-20
 * @version dp-persist-1.0.0
 */
@Repository("mallSaleDailyStatDao")
public class MallSaleDailyStatDaoImpl implements MallSaleDailyStatDao {
	@Autowired
	@Qualifier("sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	/**
	 * 查询销售数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallSaleDailyStat> queryMallSaleDailyStats(Map<String, Object> paramsMap) {
		return sqlMapClientTemplate.queryForList("MallSaleDailyStat.queryMallSaleDailyStats",paramsMap);
	}
		
	/**
	 * 查询销售数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallSaleDailyStatsCount(Map<String, Object> paramsMap){
		return (Integer) sqlMapClientTemplate.queryForObject("MallSaleDailyStat.queryMallSaleDailyStatsCount",paramsMap);
	}
	
	/**
	 * 查询销售数据(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public MallSaleDailyStat queryMallSaleDailyStatsSum(Map<String, Object> paramsMap){
		return (MallSaleDailyStat) sqlMapClientTemplate.queryForObject("MallSaleDailyStat.queryMallSaleDailyStatsSum",paramsMap);
	}
}
