package com.iuni.dp.persist.dataalarm.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.dataalarm.dao.AlarmScheduleDAO;
import com.iuni.dp.persist.dataalarm.model.AlarmSchedule;

@Repository("alarmScheduleDao")
public class AlarmScheduleDAOImpl extends BaseDaoImpl<AlarmSchedule, Serializable> implements AlarmScheduleDAO {
	
	@Override
	public void insertAlarmSchedule(AlarmSchedule alarmSchedule){
		long stime = System.currentTimeMillis();
		logger.debug("AlarmScheduleDAOImpl.insertAlarmSchedule(AlarmSchedule) entry: alarmSchedule={}",
				new Object[] { alarmSchedule.toString() });
		insert(alarmSchedule, AlarmSchedule.class.getSimpleName() + ".insertAlarmSchedule");
		logger.debug("AlarmScheduleDAOImpl.insertAlarmSchedule(AlarmSchedule) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AlarmSchedule> getToDoAlarmScheduleList(int maxLimitOneTime) {
		long stime = System.currentTimeMillis();
		logger.debug("AlarmScheduleDAOImpl.getToDoAlarmScheduleList(int) entry: maxLimitOneTime={}",
				new Object[] { maxLimitOneTime });
		List<AlarmSchedule> alarmScheduleList = ((List<AlarmSchedule>) getSqlMapClientTemplate().queryForList(
				AlarmSchedule.class.getSimpleName()+ ".getToDoAlarmScheduleList", maxLimitOneTime));
		logger.debug("AlarmScheduleDAOImpl.getToDoAlarmScheduleList(int) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return alarmScheduleList;
	}
	
	@Override
	public int deleteAlarmSchedule(int msgAlarmId) {
		long stime = System.currentTimeMillis();
		logger.debug("AlarmScheduleDAOImpl.deleteAlarmSchedule(int) entry: msgAlarmId={}",
				new Object[] { msgAlarmId });
		int deleteCount =  delete(msgAlarmId, AlarmSchedule.class.getSimpleName()+ ".deleteAlarmSchedule");
		logger.debug("AlarmScheduleDAOImpl.deleteAlarmSchedule(int) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return deleteCount;
	}
	
	@Override
	public int updateNextExecuteTimeExecuteFailNum(Date nextExecuteTime,Integer executeFailNum,Integer msgAlarmId){
		long stime = System.currentTimeMillis();
		logger.debug("AlarmScheduleDAOImpl.updateNextExecuteTimeExecuteFailNum(Date,Integer,Integer) entry: nextExecuteTime={},executeFailNum={},msgAlarmId={}",
				new Object[] { nextExecuteTime,executeFailNum,msgAlarmId });
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("nextExecuteTime", nextExecuteTime);
		paramsMap.put("executeFailNum", executeFailNum);
		paramsMap.put("msgAlarmId", msgAlarmId);
		int updateCount =  update(paramsMap, AlarmSchedule.class.getSimpleName()+ ".updateNextExecuteTimeExecuteFailNum");
		logger.debug("AlarmScheduleDAOImpl.updateNextExecuteTimeExecuteFailNum(Date,Integer,Integer) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return updateCount;
	}
	
	@Override
	public int updateExecuteFailNumAddOne(int msgAlarmId){
		long stime = System.currentTimeMillis();
		logger.debug("AlarmScheduleDAOImpl.updateExecuteFailNumAddOne(int) entry:msgAlarmId={}",
				new Object[] {msgAlarmId });
		int updateCount =  update(msgAlarmId, AlarmSchedule.class.getSimpleName()+ ".updateExecuteFailNumAddOne");
		logger.debug("AlarmScheduleDAOImpl.updateExecuteFailNumAddOne(int) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return updateCount;
	}
	
	@Override
	public AlarmSchedule getAlarmScheduleByMsgAlarmId(int msgAlarmId){
		long stime = System.currentTimeMillis();
		logger.debug("AlarmScheduleDAOImpl.getAlarmScheduleByMsgAlarmId(int) entry:msgAlarmId={}",
				new Object[] {msgAlarmId });
		AlarmSchedule findAlarmSchedule = (AlarmSchedule) getById(AlarmSchedule.class.getSimpleName() + ".getAlarmScheduleByMsgAlarmId",msgAlarmId);
		logger.debug("AlarmScheduleDAOImpl.getAlarmScheduleByMsgAlarmId(int) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return findAlarmSchedule;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AlarmScheduleDAOImpl.class);

}
