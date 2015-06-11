package com.iuni.dp.service.datareport.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datareport.model.GnAppImeiLog;

/**
 * 金立相关APP客户端IMEI首次启用记录日志Service
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public interface GnAppImeiLogService {

	/**
	 * 按条件查询金立相关APP客户端IMEI首次启用记录日志记录
	 * @param Map<String, Object> params
	 * @return List<GnAppImeiLog>
	 */
	public List<GnAppImeiLog> fetchGnAppImeiLogByMap(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端IMEI首次启用记录日志记录数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer fetchGnAppImeiLogCount(Map<String, Object> params);
	
	/**
	 * 新增金立相关APP客户端IMEI首次启用记录日志
	 * @param GnAppImeiLog gnAppImeiLog
	 * @return Integer
	 */
	public Integer saveGnAppImeiLog(GnAppImeiLog gnAppImeiLog);
	
	/**
	 * 批量新增金立相关APP客户端IMEI首次启用记录日志
	 * @param List<GnAppImeiLog> gnAppImeiLogs
	 * @return Integer
	 */
	public Integer batchSaveGnAppImeiLog(final List<GnAppImeiLog> gnAppImeiLogs);
	
}
