package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.WxClickLog;

/**
 * 
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public interface WxClickLogService {

	/**
	 * 根据ID查询微信公众帐号点击日志
	 * @param id
	 * @return WxClickLog
	 */
	public WxClickLog fetchWxClickLogById(Integer id);
	
	/**
	 * 按条件查询微信公众帐号点击日志
	 * @param params 
	 * @return List<WxClickLog>
	 */
	public List<WxClickLog> fetchWxClickLogByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询微信公众帐号点击日志
	 * @param params
	 * @return List<WxClickLog>
	 */
	public List<WxClickLog> fetchWxClickLogByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询微信公众帐号点击日志记录数
	 * @param params
	 * @return Integer
	 */
	public Integer fetchWxClickLogCount(Map<String, Object> params);
	
	/**
	 * 新增微信公众帐号点击日志
	 * @param WxClickLog
	 * @return Integer
	 */
	public Integer saveWxClickLog(WxClickLog wxClickLog);
	
	/**
	 * 根据ID删除微信公众帐号点击日志记录
	 * @param id
	 * @return Integer
	 */
	public Integer removeWxClickLogById(Integer id);
	
	/**
	 * 按条件查询活跃用户数
	 * @param params
	 * @return Integer
	 */
	public Integer fetchActiveUserCount(Map<String, Object> params);
	
}
