package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.GnAppAccessLog;

/**
 * 金立相关APP客户端登入登出记录日志DAO
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public interface GnAppAccessLogDao extends BaseDao<GnAppAccessLog, Serializable> {

	/**
	 * 按条件查询金立相关APP客户端渠道趋势统计数据
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectChannelTrendByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询金立相关APP客户端渠道趋势统计数据
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectChannelTrendByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道趋势统计数据统计数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectChannelTrendCount(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道注册用户数统计数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectRegisterOfChannelByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询金立相关APP客户端渠道注册用户数统计数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectRegisterOfChannelByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道注册用户数统计数据统计数
	 * @param params
	 * @return
	 */
	public Integer selectRegisterOfChannelCount(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道日活跃用户数统计数据
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectActiveUserOfChannelDailyByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询金立相关APP客户端渠道日活跃用户数统计数据
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectActiveUserOfChannelDailyByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道日活跃用户数统计数据统计数
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public Integer selectActiveUserOfChannelDailyCount(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道月活跃用户数统计数据
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectActiveUserOfChannelMonthlyByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询金立相关APP客户端渠道月活跃用户数统计数据
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectActiveUserOfChannelMonthlyByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道月活跃用户数统计数据统计数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectActiveUserOfChannelMonthlyCount(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道用户的启动次数分布
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectLaunchNumDistribution(Map<String, Object> params);
	
	/**
	 * 按条件查询App机型分布统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectAppUserStatOnModelByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询App机型分布统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectAppUserStatOnModelByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询App机型分布统计记录数
	 * @param params
	 * @return
	 */
	public Integer selectAppUserStatOnModelCount(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道列表
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectChannelList(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端版本列表
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectApkVersionList(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS累计总用户数
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniOSTotalUserByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI OS累计总用户数
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniOSTotalUserByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS累计总用户数记录数
	 * @param params
	 * @return
	 */
	public Integer selectIuniOSTotalUserCount(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS应用列表
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniOSAppNames(Map<String, Object> params);
	
	/**
	 * 按条件根据应用查询对应IUNI OS版本列表
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniOSApkVersions(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS用户数相关统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniOSUserStatByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI OS用户数相关统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniOSUserStatByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS用户数相关统计记录数
	 * @param params
	 * @return
	 */
	public Integer selectIuniOSUserStatCount(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS时间段用户数相关统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniOSUserStatOnPeriodByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI OS时间段用户数相关统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniOSUserStatOnPeriodByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS时间段用户数相关统计记录数
	 * @param params
	 * @return
	 */
	public Integer selectIuniOSUserStatOnPeriodCount(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS用户数版本分布
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniOSVersionDistributeByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI OS用户数版本分布
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniOSVersionDistributeByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS用户数版本分布记录数
	 * @param params
	 * @return
	 */
	public Integer selectIuniOSVersionDistributeCount(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS区域分布情况
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniOSAreaDistributeByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI OS区域分布情况
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniOSAreaDistributeByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS区域分布情况记录数
	 * @param params
	 * @return Integer
	 */
	public Integer selectIuniOSAreaDistributeCount(Map<String, Object> params);
	
	/**
	 * 新增金立相关APP客户端登入登出记录日志记录
	 * @param GnAppAccessLog wjqClientAccessLog
	 * @return Integer
	 */
	public Integer insertGnAppAccessLog4Stat(GnAppAccessLog gnAppAccessLog);
	
	/**
	 * 根据ID删除金立相关APP客户端登入登出记录日志记录
	 * @param Long id
	 * @return Integer
	 */
	public Integer deleteGnAppAccessLog4StatById(Long id);
	
}
