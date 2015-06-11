package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.IuniOrderInfo;

/**
 * IuniOrderInfo DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.2
 */
public interface IuniOrderInfoDao extends BaseDao<IuniOrderInfo, Serializable> {

	/**
	 * IUNI商城订单区域分布按日统计查询
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniOrderAreaStatByExample(Map<String, Object> params);
	
	/**
	 * IUNI商城订单区域分布按日统计分页查询
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniOrderAreaStatByPage(Map<String, Object> params);
	
	/**
	 * IUNI商城订单区域分布按日统计查询记录数
	 * @return List
	 */
	public Integer selectIuniOrderAreaStatCount(Map<String, Object> params);
	
	/**
	 * IUNI商城订单区域分布汇总统计查询
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniOrderAreaSumByExample(Map<String, Object> params);
	
	/**
	 * IUNI商城订单区域分布汇总分页查询
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniOrderAreaSumByPage(Map<String, Object> params);
	
	/**
	 * IUNI商城订单区域分布汇总查询记录数
	 * @return List
	 */
	public Integer selectIuniOrderAreaSumCount(Map<String, Object> params);
	
	/**
	 * 订单相关客服指标统计数据查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectOrderStat4Cs(Map<String, Object> params);

    /**
     * 在线客服销售数据查询
     * @param params
     * @return
     */
    public List<Map<String, Object>> selectOrderStat4CsByUser(Map<String, Object> params);
}
