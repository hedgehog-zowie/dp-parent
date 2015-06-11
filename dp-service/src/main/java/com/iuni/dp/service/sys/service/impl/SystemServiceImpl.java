package com.iuni.dp.service.sys.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.common.utils.MD5Util;
import com.iuni.dp.persist.common.constants.TypeEnum;
import com.iuni.dp.persist.sys.dao.MenuDAO;
import com.iuni.dp.persist.sys.dao.SystemDao;
import com.iuni.dp.persist.sys.dao.UserDAO;
import com.iuni.dp.persist.sys.model.Menu;
import com.iuni.dp.persist.sys.model.System;
import com.iuni.dp.persist.sys.model.User;
import com.iuni.dp.service.common.bean.Result;
import com.iuni.dp.service.sys.service.SystemService;

@Service("systemService")
public class SystemServiceImpl implements SystemService {
	
    @Override
	public Result authenticate(String loginName, String password) {
    	Result result = new Result();
    	//登录名是否为空校验
    	if (StringUtils.isEmpty(loginName)) {
    		result.setState(Result.FAIL);
			result.setErrorCode(TypeEnum.BusiExcEnum.LOGIN_NAME_NOT_EMPTY.getKey());
			result.setErrorMessage(TypeEnum.BusiExcEnum.LOGIN_NAME_NOT_EMPTY.getValue());
			return result;
		}
    	//登录密码是否为空校验
    	if (StringUtils.isEmpty(password)) {
    		result.setState(Result.FAIL);
			result.setErrorCode(TypeEnum.BusiExcEnum.PASSWORD_NOT_EMPTY.getKey());
			result.setErrorMessage(TypeEnum.BusiExcEnum.PASSWORD_NOT_EMPTY.getValue());
			return result;
		}
    	//用户名不存在
		User user = userDao.checkUserName(loginName);
		if (user == null) {
			result.setState(Result.FAIL);
			result.setErrorCode(TypeEnum.BusiExcEnum.LOGIN_NAME_NOT_EXIST.getKey());
			result.setErrorMessage(TypeEnum.BusiExcEnum.LOGIN_NAME_NOT_EXIST.getValue());
			return result;
		}
		//用户名存在,但登录密码错误
		String passwordMD5 = MD5Util.encode(password);
		if (!passwordMD5.equals(user.getPassword())) {
			result.setState(Result.FAIL);
			result.setErrorCode(TypeEnum.BusiExcEnum.PASSWORD_ERROR.getKey());
			result.setErrorMessage(TypeEnum.BusiExcEnum.PASSWORD_ERROR.getValue());
			return result;
		} 
		//鉴权成功
		result.setState(Result.SUCCESS);
		result.setData(user);
		return result;
	}
	
    @Override
	public Result getMenuList(String[] sysIds, String optId) {
    	Result result = new Result();
    	//系统ID是否为空校验
		if ((sysIds == null) || (sysIds != null && sysIds.length == 0)) {
    		result.setState(Result.FAIL);
			result.setErrorCode(TypeEnum.BusiExcEnum.SYS_ID_NOT_EMPTY.getKey());
			result.setErrorMessage(TypeEnum.BusiExcEnum.SYS_ID_NOT_EMPTY.getValue());
			return result;
		}
    	//操作员Id是否为空校验
    	if (StringUtils.isEmpty(optId)) {
    		result.setState(Result.FAIL);
			result.setErrorCode(TypeEnum.BusiExcEnum.OPT_ID_NOT_EMPTY.getKey());
			result.setErrorMessage(TypeEnum.BusiExcEnum.OPT_ID_NOT_EMPTY.getValue());
			return result;
		}
    	List<Menu> menuList = menuDao.getMenuList(sysIds, optId);
    	result.setState(Result.SUCCESS);
    	result.setData(menuList);
		return result;
	}
	
    @Override
	public Result getUserInfo(String loginName,String optId){
    	//登录名和操作员ID是否均为空校验
    	Result result = new Result();
    	if (StringUtils.isEmpty(loginName) && StringUtils.isEmpty(optId)) {
    		result.setState(Result.FAIL);
			result.setErrorCode(TypeEnum.BusiExcEnum.LOGIN_NAME_ID_NOT_EMPTY.getKey());
			result.setErrorMessage(TypeEnum.BusiExcEnum.LOGIN_NAME_ID_NOT_EMPTY.getValue());
			return result;
		}
    	//用户名不存在
    	User userParm = new User(loginName,optId);
		User user = userDao.getUserInfo(userParm);
		if (user == null) {
			result.setState(Result.FAIL);
			result.setErrorCode(TypeEnum.BusiExcEnum.LOGIN_NAME_ID_NOT_EXIST.getKey());
			result.setErrorMessage(TypeEnum.BusiExcEnum.LOGIN_NAME_ID_NOT_EXIST.getValue());
			return result;
		}
		result.setState(Result.SUCCESS);
		result.setData(user);
		return result;
    }
    
    @Override
	public Result getSystemInfo(String sysName,String sysId) {
    	//系统Id和系统名称是否均为空校验
    	Result result = new Result();
    	if (StringUtils.isEmpty(sysName) && StringUtils.isEmpty(sysId)) {
    		result.setState(Result.FAIL);
			result.setErrorCode(TypeEnum.BusiExcEnum.SYS_ID_NAME_NOT_EMPTY.getKey());
			result.setErrorMessage(TypeEnum.BusiExcEnum.SYS_ID_NAME_NOT_EMPTY.getValue());
			return result;
		}
    	//当前系统不存在
    	System systemParm = new System(sysName,sysId);
		System system = systemDao.getSystemInfo(systemParm);
		if (system == null) {
			result.setState(Result.FAIL);
			result.setErrorCode(TypeEnum.BusiExcEnum.SYS_ID_NAME_NOT_EXIST.getKey());
			result.setErrorMessage(TypeEnum.BusiExcEnum.SYS_ID_NAME_NOT_EXIST.getValue());
			return result;
		}
		result.setState(Result.SUCCESS);
		result.setData(system);
		return result;
	}

	@Override
	public List<System> getSystemList() {
		return systemDao.getSystemList();
	}

	@Autowired
	public void setSystemDao(SystemDao systemDao) {
		this.systemDao = systemDao;
	}
	
	@Autowired
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setMenuDao(MenuDAO menuDao) {
		this.menuDao = menuDao;
	}
	
	private SystemDao systemDao;
	private UserDAO userDao;
	private MenuDAO menuDao;

	final static Logger LOGGER = LoggerFactory.getLogger(SystemServiceImpl.class);

}
