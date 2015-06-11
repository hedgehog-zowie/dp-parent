package com.iuni.dp.persist.datastat.dao.wms.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.IuniWmsDailyStock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.wms.IuniWmsStockDao;

/**
 * IuniWmsStock DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.5
 */
@Repository("iuniWmsStockDao")
public class IuniWmsStockDaoImpl extends
		BaseDaoImpl<IuniWmsDailyStock, Serializable> implements IuniWmsStockDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public List<Map<String, Object>> selectIuniWmsStockDetailsByExample(
			Map<String, Object> params) {
		logger.debug("IuniWmsStockDaoImpl.selectIuniWmsStockDetailsByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsDailyStock.class.getSimpleName()
				+ ".selectIuniWmsStockDetailsByExample", params);
		
		logger.debug("IuniWmsStockDaoImpl.selectIuniWmsStockDetailsByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniWmsStockDetailsByPage(
			Map<String, Object> params) {
		logger.debug("IuniWmsStockDaoImpl.selectIuniWmsStockDetailsByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsDailyStock.class.getSimpleName()
				+ ".selectIuniWmsStockDetailsByPage", params);
		
		logger.debug("IuniWmsStockDaoImpl.selectIuniWmsStockDetailsByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniWmsStockDetailsCount(Map<String, Object> params) {
		logger.debug("IuniWmsStockDaoImpl.selectIuniWmsStockDetailsCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniWmsDailyStock.class.getSimpleName()
				+ ".selectIuniWmsStockDetailsCount", params);
		
		logger.debug("IuniWmsStockDaoImpl.selectIuniWmsStockDetailsCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

}
