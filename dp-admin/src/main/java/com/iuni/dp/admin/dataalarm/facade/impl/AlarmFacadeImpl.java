package com.iuni.dp.admin.dataalarm.facade.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.admin.dataalarm.facade.AlarmFacade;
import com.iuni.dp.admin.dataalarm.validate.AlarmFacadeImplValidity;
import com.iuni.dp.persist.common.constants.TypeEnum;
import com.iuni.dp.persist.dataalarm.model.MsgAlarm;
import com.iuni.dp.service.common.bean.Result;
import com.iuni.dp.service.common.exception.BusinessException;
import com.iuni.dp.service.common.exception.ExceptionProcessor;
import com.iuni.dp.service.common.exception.ParameterException;
import com.iuni.dp.service.dataalarm.service.BusLogService;
import com.iuni.dp.service.dataalarm.service.MsgAlarmService;

@Service("alarmFacade")
public class AlarmFacadeImpl implements AlarmFacade {
	private static final Logger logger = LoggerFactory.getLogger(AlarmFacadeImpl.class);
	private static final String opt_sucess = TypeEnum.ResultEnum.SUCESS.getValue();
	
	@Autowired
	private MsgAlarmService msgAlarmService;
	
	@Autowired
	private BusLogService busLogService;
	
	@Override
	public Result msgAlarm(String msgTypeId, String msgSubject, String msgContent){
		logger.info("AlarmFacadeImpl.msgAlarm() entry: msgTypeId={},msgSubject={},msgContent={}",
				new Object[] { msgTypeId,msgSubject,msgContent});
		long stime = System.currentTimeMillis();
		AlarmFacadeImplValidity.checkMsgAlarm(msgTypeId, msgSubject, msgContent);
		MsgAlarm msgAlarm = new MsgAlarm(msgTypeId, msgSubject, msgContent);
		try {
			msgAlarmService.saveMsgAlarm(msgAlarm);
			busLogService.saveBusLog(msgAlarm, Integer.valueOf(Result.SUCCESS),opt_sucess);
			Result result = new Result();
			result.setState(Result.SUCCESS);
			result.setData(msgAlarm);
			logger.info("AlarmFacadeImpl.msgAlarm() success: costTime={}ms",new Object[] {System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			Result peResult= ExceptionProcessor.getParameterExceptionResult(e);
			busLogService.saveBusLog(msgAlarm, Integer.valueOf(Result.FAIL),peResult.getErrorMessage());
			return peResult;
		} catch (BusinessException e) {
			Result beResult = ExceptionProcessor.getBusinessExceptionResult(e);
			busLogService.saveBusLog(msgAlarm, Integer.valueOf(Result.FAIL),beResult.getErrorMessage());
			return beResult;
		} catch (Exception e) {
			Result eResult = ExceptionProcessor.getExceptionResult(e);
			busLogService.saveBusLog(msgAlarm, Integer.valueOf(Result.FAIL),eResult.getErrorMessage());
			return eResult;
		}
	}
	
	@Override
	public Result batchMsgAlarmByList(List<MsgAlarm> msgAlarmList){
		int size = (msgAlarmList != null) ? msgAlarmList.size() : 0;
		logger.info("AlarmFacadeImpl.batchMsgAlarmByList(List<MsgAlarm>) entry: size={}",new Object[] { size});
		long stime = System.currentTimeMillis();
		try {
			AlarmFacadeImplValidity.checkBatchMsgAlarmByList(msgAlarmList);
			msgAlarmService.batchSaveMsgAlarm(msgAlarmList);
			busLogService.batchSaveBusLog(msgAlarmList, Integer.valueOf(Result.SUCCESS),opt_sucess);
			Result result =  new Result();
			result.setState(Result.SUCCESS);
			logger.info("AlarmFacadeImpl.batchMsgAlarmByList(List<MsgAlarm>) success: costTime={}ms",new Object[] {System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			Result peResult= ExceptionProcessor.getParameterExceptionResult(e);
			busLogService.batchSaveBusLog(msgAlarmList, Integer.valueOf(Result.FAIL),peResult.getErrorMessage());
			return peResult;
		} catch (BusinessException e) {
			Result beResult= ExceptionProcessor.getBusinessExceptionResult(e);
			busLogService.batchSaveBusLog(msgAlarmList, Integer.valueOf(Result.FAIL),beResult.getErrorMessage());
			return beResult;
		} catch (Exception e) {
			Result eResult= ExceptionProcessor.getExceptionResult(e);
			busLogService.batchSaveBusLog(msgAlarmList, Integer.valueOf(Result.FAIL),eResult.getErrorMessage());
			return eResult;
		}
	}
	
	@Override
	public Result batchMsgAlarmByString(String msgAlarmListJsonStr){
		logger.info("AlarmFacadeImpl.batchMsgAlarmByString(String) entry: msgAlarmListJsonStr={}",new Object[] { msgAlarmListJsonStr});
		long stime = System.currentTimeMillis();
		List<MsgAlarm> msgAlarmList = null;
		try {
			msgAlarmList = AlarmFacadeImplValidity.checkBatchMsgAlarmByString(msgAlarmListJsonStr);
			msgAlarmService.batchSaveMsgAlarm(msgAlarmList);
			busLogService.batchSaveBusLog(msgAlarmList, Integer.valueOf(Result.SUCCESS),opt_sucess);
			Result result =  new Result();
			result.setState(Result.SUCCESS);
			logger.info("AlarmFacadeImpl.batchMsgAlarmByString(String) success: costTime={}ms",new Object[] {System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			Result peResult= ExceptionProcessor.getParameterExceptionResult(e);
			busLogService.batchSaveBusLog(msgAlarmList, Integer.valueOf(Result.FAIL),peResult.getErrorMessage());
			return peResult;
		} catch (BusinessException e) {
			Result beResult= ExceptionProcessor.getBusinessExceptionResult(e);
			busLogService.batchSaveBusLog(msgAlarmList, Integer.valueOf(Result.FAIL),beResult.getErrorMessage());
			return beResult;
		} catch (Exception e) {
			Result eResult= ExceptionProcessor.getExceptionResult(e);
			busLogService.batchSaveBusLog(msgAlarmList, Integer.valueOf(Result.FAIL),eResult.getErrorMessage());
			return eResult;
		}
	}

}
