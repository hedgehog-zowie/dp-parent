package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.IuniPageNetflowDailyStat;

/**
 * IuniPageNetflowDailyStat DAO
 * 
 * @author CaiKe
 * @version dp-persist-V1.0.2
 */
public interface IuniPageNetflowDailyStatDao extends
		BaseDao<IuniPageNetflowDailyStat, Serializable> {

	/**
	 * 按条件查询IUNI商城页面流量相关数据日统计记录
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIpndsByExample(
			Map<String, Object> params);

	/**
	 * 按条件分页查询IUNI商城页面流量相关数据日统计记录
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIpndsByPage(
			Map<String, Object> params);

	/**
	 * 按条件查询IUNI商城页面流量相关数据日统计记录条数
	 * 
	 * @param params
	 * @return
	 */
	public Integer selectIpndsCount(Map<String, Object> params);

	/**
	 * 根据时间汇总查询IUNI商城页面流量相关日统计数据
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIpndsSumOnDateByExample(
			Map<String, Object> params);

	/**
	 * 根据时间汇总分页查询IUNI商城页面流量相关日统计数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIpndsSumOnDateByPage(
			Map<String, Object> params);

	/**
	 * 根据时间汇总查询IUNI商城页面流量相关日统计数据记录数
	 * @param params
	 * @return
	 */
	public Integer selectIpndsSumOnDateCount(Map<String, Object> params);

}
