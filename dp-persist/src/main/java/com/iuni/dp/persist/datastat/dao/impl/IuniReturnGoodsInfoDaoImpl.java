package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.IuniReturnGoodsInfoDao;
import com.iuni.dp.persist.datastat.model.IuniReturnGoodsInfo;

/**
 * IuniReturnGoodsInfo DAO
 * 
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.5
 */
@Repository("iuniReturnGoodsInfoDao")
public class IuniReturnGoodsInfoDaoImpl extends
		BaseDaoImpl<IuniReturnGoodsInfo, Serializable> implements
		IuniReturnGoodsInfoDao {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<Map<String, Object>> selectIuniOrderRefundDetailsByExample(
			Map<String, Object> params) {
		logger.debug("IuniReturnGoodsInfoDaoImpl.selectIuniOrderRefundDetailsByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniReturnGoodsInfo.class.getSimpleName() 
				+ ".selectIuniOrderRefundDetailsByExample", params);
		
		logger.debug("IuniReturnGoodsInfoDaoImpl.selectIuniOrderRefundDetailsByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniOrderRefundDetailsByPage(
			Map<String, Object> params) {
		logger.debug("IuniReturnGoodsInfoDaoImpl.selectIuniOrderRefundDetailsByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniReturnGoodsInfo.class.getSimpleName() 
				+ ".selectIuniOrderRefundDetailsByPage", params);
		
		logger.debug("IuniReturnGoodsInfoDaoImpl.selectIuniOrderRefundDetailsByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniOrderRefundDetailsCount(Map<String, Object> params) {
		logger.debug("IuniReturnGoodsInfoDaoImpl.selectIuniOrderRefundDetailsCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniReturnGoodsInfo.class.getSimpleName() 
				+ ".selectIuniOrderRefundDetailsCount", params);
		
		logger.debug("IuniReturnGoodsInfoDaoImpl.selectIuniOrderRefundDetailsCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

}
