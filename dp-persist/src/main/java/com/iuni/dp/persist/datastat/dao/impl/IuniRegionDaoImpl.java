package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.IuniRegionDao;
import com.iuni.dp.persist.datastat.model.IuniRegion;

/**
 * IuniRegion DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.2
 */
@Repository("iuniRegionDao")
public class IuniRegionDaoImpl extends BaseDaoImpl<IuniRegion, Serializable>
		implements IuniRegionDao {

	private Logger logger = LoggerFactory.getLogger(IuniRegionDaoImpl.class);
	
	@Override
	public List<IuniRegion> selectIuniRegionMapByParent(
			Map<String, Object> params) {
		logger.debug("IuniOrderInfoDaoImpl.selectIuniRegionMapByParent(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<IuniRegion> list = findAllObjectsByPage(IuniRegion.class.getSimpleName() 
				+ ".selectIuniRegionMapByParent", params);
		
		logger.debug("IuniOrderInfoDaoImpl.selectIuniRegionMapByParent(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

}
