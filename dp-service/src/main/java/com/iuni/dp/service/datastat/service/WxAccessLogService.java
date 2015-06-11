package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.WxAccessLog;
import com.iuni.dp.service.common.exception.ServiceException;

/**
 * 
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public interface WxAccessLogService {

	/**
	 * 根据ID查询微信公众帐号访问日志
	 * @param id
	 * @return WxAccessLog
	 */
	public WxAccessLog fetchWxAccessLogById(Integer id);
	
	/**
	 * 按条件查询微信公众帐号访问日志
	 * @param params 
	 * @return List<WxAccessLog>
	 */
	public List<WxAccessLog> fetchWxAccessLogByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询微信公众帐号访问日志
	 * @param params
	 * @return List<WxAccessLog>
	 */
	public List<WxAccessLog> fetchWxAccessLogByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询微信公众帐号访问日志记录数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer fetchWxAccessLogCount(Map<String, Object> params);
	
	/**
	 * 按条件查询微信新增粉丝数相关统计
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchNewFansStatByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询微信新增粉丝数相关统计
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchNewFansStatByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询微信新增粉丝数相关统计记录数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer fetchNewFansStatCount(Map<String, Object> params);
	
	/**
	 * 根据条件按日查询微信活跃用户数相关统计
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchActiveUserStatDailyByExample(Map<String, Object> params);
	
	/**
	 * 根据条件按日分页查询微信活跃用户数相关统计
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchActiveUserStatDailyByPage(Map<String, Object> params);
	
	/**
	 * 根据条件按日查询微信活跃用户数相关统计记录数
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public Integer fetchActiveUserStatDailyCount(Map<String, Object> params);
	
	/**
	 * 根据条件按月查询微信活跃用户数相关统计
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchActiveUserStatMonthlyByExample(Map<String, Object> params);
	
	/**
	 * 根据条件按月分页查询微信活跃用户数相关统计
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchActiveUserStatMonthlyByPage(Map<String, Object> params);
	
	/**
	 * 根据条件按月查询微信活跃用户数相关统计记录数
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public Integer fetchActiveUserStatMonthlyCount(Map<String, Object> params);
	
	/**
	 * 按条件查询微信上行文本内容相关统计
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchUpwardTextStatByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询微信上行文本内容相关统计
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchUpwardTextStatByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询微信上行文本内容相关统计记录数
	 * @param params
	 * @return Integer
	 */
	public Integer fetchUpwardTextStatCount(Map<String, Object> params);
	
	/**
	 * 按条件查询微信公众帐号图片下载排行
	 * @param params 
	 * @return List<WxAccessLog>
	 */
	public List<Map<String, Object>> selectWxImageDownloadRanks(Map<String, Object> paramMap) throws ServiceException;
	
	/**
	 * 按条件查询微信公众帐号图片下载排行-记录总数
	 * @param params 
	 * @return List<WxAccessLog>
	 */
	public Integer selectWxImageDownloadRanksCount(Map<String, Object> paramMap)throws ServiceException;
	
	/**
	 * 按条件查询微信公众帐号图片下载总次数
	 * @param params 
	 * @return List<WxAccessLog>
	 */
	public Integer selectWxImageDownloadTotalCount(Map<String, Object> paramMap)throws ServiceException;
	/**
	 * 新增微信公众帐号访问日志
	 * @param wxAccessLog
	 * @return Integer
	 */
	public Integer saveWxAccessLog(WxAccessLog wxAccessLog);
	
	/**
	 * 根据ID删除微信公众帐号访问日志记录
	 * @param id
	 * @return Integer
	 */
	public Integer removeWxAccessLogById(Integer id);
	
}
