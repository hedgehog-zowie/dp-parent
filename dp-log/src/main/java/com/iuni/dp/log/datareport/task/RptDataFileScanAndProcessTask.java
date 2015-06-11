package com.iuni.dp.log.datareport.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iuni.dp.log.datareport.bean.ReportDataLog;
import com.iuni.dp.log.datareport.constants.DataReportConstant;
import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.persist.datareport.model.GnAppAccessLog;
import com.iuni.dp.persist.datareport.model.ReportDataByMon;
import com.iuni.dp.service.common.exception.BusinessException;
import com.iuni.dp.service.common.utils.FileToolkit;
import com.iuni.dp.service.datareport.constants.ReportDataConstant;
import com.iuni.dp.service.datareport.constants.ReportDataState;
import com.iuni.dp.service.datareport.constants.ReportDataType;
import com.iuni.dp.service.datareport.service.GnAppAccessLogService;
import com.iuni.dp.service.datareport.service.ReportDataByMonService;
import com.iuni.dp.service.datareport.utils.FileStringParser;

/**
 * 数据上报文件扫描处理线程
 * @author CaiKe
 * @version V1.0.0
 */
public class RptDataFileScanAndProcessTask implements Runnable {

	private static final String rptDataStorageRecordLogger = ParseProperties
			.getPropertiesValue(DataReportConstant.RPTDATA_StorageRecord_LOGGER);
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'T'sss");
	
	private final Logger logger = LoggerFactory.getLogger(RptDataFileScanAndProcessTask.class);
	private final Logger rptDataStorageRecord_logger = LoggerFactory.getLogger(rptDataStorageRecordLogger);
	
	// 队列数据写文件路径
//	private static final File filePath = new File(ParseProperties.getPropertiesValue(ReportDataConstant.file_path));
	// 文件备份路径
//	private static final String backFilePath = ParseProperties.getPropertiesValue(ReportDataConstant.backFile_path);
	// 文件后缀
	private static final String fileExtName = FileToolkit.FILE_EXT_NAME;
	private static final String fileExt = ".txt";
	// 文件写完标记
//	private static final String fileWriteFinlsh = FileToolkit.FILE_WRITE_FINISH;
	
	private static Calendar calendar = Calendar.getInstance();
	
	// 数据上报文件每月归档检测状态
//	private static Boolean archiveByMonthCheck = true;
	private static Map<ReportDataType, Boolean> archiveByMonthCheckMap;
	
	static {
		archiveByMonthCheckMap = new HashMap<ReportDataType, Boolean>();
		archiveByMonthCheckMap.put(ReportDataType.CommonRptData, true);
		archiveByMonthCheckMap.put(ReportDataType.GnAppRptData, true);
	}
	
    private Calendar cal; 
    private File preDpFile;
    private File curDpFile;
    private String preDpBackupFilePath;
    private String curDpBackupFilePath;
    
	private ReportDataType rptDataType;
//	private Integer index;
	private ApplicationContext appCtx;
	
	public RptDataFileScanAndProcessTask(ReportDataType rptDataType, ApplicationContext appCtx) {
		super();
		this.rptDataType = rptDataType;
		this.appCtx = appCtx;
	}
	
//	public RptDataFileScanAndProcessTask(ReportDataType rptDataType, Integer index, ApplicationContext appCtx) {
//		super();
//		this.rptDataType = rptDataType;
//		this.index = index;
//		this.appCtx = appCtx;
//	}
	
	/**
	 * 每月数据上报文件归档滚动检测机制
	 */
	protected void checkArchiveByMonth() {
		int preThreadMonth = calendar.get(Calendar.MONTH);
		calendar.setTime(new Date());
		int curThreadMonth = calendar.get(Calendar.MONTH);
		if(preThreadMonth != curThreadMonth) {
			archiveByMonthCheckMap.put(rptDataType, true);
		}
	}
	
	/**
	 * 初始化文件路径等实例变量
	 */
	protected void initCondition(ReportDataType reportDataType) {
		cal = Calendar.getInstance();
		String curYear = "";
		String preYear = "";
		String curMonth = "";
		String preMonth = "";
		
		curYear = String.valueOf(cal.get(Calendar.YEAR));
		int month = cal.get(Calendar.MONTH) + 1;
		if(month < 10) {
			curMonth = "0" + month;
		} else {
			curMonth = "" + month;
		}
		cal.add(Calendar.MONTH, -1);
		preYear = String.valueOf(cal.get(Calendar.YEAR));
		month = cal.get(Calendar.MONTH) + 1;
		if(month < 10) {
			preMonth = "0" + month;
		} else {
			preMonth = "" + month;
		}
		
		String curRealFilePath = curYear + "-" + curMonth;
		String preRealFilePath = preYear + "-" + preMonth;
		String baseFilePath = "";
		String baseBackupFilePath = "";
		
		// 初始化入库以及备份各个数据上报文件路径
		if(ReportDataType.CommonRptData.equals(reportDataType)) {
			baseFilePath = ParseProperties.getPropertiesValue(ReportDataConstant.cmRptData_file_path);
			baseBackupFilePath = ParseProperties.getPropertiesValue(ReportDataConstant.cmRptData_backupFile_path);
			
		} else if(ReportDataType.GnAppRptData.equals(reportDataType)) {
			baseFilePath = ParseProperties.getPropertiesValue(ReportDataConstant.gnAppRptData_file_path);
			baseBackupFilePath = ParseProperties.getPropertiesValue(ReportDataConstant.gnAppRptData_backupFile_path);
			
		} else {
			return;
		}
		// 当前扫描数据上报文件切分路径
		curDpFile = new File(baseFilePath + curRealFilePath + "/");
		curDpBackupFilePath = baseBackupFilePath + curRealFilePath + "/";
		// 上次扫描数据上报文件切分路径
		preDpFile = new File(baseFilePath + preRealFilePath + "/");
		preDpBackupFilePath = baseBackupFilePath + preRealFilePath + "/";
	}

