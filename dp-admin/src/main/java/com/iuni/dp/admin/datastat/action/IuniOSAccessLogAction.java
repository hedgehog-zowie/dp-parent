package com.iuni.dp.admin.datastat.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.admin.datastat.bean.FusionChart;
import com.iuni.dp.admin.datastat.bean.FusionChartConfig;
import com.iuni.dp.admin.datastat.bean.FusionChartData;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.BusinessException;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.GnAppAccessLogService;

/**
 * IUNI OS Access Log Action
 * @author CaiKe
 * @version dp-admin-V1.0.2
 */
@Controller("iuniOSAccessLogAction")
@Scope("prototype")
public class IuniOSAccessLogAction extends BaseAction {

	private static final long serialVersionUID = 7243264957654413788L;
	
	private final Logger logger = LoggerFactory.getLogger(IuniOSAccessLogAction.class);
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static Calendar calendar = Calendar.getInstance();
	
	@Autowired
	private GnAppAccessLogService gnAppAccessLogService;
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	/**
	 * IUNI OS 累计总用户数相关统计查询
	 * @return String
	 */
	public String iuniosTotalUserStatView() {
		List<Map<String, Object>> totalUserStatList = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("iuniosTotalUserStat");
			
			Integer totalRecord = gnAppAccessLogService.queryIuniOSTotalUserCount(params);
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			totalUserStatList = gnAppAccessLogService.queryIuniOSTotalUserByPage(params);
			
			request.setAttribute("totalUserStatList", totalUserStatList);
			
		} catch (DBException dbex) {
			logger.error("IuniOSAccessLogAction.iuniosTotalUserStatView found DBException", dbex);
			return ERROR;
		}catch (Exception ex) {
			logger.error("IuniOSAccessLogAction.iuniosTotalUserStatView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI OS 累计总用户数相关统计列表导出至Excel
	 * @return String
	 */
	public String iuniosTotalUserStat2Excel() {
		List<Map<String, Object>> totalUserStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("iuniosTotalUserStat");
			totalUserStatList = gnAppAccessLogService.queryIuniOSTotalUserByExample(params);
			// 生成导出数据列名列表
			columnNames = genIuniosTotalUserStatColNames();
			// 生成导出数据列名变量列表
			columns = genIuniosTotalUserStatCols();
			
			if(CollectionUtils.isNotEmpty(totalUserStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "IUNI OS累计总用户数数据统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "IUNI OS累计总用户数数据统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, totalUserStatList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniOSAccessLogAction.iuniosTotalUserStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniOSAccessLogAction.iuniosTotalUserStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniOSAccessLogAction.iuniosTotalUserStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniOSAccessLogAction.iuniosTotalUserStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * IUNI OS 用户数据按时间类型统计列表
	 * @return String
	 */
	public String iuniosUserNumOnPeriodStatView() {
		List<Map<String, Object>> userNumOnPeriodStatList = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("iuniosUserNumOnPeriodStat");
			
			if(null == params.get("periodType") || StringUtils.isBlank((String)params.get("periodType"))) {
				logger.error("IuniOSAccessLogAction.iuniosUserNumOnPeriodStatView found Parameter Error.");
				return ERROR;
			}
			
			String periodType = (String) params.get("periodType");
			if("daily".equals(periodType)) {
				Integer totalRecord = gnAppAccessLogService.queryIuniOSUserStatCount(params);
				//根据当前页、总记录数、页大小获得Page
				page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
				//新增Page至参数Map
				setPageInfo2Map(page, params);
				
				userNumOnPeriodStatList = gnAppAccessLogService.queryIuniOSUserStatByPage(params);
			} else if ("weekly".equals(periodType) || "monthly".equals(periodType)
					|| "30days".equals(periodType)) {
				Integer totalRecord = gnAppAccessLogService.queryIuniOSUserStatOnPeriodCount(params);
				//根据当前页、总记录数、页大小获得Page
				page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
				//新增Page至参数Map
				setPageInfo2Map(page, params);
				
				userNumOnPeriodStatList = gnAppAccessLogService.queryIuniOSUserStatOnPeriodByPage(params);
			} else {
				logger.error("IuniOSAccessLogAction.iuniosUserNumOnPeriodStatView found Parameter Error.");
				return ERROR;
			}
			
			request.setAttribute("dailyUserStatList", userNumOnPeriodStatList);
			
		} catch (DBException dbex) {
			logger.error("IuniOSAccessLogAction.iuniosUserNumOnPeriodStatView found DBException", dbex);
			return ERROR;
		}catch (Exception ex) {
			logger.error("IuniOSAccessLogAction.iuniosUserNumOnPeriodStatView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	} 
	
	/**
	 * IUNI OS 用户数据按时间类型统计列表导出至Excel
	 * @return String
	 */
	public String iuniosUserNumOnPeriodStat2Excel() {
		List<Map<String, Object>> userNumOnPeriodStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("iuniosUserNumOnPeriodStat");
			if(null == params.get("periodType") || StringUtils.isBlank((String)params.get("periodType"))) {
				logger.error("IuniOSAccessLogAction.iuniosUserNumOnPeriodStatView found Parameter Error.");
				return ERROR;
			}
			
			String periodType = (String) params.get("periodType");
			if("daily".equals(periodType)) {
				userNumOnPeriodStatList = gnAppAccessLogService.queryIuniOSUserStatByExample(params);
				
			} else if ("weekly".equals(periodType) || "monthly".equals(periodType)
					|| "30days".equals(periodType)) {
				userNumOnPeriodStatList = gnAppAccessLogService.queryIuniOSUserStatOnPeriodByExample(params);
				
			} else {
				logger.error("IuniOSAccessLogAction.iuniosUserNumOnPeriodStatView found Parameter Error.");
				return ERROR;
			}
			
			// 生成导出数据列名列表
			columnNames = genIuniosUserNumOnPeriodStatColNames();
			// 生成导出数据列名变量列表
			columns = genIuniosUserNumOnPeriodStatCols();
			
			if(CollectionUtils.isNotEmpty(userNumOnPeriodStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "IUNI OS用户数据统计报表";
				if("daily".equals(periodType)) {
					sheetName = "IUNI OS用户数据每日统计报表";
				} else if("weekly".equals(periodType)) {
					sheetName = "IUNI OS用户数据每周统计报表";
				} else if("monthly".equals(periodType)) {
					sheetName = "IUNI OS用户数据每月统计报表";
				} else if("30days".equals(periodType)) {
					sheetName = "IUNI OS用户数据每30日统计报表";
				}
				
				String exportDate = dateFormat.format(new Date());
				fileName = sheetName + "_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, userNumOnPeriodStatList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniOSAccessLogAction.iuniosUserNumOnPeriodStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniOSAccessLogAction.iuniosUserNumOnPeriodStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniOSAccessLogAction.iuniosUserNumOnPeriodStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniOSAccessLogAction.iuniosUserNumOnPeriodStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * IUNI OS 用户数版本分布统计列表
	 * @return String
	 */
	public String iuniosVersionDistributeView() {
		List<Map<String, Object>> versionDistributeList = null;
		
		try {
			// 生成查询参数Map
			Map<String, Object> params = genParamMap("iuniOSVersionDistribute");
			
			Integer totalRecord = gnAppAccessLogService.queryIuniOSVersionDistributeCount(params);
			// 根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			// 新增Page至参数Map
			setPageInfo2Map(page, params);
			
			versionDistributeList = gnAppAccessLogService.queryIuniOSVersionDistributeByPage(params);
			
			request.setAttribute("versionDistributeList", versionDistributeList);
			
		} catch (DBException dbex) {
			logger.error("IuniOSAccessLogAction.iuniOSVersionDistributeView found DBException", dbex);
			return ERROR;
		} catch (BusinessException bsex) {
			logger.error("IuniOSAccessLogAction.iuniOSVersionDistributeView found BusinessException", bsex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("IuniOSAccessLogAction.iuniOSVersionDistributeView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI OS 用户数版本分布统计列表导出至Excel
	 * @return String
	 */
	public String iuniosVersionDistribute2Excel() {
		List<Map<String, Object>> versionDistributeList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("iuniOSVersionDistribute");
			
			versionDistributeList = gnAppAccessLogService.queryIuniOSVersionDistributeByExample(params);
			// 生成导出数据列名列表
			columnNames = genIuniOSVersionDistributeColNames();
			// 生成导出数据列名变量列表
			columns = genIuniOSVersionDistributeCols();
			
			if(CollectionUtils.isNotEmpty(versionDistributeList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "IUNI OS 用户数版本分布统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "IUNI OS 用户数版本分布统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, versionDistributeList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniOSAccessLogAction.iuniosVersionDistribute2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniOSAccessLogAction.iuniosVersionDistribute2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniOSAccessLogAction.iuniosVersionDistribute2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniOSAccessLogAction.iuniosVersionDistribute2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * 按JSON格式获取IUNI OS用户数版本分布统计报表FusionChart参数
	 */
	public void getIuniosVersionDistributeByJSON() {
		List<Map<String, Object>> versionDistributeList = null;
		
		//生成查询参数Map
		Map<String, Object> params = genParamMap("iuniOSVersionDistribute");
		versionDistributeList = gnAppAccessLogService.queryIuniOSVersionDistributeByExample(params);
		
		FusionChart fusionChart = new FusionChart();
		FusionChartConfig fusionChartConfig = new FusionChartConfig();
		List<FusionChartData> datas = new ArrayList<FusionChartData>();
		fusionChart.setChart(fusionChartConfig);
		fusionChart.setData(datas);
		
		String caption = "IUNI OS用户数版本分布图";
		String subcaption = "Source: IUNI Electrical Business Operation Center.";
		
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
		
		if(CollectionUtils.isNotEmpty(versionDistributeList)) {
			String apkVersion = "";
			BigDecimal userNum = BigDecimal.ZERO;
			String userNumStr = "0";
			String isSliced = "0";
			
			BigDecimal otherUserNum = BigDecimal.ZERO;
			int chartDataCount = 0;
			for(Map<String,Object> map : versionDistributeList) {
				++chartDataCount;
				apkVersion = (String) map.get("apkVersion");
				userNum = (BigDecimal) map.get("userNum");
				userNumStr = userNum.toString();
				
				if(chartDataCount == 1) {
					isSliced = "1";
				} else {
					isSliced = "0";
				}
				
				if(chartDataCount <= 3) {
					FusionChartData data = new FusionChartData();
					data.setLabel(apkVersion);
					data.setValue(userNumStr);
					data.setIssliced(isSliced);
					datas.add(data);
				} else if (chartDataCount > 3
						&& chartDataCount < versionDistributeList.size()) {
					otherUserNum = otherUserNum.add(userNum);
				} else {
					FusionChartData data = new FusionChartData();
					data.setLabel("其他版本");
					data.setValue(otherUserNum.add(userNum).toString());
					data.setIssliced(isSliced);
					datas.add(data);
				}
			}
		}
		
		printJson2(fusionChart, Boolean.TRUE, Boolean.FALSE);
		
		logger.debug("GnAppAccessLogAction.getGnAppLaunchNumDistributeByJSON invoke success.");
	}
	
	/**
	 * IUNI OS 用户数区域分布统计列表
	 * @return String
	 */
	public String iuniosAreaDistributeView() {
		List<Map<String, Object>> areaDistributeList = null;
		
		try {
			// 生成查询参数Map
			Map<String, Object> params = genParamMap("iuniosAreaDistribute");
			
			Integer totalRecord = gnAppAccessLogService.queryIuniOSAreaDistributeCount(params);
			// 根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			// 新增Page至参数Map
			setPageInfo2Map(page, params);
			
			areaDistributeList = gnAppAccessLogService.queryIuniOSAreaDistributeByPage(params);
			
			request.setAttribute("areaDistributeList", areaDistributeList);
			
		} catch (DBException dbex) {
			logger.error("IuniOSAccessLogAction.iuniosAreaDistributeView found DBException", dbex);
			return ERROR;
		} catch (BusinessException bsex) {
			logger.error("IuniOSAccessLogAction.iuniosAreaDistributeView found BusinessException", bsex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("IuniOSAccessLogAction.iuniosAreaDistributeView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI OS 用户数区域分布统计列表导出至Excel
	 * @return String
	 */
	public String iuniosAreaDistribute2Excel() {
		List<Map<String, Object>> areaDistributeList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("iuniosAreaDistribute");
			
			areaDistributeList = gnAppAccessLogService.queryIuniOSAreaDistributeByExample(params);
			// 生成导出数据列名列表
			columnNames = genIuniOSAreaDistributeColNames();
			// 生成导出数据列名变量列表
			columns = genIuniOSAreaDistributeCols();
			
			if(CollectionUtils.isNotEmpty(areaDistributeList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "IUNI OS 用户数区域分布统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "IUNI OS 用户数区域分布统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, areaDistributeList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniOSAccessLogAction.iuniosAreaDistribute2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniOSAccessLogAction.iuniosAreaDistribute2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniOSAccessLogAction.iuniosAreaDistribute2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniOSAccessLogAction.iuniosAreaDistribute2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * 按JSON格式获取IUNI OS用户数区域分布统计报表FusionChart参数
	 */
	public void getIuniosAreaDistributeByJSON() {
		List<Map<String, Object>> areaDistributeList = null;
		
		//生成查询参数Map
		Map<String, Object> params = genParamMap("iuniosAreaDistribute");
		areaDistributeList = gnAppAccessLogService.queryIuniOSAreaDistributeByExample(params);
		
		FusionChart fusionChart = new FusionChart();
		FusionChartConfig fusionChartConfig = new FusionChartConfig();
		List<FusionChartData> datas = new ArrayList<FusionChartData>();
		fusionChart.setChart(fusionChartConfig);
		fusionChart.setData(datas);
		
		String caption = "IUNI OS用户区域分布图";
		String subcaption = "Source: IUNI Electrical Business Operation Center.";
		
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
		
		if(CollectionUtils.isNotEmpty(areaDistributeList)) {
			String area = "";
			BigDecimal userNum = BigDecimal.ZERO;
			String userNumStr = "0";
			String isSliced = "0";
			
			BigDecimal otherUserNum = BigDecimal.ZERO;
			int chartDataCount = 0;
			for(Map<String,Object> map : areaDistributeList) {
				++chartDataCount;
				area = (String) map.get("country")+(String) map.get("province")+(String) map.get("city");
				userNum = (BigDecimal) map.get("userNum");
				userNumStr = userNum.toString();
				
				if(chartDataCount == 1) {
					isSliced = "1";
				} else {
					isSliced = "0";
				}
				
				if(chartDataCount <= 5) {
					FusionChartData data = new FusionChartData();
					data.setLabel(area);
					data.setValue(userNumStr);
					data.setIssliced(isSliced);
					datas.add(data);
				} else if (chartDataCount > 5
						&& chartDataCount < areaDistributeList.size()) {
					otherUserNum = otherUserNum.add(userNum);
				} else {
					FusionChartData data = new FusionChartData();
					data.setLabel("其他区域");
					data.setValue(otherUserNum.add(userNum).toString());
					data.setIssliced(isSliced);
					datas.add(data);
				}
			}
		}
		
		printJson2(fusionChart, Boolean.TRUE, Boolean.FALSE);
		
		logger.debug("GnAppAccessLogAction.getGnAppLaunchNumDistributeByJSON invoke success.");
	}
	
	/**
	 * 获取IUNI OS应用名列表
	 */
	public void getIuniOSAppNamesByJSON() {
		List<Map<String, Object>> appNames = null;
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("IuniOSAppNames");
			appNames = gnAppAccessLogService.queryIuniOSAppNames(params);
			
		} catch(DBException dbex) {
			logger.error("IuniOSAccessLogAction.getIuniOSAppNamesByJSON found DBException.");
			
		} catch (Exception e) {
			logger.error("IuniOSAccessLogAction.getIuniOSAppNamesByJSON found Exception.");
		}
		
		printJson2(appNames, false, false);
	}
	
	/**
	 * 获取IUNI OS版本号列表
	 */
	public void getIuniOSApkVersionsByJSON() {
		List<Map<String, Object>> apkVersions = null;
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("IuniOSApkVersions");
			apkVersions = gnAppAccessLogService.queryIuniOSApkVersions(params);
			
		} catch(DBException dbex) {
			logger.error("IuniOSAccessLogAction.getIuniOSApkVersionsByJSON found DBException.");
			
		} catch (Exception e) {
			logger.error("IuniOSAccessLogAction.getIuniOSApkVersionsByJSON found Exception.");
		}
		
		printJson2(apkVersions, false, false);
	}
	
	/**
	 * 生成查询参数Map
	 * @return Map<String, Object>
	 */
	private Map<String, Object> genParamMap(String condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		if("default".equals(flag)) {
			if("iuniosMonthlyUserStat".equals(condition)) {
				calendar.setTime(new Date());
				int curYear = calendar.get(Calendar.YEAR);
				int curMonth = calendar.get(Calendar.MONTH)+1;
				String curMonthParam = curYear + "-" + curMonth;
				statParams.put("beginDate", curMonthParam);
				statParams.put("endDate", curMonthParam);
			} else {
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
			
			// 设置APP应用默认值
			statParams.put("channelName", "IUNI OS");
			
			// 设置periodType默认值
			if("iuniosUserNumOnPeriodStat".equals(condition)) {
				statParams.put("periodType", "daily");
			}
		}
		
		if("iuniosUserNumOnPeriodStat".equals(condition)) {
			String beginDate = statParams.get("beginDate");
			String endDate = statParams.get("endDate");
			String periodType = statParams.get("periodType");
			params.put("periodType", periodType);
			
			// 计算时间区间查询参数
			List<Map<String, Object>> periodItems = calcPeriodItems(beginDate, endDate, periodType);
			params.put("periodItems", periodItems);
		}
		
		if("iuniosWeeklyUserStat".equals(condition)) {
			String beginDate = statParams.get("beginDate");
			String endDate = statParams.get("endDate");
			
			// 计算一个时间段内的每周的起始日期，并返回起始日期List
			List<String> beginDates = calcWeekStartDays(beginDate, endDate);
			params.put("beginDates", beginDates);
		} else if("iuniosMonthlyUserStat".equals(condition)) {
			String beginDate = statParams.get("beginDate");
			String endDate = statParams.get("endDate");
			
			// 计算一个时间段内每月的相应查询参数
			List<Map<String, Object>> monthItems = calcMonthItems4Query(beginDate, endDate);
			params.put("monthItems", monthItems);
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
		
		if("iuniosAreaDistribute".equals(condition)) {
			if(StringUtils.isNotBlank(statParams.get("country"))) {
				params.put("country", statParams.get("country"));			
			}
			
			if(StringUtils.isNotBlank(statParams.get("province"))) {
				params.put("province", statParams.get("province"));
			}
			
			if(StringUtils.isNotBlank(statParams.get("city"))) {
				params.put("city", statParams.get("city"));			
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
	 * 计算时间区间查询参数
	 * @param beginDate
	 * @param endDate
	 * @param periodType
	 * @return
	 */
	protected List<Map<String, Object>> calcPeriodItems(String beginDate, String endDate, String periodType) {
		List<Map<String, Object>> result = null;
		if("weekly".equals(periodType) || "30days".equals(periodType)) {
			try {
				Date sDate = dateFormat.parse(beginDate);
				Date eDate = dateFormat.parse(endDate);
				
				// 结束天数计算在内
				calendar.setTime(eDate);
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				eDate = calendar.getTime();
				int days = Integer.parseInt(String.valueOf((eDate.getTime()-sDate.getTime())/(24*3600*1000)));
				
				int addNum = 0;
				int loopNum = 0;
				if("weekly".equals(periodType)) {
					loopNum = 7;
				} else if("30days".equals(periodType)) {
					loopNum = 30;
				}
				
				if(days >= loopNum) {
					result = new ArrayList<Map<String, Object>>();
					addNum = days/loopNum;
					calendar.setTime(sDate);
					for(int i = 0; i < addNum; i++) {
						Map<String, Object> map = new HashMap<String, Object>();
						if(i == 0) {
							calendar.add(Calendar.DAY_OF_MONTH, loopNum-1);
							map.put("beginDate", beginDate);
							map.put("endDate", dateFormat.format(calendar.getTime()));
							map.put("days", loopNum);
						} else {
							calendar.add(Calendar.DAY_OF_MONTH, 1);
							map.put("beginDate", dateFormat.format(calendar.getTime()));
							calendar.add(Calendar.DAY_OF_MONTH, loopNum-1);
							map.put("endDate", dateFormat.format(calendar.getTime()));
							map.put("days", loopNum);
						}
						result.add(map);
					}
				}
				
			} catch (ParseException e) {
				logger.error("IuniOSAccessLogAction.calcPeriodItems found ParseException.", e);
//				throw new BusinessException(e);
			}
			
		} else if("monthly".equals(periodType)) {
			try {
				Date sDate = dateFormat.parse(beginDate+"-01");
				Date eDate = dateFormat.parse(endDate+"-01");
				calendar.setTime(sDate);
				int sMonth = calendar.get(Calendar.MONTH);
				int sYear = calendar.get(Calendar.YEAR);
				calendar.setTime(eDate);
				int eMonth = calendar.get(Calendar.MONTH);
				int eYear = calendar.get(Calendar.YEAR);
				int months = -1;
				if(eYear == sYear) {
					months = eMonth-sMonth+1;
				} else if(eYear > sYear) {
					months = (eYear-sYear-1)*12+(13+eMonth-sMonth);
				}
				
				if(months > 0) {
					result = new ArrayList<Map<String, Object>>();
					
					for(int i = 0; i < months; i++) {
						calendar.setTime(sDate);
						calendar.add(Calendar.MONTH, i);
						int year = calendar.get(Calendar.YEAR);
						int month = calendar.get(Calendar.MONTH)+1;
						int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
						String fromDate = year + "-" + month + "-01";
						String toDate = year + "-" + month + "-" + days;
//						String statDate = year + "-" + month;
						Map<String, Object> map = new HashMap<String, Object>();
						result.add(map);
						map.put("days", days);
						map.put("beginDate", fromDate);
						map.put("endDate", toDate);
					}
				}
				
			} catch (ParseException e) {
				logger.error("IuniOSAccessLogAction.calcMonthItems4Query found ParseException.", e);
//				throw new BusinessException(e);
			}
		} 
		
		return result;
	}
	
	/**
	 * 计算一个时间段内的每周的起始日期，并返回起始日期List
	 */
	private List<String> calcWeekStartDays(String beginDate, String endDate) {
		List<String> result = null;
		try {
			Date sDate = dateFormat.parse(beginDate);
			Date eDate = dateFormat.parse(endDate);
			
			// 结束天数计算在内
			calendar.setTime(eDate);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			eDate = calendar.getTime();
			
			int days = Integer.parseInt(String.valueOf((eDate.getTime()-sDate.getTime())/(24*3600*1000)));
			int addNum = 0;
			if(days >= 7) {
				result = new ArrayList<String>();
				addNum = days/7;
				result.add(beginDate);
				calendar.setTime(sDate);
				for(int i = 1; i < addNum; i++) {
					calendar.add(Calendar.DAY_OF_MONTH, 7);
					result.add(dateFormat.format(calendar.getTime()));
				}
			}
			
		} catch (ParseException e) {
			logger.error("IuniOSAccessLogAction.calcWeekStartDays found ParseException.", e);
//			throw new BusinessException(e);
		}
		
		return result;
	}
	
	/**
	 * 计算一个时间段内每月的相应查询参数
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	private List<Map<String, Object>> calcMonthItems4Query(String beginDate, String endDate) {
		List<Map<String, Object>> list = null;
		try {
			Date sDate = dateFormat.parse(beginDate+"-01");
			Date eDate = dateFormat.parse(endDate+"-01");
			calendar.setTime(sDate);
			int sMonth = calendar.get(Calendar.MONTH);
			int sYear = calendar.get(Calendar.YEAR);
			calendar.setTime(eDate);
			int eMonth = calendar.get(Calendar.MONTH);
			int eYear = calendar.get(Calendar.YEAR);
			int months = -1;
			if(eYear == sYear) {
				months = eMonth-sMonth+1;
			} else if(eYear > sYear) {
				months = (eYear-sYear-1)*12+(13+eMonth-sMonth);
			}
			
			if(months > 0) {
				list = new ArrayList<Map<String, Object>>();
				
				for(int i = 0; i < months; i++) {
					calendar.setTime(sDate);
					calendar.add(Calendar.MONTH, i);
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH)+1;
					int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
					String fromDate = year + "-" + month + "-01";
					String toDate = year + "-" + month + "-" + days;
					String statDate = year + "-" + month;
					Map<String, Object> map = new HashMap<String, Object>();
					list.add(map);
					map.put("month", statDate);
					map.put("days", days);
					map.put("beginDate", fromDate);
					map.put("endDate", toDate);
				}
			}
			
		} catch (ParseException e) {
			logger.error("IuniOSAccessLogAction.calcMonthItems4Query found ParseException.", e);
//			throw new BusinessException(e);
		}
		
		return list;
	}
	
	/**
	 * 生成IUNI OS累计总用户数统计报表导出数据列名变量列表
	 * @return
	 */
	private List<String> genIuniosTotalUserStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("statDate");
		cols.add("channelName");
		cols.add("appName");
		cols.add("userNum");
		
		return cols;
	}
	
	/**
	 * 生成IUNI OS累计总用户数统计统计报表导出数据列名列表
	 * @return
	 */
	private List<String> genIuniosTotalUserStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计时间");
		colNames.add("渠道名");
		colNames.add("机型");
		colNames.add("累计总用户");
		
		return colNames;
	}
	
	/**
	 * 生成IUNI OS 用户数据(每日用户数、每日新增用户数)每日统计列表导出数据列名变量列表
	 * @return
	 */
	private List<String> genIuniosUserNumOnPeriodStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("statDate");
		cols.add("userNum");
		cols.add("newUserNum");
		
		return cols;
	}
	
	/**
	 * 生成IUNI OS 用户数据按时间类型统计列表导出数据列名列表
	 * @return
	 */
	private List<String> genIuniosUserNumOnPeriodStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计时间");
		colNames.add("用户数");
		colNames.add("新增用户数");
		
		return colNames;
	}
	
	/**
	 * 生成IUNI OS 用户数版本分布统计导出数据列名变量列表
	 * @return
	 */
	private List<String> genIuniOSVersionDistributeCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("statDate");
		cols.add("apkVersion");
		cols.add("userNum");
		cols.add("versionRate");
		
		return cols;
	}
	
	/**
	 * 生成IUNI OS 用户数版本分布统计导出数据列名列表
	 * @return
	 */
	private List<String> genIuniOSVersionDistributeColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计时间");
		colNames.add("版本");
		colNames.add("用户数");
		colNames.add("版本比例");
		
		return colNames;
	}
	
	/**
	 * 生成IUNI OS 用户数区域分布统计导出数据列名变量列表
	 * @return
	 */
	private List<String> genIuniOSAreaDistributeCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("statDate");
		cols.add("country");
		cols.add("province");
		cols.add("city");
		cols.add("userNum");
		cols.add("areaRate");
		
		return cols;
	}
	
	/**
	 * 生成IUNI OS 用户数区域分布统计导出数据列名列表
	 * @return
	 */
	private List<String> genIuniOSAreaDistributeColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计时间");
		colNames.add("国家");
		colNames.add("省区");
		colNames.add("地市");
		colNames.add("用户数");
		colNames.add("区域分布比例");
		
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
