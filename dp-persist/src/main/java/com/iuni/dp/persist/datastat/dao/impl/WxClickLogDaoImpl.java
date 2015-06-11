package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.WxClickLogDao;
import com.iuni.dp.persist.datastat.model.WxClickLog;

/**
 * 微信公众帐号点击日志DAO实现类
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
@Repository("wxClickLogDao")
public class WxClickLogDaoImpl extends BaseDaoImpl<WxClickLog, Serializable> implements WxClickLogDao {

	private static final Logger logger = LoggerFactory.getLogger(WxClickLogDaoImpl.class);
	
	@Override
	public WxClickLog selectWxClickLogById(Integer id) {
		logger.debug("WxClickLogDaoImpl.selectWxClickLogById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		
		WxClickLog wxClickLog = (WxClickLog) getById(WxClickLog.class.getSimpleName()+ ".selectWxClickLogById"
				, id);
		
		logger.debug("WxClickLogDaoImpl.selectWxClickLogById(Integer) success: wxClickLog={}, costTime={}ms",
				new Object[] { wxClickLog.toString(), System.currentTimeMillis() - stime });
		return wxClickLog;
	}

	@Override
	public List<WxClickLog> selectWxClickLogByExample(Map<String, Object> params) {
		logger.debug("WxClickLogDaoImpl.selectWxClickLogByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<WxClickLog> wxClickLogs = findAllObjectsByPage(WxClickLog.class.getSimpleName() + ".selectWxClickLogByExample", params);
		
		logger.debug("WxClickLogDaoImpl.selectWxClickLogByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return wxClickLogs;
	}

	@Override
	public List<WxClickLog> selectWxClickLogByPage(Map<String, Object> params) {
		logger.debug("WxClickLogDaoImpl.selectWxClickLogByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<WxClickLog> wxClickLogs = findAllObjectsByPage(WxClickLog.class.getSimpleName() + ".selectWxClickLogByPage", params);
		
		logger.debug("WxClickLogDaoImpl.selectWxClickLogByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return wxClickLogs;
	}

	@Override
	public Integer selectWxClickLogCount(Map<String, Object> params) {
		logger.debug("WxClickLogDaoImpl.selectWxClickLogCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(WxClickLog.class.getSimpleName() + ".selectWxClickLogCount", params);
		
		logger.debug("WxClickLogDaoImpl.selectWxClickLogCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Integer insertWxClickLog(WxClickLog wxClickLog) {
		logger.debug("WxClickLogDaoImpl.insertWxClickLog(WxClickLog) entry: wxClickLog={}",
				new Object[] { wxClickLog.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) insert(wxClickLog, WxClickLog.class.getSimpleName() + ".insertWxClickLog");
		
		logger.debug("WxClickLogDaoImpl.insertWxClickLog(WxClickLog) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer deleteWxClickLogById(Integer id) {
		logger.debug("WxClickLogDaoImpl.deleteWxClickLogById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, WxClickLog.class.getSimpleName() + ".deleteWxClickLogById");
		
		logger.debug("WxClickLogDaoImpl.deleteWxClickLogById(Integer) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

	@Override
	public Integer selectActiveUserCount(Map<String, Object> params) {
		logger.debug("WxClickLogDaoImpl.selectActiveUserCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(WxClickLog.class.getSimpleName() + ".selectActiveUserCount", params);
		
		logger.debug("WxClickLogDaoImpl.selectActiveUserCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

}