	@Override
	public void run() {
		try {
			
			// 每月数据上报文件归档滚动检测机制
			checkArchiveByMonth();
			
			// 初始化文件路径等实例变量
			initCondition(rptDataType);
			
			// 线程主处理
//			mainProcessor();
			
			mainProcessor2();
			
		} catch(Exception e) {
			logger.error("RptDataFileScanAndProcessTask found Exception", e);
			boolean isAlive = Thread.currentThread().isAlive();
			if(!isAlive) {
				Thread.currentThread().start();
			}
		}
	}
	
	/**
	 * 线程主处理
	 * @throws InterruptedException
	 * @throws Exception
	 */
//	protected void mainProcessor() throws Exception {
//		// 文件前缀
//		String fileFlag = rptDataType.getValue() + "_" + index;
//		// 取出符合条件的文件目录
//		List<String> filterFilePathList = FileToolkit.allFileList2(filePath,fileExtName,fileFlag,fileWriteFinlsh);
//		if(CollectionUtils.isNotEmpty(filterFilePathList)) {
//			// 解析相应文件入库
//			parseFile2DB(rptDataType, filterFilePathList);
//		}
//	}
	
	protected void mainProcessor2() throws Exception {
		// 文件前缀
		String fileFlag = rptDataType.getValue();
		
		/**
		 * 数据上报文件每月最后一天零点归档滚动，启用扫描本月及上月数据上报文件检测机制
		 * 如上月数据上报文件仍有文件存留则下次继续进行该归档滚动检测机制
		 * 直至上月数据上报文件无存留后停止该归档滚动检测机制
		 */
		Boolean archiveByMonthCheck = archiveByMonthCheckMap.get(rptDataType);
		if(null != archiveByMonthCheck && archiveByMonthCheck) {
			List<String> preFilterFilePathList = FileToolkit.findAllFileList4Dp(preDpFile,fileExtName,fileFlag);
			if(CollectionUtils.isNotEmpty(preFilterFilePathList)) {
				// 解析相应文件入库
				parseFile2DB(rptDataType, preFilterFilePathList, preDpBackupFilePath);
			} else {
				archiveByMonthCheckMap.put(rptDataType, false);
			}
		}
		
		// 取出符合条件的文件目录
		List<String> curFilterFilePathList = FileToolkit.findAllFileList4Dp(curDpFile,fileExtName,fileFlag);
		if(CollectionUtils.isNotEmpty(curFilterFilePathList)) {
			// 解析相应文件入库
			parseFile2DB(rptDataType, curFilterFilePathList, curDpBackupFilePath);
		}
	}
	
