package com.iuni.dp.admin.datastat.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.GnAppImeiLogService;

/**
 * IUNI OS IMEI Log Action
 * @author CaiKe
 * @version dp-admin-V1.0.2
 */
@Controller("iuniOSImeiLogAction")
@Scope("prototype")
public class IuniOSImeiLogAction extends BaseAction {

	private static final long serialVersionUID = 1112958629258188862L;

	private final Logger logger = LoggerFactory.getLogger(IuniOSImeiLogAction.class);
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private GnAppImeiLogService gnAppImeiLogService;
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	/**
	 * IUNI OS留存用户(留存用户数、留存用户占比)按日统计列表
	 * @return String
	 */
	public String iuniosRemainUserOnDayStatView() {
		List<Map<String, Object>> remainUserStatList = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("iuniosRemainUserOnDayStat");
			
			if(null == params.get("remainDays")) {
				logger.error("IuniOSImeiLogAction.iuniosRemainUserOnDayStatView found Parameter Error.");
				return ERROR;
			}
			
			Integer totalRecord = gnAppImeiLogService.queryIuniOSRemainUserOnDayCount(params);
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			remainUserStatList = gnAppImeiLogService.queryIuniOSRemainUserOnDayByPage(params);
			
			request.setAttribute("remainUserStatList", remainUserStatList);
			
		} catch (DBException dbex) {
			logger.error("IuniOSImeiLogAction.iuniosRemainUserOnDayStatView found DBException", dbex);
			return ERROR;
		}catch (Exception ex) {
			logger.error("IuniOSImeiLogAction.iuniosRemainUserOnDayStatView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI OS留存用户(留存用户数、留存用户占比)按日统计列表导出至Excel
	 * @return String
	 */
	public String iuniosRemainUserOnDayStat2Excel() {
		List<Map<String, Object>> remainUserStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("iuniosRemainUserOnDayStat");
			remainUserStatList = gnAppImeiLogService.queryIuniOSRemainUserOnDayByExample(params);
			// 生成导出数据列名列表
			columnNames = genIuniosRemainUserOnDayStatColNames();
			// 生成导出数据列名变量列表
			columns = genIuniosRemainUserOnDayStatCols();
			
			if(CollectionUtils.isNotEmpty(remainUserStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "";
				Integer remainDays = (Integer) params.get("remainDays");
				if(remainDays == 1) {
					sheetName = "IUNI OS 每日留存用户统计";
				} else if(remainDays == 7) {
					sheetName = "IUNI OS 7日留存用户统计";
				} else if(remainDays == 30) {
					sheetName = "IUNI OS 30日留存用户统计";
				} else {
					sheetName = "IUNI OS 留存用户统计";
				}
				String exportDate = dateFormat.format(new Date());
				fileName = sheetName + "_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, remainUserStatList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniOSImeiLogAction.iuniosRemainUserOnDayStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniOSImeiLogAction.iuniosRemainUserOnDayStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniOSImeiLogAction.iuniosRemainUserOnDayStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniOSImeiLogAction.iuniosRemainUserOnDayStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * 按JSON格式获取GIONEE APP客户端渠道活跃用户统计报表FusionChart参数
	 */
	public void getiuniosRemainUserOnDayStatByJSON() {
		List<Map<String, Object>> remainUserStatList = null;
		Map<String, List<Map<String, Object>>> chartData = null;
		Map<String, Object> params = null;
		
		params = genParamMap("iuniosRemainUserOnDayStat");
		remainUserStatList = gnAppImeiLogService.queryIuniOSRemainUserOnDayByExample(params);
		chartData = gnAppImeiLogService.queryIuniOSRemainUserOnDay4Chart(remainUserStatList, params);
		
		FusionChart fusionChart = new FusionChart();
		FusionChartConfig fusionChartConfig = new FusionChartConfig();
		List<FusionChartCategory> categories = new ArrayList<FusionChartCategory>();
		List<FusionChartDataset> datasets = new ArrayList<FusionChartDataset>();
		fusionChart.setChart(fusionChartConfig);
		fusionChart.setCategories(categories);
		fusionChart.setDataset(datasets);
		
		String caption = "IUNI OS留存用户统计趋势图";
		String subcaption = "Source: IUNI Electrical Business Operation Center.";
		String xAxisName = "统计日期";
		String yAxisName = "留存用户率";
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
		fusionChartConfig.setNumbersuffix("%");
		fusionChartConfig.setDecimalPrecision("1");
//		fusionChartConfig.setDivintervalhints("0,1000,2000,5000,10000");
		fusionChartConfig.setDataseparator("|");
		
		if(MapUtils.isNotEmpty(chartData)) {
			String statDate = "";
			String remainUserRate = "0";
			
			// category for activeUser
			FusionChartCategory category = new FusionChartCategory();
			categories.add(category);
			String cgValue = "";
			
			int chartDataCount = 0;
			for(Entry<String,List<Map<String,Object>>> entry : chartData.entrySet()) {
				String itemName = entry.getKey();
				List<Map<String, Object>> itemData = entry.getValue();
				++chartDataCount;
				
				FusionChartDataset dataset = new FusionChartDataset();
				dataset.setSeriesname(itemName);
				datasets.add(dataset);
				String data = "";
				
				for(int i = 0; i < itemData.size(); i++) {
					Map<String, Object> channelMap = itemData.get(i);
					statDate = (String) channelMap.get("statDate");
					remainUserRate = ((BigDecimal)channelMap.get("remainUserRate")).toString();
					
					if(i < itemData.size() - 1) {
						if(chartDataCount == 1) {
							cgValue += statDate + "|";
						}
						data += remainUserRate + "|";
					} else {
						if(chartDataCount == 1) {
							cgValue += statDate;
						}
						data += remainUserRate;
					}
				}
				
				dataset.setData(data);
			}
			
			category.setCategory(cgValue);
		}
		
		printJson2(fusionChart, Boolean.TRUE, Boolean.FALSE);
		
		logger.debug("GnAppAccessLogAction.getGnAppActiveUserOfChannelStatByJSON invoke success.");
	}
	
	/**
	 * 生成查询参数Map
	 * @return Map<String, Object>
	 */
	private Map<String, Object> genParamMap(String condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		if("default".equals(flag)) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-1);
			String eDate = dateFormat.format(cal.getTime()); 
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-6);
			String sDate = dateFormat.format(cal.getTime());
			params.put("beginDate", sDate);
			params.put("endDate", eDate);
			
			// 设置日期过滤框默认值
			statParams.put("beginDate", sDate);
			statParams.put("endDate", eDate);
			
			// 设置APP应用默认值
			statParams.put("channelName", "IUNI OS");
			
			// 设置留存类型默认值
			statParams.put("remainType", "1DayRemainUser");
		}
		
		if("iuniosRemainUserOnDayStat".equals(condition)) {
			String remainType = statParams.get("remainType");
			if("1DayRemainUser".equals(remainType)) {
				params.put("remainDays", 1);
			} else if("7DaysRemainUser".equals(remainType)) {
				params.put("remainDays", 7);
			} else if("30DaysRemainUser".equals(remainType)) {
				params.put("remainDays", 30);
			}
		}
		
		if(StringUtils.isNotBlank(statParams.get("channelName"))) {
			params.put("channelName", statParams.get("channelName"));
		}
		
		if(StringUtils.isNotBlank(statParams.get("appName"))) {
			params.put("appName", statParams.get("appName"));
		}
		
		if(StringUtils.isNotBlank(statParams.get("appNames"))) {
			String appNames = statParams.get("appNames");
			String[] appNamesDetail = appNames.split(",");
			if(ArrayUtils.isNotEmpty(appNamesDetail)) {
				params.put("appNames", appNamesDetail);
			}
		}
		
		if(StringUtils.isNotBlank(statParams.get("apkVersions"))) {
			String apkVersions = statParams.get("apkVersions");
			String[] apkVersionNames = apkVersions.split(",");
			if(ArrayUtils.isNotEmpty(apkVersionNames)) {
				params.put("apkVersions", apkVersionNames);
			}
		}
		
		if(StringUtils.isNotBlank(statParams.get("beginDate"))) {
			params.put("beginDate", statParams.get("beginDate"));			
		}
		if(StringUtils.isNotBlank(statParams.get("endDate"))) {
			params.put("endDate", statParams.get("endDate"));
		}
		
		return params;
	}
	
	/**
	 * 新增Page至参数Map
	 * @param Map<String, Object> params
	 */
	private void setPageInfo2Map(Page page, Map<String, Object> params) {
		params.put("startRec", page.getStartRec());
		params.put("endRec", page.getEndRec());
	}
	
	/**
	 * 生成IUNI OS留存用户(留存用户数、留存用户占比)按日统计列表导出数据列名变量列表
	 * @return
	 */
	private List<String> genIuniosRemainUserOnDayStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("statDate");
		cols.add("newUserNum");
		cols.add("remainUserNum");
		cols.add("remainUserRate");
		
		return cols;
	}
	
	/**
	 * 生成IUNI OS留存用户(留存用户数、留存用户占比)按日统计列表导出数据列名列表
	 * @return
	 */
	private List<String> genIuniosRemainUserOnDayStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计时间");
		colNames.add("当日新增用户数");
		colNames.add("留存用户数");
		colNames.add("留存率");
		
		return colNames;
	}

	public Map<String, String> getStatParams() {
		return statParams;
	}

	public void setStatParams(Map<String, String> statParams) {
		this.statParams = statParams;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
