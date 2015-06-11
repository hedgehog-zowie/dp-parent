package com.iuni.dp.service.dataalarm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.common.constants.TypeEnum;
import com.iuni.dp.persist.dataalarm.dao.BusLogDAO;
import com.iuni.dp.persist.dataalarm.model.BusLog;
import com.iuni.dp.persist.dataalarm.model.MsgAlarm;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.dataalarm.service.BusLogService;
import com.iuni.dp.service.sys.constants.SysCons;

@Service("busLogService")
public class BusLogServiceImpl implements BusLogService {
	private static final Logger logger = LoggerFactory.getLogger(BusLogServiceImpl.class);
	
	@Autowired
	private BusLogDAO busLogDao;
	
	@Override
	public List<BusLog> fetchAllBusLog() {
		List<BusLog> busLogs = null;
		
		try {
			busLogs = busLogDao.getAllBusLog();
		} catch(DataAccessException daex) {
			logger.error("BusLogServiceImpl.fetchAllBusLog found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return busLogs;
	}
	
	@Override
	public List<BusLog> fetchBusLogByPage(Map<String,Object> params) {
		List<BusLog> busLogs = null;
		
		try {
			busLogs = busLogDao.getBusLogByPage(params);
			
		} catch(DataAccessException daex) {
			logger.error("BusLogServiceImpl.fetchBusLogByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return busLogs;
	}

	@Override
	public Integer fetchTotalCount(Map<String,Object> params) {
		Integer count = 0;
		
		try {
			count = busLogDao.getTotalCount(params);
			
		} catch(DataAccessException daex) {
			logger.error("BusLogServiceImpl.fetchTotalCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return count;
	}

	@Override
	public void saveBusLog(MsgAlarm afterContent,Integer executResult,String remark){
		logger.debug("BusLogServiceImpl.saveBusLog(Object,Integer) entry: afterContent={},executResult={},remark={}",
				new Object[] { afterContent.toString(),executResult,remark});
		long stime = System.currentTimeMillis();
		BusLog busLog = new BusLog();
		busLog.setOptId(SysCons.SYS_OPT_ID);
		busLog.setBusType(TypeEnum.BusLogEnum.SAVE_MSG_ALARM.getKey());
		busLog.setAfterContent(afterContent.toString());
		busLog.setExecutResult(executResult);
		busLog.setRemark(remark);
		busLogDao.saveBusLog(busLog);
		logger.debug("BusLogServiceImpl.saveBusLog(Object,Integer) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
	}
	
	@Override
	public void batchSaveBusLog(List<MsgAlarm> busDataObjectList,Integer executResult,String remark) {
		int busDataObjectListCount = (busDataObjectList != null) ? busDataObjectList.size() : 0;
		logger.debug("BusLogServiceImpl.batchSaveBusLog(List<Object>,Integer) entry: busDataObjectListCount={},executResult={}",
				new Object[] { busDataObjectListCount, executResult });
		long stime = System.currentTimeMillis();
		List<BusLog> busLogList = new ArrayList<BusLog>();
		if (busDataObjectListCount > 0) {
			for (Object busDataObject : busDataObjectList) {
				BusLog busLog = new BusLog();
				busLog.setOptId(SysCons.SYS_OPT_ID);
				busLog.setBusType(TypeEnum.BusLogEnum.BATCH_SAVE_MSG_ALARM.getKey());
				busLog.setAfterContent(busDataObject.toString());
				busLog.setExecutResult(executResult);
				busLog.setRemark(remark);
				busLogList.add(busLog);
			}
			busLogDao.batchSaveBusLog(busLogList);
			logger.debug("BusLogServiceImpl.batchSaveBusLog(List<Object>,Integer) success: costTime={}ms",
					new Object[] { System.currentTimeMillis() - stime });
		}
	}

}
