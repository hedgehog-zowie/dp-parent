package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.IuniUcLoginLog;
import com.iuni.dp.persist.datastat.model.IuniUcOloginLog;

/**
 * IUNI UC Login Log DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.4
 */
public interface IuniUcLoginLogDao extends BaseDao<IuniUcLoginLog, Serializable> {

	/**
	 * insert IuniUcLoginLog
	 * @param iuniUcLoginLog
	 * @return Long
	 */
	public Long insertIuniUcLoginLog(IuniUcLoginLog iuniUcLoginLog);
	
	/**
	 * batch insert IuniUcLoginLog
	 * @param iuniUcLoginLog
	 * @return Long
	 */
	public Long batchInsertIuniUcLoginLog(List<IuniUcLoginLog> iuniUcLoginLogList);
	
	/**
	 * IUNI 用户中心用户登录频次按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniUserLoginFreqByExample(Map<String, Object> params);
	
	/**
	 * IUNI 用户中心用户登录频次按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniUserLoginFreqByPage(Map<String, Object> params);
	
	/**
	 * IUNI 用户中心用户登录频次按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer selectIuniUserLoginFreqCount(Map<String, Object> params);
	
	/**
	 * insert IuniUcOloginLog
	 * @param iuniUcOloginLog
	 * @return Long
	 */
	public Long insertIuniUcOloginLog(IuniUcOloginLog iuniUcOloginLog);
	
	/**
	 * batch Insert IuniUcOloginLog
	 * @param iuniUcLoginLogList
	 * @return Long
	 */
	public Long batchInsertIuniUcOloginLog(List<IuniUcOloginLog> iuniUcOloginLogList);
	
	/**
	 * IUNI 用户中心用户登录次数按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniUserLoginNumByExample(Map<String, Object> params);
	
	/**
	 * IUNI 用户中心用户登录次数按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniUserLoginNumByPage(Map<String, Object> params);
	
	/**
	 * IUNI 用户中心用户登录次数按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer selectIuniUserLoginNumCount(Map<String, Object> params);
	
	/**
	 * IUNI 用户中心用户注册留存数按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniUserRegRetainByExample(Map<String, Object> params);
	
	/**
	 * IUNI 用户中心用户注册留存数按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> selectIuniUserRegRetainByPage(Map<String, Object> params);
	
	/**
	 * IUNI 用户中心用户注册留存数按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer selectIuniUserRegRetainCount(Map<String, Object> params);
	
}
