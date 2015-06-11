package com.iuni.dp.persist.datastat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.datastat.dao.IuniSaleDailyStatDao;
import com.iuni.dp.persist.datastat.model.IuniDayOrderConvertRate;
import com.iuni.dp.persist.datastat.model.IuniDayPvuvUserReg;
import com.iuni.dp.persist.datastat.model.IuniSaleDailyStat;

/**
 *
 * @author ZuoChangjun 2013-8-20
 * @version dp-persist-1.0.0
 */
@Repository("iuniSaleDailyStatDao")
public class IuniSaleDailyStatDaoImpl implements IuniSaleDailyStatDao {
	@Autowired
	@Qualifier("sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	/**
	 * 查询销售数据
	 * 
	 * @author ZuoChangjun
	 */
	public List<IuniSaleDailyStat> queryIuniSaleDailyStats(Map<String, Object> paramsMap) {
		return sqlMapClientTemplate.queryForList("IuniSaleDailyStat.queryIuniSaleDailyStats",paramsMap);
	}
		
	/**
	 * 查询销售数据:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryIuniSaleDailyStatsCount(Map<String, Object> paramsMap){
		return (Integer) sqlMapClientTemplate.queryForObject("IuniSaleDailyStat.queryIuniSaleDailyStatsCount",paramsMap);
	}
	
	/**
	 * 查询销售数据(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public IuniSaleDailyStat queryIuniSaleDailyStatsSum(Map<String, Object> paramsMap){
		return (IuniSaleDailyStat) sqlMapClientTemplate.queryForObject("IuniSaleDailyStat.queryIuniSaleDailyStatsSum",paramsMap);
	}
	
	/**
	 * 查询订单转化率
	 * 
	 * @author ZuoChangjun
	 */
	public List<IuniDayOrderConvertRate> queryIuniDayOrderConvertRates(Map<String, Object> paramsMap){
		return sqlMapClientTemplate.queryForList("IuniSaleDailyStat.queryIuniDayOrderConvertRates",paramsMap);
	}
		
	/**
	 * 查询订单转化率:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryIuniDayOrderConvertRatesCount(Map<String, Object> paramsMap){
		return (Integer) sqlMapClientTemplate.queryForObject("IuniSaleDailyStat.queryIuniDayOrderConvertRatesCount",paramsMap);
	}
	
	/**
	 * 查询订单转化率(总计)
	 * 
	 * @author ZuoChangjun
	 */
	public IuniDayOrderConvertRate queryIuniDayOrderConvertRatesSum(Map<String, Object> paramsMap){
		return (IuniDayOrderConvertRate) sqlMapClientTemplate.queryForObject("IuniSaleDailyStat.queryIuniDayOrderConvertRatesSum",paramsMap);
	}
	
	/**
	 * 用户注册：列表
	 * 
	 * @author ZuoChangjun
	 */
	public List<IuniDayPvuvUserReg> queryIuniUserRegDailyStats(Map<String, Object> paramsMap){
		return sqlMapClientTemplate.queryForList("IuniSaleDailyStat.queryIuniUserRegDailyStats",paramsMap);
	}
		
	/**
	 * 用户注册:总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryIuniUserRegDailyStatsCount(Map<String, Object> paramsMap){
		return (Integer) sqlMapClientTemplate.queryForObject("IuniSaleDailyStat.queryIuniUserRegDailyStatsCount",paramsMap);
	}
	
	/**
	 * 用户注册：总计
	 * 
	 * @author ZuoChangjun
	 */
	public IuniDayPvuvUserReg queryIuniUserRegDailyStatsSum(Map<String, Object> paramsMap){
		return (IuniDayPvuvUserReg) sqlMapClientTemplate.queryForObject("IuniSaleDailyStat.queryIuniUserRegDailyStatsSum",paramsMap);
	}
}
