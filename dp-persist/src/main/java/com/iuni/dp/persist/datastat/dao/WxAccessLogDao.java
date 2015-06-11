package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.WxAccessLog;

/**
 * @ClassName WxAccessLogDao
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public interface WxAccessLogDao extends BaseDao<WxAccessLog, Serializable> {

	/**
	 * 根据ID查询微信公众帐号访问日志
	 * @param id
	 * @return WxAccessLog
	 */
	public WxAccessLog selectWxAccessLogById(Integer id);
	
	/**
	 * 按条件查询微信公众帐号访问日志
	 * @param params 
	 * @return List<WxAccessLog>
	 */
	public List<WxAccessLog> selectWxAccessLogByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询微信公众帐号访问日志
	 * @param params
	 * @return List<WxAccessLog>
	 */
	public List<WxAccessLog> selectWxAccessLogByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询微信公众帐号访问日志记录数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectWxAccessLogCount(Map<String, Object> params);
	
	/**
	 * 按条件查询微信新增粉丝数相关统计
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectNewFansStatByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询微信新增粉丝数相关统计
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectNewFansStatByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询微信新增粉丝数相关统计记录数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectNewFansStatCount(Map<String, Object> params);
	
	/**
	 * 根据条件按日查询微信活跃用户数相关统计
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectActiveUserStatDailyByExample(Map<String, Object> params);
	
	/**
	 * 根据条件按日分页查询微信活跃用户数相关统计
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectActiveUserStatDailyByPage(Map<String, Object> params);
	
	/**
	 * 根据条件按日查询微信活跃用户数相关统计记录数
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public Integer selectActiveUserStatDailyCount(Map<String, Object> params);
	
	/**
	 * 根据条件按月查询微信活跃用户数相关统计
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectActiveUserStatMonthlyByExample(Map<String, Object> params);
	
	/**
	 * 根据条件按月分页查询微信活跃用户数相关统计
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectActiveUserStatMonthlyByPage(Map<String, Object> params);
	
	/**
	 * 根据条件按月查询微信活跃用户数相关统计记录数
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public Integer selectActiveUserStatMonthlyCount(Map<String, Object> params);
	
	/**
	 * 按条件查询微信上行文本内容相关统计
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectUpwardTextStatByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询微信上行文本内容相关统计
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectUpwardTextStatByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询微信上行文本内容相关统计记录数
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectUpwardTextStatCount(Map<String, Object> params);
	
	/**
	 * 按条件查询微信公众帐号图片下载排行
	 * @param params 
	 * @return List<WxAccessLog>
	 */
	public List<Map<String, Object>> selectWxImageDownloadRanks(Map<String, Object> paramMap);
	
	/**
	 * 按条件查询微信公众帐号图片下载排行-记录总数
	 * @param params 
	 * @return List<WxAccessLog>
	 */
	public Integer selectWxImageDownloadRanksCount(Map<String, Object> paramMap);
	
	/**
	 * 按条件查询微信公众帐号图片下载总次数
	 * @param params 
	 * @return List<WxAccessLog>
	 */
	public Integer selectWxImageDownloadTotalCount(Map<String, Object> paramMap);
	
	/**
	 * 新增微信公众帐号访问日志
	 * @param wxAccessLog
	 * @return Integer
	 */
	public Integer insertWxAccessLog(WxAccessLog wxAccessLog);
	
	/**
	 * 根据ID删除微信公众帐号访问日志记录
	 * @param id
	 * @return Integer
	 */
	public Integer deleteWxAccessLogById(Integer id);
	
}
