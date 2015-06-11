package com.iuni.dp.service.datareport.service;

import java.util.List;

import com.iuni.dp.persist.datareport.model.GnAppAccessLog;

/**
 * 金立相关APP客户端登入登出记录日志Service
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public interface GnAppAccessLogService {

	/**
	 * 新增金立相关APP客户端登入登出记录日志
	 * @param GnAppAccessLog gnAppAccessLog
	 * @return Integer
	 */
	public Integer saveGnAppAccessLog(GnAppAccessLog gnAppAccessLog);
	
	/**
	 * 批量新增金立相关APP客户端登入登出记录日志
	 * @param List<GnAppAccessLog> gnAppAccessLogs
	 * @return Integer
	 */
	public Integer batchSaveGnAppAccessLog(List<GnAppAccessLog> gnAppAccessLogs);
	
	/**
	 * 新增金立相关APP客户端登入登出记录日志
	 * @param GnAppAccessLog gnAppAccessLog
	 * @return Integer
	 */
	public Integer saveGnAppAccessLogEx(GnAppAccessLog gnAppAccessLog);
	
	/**
	 * 批量新增金立相关APP客户端登入登出记录日志
	 * @param List<GnAppAccessLog> gnAppAccessLogs
	 * @return Integer
	 */
	public Integer batchSaveGnAppAccessLogEx(final List<GnAppAccessLog> gnAppAccessLogs);
	
}
