package com.iuni.dp.service.dataalarm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.dataalarm.dao.OptLogDAO;
import com.iuni.dp.persist.dataalarm.model.OptLog;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.dataalarm.service.OptLogService;

@Service("optLogService")
public class OptLogServiceImpl implements OptLogService {

	private static final Logger logger = LoggerFactory.getLogger(OptLogServiceImpl.class);
	
	@Autowired
	private OptLogDAO optLogDao;
	
	@Override
	public List<OptLog> fetchAllOptLog() {
		
		List<OptLog> optLogs = null;
		
		try {
			optLogs = optLogDao.getAllOptLog();
		} catch(DataAccessException daex) {
			logger.error("OptLogServiceImpl.fetchAllOptLog found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return optLogs;
	}
	
}
