package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.IuniOrderInfoDao;
import com.iuni.dp.persist.datastat.model.IuniOrderInfo;

/**
 * IuniOrderInfo DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-perisit-1.1.2
 */
@Repository("iuniOrderInfoDao")
public class IuniOrderInfoDaoImpl extends BaseDaoImpl<IuniOrderInfo, Serializable> implements IuniOrderInfoDao {

	private final Logger logger = LoggerFactory.getLogger(IuniOrderInfoDaoImpl.class);
	
	@Override
	public List<Map<String, Object>> selectIuniOrderAreaStatByExample(
			Map<String, Object> params) {
		logger.debug("IuniOrderInfoDaoImpl.selectIuniOrderAreaStatByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniOrderInfo.class.getSimpleName() 
				+ ".selectIuniOrderAreaStatByExample", params);
		
		logger.debug("IuniOrderInfoDaoImpl.selectIuniOrderAreaStatByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniOrderAreaStatByPage(
			Map<String, Object> params) {
		logger.debug("IuniOrderInfoDaoImpl.selectIuniOrderAreaStatByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniOrderInfo.class.getSimpleName() 
				+ ".selectIuniOrderAreaStatByPage", params);
		
		logger.debug("IuniOrderInfoDaoImpl.selectIuniOrderAreaStatByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniOrderAreaStatCount(Map<String, Object> params) {
		logger.debug("IuniOrderInfoDaoImpl.selectIuniOrderAreaStatCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniOrderInfo.class.getSimpleName() 
				+ ".selectIuniOrderAreaStatCount", params);
		
		logger.debug("IuniOrderInfoDaoImpl.selectIuniOrderAreaStatCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIuniOrderAreaSumByExample(
			Map<String, Object> params) {
		logger.debug("IuniOrderInfoDaoImpl.selectIuniOrderAreaSumByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniOrderInfo.class.getSimpleName() 
				+ ".selectIuniOrderAreaSumByExample", params);
		
		logger.debug("IuniOrderInfoDaoImpl.selectIuniOrderAreaSumByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniOrderAreaSumByPage(
			Map<String, Object> params) {
		logger.debug("IuniOrderInfoDaoImpl.selectIuniOrderAreaSumByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniOrderInfo.class.getSimpleName() 
				+ ".selectIuniOrderAreaSumByPage", params);
		
		logger.debug("IuniOrderInfoDaoImpl.selectIuniOrderAreaSumByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniOrderAreaSumCount(Map<String, Object> params) {
		logger.debug("IuniOrderInfoDaoImpl.selectIuniOrderAreaSumCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniOrderInfo.class.getSimpleName() 
				+ ".selectIuniOrderAreaSumCount", params);
		
		logger.debug("IuniOrderInfoDaoImpl.selectIuniOrderAreaSumCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectOrderStat4Cs(Map<String, Object> params) {
		logger.debug("IuniOrderInfoDaoImpl.selectOrderStat4Cs(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniOrderInfo.class.getSimpleName() 
				+ ".selectOrderStat4Cs", params);
		
		logger.debug("IuniOrderInfoDaoImpl.selectOrderStat4Cs(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

    @Override
    public List<Map<String, Object>> selectOrderStat4CsByUser(Map<String, Object> params) {
        logger.debug("IuniOrderInfoDaoImpl.selectOrderStat4CsByUser(Map<String, Object>) invoke");
        Long stime = System.currentTimeMillis();

        List<Map<String, Object>> list = findAllObjectsByPage2(IuniOrderInfo.class.getSimpleName() + ".selectOrderStat4CsByUser", params);

        logger.debug("IuniOrderInfoDaoImpl.selectOrderStat4CsByUser(Map<String, Object>) success: costTime={}ms",
                new Object[] { System.currentTimeMillis() - stime });
        return list;
    }

}