	/**
	 * 文件扫描入库处理
	 * @param dataType
	 * @param fileList
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected void parseFile2DB(ReportDataType rptDataType, List<String> fileList, String backupFilePath) throws Exception {
		for (String fileFullPathName : fileList) {
			if (StringUtils.isNotEmpty(fileFullPathName)) {
				// 解析当前正在处理的文件
				long stime = System.currentTimeMillis();
				List<String> readLineList = FileToolkit.batchReadLines(fileFullPathName);
				int readLineCount = (readLineList != null) ? readLineList.size() : 0;
				
				try {
					if (readLineCount > 0) {
						if(ReportDataType.CommonRptData.equals(rptDataType)) {
							// 将ReportData类型上报数据文件读取的List<String>转换为List<ReportData>
							Map<ReportDataState, List<ReportDataByMon>> rptDataMap = FileStringParser.fromStringToCmRptData(readLineList);
							
							List<ReportDataByMon> nlRptDataList = rptDataMap.get(ReportDataState.Normal);
							List<ReportDataByMon> abnlRptDataList = rptDataMap.get(ReportDataState.Abnormal);
							ReportDataByMonService<ReportDataByMon> reportDataService = appCtx.getBean("reportDataByMonService", ReportDataByMonService.class);
							
							// 正常上报数据入库
							Integer execNlCount = reportDataService.batchSaveReportData(nlRptDataList);
							logger.debug("RptDataFileScanAndProcessTask.parseFile2DB save normal ReportData to db success : ReportDataType={}, FilePath={}, fileSize={}, execNlCount={}" 
									, new Object[] {rptDataType.getValue(), fileFullPathName, readLineCount, execNlCount});
							
							// 异常上报数据入库
							Integer execAbnlCount = reportDataService.batchSaveReportDataEx(abnlRptDataList);
							logger.debug("RptDataFileScanAndProcessTask.parseFile2DB save abnormal ReportData to db success : ReportDataType={}, FilePath={}, fileSize={}, execAbnlCount={}" 
									, new Object[] {rptDataType.getValue(), fileFullPathName, readLineCount, execAbnlCount});
							
							// 单独写ReportData类型上报数据文件入库日志
							String fileName = FileToolkit.getFileNameFromPath(fileFullPathName);
							Integer normalDataSize = nlRptDataList.size();
							Integer abNormalDataSize = abnlRptDataList.size();
							Long costTime = System.currentTimeMillis() - stime;
							ReportDataLog rptDataLog = new ReportDataLog(rptDataType.getValue(), fileName, readLineCount, normalDataSize, abNormalDataSize, execNlCount, execAbnlCount, costTime, new Date());
							String rptDataRecord = JSON.toJSONString(rptDataLog, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue);
							rptDataStorageRecord_logger.info(rptDataRecord);
							
						} else if(ReportDataType.GnAppRptData.equals(rptDataType)) {
							// 将ReportData类型上报数据文件读取的List< String >转换为List< GnAppAccessLog >
							Map<ReportDataState, List<GnAppAccessLog>> rptDataMap = FileStringParser.fromStringToGnAppRptData(readLineList);
							
							List<GnAppAccessLog> nlRptDataList = rptDataMap.get(ReportDataState.Normal);
							List<GnAppAccessLog> abnlRptDataList = rptDataMap.get(ReportDataState.Abnormal);
							GnAppAccessLogService gnAppAccessLogService = appCtx.getBean("gnAppAccessLogService", GnAppAccessLogService.class);
							
							// 正常上报数据入库
							Integer execNlCount = gnAppAccessLogService.batchSaveGnAppAccessLog(nlRptDataList);
							logger.debug("RptDataFileScanAndProcessTask.parseFile2DB save normal ReportData to db success : ReportDataType={}, FilePath={}, fileSize={}, execNlCount={}" 
									, new Object[] {rptDataType.getValue(), fileFullPathName, readLineCount, execNlCount});
							
							// 异常上报数据入库
							Integer execAbnlCount = gnAppAccessLogService.batchSaveGnAppAccessLogEx(abnlRptDataList);
							logger.debug("RptDataFileScanAndProcessTask.parseFile2DB save abnormal ReportData to db success : ReportDataType={}, FilePath={}, fileSize={}, execAbnlCount={}" 
									, new Object[] {rptDataType.getValue(), fileFullPathName, readLineCount, execAbnlCount});
							
							// 单独写ReportData类型上报数据文件入库日志
							String fileName = FileToolkit.getFileNameFromPath(fileFullPathName);
							Integer normalDataSize = nlRptDataList.size();
							Integer abNormalDataSize = abnlRptDataList.size();
							Long costTime = System.currentTimeMillis() - stime;
							ReportDataLog rptDataLog = new ReportDataLog(rptDataType.getValue(), fileName, readLineCount, normalDataSize, abNormalDataSize, execNlCount, execAbnlCount, costTime, new Date());
							String rptDataRecord = JSON.toJSONString(rptDataLog, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue);
							rptDataStorageRecord_logger.info(rptDataRecord);
						}
						
						long btime = System.currentTimeMillis();
						
						// 组合随机数
						Random random = new Random();
						String randomStr = "";
						for(int i = 0; i < 3; i++) {
							randomStr += random.nextInt(10);
						}
						String curTimeStr = dateFormat.format(new Date());
						String fileSuffix = "-" + curTimeStr + "-" + randomStr;
						int extIndex = fileFullPathName.indexOf(fileExt);
						StringBuffer buf = new StringBuffer(fileFullPathName);
						buf.insert(extIndex, fileSuffix);
						String newFileName = buf.toString();
						
						// 重命名处理完毕的文件
						FileToolkit.renameFile(fileFullPathName, newFileName);
						// 将处理完毕的文件备份到备份目录
						FileToolkit.moveFile(newFileName, backupFilePath);
						
						long mtime = System.currentTimeMillis();
						
						logger.debug("RptDataFileScanAndProcessTask.parseFile2DB move saved file to backFile success : FilePath={},costTime={}ms",
								new Object[] {fileFullPathName, mtime-btime});
					}
					
				} catch(RuntimeException e) {
					logger.error("RptDataFileScanAndProcessTask.parseFile2DB found RuntimeException, fileFullPathName: {}, readLineCount: {}" 
							, new Object[]{fileFullPathName, readLineCount});
					continue;
				} catch(Exception e) {
					logger.error("RptDataFileScanAndProcessTask.parseFile2DB found Exception, fileFullPathName: {}, readLineCount: {}" 
							, new Object[]{fileFullPathName, readLineCount});
					throw e;
				}
			}
		}
	}
	
}
