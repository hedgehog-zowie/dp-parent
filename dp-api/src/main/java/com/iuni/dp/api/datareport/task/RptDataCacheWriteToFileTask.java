package com.iuni.dp.api.datareport.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iuni.dp.persist.datareport.model.ReportDataByMon;
import com.iuni.dp.service.datareport.cache.LocalCachePool;
import com.iuni.dp.service.datareport.cache.LocalCachePoolManager;
import com.iuni.dp.service.datareport.constants.ReportDataType;

/**
 * 上报数据队列对应缓存写文件线程
 * @author CaiKe
 * @version V1.0.0
 */
public class RptDataCacheWriteToFileTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(RptDataCacheWriteToFileTask.class);
	
//	private LocalCachePool<ReportData> rptDataCachePool;
	
	private LocalCachePool<ReportDataByMon> rptDataByMonCachePool;
	
	private ReportDataType rptDataType;
	private Integer index;
	
	{
//		rptDataCachePool = LocalCachePoolManager.getInstance().getLocalRptDataCachePool();
		rptDataByMonCachePool = LocalCachePoolManager.getInstance().getLocalRptDataByMonCachePool();
	}
	
	public RptDataCacheWriteToFileTask(ReportDataType rptDataType, Integer index) {
		super();
		this.rptDataType = rptDataType;
		this.index = index;
	}
	
	@Override
	public void run() {
		
		try {
			// 队列过期缓存写文件处理
			writeRptDataFileProcessor();
		} catch(Exception e) {
			logger.error("RptDataCacheWriteToFileTask found exception", e);
		}
		
	}

	/**
	 * 队列过期缓存写文件处理器
	 * @param Long expireTime
	 */
	private void writeRptDataFileProcessor() {
		// ReportData类型上报数据过期缓存数据写文件
		rptDataByMonCachePool.writeExpireCache(index, rptDataType);
	}
	
}
