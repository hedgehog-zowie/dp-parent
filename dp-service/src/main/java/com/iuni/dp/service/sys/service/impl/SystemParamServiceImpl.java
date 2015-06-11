package com.iuni.dp.service.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.iuni.dp.persist.sys.dao.SysParamDao;
import com.iuni.dp.persist.sys.model.SysParam;
import com.iuni.dp.service.sys.constants.SysCons;
import com.iuni.dp.service.sys.service.SysParamService;

@Service("sysParamService") 
public class SystemParamServiceImpl implements SysParamService{
	
	@Autowired
	private SysParamDao sysParamDao;
	
	final static Logger LOGGER = LoggerFactory.getLogger(SystemParamServiceImpl.class);
 
	public SysParamDao getSysParamDao() {
		return sysParamDao;
	}

	public void setSysParamDao(SysParamDao sysParamDao) {
		this.sysParamDao = sysParamDao;
	}

	public void insertSysParam(SysParam sysParam) {
		sysParamDao.insertSysParam(sysParam);
		SysCons.SYS_PARAM_MAP.put(sysParam.getParamName(), sysParam.getParamVal());
	}
 
	public void deleteSysParam(String name) {
		sysParamDao.deleteSysParamByParamName(name);
	}

	public void updateSysParam(SysParam sysParam) {
		Assert.notNull(sysParam.getParamName());
		Assert.notNull(sysParam.getParamVal());
		sysParamDao.updateSysParam(sysParam);
		SysCons.SYS_PARAM_MAP.put(sysParam.getParamName(), sysParam.getParamVal());
	}

	public List<SysParam> getSysParams(String name) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paramName", name);
		return sysParamDao.getAllSysParam(map);
	}

	@Override
	public SysParam getSysParamsByParamName(String name) {
		return sysParamDao.getSysParamByName(name);
	}
 
}
