package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.IuniBuriedPointSiteDao;
import com.iuni.dp.persist.datastat.model.IuniBuriedPointSite;

/**
 * IuniBuriedPointSite DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-V1.1.0
 */
@Repository("iuniBuriedPointSiteDao")
public class IuniBuriedPointSiteDaoImpl extends
		BaseDaoImpl<IuniBuriedPointSite, Serializable> implements
		IuniBuriedPointSiteDao {

	private final Logger logger = LoggerFactory.getLogger(IuniBuriedPointSiteDaoImpl.class);
	
	@Override
	public List<IuniBuriedPointSite> selectIbpsByExample(
			Map<String, Object> params) {
		logger.debug("IuniBuriedPointSiteDaoImpl.selectIbpsByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<IuniBuriedPointSite> list = findAllObjectsByPage(IuniBuriedPointSite.class.getSimpleName() 
				+ ".selectIbpsByExample", params);
		
		logger.debug("IuniBuriedPointSiteDaoImpl.selectIbpsByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		
		return list;
	}

	@Override
	public List<IuniBuriedPointSite> selectIbpsByPage(Map<String, Object> params) {
		logger.debug("IuniBuriedPointSiteDaoImpl.selectIbpsByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<IuniBuriedPointSite> list = findAllObjectsByPage(IuniBuriedPointSite.class.getSimpleName() 
				+ ".selectIbpsByPage", params);
		
		logger.debug("IuniBuriedPointSiteDaoImpl.selectIbpsByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		
		return list;
	}

	@Override
	public Integer selectIbpsCount(Map<String, Object> params) {
		logger.debug("IuniBuriedPointSiteDaoImpl.selectIbpsCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniBuriedPointSite.class.getSimpleName() 
				+ ".selectIbpsCount", params);
		
		logger.debug("IuniBuriedPointSiteDaoImpl.selectIbpsCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Long insertIbps(IuniBuriedPointSite iuniBuriedPointSite) {
		logger.debug("IuniBuriedPointSiteDaoImpl.insertIbps(IuniBuriedPointSite) entry: IuniBuriedPointSite={}",
				new Object[] { iuniBuriedPointSite.toString() });
		long stime = System.currentTimeMillis();
		
		Long insertIndex = (Long) insert(iuniBuriedPointSite, IuniBuriedPointSite.class.getSimpleName() 
				+ ".insertIbps");
		
		logger.debug("IuniBuriedPointSiteDaoImpl.insertIbps(IuniBuriedPointSite) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer updateIbps(IuniBuriedPointSite iuniBuriedPointSite) {
		logger.debug("IuniBuriedPointSiteDaoImpl.updateIbps(IuniBuriedPointSite) entry: IuniBuriedPointSite={}",
				new Object[] { iuniBuriedPointSite.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) update(iuniBuriedPointSite, IuniBuriedPointSite.class.getSimpleName() 
				+ ".updateIbps");
		
		logger.debug("IuniBuriedPointSiteDaoImpl.updateIbps(IuniBuriedPointSite) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer deleteIbpsById(Long id) {
		logger.debug("IuniBuriedPointSiteDaoImpl.deleteIbpsById(Long) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, IuniBuriedPointSite.class.getSimpleName() + ".deleteIbpsById");
		
		logger.debug("IuniBuriedPointSiteDaoImpl.deleteIbpsById(Long) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

}
