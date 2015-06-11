package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.WxClickLog;

/**
 * @ClassName WxClickLogDao
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public interface WxClickLogDao extends BaseDao<WxClickLog, Serializable> {

	/**
	 * 根据ID查询微信公众帐号点击日志
	 * @param id
	 * @return WxClickLog
	 */
	public WxClickLog selectWxClickLogById(Integer id);
	
	/**
	 * 按条件查询微信公众帐号点击日志
	 * @param params 
	 * @return List<WxClickLog>
	 */
	public List<WxClickLog> selectWxClickLogByExample(Map<String, Object> params);
	
	/**
	 * 按条件分页查询微信公众帐号点击日志
	 * @param params
	 * @return List<WxClickLog>
	 */
	public List<WxClickLog> selectWxClickLogByPage(Map<String, Object> params);
	
	/**
	 * 按条件查询微信公众帐号点击日志记录数
	 * @param params
	 * @return Integer
	 */
	public Integer selectWxClickLogCount(Map<String, Object> params);
	
	/**
	 * 新增微信公众帐号点击日志
	 * @param WxClickLog
	 * @return Integer
	 */
	public Integer insertWxClickLog(WxClickLog wxClickLog);
	
	/**
	 * 根据ID删除微信公众帐号点击日志记录
	 * @param id
	 * @return Integer
	 */
	public Integer deleteWxClickLogById(Integer id);
	
	/**
	 * 按条件查询活跃用户数
	 * @param params
	 * @return Integer
	 */
	public Integer selectActiveUserCount(Map<String, Object> params);
	
}
