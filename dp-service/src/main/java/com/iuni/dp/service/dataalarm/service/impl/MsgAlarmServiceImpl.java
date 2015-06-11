package com.iuni.dp.service.dataalarm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.common.utils.BeanUtilEx;
import com.iuni.dp.persist.common.constants.TypeEnum;
import com.iuni.dp.persist.dataalarm.dao.AlarmScheduleDAO;
import com.iuni.dp.persist.dataalarm.dao.MsgAlarmDAO;
import com.iuni.dp.persist.dataalarm.model.AlarmSchedule;
import com.iuni.dp.persist.dataalarm.model.MsgAlarm;
import com.iuni.dp.persist.dataalarm.model.MsgType;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.DateUtil;
import com.iuni.dp.service.dataalarm.service.MsgAlarmService;
import com.iuni.dp.service.dataalarm.service.MsgTypeService;

@Service("msgAlarmService")
public class MsgAlarmServiceImpl implements MsgAlarmService {
	
	private static final Logger logger = LoggerFactory.getLogger(MsgAlarmServiceImpl.class);
	
	@Autowired
	private MsgAlarmDAO msgAlarmDao;
	
	@Autowired
	private AlarmScheduleDAO alarmScheduleDao;
	
	@Autowired
	private MsgTypeService msgTypeService;
	
	@Override
	public List<MsgAlarm> getAllMsgAlarm() {
		List<MsgAlarm> msgAlarms = null;
		try {
			msgAlarms = msgAlarmDao.getAllMsgAlarm();
		} catch(DataAccessException daex) {
			logger.error("MsgAlarmServiceImpl.getAllMsgAlarm found DataAccessException", daex);
			throw new DBException(daex);
		}
		return msgAlarms;
	}
	
