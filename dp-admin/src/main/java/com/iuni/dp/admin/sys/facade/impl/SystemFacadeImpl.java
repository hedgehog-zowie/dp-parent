package com.iuni.dp.admin.sys.facade.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.admin.sys.facade.SystemFacade;
import com.iuni.dp.persist.sys.model.Menu;
import com.iuni.dp.service.common.bean.Result;
import com.iuni.dp.service.common.exception.BusinessException;
import com.iuni.dp.service.common.exception.ExceptionProcessor;
import com.iuni.dp.service.common.exception.ParameterException;
import com.iuni.dp.service.sys.service.SystemService;

@Service("systemFacade")
public class SystemFacadeImpl implements SystemFacade {
	
	@Override
	public Result authenticate(String loginName, String password){
		logger.info("authenticate entry: loginName={},password={}",new Object[] { loginName,password});
		long stime = System.currentTimeMillis();
		try {
			Result result = systemService.authenticate(loginName, password);
			logger.info("authenticate success: loginName={},data={},costTime={}ms",
					new Object[] { loginName, result.getData(),System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Result getMenuList(String[] sysIds, String optId){
		logger.info("getMenuList entry: sysIds={},optId={}",new Object[] { sysIds,optId});
		long stime = System.currentTimeMillis();
		try {
			Result result = systemService.getMenuList(sysIds, optId);
			List<Menu> menuList = (List<Menu>)result.getData();
			int count = (menuList != null && menuList.size() > 0) ? menuList.size() : 0;
			logger.info("getMenuList success: sysIds={},optId={},count={},costTime={}ms", 
					new Object[] {sysIds,optId,count,System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}
	
	@Override
	public Result getUserInfo(String loginName, String optId){
		logger.info("getUserInfo entry: loginName={},optId={}",new Object[] { loginName,optId});
		long stime = System.currentTimeMillis();
		try {
			Result result = systemService.getUserInfo(loginName,optId);
			logger.info("getUserInfo success: loginName={},optId={},data={},costTime={}ms", 
					new Object[] {loginName,optId,result.getData(),System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result getSystemInfo(String sysName,String sysId){
		logger.info("getSystemInfo entry: sysName={},sysId={}",new Object[] { sysName,sysId});
		long stime = System.currentTimeMillis();
		try {
			Result result = systemService.getSystemInfo(sysName,sysId);
			logger.info("getSystemInfo success: sysName={},sysId={},data={},costTime={}ms", 
					new Object[] {sysName,sysId,result.getData(),System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}
	
	@Autowired
	private SystemService systemService ;
	
	private static final Logger logger = LoggerFactory.getLogger(SystemFacadeImpl.class);

}
