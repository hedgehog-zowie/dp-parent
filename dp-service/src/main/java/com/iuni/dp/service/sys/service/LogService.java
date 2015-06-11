package com.iuni.dp.service.sys.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.constants.TypeEnum.BusLogTypeEnum;
import com.iuni.dp.persist.common.constants.TypeEnum.SysLogTypeEnum;
import com.iuni.dp.persist.sys.model.Log;

/**
 * 日志服务类
 * @author menglei
 */
public interface LogService {
	
	/**
	 * 把日志信息放内存中暂存
	 * @param operId
	 * @param operName
	 * @param operIp
	 * @param memo
	 * @param logType
	 */
	//public void putLog2Memory(long operId, String operName, String operIp, String memo, String logType);
	
	/**
	 * 把系统日志信息放内存中暂存
	 * @param operId
	 * @param operName
	 * @param operIp
	 * @param memo
	 * @param syslogType
	 */
	public void putLog2Memory(long operId, String operName, String operIp,String memo, SysLogTypeEnum  syslogType);
	
	
	/**
	 * 把业务日志信息放内存中暂存
	 * @param operId
	 * @param operName
	 * @param operIp
	 * @param memo
	 * @param syslogType
	 */
	public void putLog2Memory(long operId, String operName, String operIp,String memo, BusLogTypeEnum  buslogType);
	
	/**
	 * 增加日志
	 * @param operId
	 * @param operName
	 * @param operIp
	 * @param memo
	 * @param logType
	 */
	public void insertLog(long operId, String operName, String operIp, String memo, String logType);
	
	/**
	 * 批量保存日志
	 * @param logList
	 */
	public void insertBatchLog(List<Log> logList);

	/**
	 * 根据查询条件分页查询日志
	 * @param log
	 * @return
	 */
	public List<Log> getLog(Map<String, Object> paraMap);
	
	/**
	 * 删除日志，可批量删除
	 * @param logIdArr
	 * @throws SQLException 
	 */
	public void deleteLog(String[] logIdArr) throws SQLException;
	
	/**
	 * 通过参数查询总记录数
	 * @param map
	 * @return
	 */
	public Integer getTotalCnt(Map<String, Object> map);
	
	/**
	 * 增加存放日志到内存中
	 * 只需日志内容，日志类型（可从LogConstant.java的常量定义中取）
	 * @param memo
	 * @param type
	 */
	public void putLog(String memo, String type);
	
	
	public void putLog(String memo, SysLogTypeEnum  syslogType) ;
	
	public void putLog(String memo, BusLogTypeEnum  buslogType) ;
	
	/**
	 * 每隔多长时间清除日志数据
	 * @param intervalTime
	 */
	public void deleteLogforIntervalTime();
}
