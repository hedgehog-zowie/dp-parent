package com.iuni.dp.admin.common.utils;

import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.client.HessianProxyFactory;
import com.iuni.dp.admin.sys.facade.SystemFacade;

/**
 * 单例类
 * HessianServiceFactory远程调用管理
 * @author menglei
 */
public class HessianServiceFactory {
	private static Logger logger = LoggerFactory.getLogger(HessianServiceFactory.class);
	private static final HessianProxyFactory hessianFactoryInstance = new HessianProxyFactory();

	public static SystemFacade getSystemFacade(String RM_SYSTEM_FACADE_URL) throws Exception {
		try {
			return (SystemFacade) hessianFactoryInstance.create(SystemFacade.class, RM_SYSTEM_FACADE_URL);
		} catch (MalformedURLException e) {
			logger.error("get rm interface fail:" + RM_SYSTEM_FACADE_URL);
			throw new Exception("get rm interface fail:" + RM_SYSTEM_FACADE_URL, e);
		}
	}
	
}
