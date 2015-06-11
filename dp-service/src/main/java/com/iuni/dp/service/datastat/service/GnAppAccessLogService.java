package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.GnAppAccessLog;

/**
 * 金立相关APP客户端登入登出记录日志Service
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public interface GnAppAccessLogService {

	/**
	 * 按条件查询金立相关APP客户端渠道趋势统计数据
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchChannelTrendByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询金立相关APP客户端渠道趋势统计数据
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchChannelTrendByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道趋势统计数据统计数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer fetchChannelTrendCount(Map<String, Object> params);
	
	/**
	 * 按渠道获取相应的新增用户数统计
	 * @param List<Map<String, Object>> list
	 * @return Map<String, List<Map<String, Object>>>
	 */
	public Map<String, List<Map<String, Object>>> fetchRegisterStatMapByChannel(List<Map<String, Object>> list, Map<String, Object> params); 
	
	/**
	 * 按条件查询金立相关APP客户端渠道注册用户数统计数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> fetchRegisterOfChannelByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询金立相关APP客户端渠道注册用户数统计数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> fetchRegisterOfChannelByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道注册用户数统计数据统计数
	 * @param params
	 * @return
	 */
	public Integer fetchRegisterOfChannelCount(Map<String, Object> params);
	
	/**
	 * 按渠道获取相应的活跃用户数统计
	 * 
	 * @param list
	 * @param params
	 * @param statRate
	 * @return Map<String, List<Map<String, Object>>>
	 */
	public Map<String, List<Map<String, Object>>> fetchActiveUserStatMapByChannel(
			List<Map<String, Object>> list, Map<String, Object> params,
			final String statRate);
	
	/**
	 * 按条件查询金立相关APP客户端渠道活跃用户数统计数据
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchActiveUserOfChannelDailyByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询金立相关APP客户端渠道活跃用户数统计数据
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchActiveUserOfChannelDailyByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道活跃用户数统计数据统计数
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public Integer fetchActiveUserOfChannelDailyCount(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道月活跃用户数统计数据
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchActiveUserOfChannelMonthlyByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询金立相关APP客户端渠道月活跃用户数统计数据
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchActiveUserOfChannelMonthlyByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道月活跃用户数统计数据统计数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer fetchActiveUserOfChannelMonthlyCount(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道用户的启动次数分布
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchLaunchNumDistribution(Map<String, Object> params);
	
	/**
	 * 按条件查询App机型分布统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryAppUserStatOnModelByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询App机型分布统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryAppUserStatOnModelByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询App机型分布统计记录数
	 * @param params
	 * @return
	 */
	public Integer queryAppUserStatOnModelCount(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道列表
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchChannelList(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端版本列表
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> fetchApkVersionList(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS累计总用户数
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIuniOSTotalUserByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI OS累计总用户数
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIuniOSTotalUserByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS累计总用户数记录数
	 * @param params
	 * @return
	 */
	public Integer queryIuniOSTotalUserCount(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS应用列表
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIuniOSAppNames(Map<String, Object> params);
	
	/**
	 * 按条件根据应用查询对应IUNI OS版本列表
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIuniOSApkVersions(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS用户数相关统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIuniOSUserStatByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI OS用户数相关统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIuniOSUserStatByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS用户数相关统计记录数
	 * @param params
	 * @return
	 */
	public Integer queryIuniOSUserStatCount(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS时间段用户数相关统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIuniOSUserStatOnPeriodByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI OS时间段用户数相关统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIuniOSUserStatOnPeriodByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS时间段用户数相关统计记录数
	 * @param params
	 * @return
	 */
	public Integer queryIuniOSUserStatOnPeriodCount(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS用户数版本分布
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIuniOSVersionDistributeByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI OS用户数版本分布
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryIuniOSVersionDistributeByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS用户数版本分布记录数
	 * @param params
	 * @return
	 */
	public Integer queryIuniOSVersionDistributeCount(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS区域分布情况
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniOSAreaDistributeByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI OS区域分布情况
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniOSAreaDistributeByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS区域分布情况记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIuniOSAreaDistributeCount(Map<String, Object> params);
	
	/**
	 * 新增金立相关APP客户端登入登出记录日志记录
	 * @param GnAppAccessLog wjqClientAccessLog
	 * @return Integer
	 */
	public Integer saveGnAppAccessLog(GnAppAccessLog wjqClientAccessLog);
	
	/**
	 * 根据ID删除金立相关APP客户端登入登出记录日志记录
	 * @param Long id
	 * @return Integer
	 */
	public Integer removeGnAppAccessLogById(Long id);
	
}
