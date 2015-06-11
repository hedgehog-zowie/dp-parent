package com.iuni.dp.api.datareport.facade.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.api.datareport.facade.ReportDataByMonFacade;
import com.iuni.dp.persist.datareport.model.ReportDataByMon;
import com.iuni.dp.service.common.bean.BaseResult;
import com.iuni.dp.service.datareport.constants.ReportDataType;
import com.iuni.dp.service.datareport.service2.ReportData2QueueService;

/**
 * ReportData类型按月上报数据Facade
 * @author CaiKe
 * @version dp-api-1.0.0
 */
@Service("reportDataByMonFacade")
public class ReportDataByMonFacadeImpl implements ReportDataByMonFacade<ReportDataByMon> {

	private static final Logger logger = LoggerFactory.getLogger(ReportDataByMonFacadeImpl.class);
	
	@Autowired
	private ReportData2QueueService<ReportDataByMon> reportData2QueueService;
	
	/**
	 * ReportDataByMon数据按月单条数据上报
	 */
	@Override
	public BaseResult reportData(ReportDataType rptDataType,
			ReportDataByMon reportData) {
		logger.debug("ReportDataByMonFacadeImpl.reportData(ReportDataType, ReportDataByMon) entry: rptDataType={}, reportData={}",
				new Object[] { rptDataType.getValue(), reportData});
		long stime = System.currentTimeMillis();
		BaseResult result = null;
		
		reportData2QueueService.reportDataPutQueue(rptDataType, reportData);
		
		result = new BaseResult();
		result.setState(BaseResult.SUCCESS);
		logger.debug("ReportDataByMonFacadeImpl.reportData(ReportDataType, ReportDataByMon) success: costTime={}ms"
				, new Object[] {System.currentTimeMillis() - stime });
		
		return result;
	}

	/**
	 * ReportDataByMon数据按月批量数据上报
	 */
	@Override
	public BaseResult batchReportData(ReportDataType rptDataType,
			List<ReportDataByMon> reportDataList) {
		int size = (reportDataList != null) ? reportDataList.size() : 0;
		logger.info("ReportDataByMonFacadeImpl.batchDataReport(ReportDataType, List<ReportDataByMon>) entry: rptDataType={}, reportDataList.size()={}",
				new Object[] { rptDataType.getValue(), size});
		long stime = System.currentTimeMillis();
		
		BaseResult result = null;
		
		reportData2QueueService.reportDataBatchPutQueue(rptDataType, reportDataList);
		
		result = new BaseResult();
		result.setState(BaseResult.SUCCESS);
		
		logger.info("ReportDataByMonFacadeImpl.batchDataReport(ReportDataType, List<ReportDataByMon>) success: costTime={}ms"
				, new Object[] {System.currentTimeMillis() - stime });
		
		return result;
	}
	
}
