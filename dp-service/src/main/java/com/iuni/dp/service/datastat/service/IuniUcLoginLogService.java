package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.IuniUcLoginLog;
import com.iuni.dp.persist.datastat.model.IuniUcOloginLog;

/**
 * IUNI UC Login Log Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.4
 */
public interface IuniUcLoginLogService {

	/**
	 * insert IuniUcLoginLog
	 * @param iuniUcLoginLog
	 * @return Long
	 */
	public Long saveIuniUcLoginLog(IuniUcLoginLog iuniUcLoginLog);
	
	/**
	 * batch insert IuniUcLoginLog
	 * @param iuniUcLoginLogList
	 * @return Long
	 */
	public Long batchSaveIuniUcLoginLog(List<IuniUcLoginLog> iuniUcLoginLogList);
	
	/**
	 * IUNI 用户中心用户登录频次按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniUserLoginFreqByExample(Map<String, Object> params);
	
	/**
	 * IUNI 用户中心用户登录频次按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniUserLoginFreqByPage(Map<String, Object> params);
	
	/**
	 * IUNI 用户中心用户登录频次按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIuniUserLoginFreqCount(Map<String, Object> params);
	
	/**
	 * insert IuniUcOloginLog
	 * @param iuniUcOloginLog
	 * @return Long
	 */
	public Long saveIuniUcOloginLog(IuniUcOloginLog iuniUcOloginLog);
	
	/**
	 * batch Insert IuniUcOloginLog
	 * @param iuniUcLoginLogList
	 * @return Long
	 */
	public Long batchSaveIuniUcOloginLog(List<IuniUcOloginLog> iuniUcOloginLogList);
	
	/**
	 * IUNI 用户中心用户登录次数按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniUserLoginNumByExample(Map<String, Object> params);
	
	/**
	 * IUNI 用户中心用户登录次数按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniUserLoginNumByPage(Map<String, Object> params);
	
	/**
	 * IUNI 用户中心用户登录次数按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIuniUserLoginNumCount(Map<String, Object> params);
	
	/**
	 * IUNI 用户中心用户注册留存数按条件查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniUserRegRetainByExample(Map<String, Object> params);
	
	/**
	 * IUNI 用户中心用户注册留存数按条件分页查询
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryIuniUserRegRetainByPage(Map<String, Object> params);
	
	/**
	 * IUNI 用户中心用户注册留存数按条件查询记录数
	 * @param params
	 * @return Integer
	 */
	public Integer queryIuniUserRegRetainCount(Map<String, Object> params);
	
}
