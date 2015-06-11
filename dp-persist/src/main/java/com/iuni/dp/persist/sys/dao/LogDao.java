package com.iuni.dp.persist.sys.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.sys.model.Log;

/**
 * 日志DAO
 * @author menglei
 */
public interface LogDao {
	
	public void saveLog(Log iasLog);
	
	public List<Log> getLogs(Map<String, Object> map);
	
	public void deleteLog(String[] logIdArr) throws SQLException;

	public Integer getTotalCnt(Map<String, Object> map);
	
	public void saveBatchLog(List<Log> logList);
	
	/**
	 * 每隔多长时间清除日志数据
	 * @param intervalTime
	 */
	public void deleteLogforIntervalTime(String intervalTime);
}
