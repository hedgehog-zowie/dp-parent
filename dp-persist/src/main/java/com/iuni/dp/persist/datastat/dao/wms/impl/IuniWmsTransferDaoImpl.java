package com.iuni.dp.persist.datastat.dao.wms.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.wms.IuniWmsTransferDao;
import com.iuni.dp.persist.datastat.model.IuniWmsTransfer;

/**
 * IuniWmsTransfer DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.5
 */
@Repository("iuniWmsTransferDao")
public class IuniWmsTransferDaoImpl extends
		BaseDaoImpl<IuniWmsTransfer, Serializable> implements
		IuniWmsTransferDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<Map<String, Object>> selectIuniWmsTransferStatByExample(
			Map<String, Object> params) {
		logger.debug("IuniWmsTransferDaoImpl.selectIuniWmsTransferStatByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsTransfer.class.getSimpleName() 
				+ ".selectIuniWmsTransferStatByExample", params);
		
		logger.debug("IuniWmsTransferDaoImpl.selectIuniWmsTransferStatByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniWmsTransferStatByPage(
			Map<String, Object> params) {
		logger.debug("IuniWmsTransferDaoImpl.selectIuniWmsTransferStatByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsTransfer.class.getSimpleName() 
				+ ".selectIuniWmsTransferStatByPage", params);
		
		logger.debug("IuniWmsTransferDaoImpl.selectIuniWmsTransferStatByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniWmsTransferStatCount(Map<String, Object> params) {
		logger.debug("IuniWmsTransferDaoImpl.selectIuniWmsTransferStatCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniWmsTransfer.class.getSimpleName() 
				+ ".selectIuniWmsTransferStatCount", params);
		
		logger.debug("IuniWmsTransferDaoImpl.selectIuniWmsTransferStatCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIuniInTransferDetailsByExample(
			Map<String, Object> params) {
		logger.debug("IuniWmsTransferDaoImpl.selectIuniInTransferDetailsByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsTransfer.class.getSimpleName() 
				+ ".selectIuniInTransferDetailsByExample", params);
		
		logger.debug("IuniWmsTransferDaoImpl.selectIuniInTransferDetailsByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniInTransferDetailsByPage(
			Map<String, Object> params) {
		logger.debug("IuniWmsTransferDaoImpl.selectIuniInTransferDetailsByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsTransfer.class.getSimpleName() 
				+ ".selectIuniInTransferDetailsByPage", params);
		
		logger.debug("IuniWmsTransferDaoImpl.selectIuniInTransferDetailsByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniInTransferDetailsCount(Map<String, Object> params) {
		logger.debug("IuniWmsTransferDaoImpl.selectIuniInTransferDetailsCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniWmsTransfer.class.getSimpleName() 
				+ ".selectIuniInTransferDetailsCount", params);
		
		logger.debug("IuniWmsTransferDaoImpl.selectIuniInTransferDetailsCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}
	
	
	@Override
	public List<Map<String, Object>> selectIuniWmsTransferStatForWlByPage(
			Map<String, Object> queryParams) {
		return findAllObjectsByPage2(IuniWmsTransfer.class.getSimpleName() + ".selectIuniWmsTransferStatForWlByPage", queryParams);
	}

	@Override
	public Integer selectIuniWmsTransferStatForWlCount(
			Map<String, Object> params) {
		logger.debug("IuniWmsTransferStatForWlDaoImpl.selectIuniWmsTransferStatForWlCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		//Integer count = (Integer) getObjectByProperty(IuniWmsTransfer.class.getSimpleName() 
		//		+ ".selectIuniWmsTransferStatForWlCount", params);
		
		Integer count = findAllObjectsCount(IuniWmsTransfer.class.getSimpleName()+ ".selectIuniWmsTransferStatForWlCount", params);
		
		logger.debug("IuniWmsTransferStatForWlDaoImpl.selectIuniWmsTransferStatForWlCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIuniWmsTransferForWL2Excel(
			Map<String, Object> queryParams) {
		logger.debug("IuniWmsTransferStatForWlDaoImpl.selectIuniWmsTransferForWL2Excel(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsTransfer.class.getSimpleName() 
				+ ".selectIuniWmsTransferForWL2Excel", queryParams);
		
		logger.debug("IuniWmsTransferStatForWlDaoImpl.selectIuniWmsTransferForWL2Excel(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	
}
