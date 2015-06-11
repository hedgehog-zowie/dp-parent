package com.iuni.dp.admin.datastat.action;

import java.text.SimpleDateFormat;
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
import com.iuni.dp.admin.datastat.bean.FusionChartCategory;
import com.iuni.dp.admin.datastat.bean.FusionChartConfig;
import com.iuni.dp.admin.datastat.bean.FusionChartDataset;
import com.iuni.dp.admin.datastat.vo.StatSnapshotResultVo;
import com.iuni.dp.persist.datastat.model.StatSnapshotResult;
import com.iuni.dp.service.datastat.service.StatSnapshotResultService;

/**
 * 快照类数据统计结果Action
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
@Controller("statSnapshotResultAction")
@Scope("prototype")
public class StatSnapshotResultAction extends BaseAction {

	private static final long serialVersionUID = -6170131946564502599L;

	private static final Logger logger = LoggerFactory.getLogger(StatSnapshotResultAction.class);
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
	private StatSnapshotResultService statSnapshotResultService;
	
	private StatSnapshotResultVo statSnapshotResultVo;
	
	public StatSnapshotResultVo getStatSnapshotResultVo() {
		return statSnapshotResultVo;
	}

	public void setStatSnapshotResultVo(StatSnapshotResultVo statSnapshotResultVo) {
		this.statSnapshotResultVo = statSnapshotResultVo;
	}

	/**
	 * 间隔快照类型统计分析结果列表视图
	 * @return String
	 */
	public String snapshotResultListView() {
		return SUCCESS;
	}
	
	/**
	 * 按JSON格式获取间隔快照类型统计分析结果
	 */
	public void getSnapshotResultByJSON() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(null != statSnapshotResultVo) {
			String statSchemeId = statSnapshotResultVo.getStatSchemeId();
			if(StringUtils.isNotBlank(statSchemeId)) {
				params.put("statSchemeId", statSchemeId);
			}
			String sourceId = statSnapshotResultVo.getSourceId();
			if(StringUtils.isNotBlank(sourceId)) {
				params.put("sourceId", sourceId);
			}
			String snapshotTime = statSnapshotResultVo.getSnapshotTime();
			if(StringUtils.isNotBlank(sourceId)) {
				params.put("snapshotTime", snapshotTime);
			}
		}
		
		// 固定时间统一调度统计分析结果条件查询
		List<StatSnapshotResult> statSnapshotResults = statSnapshotResultService.getAllStatSnapshotResult(params);
		
		printJson2(statSnapshotResults, Boolean.TRUE, Boolean.TRUE);
		
		logger.debug("StatSnapshotResultAction.getSnapshotResultByJSON invoke success.");
	}
	
	/**
	 * 按JSON格式获取定时调度类型统计分析报表FusionChart参数
	 */
	public void getSnapshotReportByJSON() {
		
		if(null != statSnapshotResultVo) {
			Map<String, Object> params = new HashMap<String, Object>();
			String statSchemeId = statSnapshotResultVo.getStatSchemeId();
			if(StringUtils.isNotBlank(statSchemeId)) {
				params.put("statSchemeId", statSchemeId);
			}
			String sourceId = statSnapshotResultVo.getSourceId();
			if(StringUtils.isNotBlank(sourceId)) {
				params.put("sourceId", sourceId);
			}
			String snapshotTime = statSnapshotResultVo.getSnapshotTime();
			if(StringUtils.isNotBlank(snapshotTime)) {
				params.put("snapshotTime", snapshotTime);
			}
			
			// 固定时间统一调度统计分析结果条件查询
			List<StatSnapshotResult> statSnapshotResults = statSnapshotResultService.getAllStatSnapshotResult(params);
			
			FusionChart fusionChart = new FusionChart();
			FusionChartConfig fusionChartConfig = new FusionChartConfig();
//			List<FusionChartData> fusionChartDatas = new ArrayList<FusionChartData>();
			List<FusionChartCategory> categories = new ArrayList<FusionChartCategory>();
			List<FusionChartDataset> datasets = new ArrayList<FusionChartDataset>();
			fusionChart.setChart(fusionChartConfig);
//			fusionChart.setData(fusionChartDatas);
			fusionChart.setCategories(categories);
			fusionChart.setDataset(datasets);
			
			String caption = "上报数据间隔快照类型统计分析报表";
			String subcaption = "";
			if(StringUtils.isNotBlank(snapshotTime)) {
				subcaption += "Daily from " + snapshotTime + " . Source: Gionee Electrical Business Operation Center.";
			} else {
				subcaption += "Source: Gionee Electrical Business Operation Center.";
			}
			String xAxisName = "快照时间";
			String yAxisName = "统计平均时间(ms)";
			String labelDisplay = "ROTATE";
			
			fusionChartConfig.setCaption(caption);
			fusionChartConfig.setSubcaption(subcaption);
			fusionChartConfig.setXAxisName(xAxisName);
			fusionChartConfig.setYAxisName(yAxisName);
			fusionChartConfig.setFormatnumberscale("0");
			fusionChartConfig.setLabelDisplay(labelDisplay);
			fusionChartConfig.setSlantlabels("0");
			fusionChartConfig.setAnimation("1");
			fusionChartConfig.setCompactdatamode("1");
			fusionChartConfig.setEnableiconmousecursors("0");
			fusionChartConfig.setPixelsperpoint("10");
			fusionChartConfig.setAnchorminrenderdistance("20");
			fusionChartConfig.setPalette("3");
			fusionChartConfig.setDivintervalhints("0, 600, 1200, 1800");
			fusionChartConfig.setDataseparator("|");
			
			if(null != statSnapshotResults && statSnapshotResults.size() > 0) {
				String snapshotTimeStr = "";
				String statAvgTime = "";
				
				// category
				FusionChartCategory category = new FusionChartCategory();
				String categoryStr = "";
				categories.add(category);
				
				// dataset
				FusionChartDataset dataset = new FusionChartDataset();
				String seriesname = "Close";
				String data = "";
				dataset.setSeriesname(seriesname);
				datasets.add(dataset);
				
//				for(StatSnapshotResult statSnapshotResult : statSnapshotResults) {
//					snapshotTimeStr = dateFormat.format(statSnapshotResult.getSnapshotTime());
//					statAvgTime = String.valueOf(statSnapshotResult.getStatAvgTime());
//					
//					FusionChartData fusionChartData = new FusionChartData();
//					fusionChartData.setLabel(snapshotTimeStr);
//					fusionChartData.setValue(statAvgTime);
//					fusionChartDatas.add(fusionChartData);
//				}
				
				for(int i = 0; i < statSnapshotResults.size(); i++) {
					StatSnapshotResult statSnapshotResult = statSnapshotResults.get(i);
					snapshotTimeStr = dateFormat.format(statSnapshotResult.getSnapshotTime());
					statAvgTime = String.valueOf(statSnapshotResult.getStatAvgTime());
					
					if(i < statSnapshotResults.size() - 1) {
						categoryStr += snapshotTimeStr + "|";
						data += statAvgTime + "|";
					} else {
						categoryStr += snapshotTimeStr;
						data += statAvgTime;
					}
				}
				category.setCategory(categoryStr);
				dataset.setData(data);
			}
			
			printJson2(fusionChart, Boolean.TRUE, Boolean.FALSE);
			
			logger.debug("StatSnapshotResultAction.getSnapshotReportByJSON invoke success.");
		}
		
	}
	
}
