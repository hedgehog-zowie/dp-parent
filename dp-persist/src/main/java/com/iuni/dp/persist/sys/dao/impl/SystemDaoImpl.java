package com.iuni.dp.persist.sys.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.sys.dao.SystemDao;
import com.iuni.dp.persist.sys.model.System;

/**
 * 系统DAO
 * @author menglei
 */
@Repository("systemDao")
public class SystemDaoImpl extends BaseDaoImpl<System, Serializable> implements SystemDao {
	
	@Override
	public List<System> getSystemList(){
		return getAll(System.class.getSimpleName() + ".getAllSystemList");
	}
	
	@Override
	public System getSystemInfo(System system) {
		return (System) getObjectByProperty(System.class.getSimpleName()+ ".getSystemInfo", system);
	}
	
}