	@Override
	public List<MsgAlarm> fetchMsgAlarmByPage(Map<String, Object> params) {
		List<MsgAlarm> msgAlarms = null;
		
		try {
			msgAlarms = msgAlarmDao.getMsgAlarmByPage(params);
			
		} catch(DataAccessException daex) {
			logger.error("MsgAlarmServiceImpl.fetchMsgAlarmByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return msgAlarms;
	}

	@Override
	public Integer fetchTotalCount(Map<String, Object> params) {
		Integer count = 0;
		
		try {
			count = msgAlarmDao.getTotalCount(params);
			
		} catch(DataAccessException daex) {
			logger.error("MsgAlarmServiceImpl.fetchTotalCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return count;
	}

	@Override
	public void batchSaveMsgAlarm(final List<MsgAlarm> msgAlarmList) {
		long stime = System.currentTimeMillis();
		int msgAlarmListCount = (msgAlarmList != null) ? msgAlarmList.size() : 0;
		logger.debug("MsgAlarmServiceImpl.batchSaveMsgAlarm(List<MsgAlarm>) entry: msgAlarmListCount={}"
				,new Object[] { msgAlarmListCount });
		if (msgAlarmListCount > 0) {
			List<String[]> msgAlarmIdList = msgAlarmDao.batchSaveMsgAlarm(msgAlarmList);
			if (msgAlarmIdList != null && msgAlarmIdList.size() > 0) {
				for (String[] msgAlarmTypeArray : msgAlarmIdList) {
					Integer msgTypeId = Integer.valueOf(msgAlarmTypeArray[0]);
					Integer msgAlarmId = Integer.valueOf(msgAlarmTypeArray[1]);
//					MsgType findMsgType = MsgTypeServiceImpl.getMsgTypeOjb(String.valueOf(msgTypeId));
					MsgType findMsgType = msgTypeService.getMsgTypeByMsgTypeId(String.valueOf(msgTypeId));
					if (findMsgType != null) {
						int senderStrategyCode = findMsgType.getSenderStrategyCode();
						int senderDelaySeconds = findMsgType.getSenderDelaySeconds();
						int senderTimingSeconds = findMsgType.getSenderTimingSeconds();
						Date nextExecuteTime = getNextExecuteTimeSenderStrategy(senderStrategyCode,senderDelaySeconds,senderTimingSeconds);
						AlarmSchedule alarmSchedule = new AlarmSchedule(msgAlarmId,msgTypeId,nextExecuteTime);
						BeanUtilEx.copyProperties(alarmSchedule, findMsgType);
						alarmScheduleDao.insertAlarmSchedule(alarmSchedule);
					}
				}
			}
			logger.debug("MsgAlarmServiceImpl.batchSaveMsgAlarm(List<MsgAlarm>) success: msgAlarmCountSaveDB={},costTime={}ms",
					new Object[] { msgAlarmListCount,System.currentTimeMillis() - stime });
		}
	}
	
	@Override
	public void saveMsgAlarm(MsgAlarm msgAlarm) {
		logger.debug("MsgAlarmServiceImpl.saveMsgAlarm(MsgAlarm) entry: msgAlarm={}"
				,new Object[] { msgAlarm.toString() });
		long stime = System.currentTimeMillis();
		Integer msgAlarmId = msgAlarmDao.saveMsgAlarm(msgAlarm);
//		MsgType findMsgType = MsgTypeServiceImpl.getMsgTypeOjb(msgAlarm.getMsgTypeId());
		MsgType findMsgType = msgTypeService.getMsgTypeByMsgTypeId(msgAlarm.getMsgTypeId());
		if (findMsgType != null) {
			//消息类型发送策略参数
			int senderStrategyCode = findMsgType.getSenderStrategyCode();
			int senderDelaySeconds = findMsgType.getSenderDelaySeconds();
			int senderTimingSeconds = findMsgType.getSenderTimingSeconds();
			//计算下次执行时间
			Date nextExecuteTime = getNextExecuteTimeSenderStrategy(senderStrategyCode,senderDelaySeconds,senderTimingSeconds);
			Integer msgTypeId = findMsgType.getMsgTypeId();
			AlarmSchedule alarmSchedule = new AlarmSchedule(msgAlarmId,msgTypeId,nextExecuteTime);
			BeanUtilEx.copyProperties(alarmSchedule, findMsgType);
			alarmScheduleDao.insertAlarmSchedule(alarmSchedule);
			logger.debug("MsgAlarmServiceImpl.saveMsgAlarm(MsgAlarm) success: costTime={}ms"
					, new Object[] {System.currentTimeMillis() - stime });
		}
	}
	
	@Override
	public void alarmProcessSucessLogic(int msgAlarmId,int executeNumber,int executeStrategyCode,int executeDelaySeconds){
		MsgAlarm findMsgAlarm = msgAlarmDao.getMsgAlarmByMsgAlarmId(msgAlarmId);
		if (findMsgAlarm!=null) {
			//累计执行成功总次数
			Integer executeSucessNum = findMsgAlarm.getExecuteSucessNum();
			if (TypeEnum.executeStrategyCodeEnum.EXECUTE_MANY_TIMES.getKey() == executeStrategyCode) {
				if (executeSucessNum <= executeNumber) {// 表示执行成功后，后续还需要执行
					//执行成功次数加一
					msgAlarmDao.updateExecuteSucessNum(msgAlarmId);
					//更新告警排期记录的下一次执行时间,同时将执行失败次数清零
				    Date nextExecuteTime = getNextExecuteTimeExecuteStrategy(executeStrategyCode,executeDelaySeconds);
					alarmScheduleDao.updateNextExecuteTimeExecuteFailNum(nextExecuteTime,0, msgAlarmId);
				} else {// 表示执行成功后，达到最大执行次数后，不需要再执行
					alarmProcessFailSucessNoRepeat(msgAlarmId);
				}
			}else if (TypeEnum.executeStrategyCodeEnum.EXECUTE_AT_A_TIME.getKey() == executeStrategyCode) {
				//执行成功次数加一
				msgAlarmDao.updateExecuteSucessNum(msgAlarmId);
				// 表示执行成功后，不需要再执行
				alarmProcessFailSucessNoRepeat(msgAlarmId);
			}
		}
	}
	
	@Override
	public void alarmProcessFailLogicRepeat(int msgAlarmId,int failRepeatNumber,int failStrategyCode,int failDelaySeconds){
		AlarmSchedule findAlarmSchedule = alarmScheduleDao.getAlarmScheduleByMsgAlarmId(msgAlarmId);
		if (findAlarmSchedule!=null) {
			//累计执行失败总次数
			Integer executeFailNum = findAlarmSchedule.getExecuteFailNum();
			if (TypeEnum.failStrategyCodeEnum.DO_NOT_DEAL_WITH.getKey()==failStrategyCode) {//失败后不处理
				alarmProcessFailSucessNoRepeat(msgAlarmId);
			}else if (TypeEnum.failStrategyCodeEnum.DELAY_RETRY_N_TIME.getKey()==failStrategyCode) {//失败后延时重试N次
				if (executeFailNum <= failRepeatNumber) {// 表示执行失败后，后续还需要延迟执行N次
	                //更新告警排期记录的下一次执行时间,(这里不需要更新累计执行失败总次数)
					Date nextExecuteTime = getNextExecuteTimeFailStrategy(failStrategyCode,failDelaySeconds);
					//执行失败次数加一
					executeFailNum++;
					alarmScheduleDao.updateNextExecuteTimeExecuteFailNum(nextExecuteTime,executeFailNum, msgAlarmId);
				} else {// 表示执行失败后，已经达到最大失败次数，不需要再执行
					alarmProcessFailSucessNoRepeat(msgAlarmId);
				}
			}else if (TypeEnum.failStrategyCodeEnum.IMMEDIATELY_RETRY_N_TIME.getKey()==failStrategyCode) {//失败后立即重试N次
				if (executeFailNum <= failRepeatNumber) {// 表示执行失败后，后续还需要立即执行N次
	                //更新告警排期记录的下一次执行时间,(这里不需要更新累计执行失败总次数)
					Date nextExecuteTime = getNextExecuteTimeFailStrategy(failStrategyCode,0);
					//执行失败次数加一
					executeFailNum++;
					alarmScheduleDao.updateNextExecuteTimeExecuteFailNum(nextExecuteTime,executeFailNum, msgAlarmId);
				} else {// 表示执行失败后，已经达到最大失败次数，不需要再执行
					alarmProcessFailSucessNoRepeat(msgAlarmId);
				}
			}
		}
	}
	
	@Override
	public void alarmProcessFailSucessNoRepeat(int msgAlarmId) {
		 //修改告警记录状态为"处理完结"
		 msgAlarmDao.updateAlarmState(TypeEnum.alarmStateEnum.PROCESS_COMPLETION.getKey(), msgAlarmId);
		 //删除当前告警排期记录
		 alarmScheduleDao.deleteAlarmSchedule(msgAlarmId);
	}
	
	/**
	 * @Description: 针对发送策略，下一次执行发送时间
	 *               发送策略code :11:立即发送  12：延迟发送 13:定时发送  14: 循环发送  缺省值为11
	 * @param int senderStrategyCode 发送策略code :11:立即发送  12：延迟发送 13:定时发送  14: 循环发送  缺省值为11
	 * @param Integer senderDelaySeconds 延时发送时间(相对于系统当前时间，单位：秒),缺省值为0
	 * @param Integer senderTimingSeconds 定时发送时间(相对于每天的凌晨0时,单位：秒),缺省值为0
	 * @return Date
	 */
	private Date getNextExecuteTimeSenderStrategy(int senderStrategyCode,int senderDelaySeconds,int senderTimingSeconds) {
		Date sysTimeDate = new Date();
		if (TypeEnum.senderStrategyCodeEnum.DELAY_SEND.getKey() == senderStrategyCode) {
			return DateUtil.getNextSeconds(sysTimeDate, senderDelaySeconds);
		} else if (TypeEnum.senderStrategyCodeEnum.TIMING_SEND.getKey() == senderStrategyCode) {
			Date morningTimeDate = DateUtil.getMorningDateInTheDate(sysTimeDate);
			return DateUtil.getNextSeconds(morningTimeDate, senderTimingSeconds);
		} else if (TypeEnum.senderStrategyCodeEnum.IMMEDIATELY_SEND.getKey() == senderStrategyCode) {
			return sysTimeDate;
		}
		return null;
	}
	
	/**
	 * @Description: 针对执行策略，下一次执行发送时间
	 *               执行策略Code 21:一次执行 22：多次执行  缺省值为21
	 * @param int executeStrategyCode 执行策略Code 21:一次执行 22：多次执行
	 * @param int executeDelaySeconds 执行延时时间（相对于上次执行完成的时间，单位：秒）,缺省值为0
	 * @return Date
	 */
	private Date getNextExecuteTimeExecuteStrategy(int executeStrategyCode,int executeDelaySeconds){
		Date sysTimeDate = new Date();
		if (TypeEnum.executeStrategyCodeEnum.EXECUTE_MANY_TIMES.getKey() == executeStrategyCode) {
			return DateUtil.getNextSeconds(sysTimeDate, executeDelaySeconds);
		}
		return null;
	}
	
	/**
	 * @Description: 针对失败策略，下一次执行发送时间
	 *               失败策略Code 31:不处理 32：立即重试N次 33:延时重试N次
	 * @param int failStrategyCode 失败策略Code 31:不处理 32：立即重试N次 33:延时重试N次
	 * @param int failDelaySeconds 失败重试延时时间（相对于上次失败重试执行完成的时间，单位：秒）,缺省值为0
	 * @return Date
	 */
	private Date getNextExecuteTimeFailStrategy(int failStrategyCode,int failDelaySeconds){
		Date sysTimeDate = new Date();
		if (TypeEnum.failStrategyCodeEnum.DELAY_RETRY_N_TIME.getKey() == failStrategyCode) {
			return DateUtil.getNextSeconds(sysTimeDate, failDelaySeconds);
		}else if (TypeEnum.failStrategyCodeEnum.IMMEDIATELY_RETRY_N_TIME.getKey() == failStrategyCode) {
			return DateUtil.getNextSeconds(sysTimeDate, 0);
		}
		return null;
	}

}
