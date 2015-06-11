package com.iuni.dp.persist.dataalarm.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.dataalarm.model.AlarmSchedule;

public interface AlarmScheduleDAO extends BaseDao<AlarmSchedule, Serializable> {
	
	/**
	 * 保存告警排期信息
	 * @param AlarmSchedule alarmSchedule
	 * @return void
	 */
	public void insertAlarmSchedule(AlarmSchedule alarmSchedule);
	
	/**
	 * 更新告警排期记录 下次执行时间 和 执行失败次数
	 * @param Date nextExecuteTime
	 * @param int executeFailNum
	 * @param int msgAlarmId
	 * @return int
	 */
	public int updateNextExecuteTimeExecuteFailNum(Date nextExecuteTime,Integer executeFailNum,Integer msgAlarmId);
	
	/**
	 * 更新告警排期记录失败次数,执行失败次数加一
	 * @param int msgAlarmId
	 * @return int
	 */
	public int updateExecuteFailNumAddOne(int msgAlarmId);
	
	/**
	 * 获取待处理的告警排期信息
	 * @param int maxLimitOneTime
	 * @return List<AlarmSchedule>
	 */
	public List<AlarmSchedule> getToDoAlarmScheduleList(int maxLimitOneTime);
	
	/**
	 * 根据告警Id获取告警排期对象信息
	 * @param int msgAlarmId 告警Id
	 * @return int
	 */
	public AlarmSchedule getAlarmScheduleByMsgAlarmId(int msgAlarmId);
	
	/**
	 * 根据告警id删除告警排期记录
	 * @param int msgAlarmId
	 * @return int
	 */
	public int deleteAlarmSchedule(int msgAlarmId);

}
