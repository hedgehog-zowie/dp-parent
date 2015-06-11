package com.iuni.dp.service.dataalarm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.dataalarm.dao.AlarmScheduleDAO;
import com.iuni.dp.persist.dataalarm.model.AlarmSchedule;
import com.iuni.dp.service.dataalarm.service.AlarmScheduleService;

@Repository("alarmScheduleService")
public class AlarmScheduleServiceImpl implements AlarmScheduleService {
	
	private static final Logger logger = LoggerFactory.getLogger(AlarmScheduleServiceImpl.class);
	
	@Autowired
	private AlarmScheduleDAO alarmScheduleDao;
	
	@Override
	public void insertAlarmSchedule(AlarmSchedule alarmSchedule){
		long stime = System.currentTimeMillis();
		logger.debug("AlarmScheduleServiceImpl.insertAlarmSchedule(AlarmSchedule) entry: alarmSchedule={}",
				new Object[] { alarmSchedule.toString() });
		alarmScheduleDao.insertAlarmSchedule(alarmSchedule);
		logger.debug("AlarmScheduleServiceImpl.insertAlarmSchedule(AlarmSchedule) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
	}
	
	@Override
	public List<AlarmSchedule> getToDoAlarmScheduleList(int maxLimitOneTime) {
		long stime = System.currentTimeMillis();
		logger.debug("AlarmScheduleDAOImpl.getToDoAlarmScheduleList(int) entry: maxLimitOneTime={}",
				new Object[] { maxLimitOneTime });
		List<AlarmSchedule> alarmScheduleList = alarmScheduleDao.getToDoAlarmScheduleList(maxLimitOneTime);
		logger.debug("AlarmScheduleDAOImpl.getToDoAlarmScheduleList(int) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return alarmScheduleList;
	}
	
}
