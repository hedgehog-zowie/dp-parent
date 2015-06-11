package com.iuni.dp.log.datareport.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iuni.dp.log.datareport.bean.LogDataLog;
import com.iuni.dp.log.datareport.constants.IuniLogDataConstant;
import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.persist.datastat.model.IuniUcLoginLog;
import com.iuni.dp.service.common.utils.FileToolkit;
import com.iuni.dp.service.datareport.utils.FileStringParser;
import com.iuni.dp.service.datastat.service.IuniUcLoginLogService;

/**
 * IUNI User Login Log Process Task
 * @author Kenneth.Cai@iuni.com
 * @version dp-log-1.1.4
 */
public class IuniUserLoginLogProcessTask implements Runnable {

	private static final String IUNI_LOGDATA_LOGGER = ParseProperties
			.getPropertiesValue(IuniLogDataConstant.IUNI_LOGDATA_LOGGER);
	
	private static final String IUNI_UC_LOGINLOG_FILES = ParseProperties
			.getPropertiesValue(IuniLogDataConstant.IUNI_UC_LOGINLOG_FILE);
	
	private static final String IUNI_UC_LOGINLOG_BAKUPFILES = ParseProperties
			.getPropertiesValue(IuniLogDataConstant.IUNI_UC_LOGINLOG_BACKUP_FILE);
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'T'sss");
	
	private static final String DOT = ".";
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final Logger iuniLogData_logger = LoggerFactory.getLogger(IUNI_LOGDATA_LOGGER);
	
	// IUNI UC Login log Storage File for read
	private File loginLogFile;
	
	private ApplicationContext appCtx;
	
	public IuniUserLoginLogProcessTask(ApplicationContext appCtx) {
		super();
		this.appCtx = appCtx;
	}

	@Override
	public void run() {
		try {
			// 初始化文件路径等实例变量
			initCondition();
			
			// 线程主处理
			mainProcessor();

		} catch (Exception e2) {
			logger.error("IuniUserLoginLogProcessTask found Exception", e2);
			boolean isAlive = Thread.currentThread().isAlive();
			if (!isAlive) {
				Thread.currentThread().start();
			}
		}
	}
	
	/**
	 * 初始化文件路径等实例变量
	 */
	protected void initCondition() {
		loginLogFile = new File(IUNI_UC_LOGINLOG_FILES);
	}

	/**
	 * 线程主处理
	 * @throws Exception
	 */
	protected void mainProcessor() throws Exception {
		// 取出符合条件的文件目录
		List<String> curFilterFilePathList = FileToolkit
				.findAllFileListByPrefix(loginLogFile,
						IuniLogDataConstant.LOGIN_LOG_PREFIX);
		if(CollectionUtils.isNotEmpty(curFilterFilePathList)) {
			// 解析相应文件入库
			parseFile2DB(curFilterFilePathList, IUNI_UC_LOGINLOG_BAKUPFILES);
		}
	}
	
	/**
	 * 文件扫描入库处理
	 * @param dataType
	 * @param fileList
	 * @throws Exception
	 */
	protected void parseFile2DB(List<String> fileList, String backupFilePath) throws Exception {
		for (String fileFullPathName : fileList) {
			if (StringUtils.isNotEmpty(fileFullPathName)) {
				// 解析当前正在处理的文件
				long stime = System.currentTimeMillis();
				List<String> readLineList = FileToolkit.batchReadLines(fileFullPathName);
				int readLineCount = (readLineList != null) ? readLineList.size() : 0;
				
				try {
					if (readLineCount > 0) {
						// 将ReportData类型上报数据文件读取的List<String>转换为List<ReportData>
						List<IuniUcLoginLog> iuniUcLoginLogList = FileStringParser.fromStringToLoginLog(readLineList);
						
						IuniUcLoginLogService iuniUcLoginLogService = appCtx.getBean("iuniUcLoginLogService", IuniUcLoginLogService.class);
						
						// 正常上报数据入库
						Integer execCount = iuniUcLoginLogService.batchSaveIuniUcLoginLog(iuniUcLoginLogList).intValue();
						logger.debug("IuniUserLoginLogProcessTask.parseFile2DB save IUNI UC Login Log to db success : FilePath={}, fileSize={}, execCount={}" 
								, new Object[] {fileFullPathName, readLineCount, execCount});
						
						// 单独写ReportData类型上报数据文件入库日志
						String fileName = FileToolkit.getFileNameFromPath(fileFullPathName);
						Integer dataSize = iuniUcLoginLogList.size();
						Long costTime = System.currentTimeMillis() - stime;
						LogDataLog logDataLog = new LogDataLog(
								IuniLogDataConstant.IUNI_UC_LOGIN_DATA, fileName,
								readLineCount, dataSize, execCount, costTime,
								new Date());
						String rptDataRecord = JSON.toJSONString(logDataLog,
								SerializerFeature.WriteDateUseDateFormat,
								SerializerFeature.WriteMapNullValue);
						iuniLogData_logger.info(rptDataRecord);
						
						long btime = System.currentTimeMillis();
						
						// 组合随机数
						Random random = new Random();
						String randomStr = "";
						for(int i = 0; i < 3; i++) {
							randomStr += random.nextInt(10);
						}
						String curTimeStr = dateFormat.format(new Date());
						String fileSuffix = DOT + curTimeStr + "-" + randomStr;
						StringBuffer buf = new StringBuffer(fileFullPathName);
						buf.append(fileSuffix);
						String newFileName = buf.toString();
						
						// 重命名处理完毕的文件
						FileToolkit.renameFile(fileFullPathName, newFileName);
						// 将处理完毕的文件备份到备份目录
						FileToolkit.moveFile(newFileName, backupFilePath);
						
						long mtime = System.currentTimeMillis();
						
						logger.debug("IuniUserLoginLogProcessTask.parseFile2DB move saved file to backFile success : FilePath={},costTime={}ms",
								new Object[] {fileFullPathName, mtime-btime});
					}
					
				} catch(RuntimeException e) {
					logger.error("IuniUserLoginLogProcessTask.parseFile2DB found RuntimeException, fileFullPathName: {}, readLineCount: {}" 
							, new Object[]{fileFullPathName, readLineCount});
					continue;
				} catch(Exception e) {
					logger.error("IuniUserLoginLogProcessTask.parseFile2DB found Exception, fileFullPathName: {}, readLineCount: {}" 
							, new Object[]{fileFullPathName, readLineCount});
					throw e;
				}
			}
		}
	}
	
}
