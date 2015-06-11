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
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.IuniNetflowDailyStatService;

/**
 * IuniNetflowDailyStat Action
 * @author CaiKe
 * @version dp-admin-V1.0.2
 */
@Controller("iuniNetflowDailyStatAction")
@Scope("prototype")
public class IuniNetflowDailyStatAction extends BaseAction {

	private static final long serialVersionUID = -8473609010308770817L;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final String IUNI_URL_DOMAIN = "rd.iuni.com";
	
	private Logger logger = LoggerFactory.getLogger(IuniNetflowDailyStatAction.class);

	@Autowired
	private IuniNetflowDailyStatService iuniNetflowDailyStatService; 
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	/**
	 * IUNI商城流量统计查询视图
	 * @return String
	 */
	public String iuniNetflowDailyStatView() {
		List<Map<String, Object>> netflowSumByDateList = null;
		List<Map<String, Object>> netflowDailyStatList = null;
		
		try {
			// 生成查询参数Map
			Map<String, Object> params = genParamMap("netflowDailyStat");
			
			netflowSumByDateList = iuniNetflowDailyStatService.queryIndsSumByDate(params);
			
			Integer totalRecord = iuniNetflowDailyStatService.queryIndsCount(params);
			
			// 根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			// 新增Page至参数Map
			setPageInfo2Map(page, params);
			
			netflowDailyStatList = iuniNetflowDailyStatService.queryIndsByPage(params);
			
			request.setAttribute("netflowSumByDateList", netflowSumByDateList);
			request.setAttribute("netflowDailyStatList", netflowDailyStatList);
			
		} catch (DBException dbex) {
			logger.error("IuniNetflowDailyStatAction.iuniNetflowDailyStatView found DBException", dbex);
			return ERROR;
		}catch (Exception ex) {
			logger.error("IuniNetflowDailyStatAction.iuniNetflowDailyStatView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI商城流量统计列表导出至Excel
	 * @return String
	 */
	public String iuniNetflowDailyStat2Excel() {
		List<Map<String, Object>> netflowSumByDate = null;
		List<Map<String, Object>> netflowDailyStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			// 生成查询参数Map
			Map<String, Object> params = genParamMap("netflowDailyStat");
			netflowSumByDate = iuniNetflowDailyStatService.queryIndsSumByDate(params);
			netflowDailyStatList = iuniNetflowDailyStatService.queryIndsByExample(params);
			
			// 合并汇总项
//			netflowDailyStatList.addAll(0, netflowSumByDate);
			
			// 生成导出数据列名列表
			columnNames = genNetflowDailyStatColNames();
			// 生成导出数据列名变量列表
			columns = genNetflowDailyStatCols();
			
			if(CollectionUtils.isNotEmpty(netflowDailyStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName_sum = "IUNI商城流量数据汇总统计";
				String sheetName_daily = "IUNI商城流量数据每日统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "IUNI商城流量统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName_sum, netflowSumByDate);
				sheetDataList.put(sheetName_daily, netflowDailyStatList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniNetflowDailyStatAction.iuniNetflowDailyStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniNetflowDailyStatAction.iuniNetflowDailyStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniNetflowDailyStatAction.iuniNetflowDailyStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniNetflowDailyStatAction.iuniNetflowDailyStat2Excel found Exception", ex);
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
		
		if("netflowDailyStat".equals(condition)) {
			params.put("urlDomain", IUNI_URL_DOMAIN);
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
		cols.add("pvPC");
		cols.add("avgVD");
		
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
		colNames.add("人均浏览页面");
		colNames.add("平均访问深度");
		
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
