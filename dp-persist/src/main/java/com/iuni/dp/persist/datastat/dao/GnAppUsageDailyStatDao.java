package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.GnAppUsageDailyStat;

/**
 * 金立相关APP应用基本使用情况日统计 DAO
 * @author CaiKe
 * @version dp-persist-V1.0.2
 */
public interface GnAppUsageDailyStatDao extends
		BaseDao<GnAppUsageDailyStat, Serializable> {

	/**
	 * 新增金立相关APP应用基本使用情况记录
	 * @param gnAppUsageDailyStat
	 * @return
	 */
	public Integer insertGauds(GnAppUsageDailyStat gnAppUsageDailyStat);
	
	/**
	 * 根据ID删除金立相关APP应用基本使用情况记录
	 * @param id
	 * @return
	 */
	public Integer deleteGaudsById(Long id);
	
}
