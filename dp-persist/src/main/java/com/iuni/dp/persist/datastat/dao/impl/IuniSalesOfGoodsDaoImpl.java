package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.IuniSalesOfGoodsDao;

@Repository("iuniSalesOfGoodsDao")
public class IuniSalesOfGoodsDaoImpl extends BaseDaoImpl<Object,Serializable> implements IuniSalesOfGoodsDao {
	private static final Logger logger = LoggerFactory.getLogger(IuniSalesOfGoodsDaoImpl.class);
	private static final String SQL_MAP_NAMESPACE = "IuniOrderInfo";
	
	@Override
	public List<Map<String, Object>> selectSalesOfGoodsByPage(
			Map<String, Object> paramsMap) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		logger.debug("IuniSalesOfGoodsDaoImpl.selectSalesOfGoodsByPage invoke.");
		Long stime = System.currentTimeMillis();
		
		list = findAllObjectsByPage2(SQL_MAP_NAMESPACE+"."+"iuniSalesOfGoodsByPage", paramsMap);
		
		logger.debug("IuniSalesOfGoodsDaoImpl.selectSalesOfGoodsByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectSalesOfGoodsCount(
			Map<String, Object> paramsMap) {
		Integer count = 0;
		logger.debug("IuniSalesOfGoodsDaoImpl.selectSalesOfGoodsCount invoke.");
		Long stime = System.currentTimeMillis();
		
		count = findAllObjectsCount(SQL_MAP_NAMESPACE+"."+"iuniSalesOfGoodslCount", paramsMap);
		
		logger.debug("IuniSalesOfGoodsDaoImpl.selectSalesOfGoodsCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectSalesOfGoods2Excel(
			Map<String, Object> paramsMap) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		logger.debug("IuniSalesOfGoodsDaoImpl.selectSalesOfGoods2Excel invoke.");
		Long stime = System.currentTimeMillis();
		
		list = findAllObjectsByPage2(SQL_MAP_NAMESPACE+"."+"iuniSalesOfGoods2Excel", paramsMap);
		
		logger.debug("IuniSalesOfGoodsDaoImpl.selectSalesOfGoods2Excel(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

}
