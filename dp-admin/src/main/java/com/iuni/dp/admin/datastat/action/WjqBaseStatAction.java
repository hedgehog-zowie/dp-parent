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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
import com.iuni.dp.service.datastat.service.MallPvuvDailyStatService;

/**
 * 玩机圈日常基础数据每日统计Action
 * 
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
@Controller("wjqBaseStatAction")
@Scope("prototype")
public class WjqBaseStatAction extends BaseAction {

	private static final long serialVersionUID = -1138421214319019149L;

	private static final Logger logger = LoggerFactory
			.getLogger(WjqBaseStatAction.class);

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final String StatByDay = "byDay";
	
	private static final String StatByMonth = "byMonth";
	
	@Autowired
	private MallPvuvDailyStatService mallPvuvDailyStatService;

	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;

	private String flag;
	
	/**
	 * 商城日常基础数据每日统计列表视图 
	 * @return String
	 */
	public String wjqBaseStatListView() {

		String statRate = StatByDay;
		if(StringUtils.isNotBlank(statParams.get("statRate"))) {
			statRate = statParams.get("statRate");
		}
		
		List<Map<String, Object>> wjqBaseStatList = null;
		
		try {
			if(StatByMonth.equals(statRate)) {
				//生成查询参数Map
				Map<String, Object> params = genParamMap();
				//接收人信息列表总数目分页查询
				Integer totalRecord = mallPvuvDailyStatService.getWjqMonthlyBaseStatCount(params);
				//根据当前页、总记录数、页大小获得Page
				page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
				//新增Page至参数Map
				setPageInfo2Map(params);
				
				wjqBaseStatList = mallPvuvDailyStatService.getWjqMonthlyBaseStatByPage(params);
				
				request.setAttribute("wjqBaseStatList", wjqBaseStatList);
				
			} else {
				//生成查询参数Map
				Map<String, Object> params = genParamMap();
				//接收人信息列表总数目分页查询
				Integer totalRecord = mallPvuvDailyStatService.getWjqDailyBaseStatCount(params);
				//根据当前页、总记录数、页大小获得Page
				page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
				//新增Page至参数Map
				setPageInfo2Map(params);
				
				wjqBaseStatList = mallPvuvDailyStatService.getWjqDailyBaseStatByPage(params);
				
				request.setAttribute("wjqBaseStatList", wjqBaseStatList);
			}
			
		} catch (DBException dbex) {
			logger.error("WjqBaseStatAction.wjqBaseStatListView found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("WjqBaseStatAction.wjqBaseStatListView found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	
	}
	
	/**
	 * 玩机圈日常基础数据每日统计列表导出至Excel
	 * @return String
	 */
	public String wjqBaseStatList2Excel() {

		String statRate = StatByDay;
		if(StringUtils.isNotBlank(statParams.get("statRate"))) {
			statRate = statParams.get("statRate");
		}
		
		List<Map<String, Object>> wjqBaseStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			if(StatByMonth.equals(statRate)) {
				//生成查询参数Map
				Map<String, Object> params = genParamMap();
				wjqBaseStatList = mallPvuvDailyStatService.getWjqMonthlyBaseStatByExample(params);
				// 生成导出数据列名列表
				columnNames = genWjqDailyStatColNames();
				// 生成导出数据列名变量列表
				columns = genWjqDailyStatCols();
			} else {
				//生成查询参数Map
				Map<String, Object> params = genParamMap();
				wjqBaseStatList = mallPvuvDailyStatService.getWjqDailyBaseStatByExample(params);
				// 生成导出数据列名列表
				columnNames = genWjqDailyStatColNames();
				// 生成导出数据列名变量列表
				columns = genWjqDailyStatCols();
			}
			
			if(CollectionUtils.isNotEmpty(wjqBaseStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				String sheetName = "";
				String exportDate = dateFormat.format(new Date());
				if("byMonth".equals(statRate)) {
					sheetName = "玩机圈每月访问数据统计";
					fileName = "玩机圈网站访问情况月报表_" + exportDate;
				} else {
					sheetName = "玩机圈每日访问数据统计";
					fileName = "玩机圈网站访问情况日报表_" + exportDate;
				}
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4Xssf(columnNames, columns, wjqBaseStatList, sheetName);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("WjqBaseStatAction.wjqBaseStatList2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("WjqBaseStatAction.wjqBaseStatList2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("WjqBaseStatAction.wjqBaseStatList2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("WjqBaseStatAction.wjqBaseStatList2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	
	}
	
	/**
	 * 按JSON格式获取玩机圈日常基础数据每日统计报表FusionChart参数
	 */
	public void getWjqBaseStatReportByJSON() {
		String statRate = StatByDay;
		if(StringUtils.isNotBlank(statParams.get("statRate"))) {
			statRate = statParams.get("statRate");
		}
		
		List<Map<String, Object>> wjqBaseStatList = null;
		
		if(StatByMonth.equals(statRate)) {
			//生成查询参数Map
			Map<String, Object> params = genParamMap();
			wjqBaseStatList = mallPvuvDailyStatService.getWjqMonthlyBaseStatByExample(params);
		} else {
			//生成查询参数Map
			Map<String, Object> params = genParamMap();
			wjqBaseStatList = mallPvuvDailyStatService.getWjqDailyBaseStatByExample(params);
		}
		
		FusionChart fusionChart = new FusionChart();
		FusionChartConfig fusionChartConfig = new FusionChartConfig();
		List<FusionChartCategory> categories = new ArrayList<FusionChartCategory>();
		List<FusionChartDataset> datasets = new ArrayList<FusionChartDataset>();
		fusionChart.setChart(fusionChartConfig);
		fusionChart.setCategories(categories);
		fusionChart.setDataset(datasets);
		
		String caption = "玩机圈网站访问情况趋势图";
		String subcaption = "Source: Gionee Electrical Business Operation Center.";
		String xAxisName = "统计日期";
		String yAxisName = "统计访问量";
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
//		fusionChartConfig.setDivintervalhints("0,1000,2000,5000,10000");
		fusionChartConfig.setDataseparator("|");
		
		if(null != wjqBaseStatList && wjqBaseStatList.size() > 0) {
			String bizDate = "";
			String pv = "0";
			String uv = "0";
			String ip = "0";
			
			// category for pv
			FusionChartCategory pvCategory = new FusionChartCategory();
			String pvCgValue = "";
			categories.add(pvCategory);
			
			// DataSet for PV
			FusionChartDataset pvDataset = new FusionChartDataset();
			String pvSeriesName = "浏览量(PV)";
			String pvData = "";
			pvDataset.setSeriesname(pvSeriesName);
			datasets.add(pvDataset);
			
			// DataSet for UV
			FusionChartDataset uvDataset = new FusionChartDataset();
			String uvSeriesName = "独立访客(UV)";
			String uvData = "";
			uvDataset.setSeriesname(uvSeriesName);
			datasets.add(uvDataset);
			
			// DataSet for IP
			FusionChartDataset ipDataset = new FusionChartDataset();
			String ipSeriesName = "独立IP(IP)";
			String ipData = "";
			ipDataset.setSeriesname(ipSeriesName);
			datasets.add(ipDataset);
			
			for(int i = 0; i < wjqBaseStatList.size(); i++) {
				Map<String, Object> wjqDailyStat = wjqBaseStatList.get(i);
				bizDate = (String) wjqDailyStat.get("bizDate");
				if(null != wjqDailyStat.get("pv")) {
					pv = ((BigDecimal) wjqDailyStat.get("pv")).toString();
				}
				if(null != wjqDailyStat.get("uv")) {
					uv = ((BigDecimal) wjqDailyStat.get("uv")).toString();
				}
				if(null != wjqDailyStat.get("ip")) {
					ip = ((BigDecimal) wjqDailyStat.get("ip")).toString();
				}
				
				if(i < wjqBaseStatList.size() - 1) {
					pvCgValue += bizDate + "|";
					pvData += pv + "|";
					uvData += uv + "|";
					ipData += ip + "|";
				} else {
					pvCgValue += bizDate;
					pvData += pv;
					uvData += uv;
					ipData += ip;
				}
			}
			pvCategory.setCategory(pvCgValue);
			pvDataset.setData(pvData);
			uvDataset.setData(uvData);
			ipDataset.setData(ipData);
		}
		
		printJson2(fusionChart, Boolean.TRUE, Boolean.FALSE);
		
		logger.debug("WjqBaseStatAction.getWjqBaseStatReportByJSON invoke success.");
		
	}
	
	/**
	 * 生成查询参数Map
	 * @return Map<String, Object>
	 */
	private Map<String, Object> genParamMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("urlDomain", "rd.wanjiquan.com");
		
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
		}
		
		if(StringUtils.isNotBlank(statParams.get("pvFrom"))) {
			params.put("pvFrom", statParams.get("pvFrom"));
		}
		if(StringUtils.isNotBlank(statParams.get("pvTo"))) {
			params.put("pvTo", statParams.get("pvTo"));			
		}
		if(StringUtils.isNotBlank(statParams.get("uvFrom"))) {
			params.put("uvFrom", statParams.get("uvFrom"));
		}
		if(StringUtils.isNotBlank(statParams.get("uvTo"))) {
			params.put("uvTo", statParams.get("uvTo"));
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
	private void setPageInfo2Map(Map<String, Object> params) {
		params.put("startRec", page.getStartRec());
		params.put("endRec", page.getEndRec());
	}
	
	/**
	 * 生成导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genWjqDailyStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("bizDate");
		cols.add("pv");
		cols.add("uv");
		cols.add("ip");
		cols.add("visitDepth");
		
		return cols;
	}
	
	/**
	 * 生成导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genWjqDailyStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计日期");
		colNames.add("PV");
		colNames.add("UV");
		colNames.add("IP");
		colNames.add("访问深度");
		
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
