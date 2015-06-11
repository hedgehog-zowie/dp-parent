package com.iuni.dp.admin.dataalarm.validate;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONException;

import org.apache.commons.lang.StringUtils;

import com.iuni.common.utils.CommonValidationUtil;
import com.iuni.dp.persist.dataalarm.model.MsgAlarm;
import com.iuni.dp.service.common.exception.ParameterException;
import com.iuni.dp.service.common.utils.JsonUtil;
import com.iuni.dp.service.dataalarm.service.impl.MsgTypeServiceImpl;

public class AlarmFacadeImplValidity {

	public static void checkMsgAlarm(String msgTypeId, String msgSubject, String msgContent) {
		if (StringUtils.isEmpty(msgTypeId)) {
			throw new ParameterException("msgTypeId not to allow empty!");
		}
		if (!msgTypeMap.containsKey(msgTypeId)) {
			throw new ParameterException("the client's msgTypeId not exist on the server,it's illegal!");
		}
		if (StringUtils.isEmpty(msgSubject)) {
			throw new ParameterException("msgSubject not to allow empty!");
		}
		if (!CommonValidationUtil.checkExactingLength(msgSubject, 0, 100)) {
			throw new ParameterException("msgSubject's maxLen is 100!");
		}
		if (StringUtils.isEmpty(msgContent)) {
			throw new ParameterException("msgContent not to allow empty!");
		}
		if (!CommonValidationUtil.checkExactingLength(msgContent, 0, 500)) {
			throw new ParameterException("msgContent's maxLen is 500!");
		}
	}

	public static void checkBatchMsgAlarmByList(List<MsgAlarm> msgAlarmList) {
		if (msgAlarmList == null) {
			throw new ParameterException("msgAlarmList not to allow null!");
		}
		if (msgAlarmList.size() == 0) {
			throw new ParameterException("msgAlarmList'size not to allow zero!");
		}
		if (msgAlarmList.size() > batchSaveMsgAlarmMaxLineLimit) {
			throw new ParameterException("The batch msg alarm data 's maxLineLimit is "+ batchSaveMsgAlarmMaxLineLimit + "!");
		}
		for (MsgAlarm msgAlarm : msgAlarmList) {
			if (msgAlarm != null) {
				String msgTypeId = msgAlarm.getMsgTypeId();
				String msgSubject = msgAlarm.getMsgSubject();
				String msgContent = msgAlarm.getMsgContent();
				checkMsgAlarm(msgTypeId, msgSubject, msgContent);
			}
		}
	}

	public static List<MsgAlarm> checkBatchMsgAlarmByString(String msgAlarmListJsonStr) {
		List<MsgAlarm> msgAlarmList = null ;
		if (StringUtils.isEmpty(msgAlarmListJsonStr)) {
			throw new ParameterException("msgAlarmListJsonStr not to allow empty!");
		}
		try {
			msgAlarmList = JsonUtil.toList(msgAlarmListJsonStr, MsgAlarm.class);
            if (msgAlarmList==null) {
            	throw new ParameterException("msgAlarmListJsonStr is not a effective json!");
			}
			checkBatchMsgAlarmByList(msgAlarmList);
		} catch (JSONException e) {
			throw new ParameterException("msgAlarmListJsonStr is not a effective json!");
		}
		return msgAlarmList;
	}
	
	private static int batchSaveMsgAlarmMaxLineLimit = 50;
	private static Map<String, String> msgTypeMap = MsgTypeServiceImpl.getMsgTypeMap();

}
