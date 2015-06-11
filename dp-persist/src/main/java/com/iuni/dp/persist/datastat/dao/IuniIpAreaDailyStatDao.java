package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.IuniIpAreaDailyStat;

/**
 * IuniIpAreaDailyStat DAO
 * @author CaiKe
 * @version dp-persist-V1.0.2
 */
public interface IuniIpAreaDailyStatDao extends BaseDao<IuniIpAreaDailyStat, Serializable> {
	
	/**
	 * 按条件查询IUNI商城独立IP数区域分布
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIidsByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI商城独立IP数区域分布
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIidsByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI商城独立IP数区域分布记录数
	 * @param params
	 * @return Integer
	 */
	public Integer selectIidsCount(Map<String, Object> params);
	
	/**
	 * 按条件查询时间段范围内IUNI商城独立IP汇总数区域分布
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIidsSumOnDateByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询时间段范围内IUNI商城独立IP汇总数区域分布
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIidsSumOnDateByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询时间段范围内IUNI商城独立IP汇总数区域分布记录数
	 * @param params
	 * @return Integer
	 */
	public Integer selectIidsSumOnDateCount(Map<String, Object> params);
	
}
