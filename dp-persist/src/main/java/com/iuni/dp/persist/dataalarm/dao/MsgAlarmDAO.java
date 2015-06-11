package com.iuni.dp.persist.dataalarm.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.dataalarm.model.MsgAlarm;

public interface MsgAlarmDAO extends BaseDao<MsgAlarm, Serializable> {

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
	public List<MsgAlarm> getMsgAlarmByPage(Map<String, Object> params);
	
	/**
	 * 告警对象信息列表总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer getTotalCount(Map<String, Object> params);
	
	/**
	 * 根据告警Id获取告警对象信息
	 * @param int msgAlarmId 告警Id
	 * @return int
	 */
	public MsgAlarm getMsgAlarmByMsgAlarmId(int msgAlarmId);
	
	/**
	 * 单条消息告警数据入库
	 * @param MsgAlarm msgAlarm
	 * @return int
	 */
	public int saveMsgAlarm(MsgAlarm msgAlarm);
	
	/**
	 * 批量保存消息告警信息
	 * @param List<MsgAlarm> msgAlarmList
	 * @return List<String[]>
	 */
	public List<String[]> batchSaveMsgAlarm(final List<MsgAlarm> msgAlarmList);
	
	/**
	 * 更新消息告警执行成功次数
	 * @param int msgAlarmId 告警Id
	 * @return int
	 */
	public int updateExecuteSucessNum(int msgAlarmId);
	
	/**
	 * 更新消息告警状态
	 * @param int state 告警状态
	 * @param int msgAlarmId 告警Id
	 * @return int
	 */
	public int updateAlarmState(int state, int msgAlarmId);
	
}
