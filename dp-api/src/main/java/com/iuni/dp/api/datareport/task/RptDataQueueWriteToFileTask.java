package com.iuni.dp.api.datareport.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iuni.dp.persist.datareport.model.ReportDataByMon;
import com.iuni.dp.service.datareport.cache.LocalCachePool;
import com.iuni.dp.service.datareport.cache.LocalCachePoolManager;
import com.iuni.dp.service.datareport.constants.ReportDataType;

/**
 * 上报数据对应队列写文件异步线程
 * @author CaiKe
 * @version V1.0.0
 */
public class RptDataQueueWriteToFileTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(RptDataQueueWriteToFileTask.class);
	
//	private LocalCachePool<ReportData> rptDataCachePool;
	
	private LocalCachePool<ReportDataByMon> rptDataByMonCachePool;
	
	private List<ReportDataByMon> rptDataList;
	private ReportDataType rptDataType;
	private Integer index;
	
	{
//		rptDataCachePool = LocalCachePoolManager.getInstance().getLocalRptDataCachePool();
		rptDataByMonCachePool = LocalCachePoolManager.getInstance().getLocalRptDataByMonCachePool();
	}
	
	public RptDataQueueWriteToFileTask(List<ReportDataByMon> rptDataList, ReportDataType rptDataType,
			Integer index) {
		super();
		this.rptDataList = rptDataList;
		this.rptDataType = rptDataType;
		this.index = index;
	}
	
	@Override
	public void run() {
		
		try {
			// 数据序列写文件处理
			writeFileProcessor(rptDataList, index, rptDataType);
			
		} catch(Exception e) {
			logger.error("QueueWriteToFileTask2 found exception", e);
		}
	}
	
	/**
	 * 数据序列写文件处理器
	 * @param List<String> dataList 数据序列
	 * @param Integer index 数据队列索引
	 * @param String dataType 数据类型
	 */
	private void writeFileProcessor(List<ReportDataByMon> rptDataList, Integer index, ReportDataType rptDataType) {
		// ReportData类型上报数据相应队列数据写入缓存并处理 
		rptDataByMonCachePool.setRptData2CacheFromQueue(rptDataList, index, rptDataType);
	}
	
}
