package com.iuni.dp.persist.dataalarm.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.dataalarm.dao.MsgAlarmDAO;
import com.iuni.dp.persist.dataalarm.model.MsgAlarm;

@Repository("msgAlarmDao")
public class MsgAlarmDAOImpl extends BaseDaoImpl<MsgAlarm, Serializable> implements MsgAlarmDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MsgAlarmDAOImpl.class);
	
	@Override
	public List<MsgAlarm> getAllMsgAlarm() {
		logger.debug("MsgAlarmDAOImpl.getAllMsgAlarm() invoke");
		long stime = System.currentTimeMillis();
		
		List<MsgAlarm> msgAlarms = getAll(MsgAlarm.class.getSimpleName() + ".getAllMsgAlarm");
		
		logger.debug("MsgAlarmDAOImpl.getAllMsgAlarm() success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return msgAlarms;
	}

	@Override
	public List<MsgAlarm> getMsgAlarmByPage(Map<String, Object> params) {
		logger.debug("MsgAlarmDAOImpl.getMsgAlarmByPage(Map<String, Object>) invoke");
		long stime = System.currentTimeMillis();
		
		List<MsgAlarm> msgAlarms = findAllObjectsByPage(MsgAlarm.class.getSimpleName() 
				+ ".getMsgAlarmByPage", params);
		
		logger.debug("MsgAlarmDAOImpl.getMsgAlarmByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return msgAlarms;
	}

	@Override
	public Integer getTotalCount(Map<String, Object> params) {
		logger.debug("MsgAlarmDAOImpl.getTotalCount(Map<String, Object>) invoke");
		long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(MsgAlarm.class.getSimpleName() 
				+ ".getTotalCount", params);
		
		logger.debug("MsgAlarmDAOImpl.getTotalCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
    public MsgAlarm getMsgAlarmByMsgAlarmId(int msgAlarmId){
    	logger.debug("MsgAlarmDAOImpl.getMsgAlarmByMsgAlarmId(int) entry: msgAlarmId={}"
    			,new Object[] { msgAlarmId });
		long stime = System.currentTimeMillis();
    	MsgAlarm findMsgAlarm = (MsgAlarm)getById(MsgAlarm.class.getSimpleName()+ ".getMsgAlarmByMsgAlarmId", msgAlarmId);
    	logger.debug("MsgAlarmDAOImpl.getMsgAlarmByMsgAlarmId(int) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
    	return findMsgAlarm;
    }
	
	@Override
	public int saveMsgAlarm(MsgAlarm msgAlarm) {
		logger.debug("MsgAlarmDAOImpl.saveMsgAlarm(MsgAlarm) entry: msgAlarm={}"
				,new Object[] { msgAlarm.toString() });
		long stime = System.currentTimeMillis();
		int msgAlarmId = (Integer)insert(msgAlarm, MsgAlarm.class.getSimpleName() + ".saveMsgAlarm");
		logger.debug("MsgAlarmDAOImpl.saveMsgAlarm(MsgAlarm) success: costTime={}ms",
					new Object[] { System.currentTimeMillis() - stime });
		return msgAlarmId;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<String[]> batchSaveMsgAlarm(final List<MsgAlarm> msgAlarmList){
		final List<String[]> msgAlarmIdList = new ArrayList<String[]>();
		int msgAlarmCountParm = (msgAlarmList != null) ? msgAlarmList.size() : 0;
		logger.debug("MsgAlarmDAOImpl.batchSaveMsgAlarm(List<MsgAlarm>) entry: msgAlarmCountParm={}"
				,new Object[] { msgAlarmCountParm });
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			int msgAlarmListCount = (msgAlarmList != null) ? msgAlarmList.size() : 0;
			long stime = System.currentTimeMillis();
			public Object doInSqlMapClient(SqlMapExecutor executor)throws SQLException {
				executor.startBatch();
				if (msgAlarmList != null && msgAlarmList.size() > 0) {
					for (MsgAlarm msgAlarm : msgAlarmList) {
						if (msgAlarm != null) {
							String msgTypeId = msgAlarm.getMsgTypeId();
							Integer msgAlarmId = (Integer) executor.insert(MsgAlarm.class.getSimpleName()+ ".saveMsgAlarm", msgAlarm);
							String[] msgAlarmTypeArray = new String[] {msgTypeId, String.valueOf(msgAlarmId) };
							msgAlarmIdList.add(msgAlarmTypeArray);
						}
					}
				}
				executor.executeBatch();
				logger.debug("MsgAlarmDAOImpl.batchSaveMsgAlarm(List<MsgAlarm>) success: msgAlarmCountSaveDB={},costTime={}ms",
						new Object[] { msgAlarmListCount,System.currentTimeMillis() - stime });
				return executor;
			} 
        });
		return msgAlarmIdList; 
	}
    
    @Override
	public int updateExecuteSucessNum(int msgAlarmId){
    	logger.debug("MsgAlarmDAOImpl.updateExecuteSucessNum(int) entry: msgAlarmId={}"
    			,new Object[] {msgAlarmId });
		long stime = System.currentTimeMillis();
		int updateCount = update(msgAlarmId, MsgAlarm.class.getSimpleName()+ ".updateExecuteSucessNum");
		logger.debug("MsgAlarmDAOImpl.updateExecuteSucessNum(int) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return updateCount;
    }
	
    @Override
	public int updateAlarmState(int state, int msgAlarmId) {
		logger.debug("MsgAlarmDAOImpl.updateAlarmState(int,int) entry: state={},msgAlarmId={}",
				new Object[] { state, msgAlarmId });
		long stime = System.currentTimeMillis();
    	Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("state", state);
		paramsMap.put("msgAlarmId", msgAlarmId);
		int updateCount =  update(paramsMap, MsgAlarm.class.getSimpleName()+ ".updateAlarmState");
		logger.debug("MsgAlarmDAOImpl.updateAlarmState(int,int) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return updateCount;
	}
	
}
