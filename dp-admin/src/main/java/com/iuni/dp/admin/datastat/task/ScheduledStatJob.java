package com.iuni.dp.admin.datastat.task;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.iuni.dp.persist.datastat.model.StatScheme;
import com.iuni.dp.service.datastat.constants.PfRptDataStatField;
import com.iuni.dp.service.datastat.constants.PvRptDataStatField;
import com.iuni.dp.service.datastat.constants.RptDataContentType;
import com.iuni.dp.service.datastat.service.StatSchemeService;

/**
 * 定时调度类型统计分析JOB
 * @author CaiKe
 * @version dp-task-1.0.0
 */
public class ScheduledStatJob extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledStatJob.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private StatSchemeService statSchemeService;

	private StatScheme statScheme;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {

		JobDataMap jobDataMap = context.getMergedJobDataMap();
		
		statSchemeService = (StatSchemeService) jobDataMap.get("statSchemeService");

		statScheme = (StatScheme) jobDataMap.get("statScheme");

		// 定时调度类型统计分析处理
		scheduledStatProcess(context);
		
		logger.info("ScheduledStatJob.executeInternal invoke success.");
	}
	
	/**
	 * 定时调度类型统计分析处理
	 */
	private void scheduledStatProcess(JobExecutionContext context) {
		
		logger.info("ScheduledStatJob.scheduledStatProcess start to exec.");
		
		// PF类型上报数据定时调度类型统计分析处理
		scheduledStatPfData();
		
		// PV类型上报数据定时调度类型统计分析处理
		scheduledStatPvData();
		
		// 统计当天的前一天各个广告的各个推广渠道(入口点)的PV、UV、有效订单数、总订单数和退单数
		statMallAdChannelDailyData();
		
	    // 统计金立商城当天之前的所有销售情况
		statMallSaleDailyData();
		
		// 统计GIONEE 相关 APP使用及关联订单数据
		scheduledGnAppUseAndOrderData();
		
		// 统计IUNI商城每日网络流量相关数据
		statIuniNetflowDataDaily();
		
		// 统计IUNI商城每日IP区域统计
		statIuniIpAreaDataDaily();
		
		// 统计IUNI商城每日埋点数据统计
		statIuniBpDataDaily();
		
	    // 统计IUNI商城前一天数据
		statIuniDailyData();
		
		logger.info("ScheduledStatJob.scheduledStatProcess invoke success.");
	}
	
	/**
	 * PF类型上报数据定时调度类型统计分析处理
	 */
	private void scheduledStatPfData() {
		long stime = System.currentTimeMillis();
		
		// 调用PF类型上报数据统计维度responseEnd的定时调用类型统计分析处理逻辑
		if (RptDataContentType.PF.getValue().equals(statScheme.getRptDataType())
				&& PfRptDataStatField.ResponseEnd.getValue().equals(statScheme.getStatField())) {

			Integer schemeId = statScheme.getId();
			Time statscdTime = statScheme.getStatScheduledTime();
			
			if(null == schemeId || null == statscdTime) {
				return;
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("STAT_SCHEME_ID", schemeId);
			params.put("STAT_SOURCE_ID", null);
			
			Calendar cal = Calendar.getInstance();
			params.put("STAT_DATE",dateFormat.format(cal.getTime()));
			// 10：获取上一天的正式统计数据; 11：获取当天的临时统计数据
			params.put("STAT_FLAG", 10);
			params.put("EXEC_STATUS", null);

			statSchemeService.invokeProcStatPfResponse(params);

			logger.info("ScheduledStatJob.scheduledStatPfData invoke success, cost time: {}ms"
					, new Object[]{System.currentTimeMillis() - stime});
		}
	}
	
	/**
	 * PV类型上报数据定时调度类型统计分析处理
	 */
	private void scheduledStatPvData() {
		long stime = System.currentTimeMillis();
		
		// 调用PV类型上报数据统计维度Day即每日的定时调用类型统计分析处理逻辑
		if(RptDataContentType.PV.getValue().equals(statScheme.getRptDataType())
				&& PvRptDataStatField.Day.getValue().equals(statScheme.getStatField())) {
			Integer schemeId = statScheme.getId();
			Time statscdTime = statScheme.getStatScheduledTime();
			
			if(null == schemeId || null == statscdTime) {
				return;
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			Calendar cal = Calendar.getInstance();
			params.put("STAT_DATE",dateFormat.format(cal.getTime()));
			params.put("EXEC_STATUS", null);

			statSchemeService.invokeProcStatDailyMallBasedata(params);

			logger.info("ScheduledStatJob.scheduledStatPvData invoke success, cost time: {}ms"
					, new Object[]{System.currentTimeMillis() - stime});
		}
	}
	
	/**
	 * 统计当天的前一天各个广告的各个推广渠道(入口点)的PV、UV、有效订单数、总订单数和退单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public boolean statMallAdChannelDailyData(){
		long stime = System.currentTimeMillis();
		if(RptDataContentType.MALL_AD_CHANNEL.getValue().equals(statScheme.getRptDataType())
				&& PvRptDataStatField.Day.getValue().equals(statScheme.getStatField())) {
			Integer schemeId = statScheme.getId();
			Time statscdTime = statScheme.getStatScheduledTime();
			
			if(null == schemeId || null == statscdTime) {
				logger.error("ScheduledStatJob.statMallAdChannelDailyData invoke error:schemeId is null or statScheduledTime is null.cost time: {}ms"
						, new Object[]{System.currentTimeMillis() - stime});
				return false;
			}			
			
			Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
	        calendar.add(Calendar.DATE, -1);    //得到前一天
	        String  yestedayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	        String[] arr=yestedayDate.split("-");
	        String month=arr[1];
	        if(StringUtils.isBlank(month)){
				logger.error("ScheduledStatJob.statMallAdChannelDailyData invoke error:month is null. cost time: {}ms"
						, new Object[]{System.currentTimeMillis() - stime});
				return false;
	        }
	        if(month.startsWith("0")){
	        	month=month.substring(1);
	        }
			try {
				Map<String, Object> params = new HashMap<String, Object>();
				statSchemeService.insertMallAdOrderDailyStatByTask(params);//统计前一天订单数据到MALL_AD_ORDER_DAILY_STAT
				statSchemeService.insertMallAdOrderDailyStatFinalByTask(params);//统计22天前订单数据到MALL_AD_ORDER_DAILY_STAT_FINAL
		        params.put("month", month);
				statSchemeService.insertMallAdPvuvDailyStatByTask(params);//统计前一天各个广告的各个推广渠道(入口点)的PV和UV到MALL_AD_PVUV_DAILY_STAT
			} catch (Exception e) {
				logger.error("ScheduledStatJob.statMallAdChannelDailyData invoke error:"+e.getMessage()+", cost time: {}ms"
						, new Object[]{System.currentTimeMillis() - stime});
				return false;
			}
			logger.info("ScheduledStatJob.statMallAdChannelDailyData invoke success, cost time: {}ms"
					, new Object[]{System.currentTimeMillis() - stime});
		}
		return true;
	}
	
	/**
	 * 统计金立商城当天之前的所有销售情况(由于订单状态会发生变化，故全量统计：先清空表数据，再插入。供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public boolean statMallSaleDailyData(){
		long stime = System.currentTimeMillis();
		if(RptDataContentType.MALL_SALE.getValue().equals(statScheme.getRptDataType())
				&& PvRptDataStatField.Day.getValue().equals(statScheme.getStatField())) {
			Integer schemeId = statScheme.getId();
			Time statscdTime = statScheme.getStatScheduledTime();
			
			if(null == schemeId || null == statscdTime) {
				logger.error("ScheduledStatJob.statMallSaleDailyData invoke error:schemeId is null or statScheduledTime is null. cost time: {}ms"
						, new Object[]{System.currentTimeMillis() - stime});
				return false;
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			try {
				statSchemeService.clearMallSaleDailyStatByTask();//先清空表数据
				statSchemeService.insertMallSaleDailyStatByTask(params);//统计前一天订单数据到MALL_SALE_DAILY_STAT
				statSchemeService.clearMallUserOrderDailyStatByTask();//先清空表数据
				statSchemeService.insertMallUserOrderDailyStatByTask(params);//统计前一天订单数据到MALL_SALE_DAILY_STAT
				statSchemeService.clearMallYqfOrderDailyStatByTask();//先清空表数据
				statSchemeService.insertMallYqfOrderDailyStatByTask(params);//统计前一天订单数据到MALL_YQF_ORDER_DAILY_STAT
			} catch (Exception e) {
				logger.error("ScheduledStatJob.statMallSaleDailyData invoke error:"+e.getMessage()+", cost time: {}ms"
						, new Object[]{System.currentTimeMillis() - stime});
				return false;
			}
			logger.info("ScheduledStatJob.statMallSaleDailyData invoke success, cost time: {}ms"
					, new Object[]{System.currentTimeMillis() - stime});
		}
		return true;
	}
	
	/**
	 * 统计GIONEE 相关 APP使用及关联订单数据
	 */
	private void scheduledGnAppUseAndOrderData() {
		long stime = System.currentTimeMillis();
		
		// 调用PV类型上报数据统计维度Day即每日的定时调用类型统计分析处理逻辑
		if(RptDataContentType.GIONEE_APP_USEANDORDER_DATA.getValue().equals(statScheme.getRptDataType())
				&& PvRptDataStatField.Day.getValue().equals(statScheme.getStatField())) {
			Integer schemeId = statScheme.getId();
			Time statscdTime = statScheme.getStatScheduledTime();
			
			if(null == schemeId || null == statscdTime) {
				return;
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			Calendar cal = Calendar.getInstance();
			params.put("STAT_DATE",dateFormat.format(cal.getTime()));
			params.put("EXEC_STATUS", null);

			statSchemeService.invokeProcStatGnAppBaseDataDaily(params);

			logger.info("ScheduledStatJob.scheduledGnAppUseAndOrderData invoke success, cost time: {}ms"
					, new Object[]{System.currentTimeMillis() - stime});
		}
	}
	
	/**
	 * IUNI前端网络流量数据每日统计
	 */
	private void statIuniNetflowDataDaily() {
		long stime = System.currentTimeMillis();
		
		// 调用PV类型上报数据统计维度Day即每日的定时调用类型统计分析处理逻辑
		if(RptDataContentType.IUNI_NETFLOW_DATA_DAILY.getValue().equals(statScheme.getRptDataType())
				&& PvRptDataStatField.Day.getValue().equals(statScheme.getStatField())) {
			Integer schemeId = statScheme.getId();
			Time statscdTime = statScheme.getStatScheduledTime();
			
			if(null == schemeId || null == statscdTime) {
				return;
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			Calendar cal = Calendar.getInstance();
			params.put("STAT_DATE",dateFormat.format(cal.getTime()));
			params.put("EXEC_STATUS", null);

			statSchemeService.invokeProcStatIuniNetflowDataDaily(params);

			logger.info("ScheduledStatJob.statIuniNetflowDataDaily invoke success, cost time: {}ms"
					, new Object[]{System.currentTimeMillis() - stime});
		}
	}
	
	/**
	 * 统计IUNI商城每日IP区域统计
	 */
	private void statIuniIpAreaDataDaily() {
		long stime = System.currentTimeMillis();
		
		// 调用PV类型上报数据统计维度Day即每日的定时调用类型统计分析处理逻辑
		if(RptDataContentType.IUNI_IPAREA_DATA_DAILY.getValue().equals(statScheme.getRptDataType())
				&& PvRptDataStatField.Day.getValue().equals(statScheme.getStatField())) {
			Integer schemeId = statScheme.getId();
			Time statscdTime = statScheme.getStatScheduledTime();
			
			if(null == schemeId || null == statscdTime) {
				return;
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			Calendar cal = Calendar.getInstance();
			params.put("STAT_DATE",dateFormat.format(cal.getTime()));
			params.put("EXEC_STATUS", null);

			statSchemeService.invokeProcStatIpAreaDataDaily(params);

			logger.info("ScheduledStatJob.statIuniIpAreaDataDaily invoke success, cost time: {}ms"
					, new Object[]{System.currentTimeMillis() - stime});
		}
	}
	
	/**
	 * 统计IUNI商城每日埋点数据统计
	 */
	private void statIuniBpDataDaily() {
		long stime = System.currentTimeMillis();
		
		// 调用PV类型上报数据统计维度Day即每日的定时调用类型统计分析处理逻辑
		if(RptDataContentType.IUNI_BP_DATA_DAILY.getValue().equals(statScheme.getRptDataType())
				&& PvRptDataStatField.Day.getValue().equals(statScheme.getStatField())) {
			Integer schemeId = statScheme.getId();
			Time statscdTime = statScheme.getStatScheduledTime();
			
			if(null == schemeId || null == statscdTime) {
				return;
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			Calendar cal = Calendar.getInstance();
			params.put("STAT_DATE",dateFormat.format(cal.getTime()));
			params.put("EXEC_STATUS", null);

			statSchemeService.invokeProcStatIuniBpDataDaily(params);

			logger.info("ScheduledStatJob.statIuniBpDataDaily invoke success, cost time: {}ms"
					, new Object[]{System.currentTimeMillis() - stime});
		}
	}
	
	/**
	 * 统计IUNI商城前一天的数据
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	private boolean statIuniDailyData(){
		long stime = System.currentTimeMillis();
		if(RptDataContentType.IUNI_DAILY_DATA.getValue().equals(statScheme.getRptDataType())
				&& PvRptDataStatField.Day.getValue().equals(statScheme.getStatField())) {
			Integer schemeId = statScheme.getId();
			Time statscdTime = statScheme.getStatScheduledTime();
			
			if(null == schemeId || null == statscdTime) {
				logger.error("ScheduledStatJob.statIuniDailyData invoke error:schemeId is null or statScheduledTime is null. cost time: {}ms"
						, new Object[]{System.currentTimeMillis() - stime});
				return false;
			}
			Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
	        calendar.add(Calendar.DATE, -1);    //得到前一天
	        String  yestedayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	        String[] arr=yestedayDate.split("-");
	        String month=arr[1];
	        if(StringUtils.isBlank(month)){
				logger.error("ScheduledStatJob.statIuniDailyData invoke error:month is null. cost time: {}ms"
						, new Object[]{System.currentTimeMillis() - stime});
				return false;
	        }
	        if(month.startsWith("0")){
	        	month=month.substring(1);
	        }
			Map<String, Object> params = new HashMap<String, Object>();
			try {
				//统计IUNI商城当天之前的所有销售情况(由于订单状态会发生变化，故全量统计：先清空表数据，再插入。供定时任务调用)
				statSchemeService.clearIuniSaleDailyStatByTask();//先清空表数据
				statSchemeService.insertIuniSaleDailyStatByTask(params);//统计前一天订单数据到Iuni_SALE_DAILY_STAT
				//统计IUNI商城前一天用户注册情况到Iuni_UESR_REG_DAILY_STAT
				statSchemeService.insertIuniUserRegDailyStatByTask(params);
				//统计IUNI商城前一天PV、UV到Iuni_PAGE_PVUV_DAILY_STAT
				 params.put("month", month);
				statSchemeService.insertIuniPagePvuvDailyStatByTask(params);
			} catch (Exception e) {
				logger.error("ScheduledStatJob.statIuniDailyData invoke error:"+e.getMessage()+", cost time: {}ms"
						, new Object[]{System.currentTimeMillis() - stime});
				return false;
			}
			logger.info("ScheduledStatJob.statIuniDailyData invoke success, cost time: {}ms"
					, new Object[]{System.currentTimeMillis() - stime});
		}
		return true;
	}
	
}
