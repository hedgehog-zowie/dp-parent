package com.iuni.dp.admin.datastat.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.IuniBuriedPointDailyStatService;

/**
 * IUNI商城前端埋点相关统计 Controller
 * @author Kenneth.Cai@iuni.com
 * @version dp-admin-V1.1.0
 */
@Controller("iuniBuriedPointStatAction")
@Scope("prototype")
public class IuniBuriedPointStatAction extends BaseAction {

	private static final long serialVersionUID = -5313347177855486560L;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static Calendar calendar = Calendar.getInstance();
	
	private Logger logger = LoggerFactory.getLogger(IuniBuriedPointStatAction.class);
	
	@Autowired
	private IuniBuriedPointDailyStatService iuniBuriedPointDailyStatService;
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	/**
	 * IUNI商城前端埋点点击率每日统计 
	 * @return String
	 */
	public String ibpdClickRateStatView() {
		List<Map<String, Object>> ibpdClickRateStatList = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("ibpdClickRate");
			
			Integer totalRecord = iuniBuriedPointDailyStatService.queryIbpdCount(params);
			
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			ibpdClickRateStatList = iuniBuriedPointDailyStatService.queryIbpdByPage(params);
			
			request.setAttribute("ibpdClickRateStatList", ibpdClickRateStatList);
			
		} catch (DBException dbex) {
			logger.error("IuniBuriedPointStatAction.ibpdClickRateStatView found DBException", dbex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("IuniBuriedPointStatAction.ibpdClickRateStatView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI商城前端埋点点击率统计列表导出至Excel
	 * @return String
	 */
	public String ibpdClickRateStat2Excel() {
		List<Map<String, Object>> ibpsClickRateStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("ibpdClickRate");
			ibpsClickRateStatList = iuniBuriedPointDailyStatService.queryIbpdByExample(params);
			// 生成导出数据列名列表
			columnNames = genIbpdClickrateStatColNames();
			// 生成导出数据列名变量列表
			columns = genIbpdClickrateStatCols();
			
			if(CollectionUtils.isNotEmpty(ibpsClickRateStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "IUNI商城埋点位置点击率日明细表";
				String exportDate = dateFormat.format(new Date());
				fileName = sheetName + "_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, ibpsClickRateStatList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniBuriedPointStatAction.ibpdClickRateStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniBuriedPointStatAction.ibpdClickRateStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniBuriedPointStatAction.ibpdClickRateStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniBuriedPointStatAction.ibpdClickRateStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * IUNI商城前端埋点当天实时点击率统计 
	 * @return String
	 */
	public String ibpsClickRateStatView() {
		List<Map<String, Object>> ibpsClickRateStatList = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("ibpsClickRate");
			
			Integer totalRecord = iuniBuriedPointDailyStatService.queryClickRateTodayCount(params);
			
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			ibpsClickRateStatList = iuniBuriedPointDailyStatService.queryClickRateTodayByPage(params);
			
			request.setAttribute("ibpsClickRateStatList", ibpsClickRateStatList);
			
		} catch (DBException dbex) {
			logger.error("IuniBuriedPointStatAction.ibpsClickRateStatView found DBException", dbex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("IuniBuriedPointStatAction.ibpsClickRateStatView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI商城前端埋点当天实时点击率统计列表导出至Excel
	 * @return String
	 */
	public String ibpsClickRateStat2Excel() {
		List<Map<String, Object>> ibpsClickRateStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("ibpsClickRate");
			ibpsClickRateStatList = iuniBuriedPointDailyStatService.queryClickRateTodayByExample(params);
			// 生成导出数据列名列表
			columnNames = genIbpdClickrateStatColNames();
			// 生成导出数据列名变量列表
			columns = genIbpdClickrateStatCols();
			
			if(CollectionUtils.isNotEmpty(ibpsClickRateStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "IUNI商城前端埋点点击率当天实时明细表";
				String exportDate = dateFormat.format(new Date());
				fileName = sheetName + "_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, ibpsClickRateStatList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniBuriedPointStatAction.ibpsClickRateStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniBuriedPointStatAction.ibpsClickRateStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniBuriedPointStatAction.ibpsClickRateStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniBuriedPointStatAction.ibpsClickRateStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * 生成导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genIbpdClickrateStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("statDate");
		cols.add("webSite");
		cols.add("pageName");
		cols.add("pagePosition");
		cols.add("pv");
		cols.add("uv");
		cols.add("ip");
		
		return cols;
	}
	
	/**
	 * 生成导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genIbpdClickrateStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计日期");
		colNames.add("站点信息");
		colNames.add("页面信息");
		colNames.add("页面位置");
		colNames.add("PV");
		colNames.add("UV");
		colNames.add("IP");
		
		return colNames;
	}
	
	/**
	 * 生成查询参数Map
	 * @return Map<String, Object>
	 * @throws ParseException 
	 */
	private Map<String, Object> genParamMap(String condition) throws ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		
		if("default".equals(flag)) {
			calendar.setTime(new Date());
			String curDate = dateFormat.format(calendar.getTime());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
			String eDate = dateFormat.format(calendar.getTime()); 
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-6);
			String sDate = dateFormat.format(calendar.getTime());
			params.put("beginDate", sDate);
			params.put("endDate", eDate);
			
			// 设置日期过滤框默认值
			statParams.put("beginDate", sDate);
			statParams.put("endDate", eDate);
			statParams.put("statDate", curDate);
		} 
		
		if("ibpsClickRate".equals(condition)) {
			calendar.setTime(new Date());
			params.put("curMonth", calendar.get(Calendar.MONTH)+1);
		}
		
		if(StringUtils.isNotBlank(statParams.get("pointType"))) {
			params.put("pointType", statParams.get("pointType"));
		}
		
		if(StringUtils.isNotBlank(statParams.get("beginDate"))) {
			params.put("beginDate", statParams.get("beginDate"));
		}
		
		if(StringUtils.isNotBlank(statParams.get("endDate"))) {
			params.put("endDate", statParams.get("endDate"));
		}
		
		if(StringUtils.isNotBlank(statParams.get("statDate"))) {
			params.put("statDate", statParams.get("statDate"));
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
