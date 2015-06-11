package com.iuni.dp.admin.dataalarm.facade;

import java.util.List;

import com.iuni.dp.persist.dataalarm.model.MsgAlarm;
import com.iuni.dp.service.common.bean.Result;

/**
 *************************************************************** 
 * <p> 
 * @CLASS :com.gionee.ias.alarm.facade.AlarmFacade
 * @DESCRIPTION :信息告警系统供第三方调用hessian接口   
 * @AUTHOR :menglei@gionee.com 
 * @VERSION :v1.0 
 * @DATE :2013-4-15 下午4:29:05             
 * @MODIFY  menglei  :        
 * @AUTHOR  : 
 * @VERSION :v2.0 
 * @MODIFY DATE :
 * </p>     
 ****************************************************************
 */
public interface AlarmFacade {
	
	/**
	 * @Title: msgAlarm 
	 * @Description: 消息告警接口
	 * @param String msgTypeId   消息类型ID
	 * @param String msgSubject  消息主题
	 * @param String msgContent  消息内容
	 * @return Result   
	 * @throws
	 */
	public Result msgAlarm(String msgTypeId, String msgSubject, String msgContent);
	
	/**
	 * @Title: batchMsgAlarmByList 
	 * @Description: 消息告警(可供java客户端调用)
	 * @param List<MsgAlarm> msgAlarmList  告警消息对象列表
	 * @return Result   
	 * @throws
	 */
	public Result batchMsgAlarmByList(List<MsgAlarm> msgAlarmList);
	
	/**
	 * @Title: msgAlarmListJsonStr 
	 * @Description: 消息告警(可供php客户端调用)
	 * @param String msgAlarmListJsonStr  消息告警对象列表json字符串
	 *        比如：[{"msgTypeId":"msgTypeId1","msgSubject":"msgSubject1","msgContent":"msgContent1"},
	 *               {"msgTypeId":"msgTypeId2","msgSubject":"msgSubject2","msgContent":"msgContent2"}]
	 * @return Result   
	 * @throws
	 */
	public Result batchMsgAlarmByString(String msgAlarmListJsonStr);

}
