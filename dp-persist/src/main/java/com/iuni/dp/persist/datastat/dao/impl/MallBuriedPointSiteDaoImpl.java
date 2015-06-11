package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.MallBuriedPointSiteDao;
import com.iuni.dp.persist.datastat.model.MallBuriedPointSite;

/**
 * MallBuriedPointSite DAO 
 * @author CaiKe
 * @version dp-service-V1.0.1
 */
@Repository("mallBuriedPointSiteDao")
public class MallBuriedPointSiteDaoImpl extends
		BaseDaoImpl<MallBuriedPointSite, Serializable> implements
		MallBuriedPointSiteDao {

	private Logger logger = LoggerFactory.getLogger(MallBuriedPointSiteDaoImpl.class);
	
	@Override
	public List<MallBuriedPointSite> selectMbpsByExample(
			Map<String, Object> params) {
		
		logger.debug("MallBuriedPointSiteDaoImpl.selectMbpsByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<MallBuriedPointSite> list = findAllObjectsByPage(MallBuriedPointSite.class.getSimpleName() 
				+ ".selectMbpsByExample", params);
		
		logger.debug("MallBuriedPointSiteDaoImpl.selectMbpsByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		
		return list;
	}

	@Override
	public List<MallBuriedPointSite> selectMbpsByPage(Map<String, Object> params) {
		logger.debug("MallBuriedPointSiteDaoImpl.selectMbpsByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<MallBuriedPointSite> list = findAllObjectsByPage(MallBuriedPointSite.class.getSimpleName() 
				+ ".selectMbpsByPage", params);
		
		logger.debug("MallBuriedPointSiteDaoImpl.selectMbpsByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectMbpsCount(Map<String, Object> params) {
		logger.debug("MallBuriedPointSiteDaoImpl.selectMbpsCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(MallBuriedPointSite.class.getSimpleName() 
				+ ".selectMbpsCount", params);
		
		logger.debug("MallBuriedPointSiteDaoImpl.selectMbpsCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectClickRateByExample(
			Map<String, Object> params) {
		logger.debug("MallBuriedPointSiteDaoImpl.selectClickRateByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String,Object>> list = findAllObjectsByPage2(MallBuriedPointSite.class.getSimpleName() 
				+ ".selectClickRateByExample", params);
		
		logger.debug("MallBuriedPointSiteDaoImpl.selectClickRateByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		
		return list;
	}

	@Override
	public List<Map<String, Object>> selectClickRateByPage(
			Map<String, Object> params) {
		logger.debug("MallBuriedPointSiteDaoImpl.selectClickRateByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String,Object>> list = findAllObjectsByPage2(MallBuriedPointSite.class.getSimpleName() 
				+ ".selectClickRateByPage", params);
		
		logger.debug("MallBuriedPointSiteDaoImpl.selectClickRateByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectClickRateCount(
			Map<String, Object> params) {
		logger.debug("MallBuriedPointSiteDaoImpl.selectClickRateCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(MallBuriedPointSite.class.getSimpleName() 
				+ ".selectClickRateCount", params);
		
		logger.debug("MallBuriedPointSiteDaoImpl.selectClickRateCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Long insertMbps(MallBuriedPointSite mallBuriedPointSite) {
		logger.debug("MallBuriedPointSiteDaoImpl.insertMbps(MallBuriedPointSite) entry: mallBuriedPointSite={}",
				new Object[] { mallBuriedPointSite.toString() });
		long stime = System.currentTimeMillis();
		
		Long insertIndex = (Long) insert(mallBuriedPointSite, MallBuriedPointSite.class.getSimpleName() 
				+ ".insertMbps");
		
		logger.debug("MallBuriedPointSiteDaoImpl.insertMbps(MallBuriedPointSite) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer updateMbps(MallBuriedPointSite mallBuriedPointSite) {
		logger.debug("MallBuriedPointSiteDaoImpl.updateMbps(MallBuriedPointSite) entry: mallBuriedPointSite={}",
				new Object[] { mallBuriedPointSite.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) update(mallBuriedPointSite, MallBuriedPointSite.class.getSimpleName() 
				+ ".updateMbps");
		
		logger.debug("MallBuriedPointSiteDaoImpl.updateMbps(MallBuriedPointSite) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer deleteMbpsById(Long id) {
		logger.debug("MallBuriedPointSiteDaoImpl.deleteMbpsById(Long) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, MallBuriedPointSite.class.getSimpleName() + ".deleteMbpsById");
		
		logger.debug("MallBuriedPointSiteDaoImpl.deleteMbpsById(Long) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

}
