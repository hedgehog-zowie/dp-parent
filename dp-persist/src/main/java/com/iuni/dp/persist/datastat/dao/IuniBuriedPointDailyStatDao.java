package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.IuniBuriedPointDailyStat;

/**
 * IuniBuriedPointDailyStat DAO
 * 
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.3
 */
public interface IuniBuriedPointDailyStatDao extends
		BaseDao<IuniBuriedPointDailyStat, Serializable> {

	/**
	 * IUNI前端埋点位置每日统计查询
	 * 
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIbpdByExample(
			Map<String, Object> params);

	/**
	 * IUNI前端埋点位置每日统计分页查询
	 * 
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIbpdByPage(Map<String, Object> params);

	/**
	 * IUNI前端埋点位置每日统计查询记录数
	 * 
	 * @param params
	 * @return Integer
	 */
	public Integer selectIbpdCount(Map<String, Object> params);
	
	/**
	 * IUNI前端埋点页面当日实时点击率相关统计查询
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectClickRateTodayByExample(Map<String, Object> params);
	
	/**
	 * IUNI前端埋点页面当日实时点击率相关统计分页查询
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectClickRateTodayByPage(Map<String, Object> params);
	
	/**
	 * IUNI前端埋点页面当日实时点击率相关统计数目查询
	 * @param params
	 * @return
	 */
	public Integer selectClickRateTodayCount(Map<String, Object> params);

}
