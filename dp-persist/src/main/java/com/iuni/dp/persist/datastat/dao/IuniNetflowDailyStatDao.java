package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.IuniNetflowDailyStat;

/**
 * IuniNetflowDailyStat DAO
 * @author CaiKe
 * @version dp-persist-V1.0.2
 */
public interface IuniNetflowDailyStatDao extends BaseDao<IuniNetflowDailyStat, Serializable> {

	/**
	 * 按条件查询IUNI商城流量相关数据日统计记录
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIndsByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI商城流量相关数据日统计记录
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIndsByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI商城流量相关数据日统计记录条数
	 * @param params
	 * @return
	 */
	public Integer selectIndsCount(Map<String, Object> params);
	
	/**
	 * 根据时间汇总查询IUNI商城流量相关日统计数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIndsSumByDate(Map<String, Object> params);
	
}
