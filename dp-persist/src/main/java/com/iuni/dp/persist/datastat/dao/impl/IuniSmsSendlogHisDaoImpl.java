package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.IuniSmsSendlogHisDao;
import com.iuni.dp.persist.datastat.model.IuniSmsSendlogHis;

/**
 * IuniSmsSendlogHis DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.2
 */
@Repository("iuniSmsSendlogHisDao")
public class IuniSmsSendlogHisDaoImpl extends
		BaseDaoImpl<IuniSmsSendlogHis, Serializable> implements
		IuniSmsSendlogHisDao {

	private static final Logger logger = LoggerFactory.getLogger(IuniSmsSendlogHisDaoImpl.class);
	
	@Override
	public List<Map<String, Object>> selectSendNumDailyByExample(Map<String, Object> params) {
		logger.debug("IuniSmsSendlogHisDaoImpl.selectSendNumDailyByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniSmsSendlogHis.class.getSimpleName() 
				+ ".selectSendNumDailyByExample", params);
		
		logger.debug("IuniSmsSendlogHisDaoImpl.selectSendNumDailyByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectSendNumDailyByPage(Map<String, Object> params) {
		logger.debug("IuniSmsSendlogHisDaoImpl.selectSendNumDailyByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniSmsSendlogHis.class.getSimpleName() 
				+ ".selectSendNumDailyByPage", params);
		
		logger.debug("IuniSmsSendlogHisDaoImpl.selectSendNumDailyByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectSendNumDailyCount(Map<String, Object> params) {
		logger.debug("IuniSmsSendlogHisDaoImpl.selectSendNumDailyCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniSmsSendlogHis.class.getSimpleName() 
				+ ".selectSendNumDailyCount", params);
		
		logger.debug("IuniSmsSendlogHisDaoImpl.selectSendNumDailyCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectSendNumSumByExample(Map<String, Object> params) {
		logger.debug("IuniSmsSendlogHisDaoImpl.selectSendNumSumByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniSmsSendlogHis.class.getSimpleName() 
				+ ".selectSendNumSumByExample", params);
		
		logger.debug("IuniSmsSendlogHisDaoImpl.selectSendNumSumByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

}
