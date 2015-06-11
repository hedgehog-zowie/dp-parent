package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.IuniRegion;

/**
 * IuniRegion DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.2
 */
public interface IuniRegionDao extends BaseDao<IuniRegion, Serializable> {

	/**
	 * 根据父节点查询相应地理位置信息
	 * @param params
	 * @return List
	 */
	public List<IuniRegion> selectIuniRegionMapByParent(Map<String,Object> params);
	
}
