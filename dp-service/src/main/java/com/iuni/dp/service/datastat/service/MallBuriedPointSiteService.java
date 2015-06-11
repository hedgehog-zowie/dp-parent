package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallBuriedPointSite;

/**
 * MallBuriedPointSite Service
 * @author CaiKe
 * @version dp-service-V1.0.1
 */
public interface MallBuriedPointSiteService {

	/**
	 * queryMbpsByExample
	 * @param params
	 * @return
	 */
	public List<MallBuriedPointSite> queryMbpsByExample(Map<String, Object> params);
	
	/**
	 * queryMbpsByPage
	 * @param params
	 * @return
	 */
	public List<MallBuriedPointSite> queryMbpsByPage(Map<String, Object> params);
	
	/**
	 * queryMbpsCount
	 * @param params
	 * @return
	 */
	public Integer queryMbpsCount(Map<String, Object> params);
	
	/**
	 * 前端埋点页面点击率相关统计查询
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryClickRateByExample(Map<String, Object> params);
	
	/**
	 * 前端埋点页面点击率相关统计分页查询
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryClickRateByPage(Map<String, Object> params);
	
	/**
	 * 前端埋点页面点击率相关统计数目查询
	 * @param params
	 * @return
	 */
	public Integer queryClickRateCount(Map<String, Object> params);
	
	/**
	 * insertMbps
	 * @param mallBuriedPointSite
	 * @return
	 */
	public Long saveMbps(MallBuriedPointSite mallBuriedPointSite);
	
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
	public Integer removeMbpsById(Long id);
	
}
