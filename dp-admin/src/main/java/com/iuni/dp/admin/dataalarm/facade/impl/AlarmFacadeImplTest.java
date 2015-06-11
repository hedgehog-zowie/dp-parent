package com.iuni.dp.admin.dataalarm.facade.impl;

import java.net.MalformedURLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.client.HessianProxyFactory;
import com.iuni.dp.admin.dataalarm.facade.AlarmFacade;
import com.iuni.dp.persist.dataalarm.model.MsgAlarm;
import com.iuni.dp.service.common.bean.Result;
import com.iuni.dp.service.common.utils.JsonUtil;

public class AlarmFacadeImplTest {

	public static void main(String[] args) throws MalformedURLException {
		for (int i = 0; i < 1; i++) {
			msgAlarm(i);
			//batchMsgAlarmByString(msgAlarmListJsonStr);
			//batchMsgAlarmByList(msgAlarmList);
		}
	}
	
	public static Result msgAlarm(int i) {
		logger.info("AlarmFacadeImplTest.msgAlarm(int) entry: i={}",new Object[] {i});
		long stime = System.currentTimeMillis();
		Result rs = getAlarmFacade().msgAlarm(msgTypeId, msgSubject+i, msgContent+i);
		if (rs!=null) {
			if(rs.getState().equals("1")){
	        	logger.info("AlarmFacadeImplTest.msgAlarm(int) success: costTime={}ms",new Object[] {System.currentTimeMillis() - stime });
	        }else{
	        	logger.error("AlarmFacadeImplTest.msgAlarm(int) fail:"+rs.getErrorMessage());
	        }
		}
		return rs;
	}
	
	public static Result batchMsgAlarmByList(List<MsgAlarm> msgAlarmList) {
		int count = (msgAlarmList!=null)?msgAlarmList.size():0;
		long stime = System.currentTimeMillis();
		logger.info("AlarmFacadeImplTest.batchMsgAlarmByList(List<MsgAlarm>) entry: count={}",new Object[] { count});
		Result rs = getAlarmFacade().batchMsgAlarmByList(msgAlarmList);
		if (rs!=null) {
			if(rs.getState().equals("1")){
				logger.info("AlarmFacadeImplTest.batchMsgAlarmByList(List<MsgAlarm>) success: costTime={}ms",new Object[] {System.currentTimeMillis() - stime });
	        }else{
	        	logger.error("AlarmFacadeImplTest.batchMsgAlarmByList(List<MsgAlarm>) fail:"+rs.getErrorMessage());
	        }
		}
		return rs;
	}
	
	public static Result batchMsgAlarmByString(String msgAlarmListJsonStr) {
		long stime = System.currentTimeMillis();
		logger.info("AlarmFacadeImplTest.batchMsgAlarmByString(String) entry: msgAlarmListJsonStr={}",new Object[] { msgAlarmListJsonStr});
		Result rs = getAlarmFacade().batchMsgAlarmByString(msgAlarmListJsonStr);
		if (rs!=null) {
			if(rs.getState().equals("1")){
				logger.info("AlarmFacadeImplTest.batchMsgAlarmByString(String) success: costTime={}ms",new Object[] {System.currentTimeMillis() - stime });
	        }else{
	        	logger.error("AlarmFacadeImplTest.batchMsgAlarmByString(String) fail:"+rs.getErrorMessage());
	        }
		}
		return rs;
	}
    
    private static AlarmFacade getAlarmFacade(){
    	AlarmFacade alarmFacade = null;
		try {
			String url = "http://localhost:8090/remote/alarmFacade";
			//String url = "http://ias.cm.com:8080/remote/alarmFacade";
			HessianProxyFactory facadeFactory = new HessianProxyFactory();
			alarmFacade = (AlarmFacade) facadeFactory.create(AlarmFacade.class, url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return alarmFacade;
	}
    
    private final static Logger logger = LoggerFactory.getLogger(AlarmFacadeImplTest.class);
    public static final String msgTypeId = "1";
    public static final String msgSubject = "msgSubject";
    public static final String msgContent = "msgContent";
    public static final String msgAlarmListJsonStr =  new StringBuffer().append("[")
       .append('{')
         .append('"').append("msgTypeId").append('"').append(":").append('"').append("010101").append('"').append(",")
         .append('"').append("msgSubject").append('"').append(":").append('"').append("msgSubject1").append('"').append(",")
         .append('"').append("msgContent").append('"').append(":").append('"').append("msgContent1").append('"')
       .append('}').append(',')
       .append('{')
         .append('"').append("msgTypeId").append('"').append(":").append('"').append("010101").append('"').append(",")
         .append('"').append("msgSubject").append('"').append(":").append('"').append("msgSubject2").append('"').append(",")
         .append('"').append("msgContent").append('"').append(":").append('"').append("msgContent2").append('"')
       .append('}')
	  .append("]").toString();
    public static List<MsgAlarm> msgAlarmList = JsonUtil.toList(msgAlarmListJsonStr, MsgAlarm.class);	
}
