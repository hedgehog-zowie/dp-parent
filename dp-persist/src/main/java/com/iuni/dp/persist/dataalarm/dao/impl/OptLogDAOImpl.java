package com.iuni.dp.persist.dataalarm.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.dataalarm.dao.OptLogDAO;
import com.iuni.dp.persist.dataalarm.model.OptLog;

@Repository("optLogDao")
public class OptLogDAOImpl extends BaseDaoImpl<OptLog, Serializable> implements OptLogDAO{

	private static final Logger logger = LoggerFactory.getLogger(OptLogDAOImpl.class);
	
	@Override
	public List<OptLog> getAllOptLog() {
		logger.debug("OptLogDAOImpl.getAllOptLog() invoke");
		long stime = System.currentTimeMillis();
		
		List<OptLog> busLogs = getAll(OptLog.class.getSimpleName() + ".getAllOptLog");
		
		logger.debug("OptLogDAOImpl.getAllOptLog(BusLog) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return busLogs;
	}

}
