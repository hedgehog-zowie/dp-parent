package com.iuni.dp.admin.datastat.task;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.iuni.dp.service.datastat.service.StatSchemeService;

/**
 * 统计分析计划任务线程Manager
 * @author CaiKe
 * @version dp-task-1.0.0
 */
@Component("statSchemeTaskManager")
public class StatSchemeTaskManager {
	
	private static final Logger logger = LoggerFactory.getLogger(StatSchemeTaskManager.class);	
	
	@Autowired
	private StatSchemeService statSchemeService;
	
	@Autowired
	private ThreadPoolTaskExecutor executor;
	
	@Autowired
	private Scheduler qtzJobScheduler;
	
	public void setQtzJobScheduler(Scheduler qtzJobScheduler) {
		this.qtzJobScheduler = qtzJobScheduler;
		startScanStatSchemeTask();
	}

	/**
	 * 应该启用时启用统计分析计划扫描处理线程
	 * 处理已存在并已启用的统计分析计划
	 */
	protected void startScanStatSchemeTask() {
		executor.execute(new StatSchemeScanTask(qtzJobScheduler, statSchemeService));
		
		logger.info("StatSchemeTaskManager.startScanStatSchemeTask invoke success.");
	}
	
	/**
	 * 启用 Quartz Scheduler
	 */
	protected void startQtzScheduler() {
		try {
			boolean isStart = qtzJobScheduler.isStarted();
			if(!isStart) {
				qtzJobScheduler.start();
			}
		} catch (SchedulerException e) {
			logger.error("StatSchemeTaskManager.startQtzScheduler found SchedulerException.", e);
		}
	}
	
}
