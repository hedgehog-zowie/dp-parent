package com.iuni.dp.persist.sys.dao;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.sys.model.SysParam;

/**
 * 操作参数配置表
 * @author menglei
 */
public interface SysParamDao {
	
	public void insertSysParam(SysParam sysParam);
	
	public void updateSysParam(SysParam sysParam);
	
	public void deleteSysParamByParamName(String paramName);
	
	public List<SysParam> getAllSysParam(Map<String, Object> map);
	
	public SysParam getSysParamByName(String name);
 

}
