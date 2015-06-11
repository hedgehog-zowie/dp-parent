package com.iuni.dp.service.datareport.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.persist.datareport.model.ReportData;
import com.iuni.dp.persist.datareport.model.ReportDataByMon;
import com.iuni.dp.service.datareport.constants.ReportDataConstant;

/**
 * 上报数据相应队列对应本地缓存池Manager
 * @author CaiKe
 * @version V1.0.0
 */
public class LocalCachePoolManager {

	private static final Logger logger = LoggerFactory.getLogger(LocalCachePoolManager.class);
	
	// ReportData类型上报数据单个文件可写最大行数
	private static final Integer rptDataFileMaxLineLimit = ParseProperties.getIntVal(ReportDataConstant.rptData_file_maxLine);
	
	// 当前使用队列数
	private static final Integer rptDataQueueNum = ParseProperties.getIntVal(ReportDataConstant.rptData_queue_num);
	
	// ReportData类型上报数据本地缓存池
	private LocalCachePool<ReportData> localRptDataCachePool;
	
	// ReportData类型上报数据本地缓存池
	private LocalCachePool<ReportDataByMon> localRptDataByMonCachePool;
	
	private static class LocalCachePoolManagerHolder {
		private static final LocalCachePoolManager instance = new LocalCachePoolManager();
	}
	
	public static LocalCachePoolManager getInstance() {
		return LocalCachePoolManagerHolder.instance;
	}
	
	private LocalCachePoolManager() {
		init();
	}
	
	private void init() {
		// 初始化ReportData类型上报数据本地缓存池
//		initRptDataCacahePool();
		
		// 初始化ReportData类型按月上报数据本地缓存池
		initRptDataByMonCacahePool();
	}
	
	/**
	 * @deprecated
	 * 初始化本地ReportData类型缓存池
	 */
	private void initRptDataCacahePool() {
		localRptDataCachePool = new LocalCachePool<ReportData>(rptDataFileMaxLineLimit, rptDataQueueNum);
		logger.info("LocalCachePoolManager initRptDataCacahePool success, rptDataFileMaxLineLimit: " 
				+ rptDataFileMaxLineLimit + " rptDataQueueNum: " + rptDataQueueNum);
	}
	
	private void initRptDataByMonCacahePool() {
		localRptDataByMonCachePool = new LocalCachePool<ReportDataByMon>(rptDataFileMaxLineLimit, rptDataQueueNum);
		logger.info("LocalCachePoolManager initRptDataByMonCacahePool success, rptDataFileMaxLineLimit: " 
				+ rptDataFileMaxLineLimit + " rptDataQueueNum: " + rptDataQueueNum);
	}
	
	/**
	 * @deprecated
	 * 获取ReportData类型上报数据本地缓存池
	 * @return LocalCachePool<ReportData>
	 */
	public LocalCachePool<ReportData> getLocalRptDataCachePool() {
		return localRptDataCachePool;
	}
	
	/**
	 * 获取ReportData类型按月上报数据本地缓存池
	 * @return LocalCachePool<ReportDataByMon>
	 */
	public LocalCachePool<ReportDataByMon> getLocalRptDataByMonCachePool() {
		return localRptDataByMonCachePool;
	}

	// TEST
	public static void main(String[] args) {
		
		LocalCachePool<ReportDataByMon> localCachePool = LocalCachePoolManager.getInstance().getLocalRptDataByMonCachePool();
		List<ReportDataByMon> list = localCachePool.getCacheDataList(1);
		int size = list.size();
		System.out.println(size);
		
	}
	
}
