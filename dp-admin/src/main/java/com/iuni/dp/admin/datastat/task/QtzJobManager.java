package com.iuni.dp.admin.datastat.task;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iuni.dp.persist.datastat.model.StatScheme;
import com.iuni.dp.service.datastat.service.StatSchemeService;

/**
 * Quartz JOB Manager
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class QtzJobManager {

	private static final Logger logger = LoggerFactory.getLogger(QtzJobManager.class);
	
	private Scheduler scheduler;
	
	private QtzJobDetail qtzJobDetail;
	
	private Job qtzJob;
	
	private StatSchemeService statSchemeService;
	
	private StatScheme statScheme;
	
	public QtzJobManager(Scheduler scheduler, QtzJobDetail qtzJobDetail,
			Job qtzJob, StatSchemeService statSchemeService,
			StatScheme statScheme) {
		super();
		this.scheduler = scheduler;
		this.qtzJobDetail = qtzJobDetail;
		this.qtzJob = qtzJob;
		this.statSchemeService = statSchemeService;
		this.statScheme = statScheme;
	}

	/**
	 * 更新Quartz JOB
	 */
	@SuppressWarnings({"rawtypes"})
	protected void addOrUpdateQtzJob() {
		String jobId = qtzJobDetail.getJobId();
		String jobGroup = qtzJobDetail.getJobGroup();
		String cronExp = qtzJobDetail.getCronExpression();
		String triggerName = qtzJobDetail.getTriggerName();
		Integer jobStatus = qtzJobDetail.getJobStatus();
		Class<? extends Job> jobClass = qtzJob.getClass();
		
		try {
			CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerName, jobGroup);
			//添加Job
			if(null == cronTrigger) {
				if(jobStatus == QtzJobDetail.JS_ENABLED) {
					JobDetail jobDetail = new JobDetail(jobId, jobGroup, jobClass);
					jobDetail.getJobDataMap().put("targetObjectId", jobId);
					jobDetail.getJobDataMap().put("statSchemeService", statSchemeService);
					jobDetail.getJobDataMap().put("statScheme", statScheme);
					cronTrigger = new CronTrigger(triggerName, jobGroup, cronExp);
					scheduler.scheduleJob(jobDetail, cronTrigger);
				}
			} 
			//更新Job
			else {
				String curCronExp = cronTrigger.getCronExpression();
				JobDetail curJobDetail = scheduler.getJobDetail(jobId, jobGroup);
				Class curJobClass = curJobDetail.getJobClass();
				if(jobStatus == QtzJobDetail.JS_ENABLED) {
					if(curJobClass.equals(jobClass)) {
						//更新scheme
						if(!curCronExp.equals(cronExp)) {
							cronTrigger.setCronExpression(qtzJobDetail.getCronExpression());
							curJobDetail.getJobDataMap().put("targetObjectId", jobId);
							curJobDetail.getJobDataMap().put("statSchemeService", statSchemeService);
							curJobDetail.getJobDataMap().put("statScheme", statScheme);
							//更新scheme,则重新调度
							scheduler.rescheduleJob(cronTrigger.getName(), cronTrigger.getGroup(), cronTrigger);
						}
					} else {
						//调度类型变更(如由定时调度变为间隔调度)，则先删除已有调度
						scheduler.deleteJob(jobId, jobGroup);
						
						JobDetail jobDetail = new JobDetail(jobId, jobGroup, jobClass);
						jobDetail.getJobDataMap().put("targetObjectId", jobId);
						jobDetail.getJobDataMap().put("statSchemeService", statSchemeService);
						jobDetail.getJobDataMap().put("statScheme", statScheme);
						cronTrigger = new CronTrigger(triggerName, jobGroup, cronExp);
						//再调度
						scheduler.scheduleJob(jobDetail, cronTrigger);
					}
				} else if(jobStatus == QtzJobDetail.JS_DISABLED) {
					//禁用的话，则删除调度
					scheduler.deleteJob(jobId, jobGroup);
				}
			}
		} catch (SchedulerException e) {
			logger.error("QtzJobManager.enabled found SchedulerException.", e);
		} catch (ParseException e) {
			logger.error("QtzJobManager.enabled found ParseException.", e);
		}
	}
	
	/**
	 * 删除Quartz JOB
	 */
	protected void removeQtzJob() {
		String jobId = qtzJobDetail.getJobId();
		String jobGroup = qtzJobDetail.getJobGroup();
		String triggerName = qtzJobDetail.getTriggerName();
		
		try {
			CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerName, jobGroup);
			if(null != cronTrigger) {
				scheduler.deleteJob(jobId, jobGroup);
			}
			
		} catch (SchedulerException e) {
			logger.error("QtzJobManager.disabled found SchedulerException.", e);
		}
	}
	
}
