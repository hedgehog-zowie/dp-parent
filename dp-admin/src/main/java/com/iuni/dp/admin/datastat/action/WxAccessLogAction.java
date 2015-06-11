package com.iuni.dp.admin.datastat.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
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
import com.iuni.dp.admin.datastat.constants.DataStatConstant;
import com.iuni.dp.admin.datastat.utils.ExcelUtils;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.WxAccessLogService;

/**
 * 微信公众帐号访问日志相关统计Action
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
@Controller("wxAccessLogAction")
@Scope("prototype")
public class WxAccessLogAction extends BaseAction {

	private static final long serialVersionUID = -104614683531436122L;

	private static final Logger logger = LoggerFactory.getLogger(WxAccessLogAction.class);
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final String StatByDay = "byDay";
	
	private static final String StatByMonth = "byMonth";
	
	@Autowired
	private WxAccessLogService wxAccessLogService;
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	private Page onePage = new Page();
	
	private Page twoPage = new Page();
	
	/**
	 * 按条件查询微信公众帐号图片下载排行
	 */
	private List<Map<String,Object>> wxImageDownloadRanks;
	
	/**
	 * 按条件查询微信公众帐号图片下载总次数
	 */
	private Integer totalCount;
	
	/**
	 * 截止时间
	 */
	private String endTime;
	
	/**
	 * 微信应用新增粉丝统计视图
	 * @return String
	 */
	public String newFansStatView() {

		List<Map<String, Object>> wxNewFansStatList = null;

		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("newFans");
			
			wxNewFansStatList = wxAccessLogService.fetchNewFansStatByExample(params);
			
			// 处理新增粉丝数查询结果 , 某日空数据默认置为0
			wxNewFansStatList = processStatData(wxNewFansStatList, params, "newFansNum");
			
//			Integer totalRecord = wxAccessLogService.fetchNewFansStatCount(params);
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), wxNewFansStatList.size(), page.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			wxNewFansStatList = wxNewFansStatList.subList((Integer)params.get("startRec")-1, (Integer)params.get("endRec"));
			
			request.setAttribute("newFansStatList", wxNewFansStatList);
			
		} catch (ParseException e) {
			logger.error("WxAccessLogAction.newFansStatView found ParseException",e);
			return ERROR;
		} catch (DBException dbex) {
			logger.error("WxAccessLogAction.newFansStatView found DBException", dbex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("WxAccessLogAction.newFansStatView found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	
	}
	
	/**
	 * 微信应用新增粉丝数数据统计导出至Excel
	 * @return String
	 */
	public String wxNewFansStat2Excel() {

		List<Map<String, Object>> wxNewFansStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("newFans");
			wxNewFansStatList = wxAccessLogService.fetchNewFansStatByExample(params);
			
			// 处理新增粉丝数查询结果 , 某日空数据默认置为0
			wxNewFansStatList = processStatData(wxNewFansStatList, params, "newFansNum");
			
			// 生成导出数据列名列表
			columnNames = genWxNewFansStatColNames();
			// 生成导出数据列名变量列表
			columns = genWxNewFansStatCols();
			
			if(CollectionUtils.isNotEmpty(wxNewFansStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "新增粉丝数统计";
				sheetDataList.put(sheetName, wxNewFansStatList);
				
				String exportDate = dateFormat.format(new Date());
				fileName = "微信新增粉丝数统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("WxAccessLogAction.wxNewFansStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("WxAccessLogAction.wxNewFansStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (ParseException e) {
			logger.error("WxAccessLogAction.wxNewFansStat2Excel found ParseException",e);
			return ERROR;
		} catch (DBException dbex) {
			logger.error("WxAccessLogAction.wxNewFansStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("WxAccessLogAction.wxNewFansStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	
	}
	
	/**
	 * 按JSON格式获取微信新增粉丝数统计报表FusionChart参数
	 */
	public void getWxNewFansStatByJSON() {
		List<Map<String, Object>> wxNewFansStatList = null;
		
		//生成查询参数Map
		Map<String, Object> params = genParamMap("newFans");
		wxNewFansStatList = wxAccessLogService.fetchNewFansStatByExample(params);
		try {
			wxNewFansStatList = processStatData(wxNewFansStatList, params, "newFansNum");
		} catch (ParseException e) {
			logger.error("WxAccessLogAction.getWxNewFansStatByJSON found ParseException",e);
		}
		
		FusionChart fusionChart = new FusionChart();
		FusionChartConfig fusionChartConfig = new FusionChartConfig();
		List<FusionChartCategory> categories = new ArrayList<FusionChartCategory>();
		List<FusionChartDataset> datasets = new ArrayList<FusionChartDataset>();
		fusionChart.setChart(fusionChartConfig);
		fusionChart.setCategories(categories);
		fusionChart.setDataset(datasets);
		
		String caption = "微信新增粉丝增长量曲线";
		String subcaption = "Source: Gionee Electrical Business Operation Center.";
		String xAxisName = "统计日期";
		String yAxisName = "新增粉丝数";
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
		
		if(null != wxNewFansStatList && wxNewFansStatList.size() > 0) {
			String bizDate = "";
			String newFansNum = "";
			
			// category for newFans
			FusionChartCategory newFansCategory = new FusionChartCategory();
			String newFansCgValue = "";
			categories.add(newFansCategory);
			
			// DataSet for activeUser
			FusionChartDataset newFansDataset = new FusionChartDataset();
			String newFansSeriesName = "新增粉丝数";
			String newFansData = "";
			newFansDataset.setSeriesname(newFansSeriesName);
			datasets.add(newFansDataset);
			
			for(int i = 0; i < wxNewFansStatList.size(); i++) {
				Map<String, Object> newFansStat = wxNewFansStatList.get(i);
				bizDate = (String) newFansStat.get("bizDate");
				if(null != newFansStat.get("newFansNum")) {
					newFansNum = ((BigDecimal) newFansStat.get("newFansNum")).toString();
				}
				
				if(i < wxNewFansStatList.size() - 1) {
					newFansCgValue += bizDate + "|";
					newFansData += newFansNum + "|";
				} else {
					newFansCgValue += bizDate;
					newFansData += newFansNum;
				}
			}
			newFansCategory.setCategory(newFansCgValue);
			newFansDataset.setData(newFansData);
		}
		
		printJson2(fusionChart, Boolean.TRUE, Boolean.FALSE);
		
		logger.debug("WxAccessLogAction.getWxNewFansStatByJSON invoke success.");
	}
	
	/**
	 * 微信应用活跃用户统计视图
	 * @return String
	 */
	public String activeUserStatView() {

		String statRate = StatByDay;
		if(StringUtils.isNotBlank(statParams.get("statRate"))) {
			statRate = statParams.get("statRate");
		}
		
		List<Map<String, Object>> wxActiveUserStatList = null;

		try {
			if(StatByMonth.equals(statRate)) {
				//生成查询参数Map
				Map<String, Object> params = genParamMap("activeUser");
				Integer totalRecord = wxAccessLogService.fetchActiveUserStatMonthlyCount(params);
				//根据当前页、总记录数、页大小获得Page
				page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
				//新增Page至参数Map
				setPageInfo2Map(page, params);
				
				wxActiveUserStatList = wxAccessLogService.fetchActiveUserStatMonthlyByPage(params);
			} else {
				//生成查询参数Map
				Map<String, Object> params = genParamMap("activeUser");
				Integer totalRecord = wxAccessLogService.fetchActiveUserStatDailyCount(params);
				//根据当前页、总记录数、页大小获得Page
				page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
				//新增Page至参数Map
				setPageInfo2Map(page, params);
				
				wxActiveUserStatList = wxAccessLogService.fetchActiveUserStatDailyByPage(params);
			}
			
			request.setAttribute("activeUserStatList", wxActiveUserStatList);
			
		} catch (DBException dbex) {
			logger.error("WxAccessLogAction.activeUserStatView found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("WxAccessLogAction.activeUserStatView found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	
	}
	
	/**
	 *  微信应用活跃用户统计数据导出至Excel
	 * @return String
	 */
	public String wxActiveUserStat2Excel() {

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
				Map<String, Object> params = genParamMap("activeUser");
				activeUserStatList = wxAccessLogService.fetchActiveUserStatMonthlyByExample(params);
				// 生成导出数据列名列表
				columnNames = genWxActiveUserStatColNames();
				// 生成导出数据列名变量列表
				columns = genWxActiveUserStatCols();
			} else {
				//生成查询参数Map
				Map<String, Object> params = genParamMap("activeUser");
				activeUserStatList = wxAccessLogService.fetchActiveUserStatDailyByExample(params);
				// 生成导出数据列名列表
				columnNames = genWxActiveUserStatColNames();
				// 生成导出数据列名变量列表
				columns = genWxActiveUserStatCols();
			}
			
			if(CollectionUtils.isNotEmpty(activeUserStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				String sheetName = "";
				String exportDate = dateFormat.format(new Date());
				
				if("byMonth".equals(statRate)) {
					sheetName = "微信每月活跃用户数据统计";
					fileName = "微信活跃用户数统计月报表_" + exportDate;
				} else {
					sheetName = "微信每日活跃用户数据统计";
					fileName = "微信活跃用户数统计日报表_" + exportDate;
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
	 * 按JSON格式获取微信活跃用户数统计报表FusionChart参数
	 */
	public void getWxActiveUserStatByJSON() {
		String statRate = StatByDay;
		if(StringUtils.isNotBlank(statParams.get("statRate"))) {
			statRate = statParams.get("statRate");
		}
		
		List<Map<String, Object>> wxActiveUserStatList = null;
		
		if(StatByMonth.equals(statRate)) {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("activeUser");
			wxActiveUserStatList = wxAccessLogService.fetchActiveUserStatMonthlyByExample(params);
		} else {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("activeUser");
			wxActiveUserStatList = wxAccessLogService.fetchActiveUserStatDailyByExample(params);
		}
		
		FusionChart fusionChart = new FusionChart();
		FusionChartConfig fusionChartConfig = new FusionChartConfig();
		List<FusionChartCategory> categories = new ArrayList<FusionChartCategory>();
		List<FusionChartDataset> datasets = new ArrayList<FusionChartDataset>();
		fusionChart.setChart(fusionChartConfig);
		fusionChart.setCategories(categories);
		fusionChart.setDataset(datasets);
		
		String caption = "微信活跃用户增长量曲线";
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
		
		if(null != wxActiveUserStatList && wxActiveUserStatList.size() > 0) {
			String bizDate = "";
			String activeUserNum = "";
			
			// category for activeUser
			FusionChartCategory activeUserCategory = new FusionChartCategory();
			String activeUserCgValue = "";
			categories.add(activeUserCategory);
			
			// DataSet for activeUser
			FusionChartDataset activeUserDataset = new FusionChartDataset();
			String activeUserSeriesName = "活跃用户数";
			String activeUserData = "";
			activeUserDataset.setSeriesname(activeUserSeriesName);
			datasets.add(activeUserDataset);
			
			for(int i = 0; i < wxActiveUserStatList.size(); i++) {
				Map<String, Object> activeUserStat = wxActiveUserStatList.get(i);
				bizDate = (String) activeUserStat.get("bizDate");
				if(null != activeUserStat.get("activeUserNum")) {
					activeUserNum = ((BigDecimal) activeUserStat.get("activeUserNum")).toString();
				}
				
				if(i < wxActiveUserStatList.size() - 1) {
					activeUserCgValue += bizDate + "|";
					activeUserData += activeUserNum + "|";
				} else {
					activeUserCgValue += bizDate;
					activeUserData += activeUserNum;
				}
			}
			activeUserCategory.setCategory(activeUserCgValue);
			activeUserDataset.setData(activeUserData);
		}
		
		printJson2(fusionChart, Boolean.TRUE, Boolean.FALSE);
		
		logger.debug("WxAccessLogAction.getWxActiveUserStatByJSON invoke success.");
	}
	
	/**
	 * 微信应用用户上行内容统计查询视图
	 * @return String
	 */
	public String upwardCtxStatView() {

		List<Map<String, Object>> wxKwdStatList = null;
		List<Map<String, Object>> wxNotKwdStatList = null;

		try {
			//生成查询参数Map
			Map<String, Object> params_1 = genParamMap("keyword");
			Integer totalRecord = wxAccessLogService.fetchUpwardTextStatCount(params_1);
			//根据当前页、总记录数、页大小获得Page
			onePage = Page.genPage(onePage.getCurrentPage(), totalRecord, onePage.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(onePage, params_1);
			wxKwdStatList = wxAccessLogService.fetchUpwardTextStatByPage(params_1);
			
			//生成查询参数Map
			Map<String, Object> params_2 = genParamMap("nonKeyword");
			totalRecord = wxAccessLogService.fetchUpwardTextStatCount(params_2);
			//根据当前页、总记录数、页大小获得Page
			twoPage = Page.genPage(twoPage.getCurrentPage(), totalRecord, twoPage.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(twoPage, params_2);
			wxNotKwdStatList = wxAccessLogService.fetchUpwardTextStatByPage(params_2);
			
			request.setAttribute("keywordStatList", wxKwdStatList);
			request.setAttribute("nonkeywordStatList", wxNotKwdStatList);
		} catch (DBException dbex) {
			logger.error("WxAccessLogAction.activeUserStatView found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("WxAccessLogAction.activeUserStatView found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	
	}
	
	/**
	 * 微信用户上行内容数据统计导出至Excel
	 * @return String
	 */
	public String wxUpwardCtxStat2Excel() {

		List<Map<String, Object>> wxKwdStatList = null;
		List<Map<String, Object>> wxNotKwdStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params_1 = genParamMap("keyword");
			wxKwdStatList = wxAccessLogService.fetchUpwardTextStatByExample(params_1);
			
			//生成查询参数Map
			Map<String, Object> params_2 = genParamMap("nonKeyword");
			wxNotKwdStatList = wxAccessLogService.fetchUpwardTextStatByExample(params_2);
			
			// 生成导出数据列名列表
			columnNames = genWxUpwardCtxStatColNames();
			// 生成导出数据列名变量列表
			columns = genWxUpwardCtxStatCols();
			
			// 按Sheet数据列表
			Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
			
			if((CollectionUtils.isNotEmpty(wxKwdStatList) || CollectionUtils.isNotEmpty(wxNotKwdStatList)) 
					&& CollectionUtils.isNotEmpty(columnNames) && CollectionUtils.isNotEmpty(columns)) {
				String sheetName = "";
				
				if(CollectionUtils.isNotEmpty(wxKwdStatList)) {
					sheetName = "关键字上行排行数据统计";
					sheetDataList.put(sheetName, wxKwdStatList);
				}
				
				if(CollectionUtils.isNotEmpty(wxNotKwdStatList)) {
					sheetName = "非关键字上行排行数据统计";
					sheetDataList.put(sheetName, wxNotKwdStatList);
				}
				
				String exportDate = dateFormat.format(new Date());
				fileName = "微信用户上行内容数据统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("WxAccessLogAction.wxUpwardCtxStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("WxAccessLogAction.wxUpwardCtxStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("WxAccessLogAction.wxUpwardCtxStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("WxAccessLogAction.wxUpwardCtxStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	
	}
	
	/**
	 * 处理统计数查询结果
	 * 某日空数据默认置为0
	 * @param data
	 * @param params
	 * @param type, {"newFansNum", "activeUserNum"}
	 * @return List<Map<String, Object>>
	 * @throws ParseException
	 */
	private List<Map<String, Object>> processStatData(List<Map<String, Object>> data, Map<String, Object> params, String type) throws ParseException {
		List<Map<String, Object>> newData = new ArrayList<Map<String, Object>>();
		String sDate, eDate = "";
		
		//查询参数为空，初始化开始、结束日期
		sDate = "2013-08-08";
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-1);
		eDate = dateFormat.format(cal.getTime());
		
		//查询参数不为空，再次初始化开始、结束日期
		if(StringUtils.isNotBlank((String)params.get("beginDate"))) {
			sDate = (String) params.get("beginDate");
		}
		if(StringUtils.isNotBlank((String)params.get("endDate"))) {
			eDate = (String) params.get("endDate");
		}
		
		int days = daysBetween(sDate, eDate) + 1;
		boolean isFound = false;
		
		for(int i = 0; i < days; i++) {
			String bizDate = addDays(sDate, i);
			if(CollectionUtils.isNotEmpty(data)) {
				for(Map<String,Object> map : data) {
					if(bizDate.equals(map.get("bizDate"))) {
						newData.add(map);
						map.put("rowNum", new BigDecimal(i + 1));
						isFound = true;
						continue;
					}
				}
				if(!isFound) {
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("bizDate", bizDate);
					map.put(type, BigDecimal.ZERO);
					map.put("rowNum", new BigDecimal(i + 1));
					newData.add(map);
				}
				isFound = false;
			} else {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("bizDate", bizDate);
				map.put(type, BigDecimal.ZERO);
				map.put("rowNum", new BigDecimal(i + 1));
				newData.add(map);
			}
		}
		
		return newData;
	}
	
	/**
	 * 计算间隔天数
	 * @param sDate
	 * @param eDate
	 * @return
	 * @throws ParseException
	 */
	private int daysBetween(String sDate, String eDate) throws ParseException {
		Date startDate = dateFormat.parse(sDate);
		Date endDate = dateFormat.parse(eDate);
		int days = (int) ((endDate.getTime() - startDate.getTime())/(1000*60*60*24));
		
		return days;
	}
	
	/**
	 * 增加天数
	 * @param date
	 * @param days
	 * @return
	 * @throws ParseException
	 */
	private String addDays(String date, int days) throws ParseException {
		Date dt = dateFormat.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);;
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + days);
		
		String result = dateFormat.format(cal.getTime());
		
		return result;
	}
	
	/**
	 * 生成新增粉丝数统计报表导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genWxNewFansStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("bizDate");
		cols.add("newFansNum");
		
		return cols;
	}
	
	/**
	 * 生成新增粉丝数统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genWxNewFansStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计日期 ");
		colNames.add("新增粉丝数");
		
		return colNames;
	}
	
	/**
	 * 生成新增粉丝数统计报表导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genWxActiveUserStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("bizDate");
		cols.add("activeUserNum");
		
		return cols;
	}
	
	/**
	 * 生成新增粉丝数统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genWxActiveUserStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计日期 ");
		colNames.add("活跃用户数");
		
		return colNames;
	}
	
	/**
	 * 生成用户上行内容统计报表导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genWxUpwardCtxStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("content");
		cols.add("count");
		
		return cols;
	}
	
	/**
	 * 生成用户上行内容统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genWxUpwardCtxStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("内容 ");
		colNames.add("上行次数");
		
		return colNames;
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
	 * 按条件查询微信公众帐号图片下载排行
	 * 
	 * @return
	 */
	public String selectWxImageDownloadRanks() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("endTime", StringUtils.defaultIfEmpty(endTime, null));
		try {
			// 查询图片下载总次数
		    totalCount = wxAccessLogService.selectWxImageDownloadTotalCount(paramsMap);
			// 查询总记录
		    Integer totalRecord = wxAccessLogService.selectWxImageDownloadRanksCount(paramsMap);
			if (totalRecord == null || totalRecord.intValue() == 0) {
				return SUCCESS;
			}
			// 根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			// 设置分页起始值
			paramsMap.put("startRec", page.getStartRec());
			paramsMap.put("endRec", page.getEndRec());
			// 查询列表
			wxImageDownloadRanks = wxAccessLogService.selectWxImageDownloadRanks(paramsMap);
			if(!CollectionUtils.isEmpty(wxImageDownloadRanks)){
				for(Map<String,Object> rank:wxImageDownloadRanks){
					if(StringUtils.isBlank(rank.get("imgUrl")+"")){
						rank.put("imgUrl", DataStatConstant.WEIXIN_IMG_URL+"default.jpg");
					}else{
						rank.put("imgUrl", DataStatConstant.WEIXIN_IMG_URL+rank.get("imgUrl"));
					}
				}
			}
		} catch (ServiceException se) {
			logger.error("selectWxImageDownloadRanks error:" + se.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("selectWxImageDownloadRanks error:" + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 导出excel微信公众帐号图片下载排行
	 * 
	 * @return
	 */
	public void wxImageDownloadRanks2Excel() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		// 设置分页起始值
		paramsMap.put("startRec", 1);
		paramsMap.put("endRec", Integer.MAX_VALUE);
		paramsMap.put("endTime", StringUtils.defaultIfEmpty(endTime, null));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "微信公众帐号图片下载排行_" + sdf.format(new Date());
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("wxImageDownloadRanks2Excel UnsupportedEncodingException:" + e.getMessage());
		}
		int columnNum = 8;
		ExcelUtils excelUtils = new ExcelUtils(response,fileName, columnNum);
		try {
			// 查询列表
			wxImageDownloadRanks = wxAccessLogService.selectWxImageDownloadRanks(paramsMap);
			if(!CollectionUtils.isEmpty(wxImageDownloadRanks)){
				for(Map<String,Object> rank:wxImageDownloadRanks){
					if(StringUtils.isBlank(rank.get("imgUrl")+"")){
						rank.put("imgUrl", DataStatConstant.WEIXIN_IMG_URL+"default.jpg");
					}else{
						rank.put("imgUrl", DataStatConstant.WEIXIN_IMG_URL+rank.get("imgUrl"));
					}
				}
			}
			excelUtils.wxImageDownloadRanks2Excel(wxImageDownloadRanks);
		} catch (Exception e) {
			logger.error("wxImageDownloadRanks2Excel error:" + e.getMessage());
		}
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
		}
		
		if(StringUtils.isNotBlank(statParams.get("beginDate"))) {
			params.put("beginDate", statParams.get("beginDate"));			
		}
		if(StringUtils.isNotBlank(statParams.get("endDate"))) {
			params.put("endDate", statParams.get("endDate"));
		}
		
		if("activeUser".equals(condition)) {
			params.put("msgType", "event");
			
		}else if("newFans".equals(condition)) {
			params.put("msgType", "event");
			params.put("eventType", "");
			
		} else if("keyword".equals(condition)) {
			params.put("msgType", "text");
			params.put("keywordType", 0);
			
		} else if("nonKeyword".equals(condition)) {
			params.put("msgType", "text");
			params.put("keywordType", 1);
		} 
		
		return params;
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

	public List<Map<String, Object>> getWxImageDownloadRanks() {
		return wxImageDownloadRanks;
	}

	public void setWxImageDownloadRanks(
			List<Map<String, Object>> wxImageDownloadRanks) {
		this.wxImageDownloadRanks = wxImageDownloadRanks;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Page getOnePage() {
		return onePage;
	}

	public void setOnePage(Page onePage) {
		this.onePage = onePage;
	}

	public Page getTwoPage() {
		return twoPage;
	}

	public void setTwoPage(Page twoPage) {
		this.twoPage = twoPage;
	}

}
