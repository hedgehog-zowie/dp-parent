package com.iuni.dp.api.datareport.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.persist.datareport.model.ReportDataByMon;
import com.iuni.dp.service.datareport.constants.ReportDataConstant;
import com.iuni.dp.service.datareport.constants.ReportDataType;
import com.iuni.dp.service.datareport.queue.BlockingQueuePool;
import com.iuni.dp.service.datareport.queue.QueueManager;

/**
 * 数据上报对应阻塞队列监控扫描线程
 * @author CaiKe
 * @version V1.0.0
 */
public class RptDataQueueScanAndProcessTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(RptDataQueueScanAndProcessTask.class);
	
	private static final Long queueScanDelay = ParseProperties.getLongVal(ReportDataConstant.rptData_queue_scan_delay);
	
	private Boolean isRunning = true;
	
//	private BlockingQueuePool<ReportData> rptDataQueuePool;
	
	private BlockingQueuePool<ReportDataByMon> rptDataByMonQueuePool;
	
	private ReportDataType rptDataType;
	private Integer index;
	
	// 线程池
	private ThreadPoolTaskExecutor executor;
	
	{
//		rptDataQueuePool = QueueManager.getInstance().getRptDataQueuePool();
		rptDataByMonQueuePool = QueueManager.getInstance().getRptDataByMonQueuePool();
	}
	
	public RptDataQueueScanAndProcessTask(ReportDataType rptDataType, Integer index, ThreadPoolTaskExecutor executor) {
		super();
		this.rptDataType = rptDataType;
		this.index = index;
		this.executor = executor;
	}

	@Override
	public void run() {
		logger.info("RptDataQueueScanAndProcessTask (rptDataType: " + rptDataType + " index: " + index + ") start scanning");
		try {
			// ReportData类型上报数据对应队列数据处理
			rptDataProcessor();
			
		} catch (Exception e) {
			logger.error("RptDataQueueScanAndProcessTask found Exception", e);
			boolean isAlive = Thread.currentThread().isAlive();
			if(!isAlive) {
				Thread.currentThread().start();
			}
		}
	}
	
	/**
	 * 队列数据处理
	 * @throws Exception
	 */
	private void rptDataProcessor() throws Exception {
		while(isRunning) {
			int curQueueSize = rptDataByMonQueuePool.getQueue(rptDataType, index).size();
			if(curQueueSize > 0) {
				List<ReportDataByMon> rptDataList = rptDataByMonQueuePool.batchTake(rptDataType, index, curQueueSize);
				
				executor.execute(new RptDataQueueWriteToFileTask(rptDataList, rptDataType, index));
			}
			if(curQueueSize == 0) {
				executor.execute(new RptDataCacheWriteToFileTask(rptDataType, index));
			}
			
			Thread.sleep(queueScanDelay);
		}
	}
	
	/**
	 * 判断线程池是否已满
	 * @param executor
	 * @return Boolean
	 */
	protected Boolean isThreadPoolFull(ThreadPoolTaskExecutor executor) {
		Boolean status = false;
		
		int activeCount = executor.getActiveCount();
		int maxPoolSize = executor.getMaxPoolSize();
		if(activeCount > maxPoolSize) {
			status = true;
		}
		
		return status;
	}
	
}
