package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.WxAccessLogDao;
import com.iuni.dp.persist.datastat.model.WxAccessLog;

/**
 * 微信公众帐号访问日志DAO实现类
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
@Repository("wxAccessLogDao")
public class WxAccessLogDaoImpl extends BaseDaoImpl<WxAccessLog, Serializable> implements WxAccessLogDao {

	private static final Logger logger = LoggerFactory.getLogger(WxAccessLogDaoImpl.class);
	
	@Override
	public WxAccessLog selectWxAccessLogById(Integer id) {
		logger.debug("WxAccessLogDaoImpl.selectWxAccessLogById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		
		WxAccessLog wxAccessLog = (WxAccessLog) getById(WxAccessLog.class.getSimpleName()+ ".selectWxAccessLogById"
				, id);
		
		logger.debug("WxAccessLogDaoImpl.selectWxAccessLogById(Integer) success: wxAccessLog={}, costTime={}ms",
				new Object[] { wxAccessLog.toString(), System.currentTimeMillis() - stime });
		return wxAccessLog;
	}

	@Override
	public List<WxAccessLog> selectWxAccessLogByExample(
			Map<String, Object> params) {
		logger.debug("WxAccessLogDaoImpl.selectWxAccessLogByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<WxAccessLog> wxAccessLogs = findAllObjectsByPage(WxAccessLog.class.getSimpleName() + ".selectWxAccessLogByExample", params);
		
		logger.debug("WxAccessLogDaoImpl.selectWxAccessLogByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return wxAccessLogs;
	}

	@Override
	public List<WxAccessLog> selectWxAccessLogByPage(Map<String, Object> params) {
		logger.debug("WxAccessLogDaoImpl.selectWxAccessLogByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<WxAccessLog> wxAccessLogs = findAllObjectsByPage(WxAccessLog.class.getSimpleName() + ".selectWxAccessLogByPage", params);
		
		logger.debug("WxAccessLogDaoImpl.selectWxAccessLogByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return wxAccessLogs;
	}

	@Override
	public Integer selectWxAccessLogCount(Map<String, Object> params) {
		logger.debug("WxAccessLogDaoImpl.selectWxAccessLogCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(WxAccessLog.class.getSimpleName() 
				+ ".selectWxAccessLogCount", params);
		
		logger.debug("WxAccessLogDaoImpl.selectWxAccessLogCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectNewFansStatByExample(
			Map<String, Object> params) {
		logger.debug("WxAccessLogDaoImpl.selectNewFansStatByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(WxAccessLog.class.getSimpleName() 
				+ ".selectNewFansStatByExample", params);
		
		logger.debug("WxAccessLogDaoImpl.selectNewFansStatByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectNewFansStatByPage(
			Map<String, Object> params) {
		logger.debug("WxAccessLogDaoImpl.selectNewFansStatByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(WxAccessLog.class.getSimpleName() 
				+ ".selectNewFansStatByPage", params);
		
		logger.debug("WxAccessLogDaoImpl.selectNewFansStatByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectNewFansStatCount(Map<String, Object> params) {
		logger.debug("WxAccessLogDaoImpl.selectNewFansStatCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(WxAccessLog.class.getSimpleName() 
				+ ".selectNewFansStatCount", params);
		
		logger.debug("WxAccessLogDaoImpl.selectNewFansStatCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectActiveUserStatDailyByExample(
			Map<String, Object> params) {
		logger.debug("WxAccessLogDaoImpl.selectActiveUserStatDailyByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(WxAccessLog.class.getSimpleName() 
				+ ".selectActiveUserStatDailyByExample", params);
		
		logger.debug("WxAccessLogDaoImpl.selectActiveUserStatDailyByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectActiveUserStatDailyByPage(
			Map<String, Object> params) {
		logger.debug("WxAccessLogDaoImpl.selectActiveUserStatDailyByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(WxAccessLog.class.getSimpleName() 
				+ ".selectActiveUserStatDailyByPage", params);
		
		logger.debug("WxAccessLogDaoImpl.selectActiveUserStatDailyByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectActiveUserStatDailyCount(Map<String, Object> params) {
		logger.debug("WxAccessLogDaoImpl.selectActiveUserStatDailyCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(WxAccessLog.class.getSimpleName() 
				+ ".selectActiveUserStatDailyCount", params);
		
		logger.debug("WxAccessLogDaoImpl.selectActiveUserStatDailyCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectActiveUserStatMonthlyByExample(
			Map<String, Object> params) {
		logger.debug("WxAccessLogDaoImpl.selectActiveUserStatMonthlyByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(WxAccessLog.class.getSimpleName() 
				+ ".selectActiveUserStatMonthlyByExample", params);
		
		logger.debug("WxAccessLogDaoImpl.selectActiveUserStatMonthlyByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectActiveUserStatMonthlyByPage(
			Map<String, Object> params) {
		logger.debug("WxAccessLogDaoImpl.selectActiveUserStatMonthlyByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(WxAccessLog.class.getSimpleName() 
				+ ".selectActiveUserStatMonthlyByPage", params);
		
		logger.debug("WxAccessLogDaoImpl.selectActiveUserStatMonthlyByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectActiveUserStatMonthlyCount(Map<String, Object> params) {
		logger.debug("WxAccessLogDaoImpl.selectActiveUserStatMonthlyCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(WxAccessLog.class.getSimpleName() 
				+ ".selectActiveUserStatMonthlyCount", params);
		
		logger.debug("WxAccessLogDaoImpl.selectActiveUserStatMonthlyCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectUpwardTextStatByExample(
			Map<String, Object> params) {
		logger.debug("WxAccessLogDaoImpl.selectUpwardTextStatByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(WxAccessLog.class.getSimpleName() 
				+ ".selectUpwardTextStatByExample", params);
		
		logger.debug("WxAccessLogDaoImpl.selectUpwardTextStatByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectUpwardTextStatByPage(
			Map<String, Object> params) {
		logger.debug("WxAccessLogDaoImpl.selectUpwardTextStatByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(WxAccessLog.class.getSimpleName() 
				+ ".selectUpwardTextStatByPage", params);
		
		logger.debug("WxAccessLogDaoImpl.selectUpwardTextStatByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectUpwardTextStatCount(Map<String, Object> params) {
		logger.debug("WxAccessLogDaoImpl.selectUpwardTextStatCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(WxAccessLog.class.getSimpleName() 
				+ ".selectUpwardTextStatCount", params);
		
		logger.debug("WxAccessLogDaoImpl.selectUpwardTextStatCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	/**
	 * 按条件查询微信公众帐号图片下载排行
	 * @param params 
	 * @return List<WxAccessLog>
	 */
	public List<Map<String, Object>> selectWxImageDownloadRanks(Map<String, Object> paramMap){
	logger.debug("WxAccessLogDaoImpl.selectWxImageDownloadRanks(Map<String, Object>) invoke");
	Long stime = System.currentTimeMillis();
	
	List<Map<String, Object>> list = (List<Map<String, Object>>) findAllObjectsByPage2(WxAccessLog.class.getSimpleName() 
			+ ".selectWxImageDownloadRanks", paramMap);
	
	logger.debug("WxAccessLogDaoImpl.selectWxImageDownloadRanks(Map<String, Object>) success: costTime={}ms",
			new Object[] { System.currentTimeMillis() - stime });
	return list;
	}
	
	/**
	 * 按条件查询微信公众帐号图片下载排行-记录总数
	 * @param params 
	 * @return List<WxAccessLog>
	 */
	public Integer selectWxImageDownloadRanksCount(Map<String, Object> paramMap){
		logger.debug("WxAccessLogDaoImpl.selectWxImageDownloadRanksCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count =  (Integer)getObjectByProperty(WxAccessLog.class.getSimpleName() 
				+ ".selectWxImageDownloadRanksCount", paramMap);
		
		logger.debug("WxAccessLogDaoImpl.selectWxImageDownloadRanksCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}
	
	/**
	 * 按条件查询微信公众帐号图片下载总次数
	 * @param params 
	 * @return List<WxAccessLog>
	 */
	public Integer selectWxImageDownloadTotalCount(Map<String, Object> paramMap){

		logger.debug("WxAccessLogDaoImpl.selectWxImageDownloadTotalCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer)getObjectByProperty(WxAccessLog.class.getSimpleName() + ".selectWxImageDownloadTotalCount", paramMap);
		
		logger.debug("WxAccessLogDaoImpl.selectWxImageDownloadTotalCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	
	}
	
	@Override
	public Integer insertWxAccessLog(WxAccessLog wxAccessLog) {
		logger.debug("WxAccessLogDaoImpl.insertWxAccessLog(WxAccessLog) entry: wxAccessLog={}",
				new Object[] { wxAccessLog.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) insert(wxAccessLog, WxAccessLog.class.getSimpleName() + ".insertWxAccessLog");
		
		logger.debug("WxAccessLogDaoImpl.insertWxAccessLog(WxAccessLog) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer deleteWxAccessLogById(Integer id) {
		logger.debug("WxAccessLogDaoImpl.deleteWxAccessLogById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, WxAccessLog.class.getSimpleName() + ".deleteWxAccessLogById");
		
		logger.debug("WxAccessLogDaoImpl.deleteWxAccessLogById(Integer) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

}
