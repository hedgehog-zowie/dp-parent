package com.iuni.dp.persist.dataalarm.dao;

import java.io.Serializable;
import java.util.List;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.dataalarm.model.OptLog;

public interface OptLogDAO extends BaseDao<OptLog, Serializable> {
	
	/**
	 * 获取全部的操作日志列表
	 * @return List<OptLog>
	 */
	public List<OptLog> getAllOptLog();
	
}
