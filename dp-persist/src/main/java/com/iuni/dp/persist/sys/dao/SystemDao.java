package com.iuni.dp.persist.sys.dao;

import java.util.List;

import com.iuni.dp.persist.sys.model.System;

/**
 * 系统DAO
 * @author menglei
 */
public interface SystemDao {
	
	public List<System> getSystemList();
	
	public System getSystemInfo(System system);
	
}
