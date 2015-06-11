package com.iuni.dp.admin.datastat.task;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.iuni.dp.persist.datastat.model.StatScheme;
import com.iuni.dp.service.datastat.constants.PfRptDataStatField;
import com.iuni.dp.service.datastat.constants.RptDataContentType;
import com.iuni.dp.service.datastat.service.StatSchemeService;

/**
 * 间隔快照类型统计分析JOB
 * @author CaiKe
 * @version dp-task-1.0.0
 */
public class SnapshotStatJob extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(SnapshotStatJob.class);
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private StatSchemeService statSchemeService;
	
	private StatScheme statScheme;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		JobDataMap jobDataMap = context.getMergedJobDataMap();
		
		statSchemeService = (StatSchemeService) jobDataMap.get("statSchemeService");

		statScheme = (StatScheme) jobDataMap.get("statScheme");
		
		// 间隔快照类型统计分析处理
		snapshotStatProcess(context);
		logger.debug("SnapshotStatJob.executeInternal invoke success.");
	}
	
	/**
	 * 间隔快照类型统计分析处理
	 */
	private void snapshotStatProcess(JobExecutionContext context) {
		
		logger.debug("SnapshotStatJob.snapshotStatProcess start to exec.");
		
		// PF上报数据类型间隔快照类型统计分析处理
		snapshotStatPFData(context);
		
		logger.debug("SnapshotStatJob.snapshotStatProcess invoke success.");
	}
	
	/**
	 * PF上报数据类型间隔快照类型统计分析处理
	 */
	private void snapshotStatPFData(JobExecutionContext context) {
		long stime = System.currentTimeMillis();
		
		// 调用Pf类型上报数据统计维度domContentLoadedEventEnd的间隔快照类型统计分析处理逻辑
		if(RptDataContentType.PF.getValue().equals(statScheme.getRptDataType()) 
				&& PfRptDataStatField.DomCtxLoadedEnd.getValue().equals(statScheme.getStatField())) {
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("STAT_SCHEME_ID", statScheme.getId());
			params.put("INTERVAL_TIME", statScheme.getStatSnapshotTime());
			params.put("STAT_DATE", dateFormat.format(context.getFireTime()));
			params.put("EXEC_STATUS", null);
			
			statSchemeService.invokeProcStatPfDomCtxLoaded(params);
			
			logger.debug("SnapshotStatJob.snapshotStatProcess invoke success, cost time: {}ms"
					, new Object[]{System.currentTimeMillis() - stime});
		}
	}
}
