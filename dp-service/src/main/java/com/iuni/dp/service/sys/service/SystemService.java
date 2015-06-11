package com.iuni.dp.service.sys.service;

import java.util.List;

import com.iuni.dp.persist.sys.model.System;
import com.iuni.dp.service.common.bean.Result;

public interface SystemService {
	
	/** 
	 * @Description 根据用户名、密码验证登录
	 * @param  loginName 用户名
	 * @param  password  密码
	 * @return Result
	 */
	public Result authenticate(String loginName, String password);
	
	/**
	 * 根据系统id和操作员id查询可访问的菜单列表
	 * 支持一次查询一个用户，多个系统的菜单权限
	 * @param sysIds 系统id数组
	 * @param optId  操作员id
	 * @return Result
	 */
	public Result getMenuList(String[] sysIds, String optId);
	
	/**
	 * 根据登录名或者操作员Id查询操作员信息
	 * @param loginName 用户名
	 * @param optId 操作员id
	 * @return Result
	 */
	public Result getUserInfo(String loginName, String optId);

	/**
	 * 根据系统名或者系统Id查询系统信息
	 * @param sysName 系统名
	 * @param sysId  系统Id
	 * @return Result
	 */
	public Result getSystemInfo(String sysName,String sysId);

	/**
	 * 查询系统信息列表
	 * @param systemName
	 * @return List<System>
	 */
	public List<System> getSystemList();
	
}
