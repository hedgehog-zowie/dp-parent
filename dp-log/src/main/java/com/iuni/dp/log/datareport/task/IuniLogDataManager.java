package com.iuni.dp.log.datareport.task;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import com.iuni.dp.log.datareport.constants.IuniLogDataConstant;
import com.iuni.dp.persist.common.utils.ParseProperties;

/**
 * IUNI Log Data Manager
 * @author Kenneth.Cai@iuni.com
 * @version dp-log-1.1.4
 */
@Component("iuniLogDataManager")
public class IuniLogDataManager implements ApplicationContextAware {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final Long IUNI_UC_LOGINLOG_SCAN_INTERVAL = ParseProperties
			.getLongVal(IuniLogDataConstant.IUNI_UC_LOGINLOG_SCAN_INTERVAL);
	
	private static final Long IUNI_UC_OLOGINLOG_SCAN_INTERVAL = ParseProperties
			.getLongVal(IuniLogDataConstant.IUNI_UC_OLOGINLOG_SCAN_INTERVAL);
	
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
		// IUNI UC 用户登陆日志文件定时扫描线程
		scanLoginLogOnTime();
		
		// IUNI UC 第三方用户登陆日志文件定时扫描线程
		scanOloginLogOnTime();
	}
	
	/**
	 * IUNI UC 用户登陆日志文件定时扫描线程
	 */
	protected void scanLoginLogOnTime() {
		scheduler.scheduleWithFixedDelay(new IuniUserLoginLogProcessTask(appCtx), IUNI_UC_LOGINLOG_SCAN_INTERVAL);
		logger.info("IuniLogDataManager.scanLoginLogOnTime start scheduled task With Fixed Delay, task type: "
				+ IuniLogDataConstant.IUNI_UC_LOGIN_DATA);
	}
	
	/**
	 * IUNI UC 第三方用户登陆日志文件定时扫描线程
	 */
	protected void scanOloginLogOnTime() {
		scheduler.scheduleWithFixedDelay(new IuniUserOloginLogProcessTask(appCtx), IUNI_UC_OLOGINLOG_SCAN_INTERVAL);
		logger.info("IuniLogDataManager.scanLoginLogOnTime start scheduled task With Fixed Delay, task type: "
				+ IuniLogDataConstant.IUNI_UC_OLOGIN_DATA);
	}
	
}
