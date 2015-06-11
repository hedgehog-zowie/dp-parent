package com.iuni.dp.service.datastat.service;

import com.iuni.dp.persist.datastat.model.GnAppUsageDailyStat;

/**
 * 金立相关APP应用基本使用情况日统计 Service
 * @author CaiKe
 * @version dp-service-V1.0.2
 */
public interface GnAppUsageDailyStatService {

	/**
	 * 新增金立相关APP应用基本使用情况记录
	 * @param gnAppUsageDailyStat
	 * @return
	 */
	public Integer saveGauds(GnAppUsageDailyStat gnAppUsageDailyStat);
	
	/**
	 * 根据ID删除金立相关APP应用基本使用情况记录
	 * @param id
	 * @return
	 */
	public Integer removeGaudsById(Long id);
	
}
