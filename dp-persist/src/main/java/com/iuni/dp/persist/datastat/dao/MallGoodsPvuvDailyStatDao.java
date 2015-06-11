package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.MallGoodsPvuvDailyStat;

/**
 * 商城商品PV/UV基础数据日统计表DAO
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public interface MallGoodsPvuvDailyStatDao extends BaseDao<MallGoodsPvuvDailyStat, Serializable> {

	/**
	 * 根据ID查询商城商品PV/UV基础数据日统计
	 * @param Integer id
	 * @return MallGoodsPvuvDailyStat
	 */
	public MallGoodsPvuvDailyStat selectMallGoodsPvuvDailyStatById(Integer id);
	
	/**
	 * 商城商品PV/UV基础数据日统计条件查询
	 * @param Map<String, Object> params
	 * @return List<MallGoodsPvuvDailyStat>
	 */
	public List<MallGoodsPvuvDailyStat> selectAllByExample(Map<String, Object> params);
	
	/**
	 * 商城商品PV/UV基础数据日统计分页条件查询
	 * @param Map<String, Object> params
	 * @return List<MallGoodsPvuvDailyStat>
	 */
	public List<MallGoodsPvuvDailyStat> selectMallGoodsPvuvDailyStatByPage(Map<String, Object> params);
	
	/**
	 * 商城商品PV/UV基础数据日统计总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectTotalCountByExample(Map<String, Object> params);
	
	/**
	 * 商城商品PV/UV、订单相关日常基础数据日统计条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectMallGoodsDailyBaseStatByExample(Map<String, Object> params);
	
	/**
	 * 商城商品PV/UV、订单相关日常基础数据日统计分页条件查询
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectMallGoodsDailyBaseStatByPage(Map<String, Object> params);
	
	/**
	 * 商城商品PV/UV、订单相关日常基础数据日统计总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectMallGoodsDailyBaseStatCount(Map<String, Object> params);
	
	/**
	 * 新增商城商品PV/UV基础数据日统计
	 * @param MallGoodsPvuvDailyStat mallGoodsPvuvDailyStat
	 * @return Integer
	 */
	public Integer insertMallGoodsPvuvDailyStat(MallGoodsPvuvDailyStat mallGoodsPvuvDailyStat);
	
	/**
	 * 删除商城商品PV/UV基础数据日统计
	 * @param Integer id
	 * @return Integer
	 */
	public Integer deleteMallGoodsPvuvDailyStatById(Integer id);
	
}
