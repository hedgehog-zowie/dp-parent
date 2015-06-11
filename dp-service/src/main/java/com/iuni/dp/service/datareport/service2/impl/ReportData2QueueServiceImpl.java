package com.iuni.dp.service.datareport.service2.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.persist.datareport.model.ReportDataByMon;
import com.iuni.dp.service.datareport.constants.ReportDataConstant;
import com.iuni.dp.service.datareport.constants.ReportDataType;
import com.iuni.dp.service.datareport.queue.BlockingQueuePool;
import com.iuni.dp.service.datareport.queue.QueueManager;
import com.iuni.dp.service.datareport.service2.ReportData2QueueService;

/**
 * 上报数据至Queue的Service业务类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("reportData2QueueService")
public class ReportData2QueueServiceImpl implements ReportData2QueueService<ReportDataByMon> {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportData2QueueServiceImpl.class);
	
	// 队列类型ReportDataType.CommonRptdata占用数据上报数据类型对应队列总数目
	private static final Integer queueNumOfCommonRptdata = ParseProperties.getIntVal(ReportDataConstant.commonRptData_queue_num);
		
	private BlockingQueuePool<ReportDataByMon> rptDataByMonQueuePool;
	
	{
		// 初始化队列池
		rptDataByMonQueuePool = QueueManager.getInstance().getRptDataByMonQueuePool();
		
		// 初始化队列、数据类型、队列索引对应关系
		for(int i = 0; i < queueNumOfCommonRptdata; i++) {
			int lastIndex = rptDataByMonQueuePool.genQueueByDataType(ReportDataType.CommonRptData);
			if(lastIndex < 0) {
				logger.info("ReportData2QueueServiceImpl init queue and ReportDataType and queue index relationship failed ");
			}
		}
		logger.info("ReportData2QueueServiceImpl init queue and ReportDataType and queue index relationship success ");
	}
	
	@Override
	public void reportDataPutQueue(ReportDataType rptDataType,
			ReportDataByMon reportData) {
		long stime = System.currentTimeMillis();
		logger.debug("ReportData2QueueServiceImpl.reportDataPutQueue(ReportDataType, ReportData) entry: rptDataType={}, reportData={}"
				, new Object[] { rptDataType.getValue(), reportData });
		
		rptDataByMonQueuePool.put(rptDataType, reportData);
		
		logger.debug("ReportData2QueueServiceImpl.reportDataPutQueue(ReportDataType, ReportData) success: costTime={}ms"
				, new Object[] { System.currentTimeMillis() - stime });
	}

	@Override
	public void reportDataBatchPutQueue(ReportDataType rptDataType,
			List<ReportDataByMon> reportDataList) {
		long stime = System.currentTimeMillis();
		int dataSize = (reportDataList != null) ? reportDataList.size() : 0;
		logger.debug("ReportData2QueueServiceImpl.reportDataBatchPutQueue(ReportDataType, List<ReportData>) entry: rptDataType={}, dataSize={}"
				, new Object[] { rptDataType.getValue(), dataSize });
		
		if(CollectionUtils.isNotEmpty(reportDataList)) {
			for(ReportDataByMon reportData : reportDataList) {
				reportDataPutQueue(rptDataType, reportData);
			}
		}
		
		logger.debug("ReportData2QueueServiceImpl.reportDataBatchPutQueue(ReportDataType, List<ReportData>) success: costTime={}ms"
				, new Object[] { System.currentTimeMillis() - stime });
	}

}
