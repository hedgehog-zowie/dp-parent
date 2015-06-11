package com.iuni.dp.admin.datastat.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.ReportDataByMonService;

/**
 * IUNI ReportDataByMon Action
 * @author Kenneth.Cai@iuni.com
 * @version ips-V1.1.0
 */
@Controller("iuniReportDataByMonAction")
@Scope("prototype")
public class IuniReportDataByMonAction extends BaseAction {

	private static final long serialVersionUID = -7473640326726740623L;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final String IUNI_URL_DOMAIN = "rd.iuni.com";
	
	private static final String ReportType_PV = "pv";
	
	private static Calendar cal = Calendar.getInstance();
	
	private Logger logger = LoggerFactory.getLogger(IuniReportDataByMonAction.class);
	
	@Autowired
	private ReportDataByMonService reportDataByMon4StatService;
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	/**
	 * IUNI商城流量数据当天实时汇总统计查询视图
	 * @return String
	 */
	public String iuniNetflowSumTodayStatView() {
		List<Map<String, Object>> netflowSumTodayList = null;
		
		try {
			// 生成查询参数Map
			Map<String, Object> params = genParamMap("netflowSumTodayStat");
			
			netflowSumTodayList = reportDataByMon4StatService.queryNetflowSumToday(params);
			
			request.setAttribute("netflowSumTodayList", netflowSumTodayList);
			
		} catch (DBException dbex) {
			logger.error("IuniReportDataByMonAction.iuniNetflowDailyStatView found DBException", dbex);
			return ERROR;
		}catch (Exception ex) {
			logger.error("IuniReportDataByMonAction.iuniNetflowDailyStatView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI商城流量数据当天实时汇总统计列表导出至Excel
	 * @return String
	 */
	public String iuniNetflowSumTodayStat2Excel() {
		List<Map<String, Object>> netflowSumTodayList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			// 生成查询参数Map
			Map<String, Object> params = genParamMap("netflowSumTodayStat");
			netflowSumTodayList = reportDataByMon4StatService.queryNetflowSumToday(params);
			
			// 合并汇总项
//			netflowDailyStatList.addAll(0, netflowSumByDate);
			
			// 生成导出数据列名列表
			columnNames = genNetflowDailyStatColNames();
			// 生成导出数据列名变量列表
			columns = genNetflowDailyStatCols();
			
			if(CollectionUtils.isNotEmpty(netflowSumTodayList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName_sum = "IUNI商城流量数据当天实时汇总统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "IUNI商城流量当天实时汇总统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName_sum, netflowSumTodayList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniReportDataByMonAction.iuniNetflowSumTodayStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniReportDataByMonAction.iuniNetflowSumTodayStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniReportDataByMonAction.iuniNetflowSumTodayStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniReportDataByMonAction.iuniNetflowSumTodayStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * 生成查询参数Map
	 * @return Map<String, Object>
	 */
	private Map<String, Object> genParamMap(String condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		if("default".equals(flag)) {
			String statDate = dateFormat.format(new Date());
			params.put("statDate", statDate);
			
			// 设置日期过滤框默认值
			statParams.put("statDate", statDate);
		}
		
		if("netflowSumTodayStat".equals(condition)) {
			params.put("urlDomain", IUNI_URL_DOMAIN);
			params.put("reportType", ReportType_PV);
			
			cal.setTime(new Date());
			String rptdata_tbl = "T_REPORT_DATA_" + (cal.get(Calendar.MONTH)+1);
			params.put("rptdata_tbl", rptdata_tbl);
		}
		
		if(StringUtils.isNotBlank(statParams.get("statDate"))) {
			params.put("statDate", statParams.get("statDate"));			
		}
		
		return params;
	}
	
	/**
	 * 生成注册用户数统计报表导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genNetflowDailyStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("statDate");
		cols.add("pv");
		cols.add("uv");
		cols.add("vv");
		cols.add("ip");
//		cols.add("pvPC");
//		cols.add("avgVD");
		
		return cols;
	}
	
	/**
	 * 生成注册用户数统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genNetflowDailyStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计日期");
		colNames.add("PV");
		colNames.add("UV");
		colNames.add("VV");
		colNames.add("IP");
//		colNames.add("人均浏览页面");
//		colNames.add("平均访问深度");
		
		return colNames;
	}
	
	/**
	 * 新增Page至参数Map
	 * @param Map<String, Object> params
	 */
//	private void setPageInfo2Map(Page page, Map<String, Object> params) {
//		params.put("startRec", page.getStartRec());
//		params.put("endRec", page.getEndRec());
//	}

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
