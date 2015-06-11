package com.iuni.dp.service.datareport.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.iuni.dp.persist.datareport.dao.ReportDataByMonDao;
import com.iuni.dp.persist.datareport.model.ReportDataByMon;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datareport.service.ReportDataByMonService;

/**
 * ReportData类型按月上报数据Service
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("reportDataByMonService")
public class ReportDataByMonServiceImpl implements ReportDataByMonService<ReportDataByMon> {

	private static final Logger logger = LoggerFactory.getLogger(ReportDataByMonServiceImpl.class);
	
	@Autowired
	private ReportDataByMonDao reportDataByMonDao;
	
	@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 10)
	@Override
	public Integer saveReportData(ReportDataByMon reportData) {
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		logger.debug("ReportDataByMonServiceImpl.saveReportData(ReportData) entry: reportData={}"
				, new Object[] { reportData });
		
		try {
			execCount = reportDataByMonDao.insertReportDataByMon(reportData);
			
		} catch(DataAccessException daex) {
			logger.error("ReportDataByMonServiceImpl.saveReportData(ReportData) found DataAccessException ", daex);
			throw new DBException(daex);
		}
		
		logger.debug("ReportDataByMonServiceImpl.saveReportData(ReportData) success: execCount={},costTime={}ms"
				, new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 10)
	@Override
	public Integer batchSaveReportData(List<ReportDataByMon> reportDataList) {
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		int dataSize = (reportDataList != null) ? reportDataList.size() : 0;
		logger.debug("ReportDataByMonServiceImpl.batchSaveReportData(List<ReportData>) entry: dataSize={}" 
				, new Object[] { dataSize });
		
		try {
			execCount = reportDataByMonDao.batchInsertReportDataByMon(reportDataList);
			
		} catch(DataAccessException daex) {
			logger.error("ReportDataByMonServiceImpl.batchSaveReportData(List<ReportData>) found DataAccessException ", daex);
			throw new DBException(daex);
		}
		
		logger.debug("ReportDataByMonServiceImpl.batchSaveReportData(List<ReportData>) success: execCount={},costTime={}ms"
				, new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 10)
	@Override
	public Integer saveReportDataEx(ReportDataByMon reportData) {
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		logger.debug("ReportDataByMonServiceImpl.saveReportDataEx(ReportData) entry: reportData={}"
				, new Object[] { reportData });
		
		try {
			execCount = reportDataByMonDao.insertReportDataEx(reportData);
			
		} catch(DataAccessException daex) {
			logger.error("ReportDataByMonServiceImpl.saveReportDataEx(ReportData) found DataAccessException ", daex);
			throw new DBException(daex);
		}
		
		logger.debug("ReportDataByMonServiceImpl.saveReportDataEx(ReportData) success: execCount={},costTime={}ms"
				, new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 10)
	@Override
	public Integer batchSaveReportDataEx(List<ReportDataByMon> reportDataList) {
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		int dataSize = (reportDataList != null) ? reportDataList.size() : 0;
		logger.debug("ReportDataByMonServiceImpl.batchSaveReportDataEx(List<ReportData>) entry: dataSize={}" 
				, new Object[] { dataSize });
		
		try {
			execCount = reportDataByMonDao.batchInsertReportDataEx(reportDataList);
			
		} catch(DataAccessException daex) {
			logger.error("ReportDataByMonServiceImpl.batchSaveReportDataEx(List<ReportData>) found DataAccessException ", daex);
			throw new DBException(daex);
		}
		
		logger.debug("ReportDataByMonServiceImpl.batchSaveReportDataEx(List<ReportData>) success: execCount={},costTime={}ms"
				, new Object[] { execCount, System.currentTimeMillis() - stime });
		
		return execCount;
	}
	
}
