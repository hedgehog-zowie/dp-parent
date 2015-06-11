package com.iuni.dp.service.sys.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.iuni.dp.persist.common.constants.TypeEnum.BusLogTypeEnum;
import com.iuni.dp.persist.common.constants.TypeEnum.SysLogTypeEnum;
import com.iuni.dp.persist.sys.dao.LogDao;
import com.iuni.dp.persist.sys.model.Log;
import com.iuni.dp.service.sys.constants.LogList;
import com.iuni.dp.service.sys.service.LogService;

/**
 * @author menglei
 */
@Service("logService")  
public class LogServiceImpl implements LogService{
	
	@Autowired 
	private LogDao logDao;
	
	final static Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);
	
	public LogDao getMopLogDao() {
		return logDao;
	}

	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}
 
	/**
	 * 保存单个日志
	 */
	public void insertLog(long operId, String operName, String operIp,String memo, String logType) {
		Log log = new Log(operId, operName, operIp, memo, logType);
		logDao.saveLog(log);
	}

	public List<Log> getLog(Map<String, Object> paramMap) {
		List<Log> loglist=logDao.getLogs(paramMap);
		return loglist;
	}

	/**
	 * 删除日志，可批量删除
	 */
	public void deleteLog(String[] logIdArr) throws SQLException {
		logDao.deleteLog(logIdArr);
	}

	/**
	 * 根据查询条件获取总页数
	 */
	public Integer getTotalCnt(Map<String, Object> map) {
		return logDao.getTotalCnt(map);
	}

	/**
	 * 批量保存日志
	 */
	public void insertBatchLog(List<Log> logList) {
		Collections.reverse(logList);// 把最后保存的内容排在前面
		logDao.saveBatchLog(logList);
	}

	
	public void putLog2Memory(long operId, String operName, String operIp,String memo, String logType) {
		Assert.notNull(memo);
		Assert.notNull(logType);
		Log log = new Log(operId, operName, operIp, memo, logType);
		log.setCreateTime(new Date());
		synchronized (LogList.LOGLISTLOCK) {
			LogList.list.add(log);
		}
	}
	
	public void putLog2Memory(long operId, String operName, String operIp,String memo, SysLogTypeEnum  syslogType) {
		Assert.notNull(memo);
		Assert.notNull(syslogType);
		Log log = new Log(operId, operName, operIp, memo, syslogType);
		log.setCreateTime(new Date());
		synchronized (LogList.LOGLISTLOCK) {
			LogList.list.add(log);
		}
	}
	
	public void putLog2Memory(long operId, String operName, String operIp,String memo, BusLogTypeEnum  buslogType) {
		Assert.notNull(memo);
		Assert.notNull(buslogType);
		Log log = new Log(operId, operName, operIp, memo, buslogType);
		log.setCreateTime(new Date());
		synchronized (LogList.LOGLISTLOCK) {
			LogList.list.add(log);
		}
	}

	@Override
	public void putLog(String memo, String type) {
//		Map<String, Object> session = ActionContext.getContext().getSession();
//		User usr = (User) session.get("user");
//		this.putLog2Memory(usr.getUser_id(), usr.getUser_name(), HttpUtil.getIp(null), memo, type);
	}
	
	public void putLog(String memo, SysLogTypeEnum  syslogType) {		
//		Map<String, Object> session = ActionContext.getContext().getSession();
//		User usr = (User) session.get("user");
//		this.putLog2Memory(usr.getUser_id(), usr.getUser_name(), HttpUtil.getIp(null), memo, syslogType);
	}
	
	public void putLog(String memo, BusLogTypeEnum  buslogType) {
//		Map<String, Object> session = ActionContext.getContext().getSession();
//		User usr = (User) session.get("user");
//		this.putLog2Memory(usr.getUser_id(), usr.getUser_name(), HttpUtil.getIp(null), memo, buslogType);
	}

	@Override
	public void deleteLogforIntervalTime() {
		Date date = new Date();// 当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化对象
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		calendar.add(Calendar.MONTH, -2);// 月份减一
		String intervalTime = sdf.format(calendar.getTime());
		System.out.println("intervalTime:" + intervalTime);// 输出格式化的日期
		logDao.deleteLogforIntervalTime(intervalTime);
	}
 
}
