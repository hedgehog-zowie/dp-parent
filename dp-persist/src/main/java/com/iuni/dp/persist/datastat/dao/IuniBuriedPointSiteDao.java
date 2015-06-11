package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.IuniBuriedPointSite;

/**
 * IuniBuriedPointSite DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-V1.1.0
 */
public interface IuniBuriedPointSiteDao extends
		BaseDao<IuniBuriedPointSite, Serializable> {

	/**
	 * selectIbpsByExample
	 * @param params
	 * @return
	 */
	public List<IuniBuriedPointSite> selectIbpsByExample(Map<String, Object> params);
	
	/**
	 * selectIbpsByPage
	 * @param params
	 * @return
	 */
	public List<IuniBuriedPointSite> selectIbpsByPage(Map<String, Object> params);
	
	/**
	 * selectIbpsCount
	 * @param params
	 * @return
	 */
	public Integer selectIbpsCount(Map<String, Object> params);
	
	/**
	 * insertIbps
	 * @param IuniBuriedPointSite
	 * @return
	 */
	public Long insertIbps(IuniBuriedPointSite iuniBuriedPointSite);
	
	/**
	 * updateIbps
	 * @param IuniBuriedPointSite
	 * @return
	 */
	public Integer updateIbps(IuniBuriedPointSite iuniBuriedPointSite);
	
	/**
	 * deleteIbpsById
	 * @param id
	 * @return
	 */
	public Integer deleteIbpsById(Long id);
	
}
