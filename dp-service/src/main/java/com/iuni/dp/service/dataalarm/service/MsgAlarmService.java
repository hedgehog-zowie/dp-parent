package com.iuni.dp.service.dataalarm.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.dataalarm.model.MsgAlarm;

public interface MsgAlarmService {
	
	/**
	 * 获取全部告警对象信息列表
	 * @return List<MsgAlarm>
	 */
	public List<MsgAlarm> getAllMsgAlarm();
	
	/**
	 * 告警对象信息列表分页查询
	 * @param Map<String, Object> params
	 * @return List<MsgAlarm>
	 */
	public List<MsgAlarm> fetchMsgAlarmByPage(Map<String, Object> params);
	
	/**
	 * 告警对象信息列表总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer fetchTotalCount(Map<String, Object> params);
	
	/**
	 * 批量保存消息告警数据
	 * @param List<MsgAlarm> msgAlarmList
	 * @return void
	 */
	public void batchSaveMsgAlarm(final List<MsgAlarm> msgAlarmList);
	
	/**
	 * 单条消息告警数据入库
	 * @param MsgAlarm msgAlarm
	 * @return void
	 */
	public void saveMsgAlarm(MsgAlarm msgAlarm);
	
	/**
	 * 告警处理成功逻辑
	 * @param int msgAlarmId          消息告警ID
	 * @param int executeNumber       执行次数，,缺省值为0
	 * @param int executeStrategyCode 执行策略Code 21:一次执行 22：多次执行
	 * @param int executeDelaySeconds 执行延时时间（相对于上次执行完成的时间，单位：秒）,缺省值为0 
	 * @return void
	 */
	public void alarmProcessSucessLogic(int msgAlarmId,int executeNumber,int executeStrategyCode,int executeDelaySeconds);
	
	/**
	 * 告警处理失败逻辑(失败需要重发)
	 * @param int msgAlarmId       消息告警ID
	 * @param int failRepeatNumber 失败重试次数,缺省值为0
	 * @param int failStrategyCode 失败策略Code 31:不处理 32：立即重试N次 33:延时重试N次
	 * @param int failDelaySeconds 失败重试延时时间（相对于上次失败重试执行完成的时间，单位：秒）,缺省值为0
	 * @return void
	 */
	public void alarmProcessFailLogicRepeat(int msgAlarmId,int failRepeatNumber,int failStrategyCode,int failDelaySeconds);
	
	/**
	 * 告警处理失败逻辑(失败/成功后不需要重发)
	 * @param int msgAlarmId
	 * @return void
	 */
	public void alarmProcessFailSucessNoRepeat(int msgAlarmId);
	
}
