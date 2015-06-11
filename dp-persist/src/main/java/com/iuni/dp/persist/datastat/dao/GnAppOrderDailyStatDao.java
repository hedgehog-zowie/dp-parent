package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.GnAppOrderDailyStat;

/**
 * 金立相关APP应用关联订单情况日统计 DAO
 * @author CaiKe
 * @version dp-persist-V1.0.2
 */
public interface GnAppOrderDailyStatDao extends
		BaseDao<GnAppOrderDailyStat, Serializable> {

	/**
	 * 按条件查询金立相关App渠道销售情况
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectGnAppChannelSalesByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询金立相关App渠道销售情况
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectGnAppChannelSalesByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关App渠道销售情况记录数
	 * @param params
	 * @return
	 */
	public Integer selectGnAppChannelSalesCount(Map<String, Object> params);
	
	/**
	 * 新增金立相关APP应用关联订单情况记录
	 * @param gnAppOrderDailyStat
	 * @return
	 */
	public Integer insertGaods(GnAppOrderDailyStat gnAppOrderDailyStat);
	
	/**
	 * 根据ID删除金立相关APP应用关联订单情况记录
	 * @param id
	 * @return
	 */
	public Integer deleteGaodsById(Long id);
	
}
