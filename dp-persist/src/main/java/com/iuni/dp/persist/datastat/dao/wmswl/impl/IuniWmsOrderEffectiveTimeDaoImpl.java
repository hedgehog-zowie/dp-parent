package com.iuni.dp.persist.datastat.dao.wmswl.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsOrderEffectiveTimeDao;

@Repository("iuniWmsOrderInfoWlDao")
public class IuniWmsOrderEffectiveTimeDaoImpl extends BaseDaoImpl<Object,Serializable>  implements IuniWmsOrderEffectiveTimeDao{
	
	private static final Logger logger = LoggerFactory.getLogger(IuniWmsOrderEffectiveTimeDaoImpl.class);
	private static final String SQL_MAP_NAMESPACE = "IuniWmsOrderEffectiveTime";
	@Override
	public List<Map<String, Object>> selectIuniWmsOrderEffectiveWlByPage(
			Map<String, Object> queryParams) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		logger.debug("IuniWmsOrderEffectiveTimeDaoImpl.selectIuniWmsOrderEffectiveWlByPage invoke.");
		Long stime = System.currentTimeMillis();
		
		list = findAllObjectsByPage2(SQL_MAP_NAMESPACE+"."+"iuniWmsPositiveOrderWlByPage", queryParams);
		
		logger.debug("IuniWmsOrderEffectiveTimeDaoImpl.selectIuniWmsOrderEffectiveWlByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniWmsOrderEffectiveWlCount(
			Map<String, Object> queryParams) {
		return findAllObjectsCount(SQL_MAP_NAMESPACE+"."+"iuniWmsPositiveOrderWlCount",queryParams);
	}

	@Override
	public List<Map<String, Object>> selectIuniWmsOrderEffectiveWl2Excel(
			Map<String, Object> queryParams) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		logger.debug("IuniWmsOrderEffectiveTimeDaoImpl.selectIuniWmsOrderEffectiveWl2Excel invoke.");
		Long stime = System.currentTimeMillis();
		
		list = findAllObjectsByPage2(SQL_MAP_NAMESPACE+"."+"iuniWmsPositiveOrderWl2Excel", queryParams);
		
		logger.debug("IuniWmsOrderEffectiveTimeDaoImpl.selectIuniWmsOrderEffectiveWl2Excel(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}
	

	

}
