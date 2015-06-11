package com.iuni.dp.service.datareport.constants;

/**
 * ReportData类型上报数据相关配置
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public interface ReportDataConstant {
	
	/**
	 * Common Report Data Storage files
	 */
	final String cmRptData_file_path = "cmRptData.file.path";
	
	/**
	 * Common Report Data Backup files
	 */
	final String cmRptData_backupFile_path = "cmRptData.backupFile.path";
	
	/**
	 * GnApp Report Data Storage files
	 */
	final String gnAppRptData_file_path = "gnAppRptData.file.path";
	
	/**
	 * GnApp Report Data Backup files
	 */
	final String gnAppRptData_backupFile_path = "gnAppRptData.backupFile.path";
	
	/**
	 * The report data file path(新建文件目录)
	 */
	final String file_path = "file.path";
	
	/**
	 * The report data back file path(备份文件目录)
	 */
	final String backFile_path = "backFile.path";

	/**
	 * ReportData数据类型对应队列总数目
	 */
	final String rptData_queue_num = "queue.reportdata.number";
	
	/**
	 * ReportData数据类型对应队列最大容量
	 */
	final String rptData_queue_capacity = "queue.reportdata.capacity";
	
	/**
	 * 队列类型commonRptData占用ReportData数据类型对应队列总数目
	 */
	final String commonRptData_queue_num = "queue.reportdata.commonRptData.number";
	
	/**
	 * ReportData类型上报数据队列扫描延迟时间(millisecond)
	 */
	final String rptData_queue_scan_delay = "queue.reportdata.scan.delay";
	
	/**
	 * ReportData类型上报数据文件扫描延迟时间(millisecond)
	 */
	final String rptData_file_scan_delay = "file.reportdata.scan.delay";
	
	/**
	 * ReportData类型上报数据单个文件可写最大行数
	 */
	final String rptData_file_maxLine = "file.reportdata.maxLineLimit";
	
	/**
	 * 队列对应本地缓存过期时间(second)
	 */
	final String rptData_cache_expireTime = "cache.reportdata.expireTime";
	
}
