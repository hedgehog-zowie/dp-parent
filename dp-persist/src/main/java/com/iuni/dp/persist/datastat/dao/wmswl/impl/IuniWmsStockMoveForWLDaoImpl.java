package com.iuni.dp.persist.datastat.dao.wmswl.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.wmswl.IuniWmsStockMoveForWLDao;

@Repository("iuniWmsStockMoveForWLDao")
public class IuniWmsStockMoveForWLDaoImpl extends BaseDaoImpl<Object, Serializable> implements IuniWmsStockMoveForWLDao {

	 private static final Logger logger = LoggerFactory.getLogger(IuniWmsStockSourceForWlDaoImpl.class);

	@Override
	public List<Map<String, Object>> selectIuniWmsStockMoveForWlByPage(Map<String, Object> queryParams) {
		logger.debug("IuniWmsStockMoveForWLDaoImpl.selectIuniWmsStockMoveForWlByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2("IuniWmsStockMoveForWl" 
				+ ".selectIuniWmsStockMoveForWlByPage", queryParams);
		
		logger.debug("IuniWmsStockMoveForWLDaoImpl.selectIuniWmsStockMoveForWlByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
		
	}

	@Override
	public Integer selectIuniWmsStockMoveForWLCount(
			Map<String, Object> queryParams) {
		Integer count = findAllObjectsCount("IuniWmsStockMoveForWl"
				+ ".selectIuniWmsStockMoveForWLCount", queryParams);
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIuniWmsStockMoveForWl2Excel(
			Map<String, Object> queryParams) {
		logger.debug("IuniWmsStockMoveForWLDaoImpl.selectIuniWmsStockMoveForWl2Excel(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2("IuniWmsStockMoveForWl" 
				+ ".selectIuniWmsStockMoveForWl2Excel", queryParams);
		
		logger.debug("IuniWmsStockMoveForWLDaoImpl.selectIuniWmsStockMoveForWl2Excel(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

}
