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
import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.GnAppImeiLogService;

/**
 * 玩机圈APP客户端IMEI首次启用时间日志Action
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
@Controller("gnAppImeiLogAction")
@Scope("prototype")
public class GnAppImeiLogAction extends BaseAction {

	private static final long serialVersionUID = 2935286313428709226L;

	private static final Logger logger = LoggerFactory.getLogger(GnAppImeiLogAction.class);
	
	private static final String WjqAppName = ParseProperties.getPropertiesValue("WanjiquanApp.name");
//	
	private static final String ElifeAppName = ParseProperties.getPropertiesValue("ElifeApp.name");
//	
	private static final String GioneeshopAppName = ParseProperties.getPropertiesValue("GioneeshopApp.name");
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private GnAppImeiLogService gnAppImeiLogService;
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	/**
	 * 玩机圈APP客户端渠道新增用户数统计视图
	 * @return String
	 */
	public String gnAppNewUserOfChannelStatView() {
		List<Map<String, Object>> newUserOfChannelStatList = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("newUserOfChannel");
			
			Integer totalRecord = gnAppImeiLogService.fetchNewUserOfChannelCount(params);
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			newUserOfChannelStatList = gnAppImeiLogService.fetchNewUserOfChannelByPage(params);
			
			request.setAttribute("newUserOfChannelStatList", newUserOfChannelStatList);
			
		} catch (DBException dbex) {
			logger.error("GnAppImeiLogAction.gnAppNewUserOfChannelStatView found DBException", dbex);
			return ERROR;
		}catch (Exception ex) {
			logger.error("GnAppImeiLogAction.gnAppNewUserOfChannelStatView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 玩机圈APP客户端渠道新增用户数统计列表导出至Excel
	 * @return String
	 */
	public String gnAppNewUserOfChannelStat2Excel() {
		List<Map<String, Object>> newUserOfChannelStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("newUserOfChannel");
			newUserOfChannelStatList = gnAppImeiLogService.fetchNewUserOfChannelByExample(params);
			// 生成导出数据列名列表
			columnNames = genNewUserOfChannelStatColNames();
			// 生成导出数据列名变量列表
			columns = genNewUserOfChannelStatCols();
			
			if(CollectionUtils.isNotEmpty(newUserOfChannelStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "玩机圈APP渠道新增用户数数据统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "玩机圈APP渠道新增用户数数据统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, newUserOfChannelStatList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("GnAppImeiLogAction.gnAppNewUserOfChannelStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("GnAppImeiLogAction.gnAppNewUserOfChannelStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("GnAppImeiLogAction.gnAppNewUserOfChannelStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("GnAppImeiLogAction.gnAppNewUserOfChannelStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * 按JSON格式获取玩机圈APP客户端渠道新增用户数统计报表FusionChart参数
	 */
	public void getGnAppNewUserOfChannelStatByJSON() {
		List<Map<String, Object>> newUserOfChannelStatList = null;
		
		//生成查询参数Map
		Map<String, Object> params = genParamMap("newUserOfChannel");
		newUserOfChannelStatList = gnAppImeiLogService.fetchNewUserOfChannelByExample(params);
		Map<String, List<Map<String, Object>>> chartData = gnAppImeiLogService.fetchNewUserStatMapByChannel(newUserOfChannelStatList, params);
		
		FusionChart fusionChart = new FusionChart();
		FusionChartConfig fusionChartConfig = new FusionChartConfig();
		List<FusionChartCategory> categories = new ArrayList<FusionChartCategory>();
		List<FusionChartDataset> datasets = new ArrayList<FusionChartDataset>();
		fusionChart.setChart(fusionChartConfig);
		fusionChart.setCategories(categories);
		fusionChart.setDataset(datasets);
		
		String caption = getAppName4Report((String)params.get("appName")) + "渠道新增用户数统计趋势图";
		String subcaption = "Source: Gionee Electrical Business Operation Center.";
		String xAxisName = "统计日期";
		String yAxisName = "渠道新增用户数";
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
		
		if(MapUtils.isNotEmpty(chartData)) {
			String bizDate = "";
			String newUserNum = "0";
			
			FusionChartCategory category = new FusionChartCategory();
			categories.add(category);
			String cgValue = "";
			
			int chartDataCount = 0;
			for(Entry<String,List<Map<String,Object>>> entry : chartData.entrySet()) {
				String channelName = entry.getKey();
				List<Map<String, Object>> channelData = entry.getValue();
				++chartDataCount;
				
				FusionChartDataset dataset = new FusionChartDataset();
				dataset.setSeriesname(channelName);
				datasets.add(dataset);
				String data = "";
				
				for(int i = 0; i < channelData.size(); i++) {
					Map<String, Object> channelMap = channelData.get(i);
					bizDate = (String) channelMap.get("bizDate");
					newUserNum = ((BigDecimal)channelMap.get("newUserNum")).toString();
					
					if(i < channelData.size() - 1) {
						if(chartDataCount == 1) {
							cgValue += bizDate + "|";
						}
						data += newUserNum + "|";
					} else {
						if(chartDataCount == 1) {
							cgValue += bizDate;
						}
						data += newUserNum;
					}
				}
				
				dataset.setData(data);
			}
			
			category.setCategory(cgValue);
		}
		
		printJson2(fusionChart, Boolean.TRUE, Boolean.FALSE);
		
		logger.debug("GnAppImeiLogAction.getGnAppNewUserOfChannelStatByJSON invoke success.");
	}
	
	/**
	 * 返回适应报表表头输出的APP应用名
	 * @param appName
	 * @return Stirng
	 */
	protected String getAppName4Report(String appName) {
		String result = "";
		
		if(WjqAppName.equals(appName)) {
			result = "玩机圈社区APP";
		} else if(ElifeAppName.equals(appName)) {
			result = "Elife社区APP";
		} else if(GioneeshopAppName.equals(appName)) {
			result = "金立商城APP";
		} else {
			result = "APP应用";
		}
		
		return result;
	}
	
	/**
	 * 生成导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genNewUserOfChannelStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("channelName");
		cols.add("newUserNum");
		cols.add("bizDate");
		
		return cols;
	}
	
	/**
	 * 生成导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genNewUserOfChannelStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("渠道名");
		colNames.add("新增用户数");
		colNames.add("统计日期");
		
		return colNames;
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
			statParams.put("appName", "WJQ APP");
		}
		
		if(StringUtils.isNotBlank(statParams.get("appName"))) {
			params.put("appName", statParams.get("appName"));
		}
		
		if(StringUtils.isNotBlank(statParams.get("channels"))) {
			String channels = statParams.get("channels");
			String[] channelNames = channels.split(",");
			if(ArrayUtils.isNotEmpty(channelNames)) {
				params.put("channelNames", channelNames);
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
