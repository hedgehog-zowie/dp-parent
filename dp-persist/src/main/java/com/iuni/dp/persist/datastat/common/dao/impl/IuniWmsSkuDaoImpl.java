package com.iuni.dp.persist.datastat.common.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.common.model.OrderSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.common.dao.IuniWmsSkuDao;
import com.iuni.dp.persist.datastat.common.model.IuniWmsSku;

@Repository("iuniWmsSkuDao")
public class IuniWmsSkuDaoImpl extends BaseDaoImpl<IuniWmsSku, Serializable> implements IuniWmsSkuDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static final String SQL_MAP_NAMESPACE = "IuniWmsSku";

	@Override
	public List<Map<String, Object>> selectIuniWmsSku(Map<String,Object> params) {
		List<Map<String, Object>> list = findAllObjectsByPage2(SQL_MAP_NAMESPACE+"."+"selectIuniWmsSku", params);
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniWmsWaresCategory() {
		logger.debug("IuniWmsSkuDaoImpl.selectIuniWmsWaresCategory() invoke");

		Long startTime = System.currentTimeMillis();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = findAllObjectsByPage2(SQL_MAP_NAMESPACE + ".selectWaresCategory", null);
		} catch (Exception e) {
			logger.error("IuniWmsSkuDaoImpl.selectIuniWmsWaresCategory() failed, error mesg:", e.getMessage());
		}

		logger.debug("IuniWmsSkuDaoImpl.selectIuniWmsWaresCategory() success: costTime={}ms", new Object[]{System.currentTimeMillis() - startTime});

		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniWmsWares(Integer catId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("catId", catId);

		logger.debug("IuniWmsSkuDaoImpl.selectIuniWmsWares() invoke");

		Long startTime = System.currentTimeMillis();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = findAllObjectsByPage2(SQL_MAP_NAMESPACE + ".selectWares", params);
		} catch (Exception e) {
			logger.error("IuniWmsSkuDaoImpl.selectIuniWmsWares() failed, error mesg:", e.getMessage());
		}

		logger.debug("IuniWmsSkuDaoImpl.selectIuniWmsWares() success: costTime={}ms", new Object[]{System.currentTimeMillis() - startTime});

		return list;
	}

}
