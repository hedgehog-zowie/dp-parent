package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.IuniUcLoginLogDao;
import com.iuni.dp.persist.datastat.model.IuniUcLoginLog;
import com.iuni.dp.persist.datastat.model.IuniUcOloginLog;
import com.iuni.dp.persist.datastat.utils.UserUtil;

/**
 * IUNI UC Login Log DAO
 * 
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.4
 */
@Repository("iuniUcLoginLogDao")
public class IuniUcLoginLogDaoImpl extends
		BaseDaoImpl<IuniUcLoginLog, Serializable> implements IuniUcLoginLogDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Long insertIuniUcLoginLog(IuniUcLoginLog iuniUcLoginLog) {
		logger.debug("IuniUcLoginLogDaoImpl.insertIuniUcLoginLog(IuniUcLoginLog) entry: iuniUcLoginLog={}",
				new Object[] { iuniUcLoginLog.toString() });
		long stime = System.currentTimeMillis();
		
		Long insertIndex = (Long) insert(iuniUcLoginLog, IuniUcLoginLog.class.getSimpleName() 
				+ ".insertIuniUcLoginLog");
		
		logger.debug("IuniUcLoginLogDaoImpl.insertIuniUcLoginLog(IuniUcLoginLog) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@SuppressWarnings({"deprecation"})
	@Override
	public Long batchInsertIuniUcLoginLog(final List<IuniUcLoginLog> iuniUcLoginLogList) {
		long stime = System.currentTimeMillis();
		Long execCount = 0l;
		
		int dataSize = (iuniUcLoginLogList != null) ? iuniUcLoginLogList.size() : 0;
		logger.debug("IuniUcLoginLogDaoImpl.batchInsertIuniUcLoginLog(List<IuniUcLoginLog>) entry: dataSize={}"
				,new Object[] { dataSize });
		
		if (CollectionUtils.isEmpty(iuniUcLoginLogList)) {
			return execCount;
		}
		
		execCount = getSqlMapClientTemplate().execute(new SqlMapClientCallback<Long>() {
			Long execCount = 0l;
			@Override
			public Long doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for(IuniUcLoginLog iuniUcLoginLog : iuniUcLoginLogList){
					if(null != iuniUcLoginLog){
						// 用户登陆状态过滤
						String loginResult = iuniUcLoginLog.getLoginResult();
						String userNameStr = iuniUcLoginLog.getUserName();
						if(!"0".equals(loginResult) || userNameStr.length() > 40) {
							continue;
						}
						
						// 用户名、手机号码、邮箱判断
						if(UserUtil.isPhoneNum(userNameStr)) {
							iuniUcLoginLog.setMobile(userNameStr);
							iuniUcLoginLog.setUserName(null);
						} else if(UserUtil.isMail(userNameStr)) {
							iuniUcLoginLog.setEmail(userNameStr);
							iuniUcLoginLog.setUserName(null);
						} else {
							// 无需处理
						}
						executor.insert(IuniUcLoginLog.class.getSimpleName()+".insertIuniUcLoginLog", iuniUcLoginLog);
						++execCount;
					}
	            }
	            executor.executeBatch(); 
				
				return execCount;
			}
		});
		
		logger.debug("IuniUcLoginLogDaoImpl.batchInsertIuniUcLoginLog(List<IuniUcLoginLog>) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}

	@Override
	public List<Map<String, Object>> selectIuniUserLoginFreqByExample(
			Map<String, Object> params) {
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserLoginFreqByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniUcLoginLog.class.getSimpleName() 
				+ ".selectIuniUserLoginFreqByExample", params);
		
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserLoginFreqByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniUserLoginFreqByPage(
			Map<String, Object> params) {
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserLoginFreqByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniUcLoginLog.class.getSimpleName() 
				+ ".selectIuniUserLoginFreqByPage", params);
		
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserLoginFreqByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniUserLoginFreqCount(Map<String, Object> params) {
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserLoginFreqCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniUcLoginLog.class.getSimpleName() 
				+ ".selectIuniUserLoginFreqCount", params);
		
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserLoginFreqCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Long insertIuniUcOloginLog(IuniUcOloginLog iuniUcOloginLog) {
		logger.debug("IuniUcLoginLogDaoImpl.insertIuniUcOloginLog(IuniUcLoginLog) entry: iuniUcLoginLog={}",
				new Object[] { iuniUcOloginLog.toString() });
		long stime = System.currentTimeMillis();
		
		Long insertIndex = (Long) insert(iuniUcOloginLog, IuniUcLoginLog.class.getSimpleName() 
				+ ".insertIuniUcOloginLog");
		
		logger.debug("IuniUcLoginLogDaoImpl.insertIuniUcOloginLog(IuniUcLoginLog) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@SuppressWarnings({"deprecation"})
	@Override
	public Long batchInsertIuniUcOloginLog(
			final List<IuniUcOloginLog> iuniUcOloginLogList) {
		long stime = System.currentTimeMillis();
		Long execCount = 0l;
		
		int dataSize = (iuniUcOloginLogList != null) ? iuniUcOloginLogList.size() : 0;
		logger.debug("IuniUcLoginLogDaoImpl.batchInsertIuniUcLoginLog(List<IuniUcLoginLog>) entry: dataSize={}"
				,new Object[] { dataSize });
		
		if (CollectionUtils.isEmpty(iuniUcOloginLogList)) {
			return execCount;
		}
		
		execCount = getSqlMapClientTemplate().execute(new SqlMapClientCallback<Long>() {
			Long execCount = 0l;
			@Override
			public Long doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for(IuniUcOloginLog iuniUcOloginLog : iuniUcOloginLogList){
					if(null != iuniUcOloginLog){
						// 用户登陆状态过滤
						String loginResult = iuniUcOloginLog.getLoginResult();
						if(!"0".equals(loginResult)) {
							continue;
						}
						
						executor.insert(IuniUcLoginLog.class.getSimpleName()+".insertIuniUcOloginLog", iuniUcOloginLog);
						++execCount;
					}
	            }
	            executor.executeBatch(); 
				
				return execCount;
			}
		});
		
		logger.debug("IuniUcLoginLogDaoImpl.batchInsertIuniUcOloginLog(List<IuniUcLoginLog>) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}

	@Override
	public List<Map<String, Object>> selectIuniUserLoginNumByExample(
			Map<String, Object> params) {
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserLoginNumByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniUcLoginLog.class.getSimpleName() 
				+ ".selectIuniUserLoginNumByExample", params);
		
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserLoginNumByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniUserLoginNumByPage(
			Map<String, Object> params) {
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserLoginNumByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniUcLoginLog.class.getSimpleName() 
				+ ".selectIuniUserLoginNumByPage", params);
		
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserLoginNumByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniUserLoginNumCount(Map<String, Object> params) {
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserLoginNumCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniUcLoginLog.class.getSimpleName() 
				+ ".selectIuniUserLoginNumCount", params);
		
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserLoginNumCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIuniUserRegRetainByExample(
			Map<String, Object> params) {
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserRegRetainByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniUcLoginLog.class.getSimpleName() 
				+ ".selectIuniUserRegRetainByExample", params);
		
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserRegRetainByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniUserRegRetainByPage(
			Map<String, Object> params) {
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserRegRetainByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniUcLoginLog.class.getSimpleName() 
				+ ".selectIuniUserRegRetainByPage", params);
		
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserRegRetainByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniUserRegRetainCount(Map<String, Object> params) {
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserRegRetainCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniUcLoginLog.class.getSimpleName() 
				+ ".selectIuniUserRegRetainCount", params);
		
		logger.debug("IuniUcLoginLogDaoImpl.selectIuniUserRegRetainCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}
	
}
