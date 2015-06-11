package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.MallBuriedPointSite;

/**
 * MallBuriedPointSite DAO
 * @author CaiKe
 * @version dp-persist-1.0.1
 */
public interface MallBuriedPointSiteDao extends BaseDao<MallBuriedPointSite, Serializable> {

	/**
	 * selectMbpsByExample
	 * @param params
	 * @return
	 */
	public List<MallBuriedPointSite> selectMbpsByExample(Map<String, Object> params);
	
	/**
	 * selectMbpsByPage
	 * @param params
	 * @return
	 */
	public List<MallBuriedPointSite> selectMbpsByPage(Map<String, Object> params);
	
	/**
	 * selectMbpsCount
	 * @param params
	 * @return
	 */
	public Integer selectMbpsCount(Map<String, Object> params);
	
	/**
	 * 前端埋点页面点击率相关统计查询
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectClickRateByExample(Map<String, Object> params);
	
	/**
	 * 前端埋点页面点击率相关统计分页查询
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectClickRateByPage(Map<String, Object> params);
	
	/**
	 * 前端埋点页面点击率相关统计数目查询
	 * @param params
	 * @return
	 */
	public Integer selectClickRateCount(Map<String, Object> params);
	
	/**
	 * insertMbps
	 * @param mallBuriedPointSite
	 * @return
	 */
	public Long insertMbps(MallBuriedPointSite mallBuriedPointSite);
	
	/**
	 * updateMbps
	 * @param mallBuriedPointSite
	 * @return
	 */
	public Integer updateMbps(MallBuriedPointSite mallBuriedPointSite);
	
	/**
	 * deleteMbpsById
	 * @param id
	 * @return
	 */
	public Integer deleteMbpsById(Long id);
	
}
