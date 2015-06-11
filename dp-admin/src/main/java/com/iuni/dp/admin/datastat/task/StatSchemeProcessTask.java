package com.iuni.dp.admin.datastat.task;

import java.sql.Time;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iuni.dp.admin.datastat.constants.StatSchemeTaskType;
import com.iuni.dp.admin.datastat.constants.StatStrategy;
import com.iuni.dp.persist.datastat.model.StatScheme;
import com.iuni.dp.service.datastat.service.StatSchemeService;

/**
 * 统计分析计划前端配置处理任务(更改单个scheme)
 * @author CaiKe
 * @version dp-task-1.0.0
 */
public class StatSchemeProcessTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(StatSchemeProcessTask.class);
	
	private Scheduler qtzJobScheduler;
	
	private StatSchemeService statSchemeService;
	
	private Integer statSchemeId;
	
	private StatSchemeTaskType taskType;

	public StatSchemeProcessTask(Scheduler qtzJobScheduler,
			StatSchemeService statSchemeService, Integer statSchemeId,
			StatSchemeTaskType taskType) {
		super();
		this.qtzJobScheduler = qtzJobScheduler;
		this.statSchemeService = statSchemeService;
		this.statSchemeId = statSchemeId;
		this.taskType = taskType;
	}

	@Override
	public void run() {
		try {
			logger.info("StatSchemeProcessTask.run start to exec.");
			
			// 主线程处理
			mainProcessor();
			
			logger.info("StatSchemeProcessTask.run invoke success.");
			
		} catch(Exception e) {
			logger.error("StatSchemeProcessTask.run found exception...", e);
			boolean isAlive = Thread.currentThread().isAlive();
			if(!isAlive) {
				Thread.currentThread().start();
			}
		}
	}

	/**
	 * 主线程处理
	 */
	private void mainProcessor() {
		
		// 启用状态统计分析计划处理
		statSchemeProcesser(statSchemeId, taskType);
		
		logger.info("StatSchemeProcessTask.mainProcessor invoke succcess.");
	}
	
	/**
	 * 启用状态统计分析计划处理
	 * @param statSchemes
	 */
	private void statSchemeProcesser(Integer statSchemeId, StatSchemeTaskType taskType) {
		
		if(StatSchemeTaskType.Remove.equals(taskType)) {
			StatScheme statScheme = new StatScheme();
			statScheme.setId(statSchemeId);
			
			QtzJobDetail qtzJobDetail = new QtzJobDetail();
			String jobId = "RptDataStatJob-" + statScheme.getId();
			String jobGroup = "ReportDataStat";
			qtzJobDetail.setJobId(jobId);
			qtzJobDetail.setJobGroup(jobGroup);
			
			QtzJobManager qtzJobManager = new QtzJobManager(qtzJobScheduler, qtzJobDetail, null, statSchemeService, statScheme);
			
			// 删除Quartz JOB
			qtzJobManager.removeQtzJob();
			
		} else if(StatSchemeTaskType.AddOrUpdate.equals(taskType)) {
			StatScheme statScheme = statSchemeService.getStatSchemeById(statSchemeId);
			
			// 上报数据类型/统计维度任一为空即为异常任务，不予处理
			if(StringUtils.isBlank(statScheme.getRptDataType()) || StringUtils.isBlank(statScheme.getStatField())) {
				return;
			}
			
			// 统计分析计划类型参数
			Integer statStrategyCode = statScheme.getStatStrategyCode();
			Time statScheduledTime = statScheme.getStatScheduledTime();
			Integer statSnapshotTime = statScheme.getStatSnapshotTime();
			
			QtzJobDetail qtzJobDetail = new QtzJobDetail();
			Job qtzJob = null;
			
			String jobId = "RptDataStatJob-" + statScheme.getId();
			String jobGroup = "ReportDataStat";
			String cronExpression = null;
			
			if(StatStrategy.Scheduled.getValue() == statStrategyCode.intValue()) {
				if(null != statScheduledTime) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(statScheduledTime);
					cronExpression = cal.get(Calendar.SECOND) + " " + cal.get(Calendar.MINUTE) + " " + cal.get(Calendar.HOUR_OF_DAY) + " * * ?";
				}
				
			} else if(StatStrategy.Snapshot.getValue() == statStrategyCode.intValue()) {
				if(null != statSnapshotTime) {
					cronExpression  = "*/" + statSnapshotTime + " * * * * ?";
				}
			}
			
			qtzJobDetail.setJobId(jobId);
			qtzJobDetail.setJobGroup(jobGroup);
			qtzJobDetail.setCronExpression(cronExpression);
			qtzJobDetail.setJobStatus(statScheme.getStatus());
			
			if(StatStrategy.Scheduled.getValue() == statStrategyCode.intValue()) {
				qtzJob = new ScheduledStatJob();
				
			} else if(StatStrategy.Snapshot.getValue() == statStrategyCode.intValue()) {
				qtzJob = new SnapshotStatJob();
			}
			
			if(null != qtzJob && StringUtils.isNotBlank(qtzJobDetail.getCronExpression()) 
					&& null != qtzJobDetail.getJobStatus()) {
				QtzJobManager qtzJobManager = new QtzJobManager(qtzJobScheduler, qtzJobDetail, qtzJob, statSchemeService, statScheme);
				
				// 更新Quartz JOB
				qtzJobManager.addOrUpdateQtzJob();
			}
		}
		
	}
	
}
