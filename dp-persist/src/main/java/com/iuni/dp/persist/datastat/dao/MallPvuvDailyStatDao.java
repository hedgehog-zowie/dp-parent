package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.MallPvuvDailyStat;

/**
 * 商城PV/UV基础数据日统计DAO
 * @author CaiKe
 * @version V1.0.0
 */
public interface MallPvuvDailyStatDao extends BaseDao<MallPvuvDailyStat, Serializable> {

	/**
	 * 根据ID查询商城PV/UV基础数据日统计
	 * @param Integer id
	 * @return MallPvuvDailyStat
	 */
	public MallPvuvDailyStat selectMallPvuvDailyStatById(Integer id);
	
	/**
	 * 商城PV/UV基础数据日统计条件查询
	 * @param Map<String, Object> params
	 * @return List<MallPvuvDailyStat>
	 */
	public List<MallPvuvDailyStat> selectAllByExample(Map<String, Object> params);
	
	/**
	 * 商城PV/UV基础数据日统计分页条件查询
	 * @param Map<String, Object> params
	 * @return List<MallPvuvDailyStat>
	 */
	public List<MallPvuvDailyStat> selectMallPvuvDailyStatByPage(Map<String, Object> params);
	
	/**
	 * 商城PV/UV基础数据日统计总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectTotalCountByExample(Map<String, Object> params);
	
	/**
	 * 商城PV/UV、订单相关日常基础数据日统计条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectMallDailyBaseStatByExample(Map<String, Object> params);
	
	/**
	 * 商城PV/UV、订单相关日常基础数据日统计分页条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectMallDailyBaseStatByPage(Map<String, Object> params);
	
	/**
	 * 商城PV/UV、订单相关日常基础数据日统计总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectMallDailyBaseStatCount(Map<String, Object> params);
	
	/**
	 * 玩机圈PV/UV相关日常基础数据日统计条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectWjqDailyBaseStatByExample(Map<String, Object> params);
	
	/**
	 * 玩机圈PV/UV相关日常基础数据日统计分页条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectWjqDailyBaseStatByPage(Map<String, Object> params);
	
	/**
	 * 玩机圈PV/UV相关日常基础数据日统计总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectWjqDailyBaseStatCount(Map<String, Object> params);
	
	/**
	 * 玩机圈PV/UV相关日常基础数据月统计条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectWjqMonthlyBaseStatByExample(Map<String, Object> params);
	
	/**
	 * 玩机圈PV/UV相关日常基础数据月统计分页条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectWjqMonthlyBaseStatByPage(Map<String, Object> params);
	
	/**
	 * 玩机圈PV/UV相关日常基础数据月统计总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectWjqMonthlyBaseStatCount(Map<String, Object> params);
	
	/**
	 * 新增商城PV/UV基础数据日统计
	 * @param MallPvuvDailyStat mallPvuvDailyStat
	 * @return Integer
	 */
	public Integer insertMallPvuvDailyStat(MallPvuvDailyStat mallPvuvDailyStat);
	
	/**
	 * 删除商城PV/UV基础数据日统计
	 * @param Integer id
	 * @return Integer
	 */
	public Integer deleteMallPvuvDailyStatById(Integer id);
	
}
