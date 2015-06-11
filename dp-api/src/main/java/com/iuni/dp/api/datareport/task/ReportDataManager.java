package com.iuni.dp.api.datareport.task;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datareport.model.ReportDataByMon;
import com.iuni.dp.service.datareport.constants.ReportDataType;
import com.iuni.dp.service.datareport.queue.BlockingQueuePool;
import com.iuni.dp.service.datareport.queue.QueueManager;

/**
 * 页面相关数据上报Manager
 * 页面数据上报系统队列监控扫描、文件扫描入库线程启用
 * @author CaiKe
 * @version V1.0.0
 */
@Service("reportDataManager")
public class ReportDataManager {

	private static final Logger logger = LoggerFactory.getLogger(ReportDataManager.class);
	
	// ReportData类型上报数据队列池
//	private BlockingQueuePool<ReportData> rptDataQueuePool;
	
	// ReportData类型按月上报数据队列池
	private BlockingQueuePool<ReportDataByMon> rptDataByMonQueuePool;
	
	{
//		rptDataQueuePool = QueueManager.getInstance().getRptDataQueuePool();
		rptDataByMonQueuePool = QueueManager.getInstance().getRptDataByMonQueuePool();
	}
	
	@Autowired
	private ThreadPoolTaskExecutor executor;
	
	@PostConstruct
	public void manage() {
		// 启动ReportData类型上报数据对应队列扫描处理主线程
		scanRptDataQueue();
	}

	/**
	 * 队列扫描处理
	 */
	private void scanRptDataQueue() {
		Map<ReportDataType, List<Integer>> distributeMap = rptDataByMonQueuePool.getDistributeMap();
		for(Entry<ReportDataType, List<Integer>> entry: distributeMap.entrySet()) {
			ReportDataType rptDataType = entry.getKey();
			List<Integer> indexList = entry.getValue();
			for(Integer index : indexList) {
//				BlockingQueue<ReportDataByMon> rptDataqueue = rptDataByMonQueuePool.getQueue(rptDataType, index);
				rptDataQueueProcessor(rptDataType, index);
			}
		}
	}
	
	/**
	 * 当前队列池中阻塞队列处理器
	 * @param String dataType
	 * @param Integer index
	 */
	private void rptDataQueueProcessor(ReportDataType rptDataType, Integer index) {
		executor.execute(new RptDataQueueScanAndProcessTask(rptDataType, index, executor));
		logger.warn("ReportDataManager start RptDataQueueScanAndProcessTask (rptDataType: " 
				+ rptDataType.getValue() + " index: " + index + ") success");
	}

}
