package com.iuni.dp.admin.datastat.task;

import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iuni.dp.admin.datastat.constants.StatStrategy;
import com.iuni.dp.persist.datastat.model.StatScheme;
import com.iuni.dp.service.datastat.service.StatSchemeService;

/**
 * 应用启动时统计分析计划扫描处理线程(scheme扫描)
 * @author CaiKe
 * @version dp-task-1.0.0
 */
public class StatSchemeScanTask implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(StatSchemeScanTask.class);
	
	private Scheduler qtzJobScheduler;
	private StatSchemeService statSchemeService;
	
	public StatSchemeScanTask(Scheduler qtzJobScheduler,
			StatSchemeService statSchemeService) {
		super();
		this.qtzJobScheduler = qtzJobScheduler;
		this.statSchemeService = statSchemeService;
	}

	@Override
	public void run() {
		try {
			logger.info("StatSchemeScanTask.run start to exec.");
			
			// 主线程处理
			mainProcessor();
			
			logger.info("StatSchemeScanTask.run invoke success.");
			
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
		
		// 扫描应用启动时已存在的统计分析计划并进行处理
		scanExistStatScheme();
		
		logger.info("StatSchemeScanTask.mainProcessor invoke succcess.");
	}
	
	/**
	 * 扫描应用启动时已存在的统计分析计划并进行处理
	 */
	private void scanExistStatScheme() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", 1);
		List<StatScheme> statSchemes = statSchemeService.getAllStatScheme(params);
		
		for(StatScheme statScheme : statSchemes) {
			if(null != statScheme) {
				statSchemeProcesser(statScheme);
			}
		}
		
		logger.info("StatSchemeScanTask.scanExistStatScheme invoke success.");
	}
	
	/**
	 * 启用状态统计分析计划处理
	 * @param statSchemes
	 */
	private void statSchemeProcesser(StatScheme statScheme) {
		
		logger.debug("StatSchemeScanTask.statSchemeProcesser start to process statScheme, StatScheme: {}"
				, new Object[]{statScheme});
		long stime = System.currentTimeMillis();
		
		// 上报数据类型/统计维度任一为空即为异常任务，不予处理
		if(StringUtils.isBlank(statScheme.getRptDataType()) || StringUtils.isBlank(statScheme.getStatField())) {
			return;
		}
		
		// 统计分析计划类型参数(11:定时；12:间隔)
		Integer statStrategyCode = statScheme.getStatStrategyCode();
		Time statScheduledTime = statScheme.getStatScheduledTime();
		Integer statSnapshotTime = statScheme.getStatSnapshotTime();
		
		QtzJobDetail qtzJobDetail = new QtzJobDetail();
		Job qtzJob = null;
		
		String jobId = "RptDataStatJob-" + statScheme.getId();
		String jobGroup = "ReportDataStat";//报表统计组
		String cronExpression = null;
		
		//定时统计
		if(StatStrategy.Scheduled.getValue() == statStrategyCode.intValue()) {
			if(null != statScheduledTime) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(statScheduledTime);
				cronExpression = cal.get(Calendar.SECOND) + " " + cal.get(Calendar.MINUTE) + " " + cal.get(Calendar.HOUR_OF_DAY) + " * * ?";
			}
			
		} 
		//间隔统计
		else if(StatStrategy.Snapshot.getValue() == statStrategyCode.intValue()) {
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
			
			// 添加Quartz JOB
			qtzJobManager.addOrUpdateQtzJob();
			
			logger.debug("StatSchemeScanTask.statSchemeProcesser have processed a normal statScheme, cost time: {}ms"
					, new Object[]{System.currentTimeMillis() - stime});
		} else {
			
			logger.debug("StatSchemeScanTask.statSchemeProcesser have processed a invalid statScheme, cost time: {}ms"
					, new Object[]{System.currentTimeMillis() - stime});
		}
		
	}

}
