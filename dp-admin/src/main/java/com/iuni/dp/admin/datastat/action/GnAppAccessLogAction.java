package com.iuni.dp.admin.datastat.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.iuni.dp.admin.datastat.bean.FusionChartData;
import com.iuni.dp.admin.datastat.bean.FusionChartDataset;
import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.GnAppAccessLogService;

/**
 * 玩机圈客户端登入登出记录日志Action
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
@Controller("gnAppAccessLogAction")
@Scope("prototype")
public class GnAppAccessLogAction extends BaseAction {

	private static final long serialVersionUID = 1036216403552097016L;

	private static final Logger logger = LoggerFactory.getLogger(GnAppAccessLogAction.class);
	
	private static final String StatByDay = "byDay";
	
	private static final String StatByMonth = "byMonth";
	
	private static final String WjqAppName = ParseProperties.getPropertiesValue("WanjiquanApp.name");
//	
	private static final String ElifeAppName = ParseProperties.getPropertiesValue("ElifeApp.name");
//	
	private static final String GioneeshopAppName = ParseProperties.getPropertiesValue("GioneeshopApp.name");
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static NumberFormat percentFormat = NumberFormat.getPercentInstance();
	
	@Autowired
	private GnAppAccessLogService gnAppAccessLogService;
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	/**
	 * GIONEE APP客户端渠道趋势统计数据查询视图
	 * @return String
	 */
	public String gnAppChannelTrendStatView() {
		List<Map<String, Object>> channelTrendStatList = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("channelTrend");
			
			Integer totalRecord = gnAppAccessLogService.fetchChannelTrendCount(params);
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			channelTrendStatList = gnAppAccessLogService.fetchChannelTrendByPage(params);
			
			request.setAttribute("channelTrendStatList", channelTrendStatList);
			
		} catch (DBException dbex) {
			logger.error("GnAppAccessLogAction.gnAppChannelTrendStatView found DBException", dbex);
			return ERROR;
		}catch (Exception ex) {
			logger.error("GnAppAccessLogAction.gnAppChannelTrendStatView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * GIONEE APP客户端渠道趋势统计列表导出至Excel
	 * @return String
	 */
	public String gnAppChannelTrendStat2Excel() {
		List<Map<String, Object>> channelTrendStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("channelTrend");
			channelTrendStatList = gnAppAccessLogService.fetchChannelTrendByExample(params);
			// 生成导出数据列名列表
			columnNames = genChannelTrendStatColNames();
			// 生成导出数据列名变量列表
			columns = genChannelTrendStatCols();
			
			if(CollectionUtils.isNotEmpty(channelTrendStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "GIONEE APP客户端渠道趋势数据统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "GIONEE APP客户端渠道趋势统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, channelTrendStatList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("GnAppAccessLogAction.gnAppChannelTrendStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("GnAppAccessLogAction.gnAppChannelTrendStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("GnAppAccessLogAction.gnAppChannelTrendStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("GnAppAccessLogAction.gnAppChannelTrendStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * GIONEE APP客户端渠道注册用户统计数据查询视图
	 * @return String
	 */
	public String gnAppRegisterOfChannelStatView() {
		List<Map<String, Object>> registerOfChannelStatList = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("registerOfChannel");
			
			Integer totalRecord = gnAppAccessLogService.fetchRegisterOfChannelCount(params);
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			registerOfChannelStatList = gnAppAccessLogService.fetchRegisterOfChannelByPage(params);
			
			request.setAttribute("registerOfChannelStatList", registerOfChannelStatList);
			
		} catch (DBException dbex) {
			logger.error("GnAppAccessLogAction.gnAppRegisterOfChannelStatView found DBException", dbex);
			return ERROR;
		}catch (Exception ex) {
			logger.error("GnAppAccessLogAction.gnAppRegisterOfChannelStatView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * GIONEE APP客户端渠道注册用户统计列表导出至Excel
	 * @return String
	 */
	public String gnAppRegisterOfChannelStat2Excel() {
		List<Map<String, Object>> registerOfChannelStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("registerOfChannel");
			registerOfChannelStatList = gnAppAccessLogService.fetchRegisterOfChannelByExample(params);
			// 生成导出数据列名列表
			columnNames = genRegisterOfChannelStatColNames();
			// 生成导出数据列名变量列表
			columns = genRegisterOfChannelStatCols();
			
			if(CollectionUtils.isNotEmpty(registerOfChannelStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "GIONEE APP渠道注册用户数数据统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "GIONEE APP渠道注册用户数数据统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, registerOfChannelStatList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("GnAppAccessLogAction.gnAppRegisterOfChannelStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("GnAppAccessLogAction.gnAppRegisterOfChannelStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("GnAppAccessLogAction.gnAppRegisterOfChannelStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("GnAppAccessLogAction.gnAppRegisterOfChannelStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * 按JSON格式获取玩机圈APP客户端渠道注册用户数统计报表FusionChart参数
	 */
	public void getGnAppRegisterOfChannelStatByJSON() {
		List<Map<String, Object>> registerOfChannelStatList = null;
		
		//生成查询参数Map
		Map<String, Object> params = genParamMap("newUserOfChannel");
		registerOfChannelStatList = gnAppAccessLogService.fetchRegisterOfChannelByExample(params);
		Map<String, List<Map<String, Object>>> chartData = gnAppAccessLogService.fetchRegisterStatMapByChannel(registerOfChannelStatList, params);
		
		FusionChart fusionChart = new FusionChart();
		FusionChartConfig fusionChartConfig = new FusionChartConfig();
		List<FusionChartCategory> categories = new ArrayList<FusionChartCategory>();
		List<FusionChartDataset> datasets = new ArrayList<FusionChartDataset>();
		fusionChart.setChart(fusionChartConfig);
		fusionChart.setCategories(categories);
		fusionChart.setDataset(datasets);
		
		String caption = getAppName4Report((String)params.get("appName")) + "渠道注册用户数统计趋势图";
		String subcaption = "Source: Gionee Electrical Business Operation Center.";
		String xAxisName = "统计日期";
		String yAxisName = "渠道注册用户数";
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
			String registerNum = "0";
			
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
					registerNum = ((BigDecimal)channelMap.get("registerNum")).toString();
					
					if(i < channelData.size() - 1) {
						if(chartDataCount == 1) {
							cgValue += bizDate + "|";
						}
						data += registerNum + "|";
					} else {
						if(chartDataCount == 1) {
							cgValue += bizDate;
						}
						data += registerNum;
					}
				}
				
				dataset.setData(data);
			}
			
			category.setCategory(cgValue);
		}
		
		printJson2(fusionChart, Boolean.TRUE, Boolean.FALSE);
		
		logger.debug("GnAppAccessLogAction.getGnAppRegisterOfChannelStatByJSON invoke success.");
	}
	
	/**
	 * GIONEE APP客户端渠道活跃用户统计数据查询视图
	 * @return String
	 */
	public String gnAppActiveUserOfChannelStatView() {
		String statRate = StatByDay;
		if(StringUtils.isNotBlank(statParams.get("statRate"))) {
			statRate = statParams.get("statRate");
		}
		
		List<Map<String, Object>> activeUserOfChannelStatList = null;

		try {
			if(StatByMonth.equals(statRate)) {
				//生成查询参数Map
				Map<String, Object> params = genParamMap("activeUserOfChannel");
				Integer totalRecord = gnAppAccessLogService.fetchActiveUserOfChannelMonthlyCount(params);
				//根据当前页、总记录数、页大小获得Page
				page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
				//新增Page至参数Map
				setPageInfo2Map(page, params);
				
				activeUserOfChannelStatList = gnAppAccessLogService.fetchActiveUserOfChannelMonthlyByPage(params);
			} else {
				//生成查询参数Map
				Map<String, Object> params = genParamMap("activeUserOfChannel");
				Integer totalRecord = gnAppAccessLogService.fetchActiveUserOfChannelDailyCount(params);
				//根据当前页、总记录数、页大小获得Page
				page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
				//新增Page至参数Map
				setPageInfo2Map(page, params);
				
				activeUserOfChannelStatList = gnAppAccessLogService.fetchActiveUserOfChannelDailyByPage(params);
			}
			
			request.setAttribute("activeUserOfChannelStatList", activeUserOfChannelStatList);
			
		} catch (DBException dbex) {
			logger.error("GnAppAccessLogAction.gnAppActiveUserOfChannelStatView found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("GnAppAccessLogAction.gnAppActiveUserOfChannelStatView found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * GIONEE APP客户端渠道活跃用户统计数据导出至Excel
	 * @return String
	 */
	public String gnAppActiveUserOfChannelStat2Excel() {
		String statRate = StatByDay;
		if(StringUtils.isNotBlank(statParams.get("statRate"))) {
			statRate = statParams.get("statRate");
		}
		
		List<Map<String, Object>> activeUserStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			if(StatByMonth.equals(statRate)) {
				//生成查询参数Map
				Map<String, Object> params = genParamMap("activeUserOfChannel");
				activeUserStatList = gnAppAccessLogService.fetchActiveUserOfChannelMonthlyByExample(params);
				// 生成导出数据列名列表
				columnNames = genActiveUserOfChannelStatColNames();
				// 生成导出数据列名变量列表
				columns = genActiveUserOfChannelStatCols();
			} else {
				//生成查询参数Map
				Map<String, Object> params = genParamMap("activeUserOfChannel");
				activeUserStatList = gnAppAccessLogService.fetchActiveUserOfChannelDailyByExample(params);
				// 生成导出数据列名列表
				columnNames = genActiveUserOfChannelStatColNames();
				// 生成导出数据列名变量列表
				columns = genActiveUserOfChannelStatCols();
			}
			
			if(CollectionUtils.isNotEmpty(activeUserStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				String sheetName = "";
				String exportDate = dateFormat.format(new Date());
				
				if("byMonth".equals(statRate)) {
					sheetName = "GIONEE APP每月活跃用户数据统计";
					fileName = "GIONEE APP活跃用户数统计月报表_" + exportDate;
				} else {
					sheetName = "GIONEE APP每日活跃用户数据统计";
					fileName = "GIONEE APP活跃用户数统计日报表_" + exportDate;
				}
				sheetDataList.put(sheetName, activeUserStatList);
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("GnAppAccessLogAction.gnAppActiveUserOfChannelStatView found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("GnAppAccessLogAction.gnAppActiveUserOfChannelStatView found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("GnAppAccessLogAction.gnAppActiveUserOfChannelStatView found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("GnAppAccessLogAction.gnAppActiveUserOfChannelStatView found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * 按JSON格式获取GIONEE APP客户端渠道活跃用户统计报表FusionChart参数
	 */
	public void getGnAppActiveUserOfChannelStatByJSON() {
		String statRate = StatByDay;
		if(StringUtils.isNotBlank(statParams.get("statRate"))) {
			statRate = statParams.get("statRate");
		}
		
		List<Map<String, Object>> activeUserStatList = null;
		Map<String, List<Map<String, Object>>> chartData = null;
		Map<String, Object> params = null;
		
		if(StatByMonth.equals(statRate)) {
			//生成查询参数Map
			params = genParamMap("activeUserOfChannel");
			activeUserStatList = gnAppAccessLogService.fetchActiveUserOfChannelMonthlyByExample(params);
			chartData = gnAppAccessLogService.fetchActiveUserStatMapByChannel(activeUserStatList, params, StatByMonth);
		} else {
			//生成查询参数Map
			params = genParamMap("activeUserOfChannel");
			activeUserStatList = gnAppAccessLogService.fetchActiveUserOfChannelDailyByExample(params);
			chartData = gnAppAccessLogService.fetchActiveUserStatMapByChannel(activeUserStatList, params, StatByDay);
		}
		
		
		FusionChart fusionChart = new FusionChart();
		FusionChartConfig fusionChartConfig = new FusionChartConfig();
		List<FusionChartCategory> categories = new ArrayList<FusionChartCategory>();
		List<FusionChartDataset> datasets = new ArrayList<FusionChartDataset>();
		fusionChart.setChart(fusionChartConfig);
		fusionChart.setCategories(categories);
		fusionChart.setDataset(datasets);
		
		String caption = getAppName4Report((String)params.get("appName")) + "渠道活跃用户数统计趋势图";
		String subcaption = "Source: Gionee Electrical Business Operation Center.";
		String xAxisName = "统计日期";
		String yAxisName = "活跃用户数";
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
			String activeUserNum = "0";
			
			// category for activeUser
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
					activeUserNum = ((BigDecimal)channelMap.get("activeUserNum")).toString();
					
					if(i < channelData.size() - 1) {
						if(chartDataCount == 1) {
							cgValue += bizDate + "|";
						}
						data += activeUserNum + "|";
					} else {
						if(chartDataCount == 1) {
							cgValue += bizDate;
						}
						data += activeUserNum;
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
	 * GIONEE 客户端渠道用户的启动次数分布查询视图
	 * @return String
	 */
	public String gnAppLaunchNumDistributeView() {
		List<Map<String, Object>> launchNumDistributeList = null;
		List<Map<String, Object>> launchNumDistribute = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("launchNumDistribute");
			
			launchNumDistributeList = gnAppAccessLogService.fetchLaunchNumDistribution(params);
			
			// 处理启动次数分布数据
			launchNumDistribute = processDistributeData(launchNumDistributeList);
			
			request.setAttribute("launchNumDistributeList", launchNumDistribute);
			
		} catch (DBException dbex) {
			logger.error("GnAppAccessLogAction.gnAppLaunchNumDistributeView found DBException", dbex);
			return ERROR;
		}catch (Exception ex) {
			logger.error("GnAppAccessLogAction.gnAppLaunchNumDistributeView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * GIONEE APP渠道启动次数用户分布统计数据导出至Excel
	 * @return String
	 */
	public String gnAppLaunchNumDistribute2Excel() {
		List<Map<String, Object>> launchNumDistributeList = null;
		List<Map<String, Object>> launchNumDistribute = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("launchNumDistribute");
			launchNumDistributeList = gnAppAccessLogService.fetchLaunchNumDistribution(params);
			
			// 处理启动次数分布数据
			launchNumDistribute = processDistributeData(launchNumDistributeList);
			
			// 生成导出数据列名列表
			columnNames = genLaunchNumDistributeColNames();
			// 生成导出数据列名变量列表
			columns = genLaunchNumDistributeCols();
			
			if(CollectionUtils.isNotEmpty(launchNumDistribute) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				String sheetName = "";
				String exportDate = dateFormat.format(new Date());
				
				sheetName = "GIONEE APP渠道启动次数用户分布数据统计";
				fileName = "GIONEE APP渠道启动次数用户分布统计报表_" + exportDate;
				sheetDataList.put(sheetName, launchNumDistribute);
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("GnAppAccessLogAction.gnAppLaunchNumDistribute2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("GnAppAccessLogAction.gnAppLaunchNumDistribute2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("GnAppAccessLogAction.gnAppLaunchNumDistribute2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("GnAppAccessLogAction.gnAppLaunchNumDistribute2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * 按JSON格式获取GIONEE 客户端渠道用户的启动次数分布统计报表FusionChart参数
	 */
	public void getGnAppLaunchNumDistributeByJSON() {
		List<Map<String, Object>> launchNumDistributeList = null;
		List<Map<String, Object>> launchNumDistribute = null;
		
		//生成查询参数Map
		Map<String, Object> params = genParamMap("launchNumDistribute");
		
		launchNumDistributeList = gnAppAccessLogService.fetchLaunchNumDistribution(params);
		
		// 处理启动次数分布数据
		launchNumDistribute = processDistributeData(launchNumDistributeList);
		
		FusionChart fusionChart = new FusionChart();
		FusionChartConfig fusionChartConfig = new FusionChartConfig();
		List<FusionChartData> datas = new ArrayList<FusionChartData>();
		fusionChart.setChart(fusionChartConfig);
		fusionChart.setData(datas);
		
		String caption = getAppName4Report((String)params.get("appName")) + "渠道启动次数用户分布图";
		String subcaption = "Source: Gionee Electrical Business Operation Center.";
		
		fusionChartConfig.setCaption(caption);
		fusionChartConfig.setSubcaption(subcaption);
		fusionChartConfig.setFormatnumberscale("0");
		fusionChartConfig.setPalette("2");
		fusionChartConfig.setAnimation("1");
		fusionChartConfig.setPieslicedepth("30");
		fusionChartConfig.setStartingangle("125");
		fusionChartConfig.setSlicingDistance("30");
		fusionChartConfig.setShowlegend("1");
		fusionChartConfig.setShowPercentInToolTip("1");
		fusionChartConfig.setShowvalues("1");
		
		if(CollectionUtils.isNotEmpty(launchNumDistribute)) {
			String launchSection = "";
			String launchDistribute = "0";
			String isSliced = "0";
			
			int chartDataCount = 0;
			for(Map<String,Object> map : launchNumDistribute) {
				++chartDataCount;
				launchSection = (String) map.get("launchSection");
				launchDistribute = ((Integer) map.get("distributeNum")).toString();
				if(chartDataCount == 1) {
					isSliced = "1";
				} else {
					isSliced = "0";
				}
				
				FusionChartData data = new FusionChartData();
				data.setLabel(launchSection);
				data.setValue(launchDistribute);
				data.setIssliced(isSliced);
				datas.add(data);
			}
		}
		
		printJson2(fusionChart, Boolean.TRUE, Boolean.FALSE);
		
		logger.debug("GnAppAccessLogAction.getGnAppLaunchNumDistributeByJSON invoke success.");
	}
	
	/**
	 * APP用户访问机型统计查询视图
	 * @return String
	 */
	public String appUserStatOnModelView() {
		List<Map<String, Object>> appStatOnModelList = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("appStatOnModel");
			
			Integer totalRecord = gnAppAccessLogService.queryAppUserStatOnModelCount(params);
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			appStatOnModelList = gnAppAccessLogService.queryAppUserStatOnModelByPage(params);
			
			request.setAttribute("appStatOnModelList", appStatOnModelList);
			
		} catch (DBException dbex) {
			logger.error("GnAppAccessLogAction.appUserStatOnModelView found DBException", dbex);
			return ERROR;
		}catch (Exception ex) {
			logger.error("GnAppAccessLogAction.appUserStatOnModelView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * APP用户访问机型统计列表导出至Excel
	 * @return String
	 */
	public String appUserStatOnModel2Excel() {
		List<Map<String, Object>> appStatOnModelList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("appStatOnModel");
			appStatOnModelList = gnAppAccessLogService.queryAppUserStatOnModelByExample(params);
			// 生成导出数据列名列表
			columnNames = genAppUserStatOnModelColNames();
			// 生成导出数据列名变量列表
			columns = genAppUserStatOnModelCols();
			
			if(CollectionUtils.isNotEmpty(appStatOnModelList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "APP端用户访问机型数据统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "APP端用户访问机型报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, appStatOnModelList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("GnAppAccessLogAction.appUserStatOnModel2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("GnAppAccessLogAction.appUserStatOnModel2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("GnAppAccessLogAction.appUserStatOnModel2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("GnAppAccessLogAction.appUserStatOnModel2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * 按JSON格式获取APP用户访问机型统计报表FusionChart参数
	 */
	public void getAppUserStatOnModelByJSON() {
		List<Map<String, Object>> appStatOnModelList = null;
		
		//生成查询参数Map
		Map<String, Object> params = genParamMap("appStatOnModel");
		appStatOnModelList = gnAppAccessLogService.queryAppUserStatOnModelByExample(params);
		
		FusionChart fusionChart = new FusionChart();
		FusionChartConfig fusionChartConfig = new FusionChartConfig();
		List<FusionChartData> fusionChartDatas = new ArrayList<FusionChartData>();
		fusionChart.setChart(fusionChartConfig);
		fusionChart.setData(fusionChartDatas);
		
		String caption = "上报数据定时调度类型统计分析报表";
		String subcaption = "Source: Gionee Electrical Business Operation Center.";
		String xAxisName = "机型";
		String yAxisName = "统计数据";
		
		fusionChartConfig.setCaption(caption);
		fusionChartConfig.setSubcaption(subcaption);
		fusionChartConfig.setXAxisName(xAxisName);
		fusionChartConfig.setYAxisName(yAxisName);
		fusionChartConfig.setFormatnumberscale("0");
		fusionChartConfig.setLabelDisplay("STAGGER");
		fusionChartConfig.setSlantlabels("1");
		fusionChartConfig.setDecimalPrecision("2");
		
		String statIndex = "launchNum";
		if(CollectionUtils.isNotEmpty(appStatOnModelList)) {
			statIndex = (String) params.get("statIndex");
			String statNum = "";
			for(Map<String, Object> appStatOnModel : appStatOnModelList) {
				if("launchNum".equals(statIndex)) {
					statNum = ((BigDecimal) appStatOnModel.get("launchNum")).toString();
				} else if("launchUserNum".equals(statIndex)) {
					statNum = ((BigDecimal) appStatOnModel.get("launchUserNum")).toString();
				} else if("newUserNum".equals(statIndex)) {
					statNum = ((BigDecimal) appStatOnModel.get("newUserNum")).toString();
				} else if("avgTime".equals(statIndex)) {
					statNum = ((BigDecimal) appStatOnModel.get("avgTime")).toString();
				}
				
				String statType = (String) appStatOnModel.get("mobileModel");
				
				FusionChartData fusionChartData = new FusionChartData();
				fusionChartData.setLabel(statType);
				fusionChartData.setValue(statNum);
				
				fusionChartDatas.add(fusionChartData);
			}
		}
		
		printJson2(fusionChart, Boolean.TRUE, Boolean.FALSE);
		
		logger.debug("StatScheduledResultAction.getScheduledReportByJSON invoke success.");
	}
	
	/**
	 * 按JSON格式获取GIONEE APP客户端渠道列表
	 */
	public void getChannelListByJSON() {
		List<Map<String, Object>> channelList = null;
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("channelList");
			channelList = gnAppAccessLogService.fetchChannelList(params);
			
		} catch(DBException dbex) {
			logger.error("GnAppAccessLogAction.getChannelListByJSON found DBException.");
			
		} catch (Exception e) {
			logger.error("GnAppAccessLogAction.getChannelListByJSON found Exception.");
		}
		
		printJson2(channelList, false, false);
	}
	
	/**
	 * 按JSON格式获取玩机圈APP客户端版本列表
	 */
	public void getApkVersionListByJSON() {
		List<Map<String, Object>> apkVersionList = null;
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("apkVersionList");
			apkVersionList = gnAppAccessLogService.fetchApkVersionList(params);
			
		} catch(DBException dbex) {
			logger.error("GnAppAccessLogAction.getApkVersionListByJSON found DBException.");
			
		} catch (Exception e) {
			logger.error("GnAppAccessLogAction.getApkVersionListByJSON found Exception.");
		}
		
		printJson2(apkVersionList, false, false);
	}
	
	/**
	 * 处理分布数据
	 * @return List<Map<String, Object>>
	 */
	protected List<Map<String, Object>> processDistributeData(List<Map<String, Object>> list) {
		List<Map<String, Object>> result = null;
		if(CollectionUtils.isEmpty(list)) {
			return result;
		}
		
		result = new ArrayList<Map<String, Object>>();
		int between1and2 = 0;
		int between3and5 = 0;
		int between6and9 = 0;
		int between10and19 = 0;
		int between20and50 = 0;
		int mornThan50 = 0;
		double total = 0;
		
		for(Map<String, Object> map : list) {
			Double avgLaunchNum = ((BigDecimal) map.get("avgLaunchNum")).doubleValue();
			if(avgLaunchNum >=0 && avgLaunchNum < 3) {
				++between1and2;
			} else if(avgLaunchNum >=3 && avgLaunchNum < 6) {
				++between3and5;
			} else if(avgLaunchNum >=6 && avgLaunchNum < 10) {
				++between6and9;
			} else if(avgLaunchNum >=10 && avgLaunchNum < 20) {
				++between10and19;
			} else if(avgLaunchNum >=20 && avgLaunchNum < 50) {
				++between20and50;
			} else {
				++mornThan50;
			}
		}
		
		total = between1and2 + between3and5 + between6and9 + between10and19 + between20and50 + mornThan50;
		percentFormat.setMinimumFractionDigits(2);
		percentFormat.setMaximumFractionDigits(2);
		
		Map<String, Integer> distributeMap = new LinkedHashMap<String, Integer>();
		distributeMap.put("1 ~ 2次", between1and2);
		distributeMap.put("3 ~ 5次", between3and5);
		distributeMap.put("6 ~ 9次", between6and9);
		distributeMap.put("10 ~ 19次", between10and19);
		distributeMap.put("20 ~ 50次", between20and50);
		distributeMap.put("50次以上", mornThan50);
		
		int loopIndex = 0;
		for(Entry<String, Integer> entry : distributeMap.entrySet()) {
			++loopIndex;
			String key = entry.getKey();
			Integer value = entry.getValue();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rowNum", loopIndex);
			map.put("launchSection", key);
			map.put("distributeNum", value);
			map.put("distributeRate", percentFormat.format(value/total));
			result.add(map);
		}
		
		return result;
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
	 * 生成注册用户数统计报表导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genChannelTrendStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("channelName");
		cols.add("launchUserNum");
		cols.add("launchNum");
		cols.add("avgTime");
		cols.add("newUserNum");
		cols.add("newUserRatio");
		
		return cols;
	}
	
	/**
	 * 生成注册用户数统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genChannelTrendStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("渠道名");
		colNames.add("启动用户");
		colNames.add("启动次数");
		colNames.add("平均使用时长");
		colNames.add("新增用户");
		colNames.add("新增用户占比");
		
		return colNames;
	}
	
	/**
	 * 生成注册用户数统计报表导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genRegisterOfChannelStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("channelName");
		cols.add("registerNum");
		cols.add("bizDate");
		
		return cols;
	}
	
	/**
	 * 生成注册用户数统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genRegisterOfChannelStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("渠道名");
		colNames.add("注册用户数");
		colNames.add("统计日期");
		
		return colNames;
	}
	
	/**
	 * 生成活跃用户数统计报表导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genActiveUserOfChannelStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("channelName");
		cols.add("activeUserNum");
		cols.add("bizDate");
		
		return cols;
	}
	
	/**
	 * 生成活跃用户数统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genActiveUserOfChannelStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("渠道名");
		colNames.add("活跃用户数");
		colNames.add("统计日期");
		
		return colNames;
	}
	
	/**
	 * 生成活跃用户数统计报表导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genLaunchNumDistributeCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("launchSection");
		cols.add("distributeNum");
		cols.add("distributeRate");
		
		return cols;
	}
	
	/**
	 * 生成活跃用户数统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genLaunchNumDistributeColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("启动次数区间");
		colNames.add("区间所占用户数");
		colNames.add("区间用户分布");
		
		return colNames;
	}
	
	/**
	 * 生成APP用户访问机型统计列表导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genAppUserStatOnModelCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("mobileModel");
		cols.add("launchNum");
		cols.add("launchUserNum");
		cols.add("newUserNum");
		cols.add("avgTime");
		cols.add("avgTimeFmt");
		cols.add("newUserRatio");
		
		return cols;
	}
	
	/**
	 * 生成APP用户访问机型统计列表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genAppUserStatOnModelColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("机型");
		colNames.add("累计启动次数");
		colNames.add("累计启动用户数");
		colNames.add("累计新用户数");
		colNames.add("平均使用时长数(s)");
		colNames.add("平均使用时长");
		colNames.add("新增用户占比");
		
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
			statParams.put("appName", "GIONEESHOP APP");
			
			// 设置statIndex默认值
			if("appStatOnModel".equals(condition)) {
				statParams.put("statIndex", "launchNum");
			}
		}
		
		if("appStatOnModel".equals(condition)) {
			if(StringUtils.isNotBlank(statParams.get("statIndex"))) {
				params.put("statIndex", statParams.get("statIndex"));
			}
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
