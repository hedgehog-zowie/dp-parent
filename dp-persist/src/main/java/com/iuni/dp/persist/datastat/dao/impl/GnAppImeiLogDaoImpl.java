package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.GnAppImeiLogDao;
import com.iuni.dp.persist.datastat.model.GnAppImeiLog;

/**
 * 金立相关APP客户端IMEI首次启用时间日志DAO实现类
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
@Repository("gnAppImeiLog4StatDao")
public class GnAppImeiLogDaoImpl extends
		BaseDaoImpl<GnAppImeiLog, Serializable> implements
		GnAppImeiLogDao {

	private static final Logger logger = LoggerFactory.getLogger(GnAppImeiLogDaoImpl.class);
	
	@Override
	public List<Map<String, Object>> selectNewUserOfChannelByExample(
			Map<String, Object> params) {
		logger.debug("GnAppImeiLogDaoImpl.selectNewUserOfChannelByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppImeiLog.class.getSimpleName() 
				+ ".selectNewUserOfChannelByExample", params);
		
		logger.debug("GnAppImeiLogDaoImpl.selectNewUserOfChannelByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectNewUserOfChannelByPage(
			Map<String, Object> params) {
		logger.debug("GnAppImeiLogDaoImpl.selectNewUserOfChannelByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppImeiLog.class.getSimpleName() 
				+ ".selectNewUserOfChannelByPage", params);
		
		logger.debug("GnAppImeiLogDaoImpl.selectNewUserOfChannelByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectNewUserOfChannelCount(Map<String, Object> params) {
		logger.debug("GnAppImeiLogDaoImpl.selectNewUserOfChannelCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(GnAppImeiLog.class.getSimpleName() 
				+ ".selectNewUserOfChannelCount", params);
		
		logger.debug("GnAppImeiLogDaoImpl.selectNewUserOfChannelCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIuniOSRemainUserOnDayByExample(
			Map<String, Object> params) {
		logger.debug("GnAppImeiLogDaoImpl.selectIuniOSRemainUserOnDayByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppImeiLog.class.getSimpleName() 
				+ ".selectIuniOSRemainUserOnDayByExample", params);
		
		logger.debug("GnAppImeiLogDaoImpl.selectIuniOSRemainUserOnDayByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniOSRemainUserOnDayByPage(
			Map<String, Object> params) {
		logger.debug("GnAppImeiLogDaoImpl.selectIuniOSRemainUserOnDayByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppImeiLog.class.getSimpleName() 
				+ ".selectIuniOSRemainUserOnDayByPage", params);
		
		logger.debug("GnAppImeiLogDaoImpl.selectIuniOSRemainUserOnDayByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniOSRemainUserOnDayCount(Map<String, Object> params) {
		logger.debug("GnAppImeiLogDaoImpl.selectIuniOSRemainUserOnDayCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(GnAppImeiLog.class.getSimpleName() 
				+ ".selectIuniOSRemainUserOnDayCount", params);
		
		logger.debug("GnAppImeiLogDaoImpl.selectIuniOSRemainUserOnDayCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Integer insertGnAppImeiLog4Stat(GnAppImeiLog gnAppImeiLog) {
		logger.debug("GnAppImeiLogDaoImpl.insertGnAppImeiLog4Stat(GnAppImeiLog) entry: wxAccessLog={}",
				new Object[] { gnAppImeiLog.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) insert(gnAppImeiLog, GnAppImeiLog.class.getSimpleName() 
				+ ".insertGnAppImeiLog4Stat");
		
		logger.debug("GnAppImeiLogDaoImpl.insertGnAppImeiLog4Stat(GnAppImeiLog) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer deleteGnAppImeiLog4StatById(Long id) {
		logger.debug("GnAppImeiLogDaoImpl.deleteGnAppImeiLog4StatById(Long) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, GnAppImeiLog.class.getSimpleName() + ".deleteGnAppImeiLog4StatById");
		
		logger.debug("GnAppImeiLogDaoImpl.deleteGnAppImeiLog4StatById(Long) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

}
