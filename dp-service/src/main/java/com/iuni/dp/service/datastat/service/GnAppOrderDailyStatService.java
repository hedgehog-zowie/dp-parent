package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.GnAppOrderDailyStat;

/**
 * 金立相关APP应用关联订单情况日统计 Service
 * @author CaiKe
 * @version dp-service-V1.0.2
 */
public interface GnAppOrderDailyStatService {

	/**
	 * 按条件查询金立相关App渠道销售情况
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryGnAppChannelSalesByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询金立相关App渠道销售情况
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryGnAppChannelSalesByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关App渠道销售情况记录数
	 * @param params
	 * @return
	 */
	public Integer queryGnAppChannelSalesCount(Map<String, Object> params);
	
	/**
	 * 新增金立相关APP应用关联订单情况记录
	 * @param gnAppOrderDailyStat
	 * @return
	 */
	public Integer saveGaods(GnAppOrderDailyStat gnAppOrderDailyStat);
	
	/**
	 * 根据ID删除金立相关APP应用关联订单情况记录
	 * @param id
	 * @return
	 */
	public Integer removeGaodsById(Long id);
	
}
