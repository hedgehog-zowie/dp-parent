package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.IuniRegion;

public interface IuniRegionService {

	/**
	 * 根据父节点查询相应地理位置信息
	 * @param params
	 * @return List
	 */
	public List<IuniRegion> queryIuniRegionMapByParent(Map<String,Object> params);
	
}
