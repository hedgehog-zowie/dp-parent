package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.MallGoodsOrderDailyStat;

/**
 * 商城订单商品基础数据日统计DAO
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public interface MallGoodsOrderDailyStatDao extends BaseDao<MallGoodsOrderDailyStat, Serializable> {

	/**
	 * 根据ID查询商城订单商品基础数据日统计
	 * @param Integer id
	 * @return MallGoodsOrderDailyStat
	 */
	public MallGoodsOrderDailyStat selectMallGoodsOrderDailyStatById(Integer id);
	
	/**
	 * 商城订单商品基础数据日统计条件查询
	 * @param Map<String, Object> params
	 * @return List<MallGoodsOrderDailyStat>
	 */
	public List<MallGoodsOrderDailyStat> selectAllByExample(Map<String, Object> params);
	
	/**
	 * 商城订单商品基础数据日统计分页条件查询
	 * @param Map<String, Object> params
	 * @return List<MallGoodsOrderDailyStat>
	 */
	public List<MallGoodsOrderDailyStat> selectMallGoodsOrderDailyStatByPage(Map<String, Object> params);
	
	/**
	 * 商城订单商品基础数据日统计总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectTotalCountByExample(Map<String, Object> params);
	
	/**
	 * 新增商城订单商品基础数据日统计
	 * @param MallGoodsOrderDailyStat mallGoodsOrderDailyStat
	 * @return Integer
	 */
	public Integer insertMallGoodsOrderDailyStat(MallGoodsOrderDailyStat mallGoodsOrderDailyStat);
	
	/**
	 * 删除商城订单商品基础数据日统计
	 * @param Integer id
	 * @return Integer
	 */
	public Integer deleteMallGoodsOrderDailyStatById(Integer id);
	
}
