package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.IuniBuriedPointSite;

/**
 * IuniBuriedPointSite Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-V1.1.0
 */
public interface IuniBuriedPointSiteService {

	/**
	 * queryIbpsByExample
	 * @param params
	 * @return
	 */
	public List<IuniBuriedPointSite> queryIbpsByExample(Map<String, Object> params);
	
	/**
	 * queryIbpsByPage
	 * @param params
	 * @return
	 */
	public List<IuniBuriedPointSite> queryIbpsByPage(Map<String, Object> params);
	
	/**
	 * queryIbpsCount
	 * @param params
	 * @return
	 */
	public Integer queryIbpsCount(Map<String, Object> params);
	
	/**
	 * saveIbps
	 * @param IuniBuriedPointSite
	 * @return
	 */
	public Long saveIbps(IuniBuriedPointSite iuniBuriedPointSite);
	
	/**
	 * updateIbps
	 * @param IuniBuriedPointSite
	 * @return
	 */
	public Integer updateIbps(IuniBuriedPointSite iuniBuriedPointSite);
	
	/**
	 * removeIbpsById
	 * @param id
	 * @return
	 */
	public Integer removeIbpsById(Long id);
	
}
