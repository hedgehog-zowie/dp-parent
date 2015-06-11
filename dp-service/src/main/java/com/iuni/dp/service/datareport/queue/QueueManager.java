package com.iuni.dp.service.datareport.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.persist.datareport.model.ReportData;
import com.iuni.dp.persist.datareport.model.ReportDataByMon;
import com.iuni.dp.service.datareport.constants.ReportDataConstant;
import com.iuni.dp.service.datareport.constants.ReportDataType;

/**
 * 上报数据阻塞队列池Manager
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public class QueueManager {
	private static final Logger logger = LoggerFactory.getLogger(QueueManager.class);
	
	private static class QueueManagerHolder {
		private static final QueueManager instance = new QueueManager();
	}
	
	private QueueManager() {
		init();
	}
	
	public static QueueManager getInstance() {
		return QueueManagerHolder.instance;
	}
	
	// 数据上报数据类型对应队列总数目 
	private static final Integer queueNumOfRptData = ParseProperties.getIntVal(ReportDataConstant.rptData_queue_num);
	
	// 数据上报数据类型对应队列最大容量
	private static final Integer queueCapacityOfRptData = ParseProperties.getIntVal(ReportDataConstant.rptData_queue_capacity);
	
	// 数据上报类型数据队列池
	private BlockingQueuePool<ReportData> rptDataQueuePool;
	
	// 按月数据上报类型数据队列池
	private BlockingQueuePool<ReportDataByMon> rptDataByMonQueuePool;
	
	private void init() {
		// 初始化数据上报类型数据队列池
//		initRptDataQueuePool();
		
		// 初始化按月数据上报类型数据队列池
		initRptDataByMonQueuePool();
	}
	
	/**
	 * @deprecated
	 * 初始化数据上报类型数据队列池
	 */
	private void initRptDataQueuePool() {
		rptDataQueuePool = new BlockingQueuePool<ReportData>(queueNumOfRptData, queueCapacityOfRptData);
		logger.info("QueueManager init QueuePool of ReportData success, queueNumOfRptData: " 
				+ queueNumOfRptData + " queueCapacityOfRptData : " + queueCapacityOfRptData);
	}
	
	private void initRptDataByMonQueuePool() {
		rptDataByMonQueuePool = new BlockingQueuePool<ReportDataByMon>(queueNumOfRptData, queueCapacityOfRptData);
		logger.info("QueueManager init QueuePool of ReportDataByMon success, queueNumOfRptData: " 
				+ queueNumOfRptData + " queueCapacityOfRptData : " + queueCapacityOfRptData);
	}
	
	/**
	 * @deprecated
	 * 获取数据上报类型数据队列池
	 * @return BlockingQueuePool<ReportData>
	 */
	public BlockingQueuePool<ReportData> getRptDataQueuePool() {
		return rptDataQueuePool;
	}
	
	/**
	 * 获取按月数据上报类型数据队列池
	 * @return BlockingQueuePool<ReportDataByMon>
	 */
	public BlockingQueuePool<ReportDataByMon> getRptDataByMonQueuePool() {
		return rptDataByMonQueuePool;
	}

	// Unit TEST
	public static void main(String[] args) {
		BlockingQueuePool<ReportDataByMon> rptDataByMonQueuePool = QueueManager.getInstance().getRptDataByMonQueuePool();
		
		rptDataByMonQueuePool.genQueueByDataType(ReportDataType.CommonRptData);
		rptDataByMonQueuePool.genQueueByDataType(ReportDataType.CommonRptData);
		
		ReportDataByMon demo1 = new ReportDataByMon();
		demo1.setSourceId("11111");
		ReportDataByMon demo2 = new ReportDataByMon();
		demo2.setSourceId("22222");
		
		rptDataByMonQueuePool.put(ReportDataType.CommonRptData,0, demo1);
		rptDataByMonQueuePool.put(ReportDataType.CommonRptData,1, demo2);
		System.out.println(rptDataByMonQueuePool.take(ReportDataType.CommonRptData, 0));
		System.out.println(rptDataByMonQueuePool.take(ReportDataType.CommonRptData, 1));
		
	}
	
}
