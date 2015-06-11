package com.iuni.dp.persist.datareport.dao;

import java.io.Serializable;
import java.util.List;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datareport.model.GnAppAccessLog;

/**
 * 金立相关APP客户端登入登出记录日志DAO
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public interface GnAppAccessLogDao extends BaseDao<GnAppAccessLog, Serializable> {

	/**
	 * 新增金立相关APP客户端登入登出记录日志
	 * @param GnAppAccessLog gnAppAccessLog
	 * @return Integer
	 */
	public Integer insertGnAppAccessLog(GnAppAccessLog gnAppAccessLog);
	
	/**
	 * 批量新增金立相关APP客户端登入登出记录日志
	 * @param List<GnAppAccessLog> gnAppAccessLogs
	 * @return Integer
	 */
	public Integer batchInsertGnAppAccessLog(final List<GnAppAccessLog> gnAppAccessLogs);
	
	/**
	 * 新增金立相关APP客户端登入登出记录日志
	 * @param GnAppAccessLog gnAppAccessLog
	 * @return Integer
	 */
	public Integer insertGnAppAccessLogEx(GnAppAccessLog gnAppAccessLog);
	
	/**
	 * 批量新增金立相关APP客户端登入登出记录日志
	 * @param List<GnAppAccessLog> gnAppAccessLogs
	 * @return Integer
	 */
	public Integer batchInsertGnAppAccessLogEx(final List<GnAppAccessLog> gnAppAccessLogs);
	
}
