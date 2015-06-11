package com.iuni.dp.persist.datareport.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datareport.model.GnAppImeiLog;

/**
 * 金立相关APP客户端IMEI首次启用记录日志DAO
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public interface GnAppImeiLogDao extends BaseDao<GnAppImeiLog, Serializable> {

	/**
	 * 按条件查询金立相关APP客户端IMEI首次启用记录日志记录
	 * @param Map<String, Object> params
	 * @return List<GnAppImeiLog>
	 */
	public List<GnAppImeiLog> selectGnAppImeiLogByMap(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端IMEI首次启用记录日志记录数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectGnAppImeiLogCount(Map<String, Object> params);
	
	/**
	 * 新增金立相关APP客户端IMEI首次启用记录日志
	 * @param GnAppImeiLog gnAppImeiLog
	 * @return Integer
	 */
	public Integer insertGnAppImeiLog(GnAppImeiLog gnAppImeiLog);
	
	/**
	 * 批量新增金立相关APP客户端IMEI首次启用记录日志
	 * @param List<GnAppImeiLog> gnAppImeiLogs
	 * @return Integer
	 */
	public Integer batchInsertGnAppImeiLog(final List<GnAppImeiLog> gnAppImeiLogs);
	
}
