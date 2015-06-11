package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.GnAppImeiLog;

/**
 * 金立相关APP客户端IMEI首次启用时间日志Service
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public interface GnAppImeiLogService {
	
	/**
	 * 按渠道获取相应的新增用户数统计
	 * @param List<Map<String, Object>> list
	 * @return Map<String, List<Map<String, Object>>>
	 */
	public Map<String, List<Map<String, Object>>> fetchNewUserStatMapByChannel(List<Map<String, Object>> list, Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道新增用户数统计
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchNewUserOfChannelByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询金立相关APP客户端渠道新增用户数统计
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchNewUserOfChannelByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道新增用户数统计数目
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer fetchNewUserOfChannelCount(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS留存用户相关统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIuniOSRemainUserOnDayByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI OS留存用户相关统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIuniOSRemainUserOnDayByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS留存用户相关统计记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIuniOSRemainUserOnDayCount(Map<String, Object> params);
	
	/**
	 * 按条件为Chart图表查询IUNI OS留存率
	 * @param list
	 * @param params
	 * @return
	 */
	public Map<String, List<Map<String, Object>>> queryIuniOSRemainUserOnDay4Chart(
			List<Map<String, Object>> list, Map<String, Object> params);
	
	/**
	 * 新增金立相关APP客户端IMEI首次启用时间日志记录
	 * @param GnAppImeiLog wjqClientImeiLog
	 * @return Integer
	 */
	public Integer saveGnAppImeiLog(GnAppImeiLog wjqClientImeiLog);
	
	/**
	 * 删除金立相关APP客户端IMEI首次启用时间日志记录
	 * @param Long id
	 * @return Integer
	 */
	public Integer removeGnAppImeiLogById(Long id);
	
}
