package com.iuni.dp.log.datareport.task;

//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.service.datareport.constants.ReportDataConstant;
import com.iuni.dp.service.datareport.constants.ReportDataType;

/**
 * 页面相关数据上报Manager
 * 页面数据上报文件扫描入库线程启用
 * @author CaiKe
 * @version dp-log-1.0.0
 */
@Service("reportDataManager")
public class ReportDataManager implements ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(ReportDataManager.class);
	
	// 队列类型ReportDataType.CommonRptdata占用数据上报数据类型对应队列总数目
//	private static final Integer queueNumOfCommonRptdata = ParseProperties.getIntVal(ReportDataConstant.commonRptData_queue_num);

	// 数据上报数据类型对应队列总数目 
//	private static final Integer queueNumOfRptData = ParseProperties.getIntVal(ReportDataConstant.rptData_queue_num);
	
	// 数据上报文件扫描延迟时间(millisecond)
	private static final Long fileScanDelay = ParseProperties.getLongVal(ReportDataConstant.rptData_file_scan_delay);
	
	// 已分配队列索引列表
//	private List<Integer> distributedIndexs = new ArrayList<Integer>(queueNumOfRptData);

	// 数据类型与队列索引对应Map
//	private Map<ReportDataType, List<Integer>> distributeMap = new HashMap<ReportDataType, List<Integer>>();
	
	// Spring ApplicationContext
	private ApplicationContext appCtx;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.appCtx = applicationContext;
	}
	
	@Autowired
	private ThreadPoolTaskExecutor executor;
	
	@Autowired
	private ThreadPoolTaskScheduler scheduler;
	
	@PostConstruct
	public void manage() {
		// 初始化上报数据队列、数据类型、队列索引对应关系
//		genRptDataTypeMap();
		
		// 启动ReportData类型上报数据文件扫描处理主线程
//		scanRptDataFile();
		
		// 通用上报数据文件定时扫描线程
		scheduler.scheduleWithFixedDelay(new RptDataFileScanAndProcessTask(ReportDataType.CommonRptData, appCtx), fileScanDelay);
		logger.info("ReportDataManager.manage start scheduled task With Fixed Delay, task type: "
				+ ReportDataType.CommonRptData.getValue());
		
		// Gionee APP上报数据文件定时扫描线程
		scheduler.scheduleWithFixedDelay(new RptDataFileScanAndProcessTask(ReportDataType.GnAppRptData, appCtx), fileScanDelay);
		logger.info("ReportDataManager.manage start scheduled task With Fixed Delay, task type: "
				+ ReportDataType.GnAppRptData.getValue());
	}
	
	/**
	 * 初始化上报数据队列、数据类型、队列索引对应关系
	 */
//	protected void genRptDataTypeMap() {
//		for(int i = 0; i < queueNumOfCommonRptdata; i++) {
//			int lastIndex = genQueueByDataType(ReportDataType.CommonRptData);
//			if(lastIndex < 0) {
//				logger.info("ReportDataManager.genRptDataTypeMap init queue and ReportDataType and queue index relationship failed ");
//			}
//		}
//		logger.info("ReportDataManager.genRptDataTypeMap init queue and ReportDataType and queue index relationship success ");
//	}
	
	/**
	 * 根据数据类型ReportDataType生成队列、数据类型、队列索引对应关系
	 * 返回当前类型ReportDataType新对应的队列Index
	 * @param ReportDataType rptDataType
	 * @return Integer
	 */
//	protected Integer genQueueByDataType(ReportDataType rptDataType) {
//		Integer lastIndex = -1;
//		Collections.sort(distributedIndexs);
//		if(distributedIndexs.size() < queueNumOfRptData) {
//			if(distributedIndexs.size() > 0) {
//				lastIndex = distributedIndexs.get(distributedIndexs.size() - 1);
//			}
//			distributedIndexs.add(++lastIndex);
//			if(distributeMap.containsKey(rptDataType)) {
//				distributeMap.get(rptDataType).add(lastIndex);
//			} else {
//				List<Integer> indexList = new ArrayList<Integer>();
//				indexList.add(lastIndex);
//				distributeMap.put(rptDataType, indexList);
//			}
//		}
//		
//		return lastIndex;
//	}
	
	/**
	 * 根据dataType、index启用符合相应规则文件的扫描线程
	 */
//	protected void scanRptDataFile() {
//		for(Entry<ReportDataType, List<Integer>> entry: distributeMap.entrySet()) {
//			ReportDataType rptDataType = entry.getKey();
//			List<Integer> indexList = entry.getValue();
//			for(Integer index : indexList) {
//				scheduler.scheduleWithFixedDelay(new RptDataFileScanAndProcessTask(rptDataType, index, appCtx), fileScanDelay);
//				logger.warn("ReportDataManager start RptDataFileScanAndProcessTask (rptDataType: " 
//						+ rptDataType + " index: " + index + ") success");
//			}
//		}
//	}

}
