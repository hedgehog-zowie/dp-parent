package com.iuni.dp.service.sys.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iuni.dp.persist.common.constants.SysCons;
import com.iuni.dp.persist.sys.model.SysParam;

/**
 * 系统配置参数初始化
 * @author Liangyongx
 *
 */
public class SysParamInitializtion {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(SysParamInitializtion.class);
 
	public SysParamInitializtion() {
		super();		 
	}
	
	public SysParamInitializtion(SysParamService sysParamService) {
		LOGGER.info("----------系统配置参数加载到内存中 begin----------");
		List<SysParam> dataList = sysParamService.getSysParams(null);//加载所有记录
		if(CollectionUtils.isNotEmpty(dataList)){
			for(SysParam sysParam : dataList){
				if(sysParam != null){
					SysCons.SYS_PARAM_MAP.put(sysParam.getParamName(), sysParam.getParamVal());
				}
			}
		}
		LOGGER.info("----------系统配置参数加载到内存中 end----------");
	}
	
}
