package com.iuni.dp.service.sys.service;

import java.util.List;

import com.iuni.dp.persist.sys.model.SysParam;

public interface SysParamService {
	
	public void insertSysParam(SysParam sysParam);
	
//	public SysParam getSysParamByName(String name);
	
	public List<SysParam> getSysParams(String name);
	
	public void deleteSysParam(String name);
	
	public void updateSysParam(SysParam sysParam);
	
	public SysParam getSysParamsByParamName(String name);
	 
}
