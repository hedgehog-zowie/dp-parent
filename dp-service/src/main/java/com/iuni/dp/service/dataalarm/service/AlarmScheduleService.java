package com.iuni.dp.service.dataalarm.service;

import java.util.List;

import com.iuni.dp.persist.dataalarm.model.AlarmSchedule;

public interface AlarmScheduleService {
	
	/**
	 * 保存告警排期信息
	 * @param AlarmSchedule alarmSchedule
	 * @return void
	 */
	public void insertAlarmSchedule(AlarmSchedule alarmSchedule);
	
	/**
	 * 获取待处理的告警排期信息
	 * @param int maxLimitOneTime
	 * @return List<AlarmSchedule>
	 */
	public List<AlarmSchedule> getToDoAlarmScheduleList(int maxLimitOneTime);

}
