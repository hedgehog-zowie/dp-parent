package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.GnAppImeiLog;

/**
 * 金立相关APP客户端IMEI首次启用记录日志DAO
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public interface GnAppImeiLogDao extends BaseDao<GnAppImeiLog, Serializable> {
	
	/**
	 * 按条件查询金立相关APP客户端渠道新增用户数统计
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectNewUserOfChannelByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询金立相关APP客户端渠道新增用户数统计
	 * @param Map<String, Object> params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectNewUserOfChannelByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询金立相关APP客户端渠道新增用户数统计数目
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer selectNewUserOfChannelCount(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS留存用户相关统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniOSRemainUserOnDayByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询IUNI OS留存用户相关统计
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectIuniOSRemainUserOnDayByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询IUNI OS留存用户相关统计记录数
	 * @param params
	 * @return Integer
	 */
	public Integer selectIuniOSRemainUserOnDayCount(Map<String, Object> params);
	
	/**
	 * 新增金立相关APP客户端IMEI首次启用时间日志记录
	 * @param GnAppImeiLog wjqClientImeiLog
	 * @return Integer
	 */
	public Integer insertGnAppImeiLog4Stat(GnAppImeiLog gnAppImeiLog);
	
	/**
	 * 删除金立相关APP客户端IMEI首次启用时间日志记录
	 * @param Long id
	 * @return Integer
	 */
	public Integer deleteGnAppImeiLog4StatById(Long id);
	
}
