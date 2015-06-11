package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.ReportDataByMonDao;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.ReportDataByMonService;

/**
 * ReportDataByMon Service for Statistics
 * @author Kenneth.Cai@iuni.com
 * @version ips-V1.1.0
 */
@Service("reportDataByMon4StatService")
public class ReportDataByMonServiceImpl implements ReportDataByMonService {

	private final Logger logger = LoggerFactory.getLogger(ReportDataByMonServiceImpl.class);
	
	@Autowired
	private ReportDataByMonDao reportDataByMon4StatDao;
	
	@Override
	public List<Map<String, Object>> queryNetflowSumToday(
			Map<String, Object> params) {
		List<Map<String, Object>> list = null;
		
		try {
			list = reportDataByMon4StatDao.selectNetflowSumToday(params);
			logger.debug("ReportDataByMonServiceImpl.queryNetflowSumToday invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("ReportDataByMonServiceImpl.queryNetflowSumToday found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return list;
	}

}
