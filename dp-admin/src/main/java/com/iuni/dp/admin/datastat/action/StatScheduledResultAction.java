package com.iuni.dp.admin.datastat.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.admin.datastat.bean.FusionChart;
import com.iuni.dp.admin.datastat.bean.FusionChartConfig;
import com.iuni.dp.admin.datastat.bean.FusionChartData;
import com.iuni.dp.admin.datastat.utils.StatUtils;
import com.iuni.dp.admin.datastat.vo.StatScheduledResultVo;
import com.iuni.dp.persist.datastat.model.StatScheduledResult;
import com.iuni.dp.service.datastat.service.StatScheduledResultService;

/**
 * 定时统计类数据统计结果Action
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
@Controller("statScheduledResultAction")
@Scope("prototype")
public class StatScheduledResultAction extends BaseAction {

	private static final long serialVersionUID = 5443148483461723784L;

	private static final Logger logger = LoggerFactory.getLogger(StatScheduledResultAction.class);
	
	@Autowired
	private StatScheduledResultService statScheduledResultService;
	
	private StatScheduledResultVo statScheduledResultVo;
	
	public StatScheduledResultVo getStatScheduledResultVo() {
		return statScheduledResultVo;
	}

	public void setStatScheduledResultVo(StatScheduledResultVo statScheduledResultVo) {
		this.statScheduledResultVo = statScheduledResultVo;
	}

	/**
	 * 定时调度类型统计分析结果列表视图
	 * @return String
	 */
	public String scheduledResultListView() {		
		return SUCCESS;
	}
	
	/**
	 * 按JSON格式获取定时调度类型统计分析结果
	 */
	public void getScheduledResultByJSON() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(null != statScheduledResultVo) {
			String statSchemeId = statScheduledResultVo.getStatSchemeId();
			if(StringUtils.isNotBlank(statSchemeId)) {
				params.put("statSchemeId", statSchemeId);
			}
			String sourceId = statScheduledResultVo.getSourceId();
			if(StringUtils.isNotBlank(sourceId)) {
				params.put("sourceId", sourceId);
			}
			String scheduledTime = statScheduledResultVo.getScheduledTime();
			if(StringUtils.isNotBlank(sourceId)) {
				params.put("scheduledTime", scheduledTime);
			}
		}
		
		// 固定时间统一调度统计分析结果条件查询
		List<StatScheduledResult> statScheduledResults = statScheduledResultService.fetchStatScheduledResultByCondition(params);
		
		printJson2(statScheduledResults, Boolean.TRUE, Boolean.TRUE);
		
		logger.debug("StatScheduledResultAction.getScheduledResultByJSON invoke success.");
	}
	
	/**
	 * 按JSON格式获取定时调度类型统计分析报表FusionChart参数
	 */
	public void getScheduledReportByJSON() {
		
		if(null != statScheduledResultVo) {
			Map<String, Object> params = new HashMap<String, Object>();
			String statSchemeId = statScheduledResultVo.getStatSchemeId();
			if(StringUtils.isNotBlank(statSchemeId)) {
				params.put("statSchemeId", statSchemeId);
			}
			String sourceId = statScheduledResultVo.getSourceId();
			if(StringUtils.isNotBlank(sourceId)) {
				params.put("sourceId", sourceId);
			}
			String scheduledTime = statScheduledResultVo.getScheduledTime();
			if(StringUtils.isNotBlank(scheduledTime)) {
				params.put("scheduledTime", scheduledTime);
			}
			
			// 固定时间统一调度统计分析结果条件查询
			List<StatScheduledResult> statScheduledResults = statScheduledResultService.fetchStatScheduledResultByCondition(params);
			
			FusionChart fusionChart = new FusionChart();
			FusionChartConfig fusionChartConfig = new FusionChartConfig();
			List<FusionChartData> fusionChartDatas = new ArrayList<FusionChartData>();
			fusionChart.setChart(fusionChartConfig);
			fusionChart.setData(fusionChartDatas);
			
			String caption = "上报数据定时调度类型统计分析报表";
			String subcaption = "";
			if(StringUtils.isNotBlank(scheduledTime)) {
				subcaption += "Daily from " + scheduledTime + " .Source: Gionee Electrical Business Operation Center.";
			} else {
				subcaption += "Source: Gionee Electrical Business Operation Center.";
			}
			String xAxisName = "时间范围(s)";
			String yAxisName = "上报数据数量";
			
			fusionChartConfig.setCaption(caption);
			fusionChartConfig.setSubcaption(subcaption);
			fusionChartConfig.setXAxisName(xAxisName);
			fusionChartConfig.setYAxisName(yAxisName);
			fusionChartConfig.setFormatnumberscale("0");
			
			if(null != statScheduledResults && statScheduledResults.size() > 0) {
				for(StatScheduledResult statScheduledResult : statScheduledResults) {
					String statType = StatUtils.convertStatType4PfResponse(statScheduledResult.getStatType());
					String statNum = String.valueOf(statScheduledResult.getStatNum());
					
					FusionChartData fusionChartData = new FusionChartData();
					fusionChartData.setLabel(statType);
					fusionChartData.setValue(statNum);
					
					fusionChartDatas.add(fusionChartData);
				}
			}
			
			printJson2(fusionChart, Boolean.TRUE, Boolean.FALSE);
			
			logger.debug("StatScheduledResultAction.getScheduledReportByJSON invoke success.");
		}
		
	}
	
}
