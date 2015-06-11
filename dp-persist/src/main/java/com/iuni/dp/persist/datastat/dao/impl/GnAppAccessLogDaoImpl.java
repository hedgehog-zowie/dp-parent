package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.GnAppAccessLogDao;
import com.iuni.dp.persist.datastat.model.GnAppAccessLog;

/**
 * 金立相关APP客户端登入登出记录日志DAO实现类
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
@Repository("gnAppAccessLog4StatDao")
public class GnAppAccessLogDaoImpl extends BaseDaoImpl<GnAppAccessLog, Serializable> implements GnAppAccessLogDao {

	private static final Logger logger = LoggerFactory.getLogger(GnAppAccessLogDaoImpl.class);
	
	@Override
	public List<Map<String, Object>> selectChannelTrendByExample(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectChannelTrendByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectChannelTrendByExample", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectChannelTrendByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectChannelTrendByPage(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectChannelTrendByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectChannelTrendByPage", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectChannelTrendByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectChannelTrendCount(Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectChannelTrendCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(GnAppAccessLog.class.getSimpleName() 
				+ ".selectChannelTrendCount", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectChannelTrendCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectRegisterOfChannelByExample(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectRegisterOfChannelByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectRegisterOfChannelByExample", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectRegisterOfChannelByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectRegisterOfChannelByPage(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectRegisterOfChannelByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectRegisterOfChannelByPage", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectRegisterOfChannelByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectRegisterOfChannelCount(Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectRegisterOfChannelCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(GnAppAccessLog.class.getSimpleName() 
				+ ".selectRegisterOfChannelCount", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectRegisterOfChannelCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectActiveUserOfChannelDailyByExample(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectActiveUserOfChannelDailyByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectActiveUserOfChannelDailyByExample", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectActiveUserOfChannelDailyByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectActiveUserOfChannelDailyByPage(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectActiveUserOfChannelDailyByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectActiveUserOfChannelDailyByPage", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectActiveUserOfChannelDailyByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectActiveUserOfChannelDailyCount(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectActiveUserOfChannelDailyCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(GnAppAccessLog.class.getSimpleName() 
				+ ".selectActiveUserOfChannelDailyCount", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectActiveUserOfChannelDailyCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectActiveUserOfChannelMonthlyByExample(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectActiveUserOfChannelMonthlyByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectActiveUserOfChannelMonthlyByExample", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectActiveUserOfChannelMonthlyByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectActiveUserOfChannelMonthlyByPage(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectActiveUserOfChannelMonthlyByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectActiveUserOfChannelMonthlyByPage", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectActiveUserOfChannelMonthlyByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectActiveUserOfChannelMonthlyCount(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectActiveUserOfChannelMonthlyCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(GnAppAccessLog.class.getSimpleName() 
				+ ".selectActiveUserOfChannelMonthlyCount", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectActiveUserOfChannelMonthlyCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectLaunchNumDistribution(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectLaunchNumDistribution(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectLaunchNumDistribution", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectLaunchNumDistribution(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectAppUserStatOnModelByExample(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectAppUserStatOnModelByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectAppUserStatOnModelByExample", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectAppUserStatOnModelByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectAppUserStatOnModelByPage(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectAppUserStatOnModelByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectAppUserStatOnModelByPage", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectAppUserStatOnModelByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectAppUserStatOnModelCount(Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectAppUserStatOnModelCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(GnAppAccessLog.class.getSimpleName() 
				+ ".selectAppUserStatOnModelCount", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectAppUserStatOnModelCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectChannelList(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectChannelList(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectChannelList", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectChannelList(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectApkVersionList(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectApkVersionList(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectApkVersionList", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectApkVersionList(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniOSTotalUserByExample(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSTotalUserByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSTotalUserByExample", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSTotalUserByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniOSTotalUserByPage(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSTotalUserByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSTotalUserByPage", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSTotalUserByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniOSTotalUserCount(Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSTotalUserCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSTotalUserCount", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSTotalUserCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIuniOSAppNames(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSAppNames(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSAppNames", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSAppNames(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniOSApkVersions(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSApkVersions(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSApkVersions", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSApkVersions(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniOSUserStatByExample(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSUserStatByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSUserStatByExample", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSUserStatByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniOSUserStatByPage(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSUserStatByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSUserStatByPage", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSUserStatByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniOSUserStatCount(Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSUserStatCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSUserStatCount", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSUserStatCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIuniOSUserStatOnPeriodByExample(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSUserStatOnPeriodByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSUserStatOnPeriodByExample", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSUserStatOnPeriodByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniOSUserStatOnPeriodByPage(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSUserStatOnPeriodByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSUserStatOnPeriodByPage", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSUserStatOnPeriodByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniOSUserStatOnPeriodCount(Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSUserStatOnPeriodCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSUserStatOnPeriodCount", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSUserStatOnPeriodCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIuniOSVersionDistributeByExample(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSVersionDistributeByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSVersionDistributeByExample", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSVersionDistributeByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniOSVersionDistributeByPage(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSVersionDistributeByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSVersionDistributeByPage", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSVersionDistributeByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniOSVersionDistributeCount(Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSVersionDistributeCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSVersionDistributeCount", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSVersionDistributeCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIuniOSAreaDistributeByExample(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSAreaDistributeByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSAreaDistributeByExample", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSAreaDistributeByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniOSAreaDistributeByPage(
			Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSAreaDistributeByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSAreaDistributeByPage", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSAreaDistributeByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniOSAreaDistributeCount(Map<String, Object> params) {
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSAreaDistributeCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(GnAppAccessLog.class.getSimpleName() 
				+ ".selectIuniOSAreaDistributeCount", params);
		
		logger.debug("GnAppAccessLogDaoImpl.selectIuniOSAreaDistributeCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Integer insertGnAppAccessLog4Stat(GnAppAccessLog gnAppAccessLog) {
		logger.debug("GnAppAccessLogDaoImpl.insertGnAppAccessLog4Stat(GnAppAccessLog) entry: wxAccessLog={}",
				new Object[] { gnAppAccessLog.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) insert(gnAppAccessLog, GnAppAccessLog.class.getSimpleName() 
				+ ".insertGnAppAccessLog4Stat");
		
		logger.debug("GnAppAccessLogDaoImpl.insertGnAppAccessLog4Stat(GnAppAccessLog) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer deleteGnAppAccessLog4StatById(Long id) {
		logger.debug("GnAppAccessLogDaoImpl.deleteGnAppAccessLog4StatById(Long) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, GnAppAccessLog.class.getSimpleName() + ".deleteGnAppAccessLog4StatById");
		
		logger.debug("GnAppAccessLogDaoImpl.deleteGnAppAccessLog4StatById(Long) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

}
