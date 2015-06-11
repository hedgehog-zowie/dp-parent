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
import com.iuni.dp.service.datastat.service.IuniPageNetflowDailyStatService;

/**
 * IuniPageNetflowDailyStat Action
 * @author CaiKe
 * @version dp-admin-V1.0.2
 */
@Controller("iuniPageNetflowDailyStatAction")
@Scope("prototype")
public class IuniPageNetflowDailyStatAction extends BaseAction {

	private static final long serialVersionUID = -1410332971526922010L;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final String IUNI_URL_DOMAIN = "rd.iuni.com";
	
	private Logger logger = LoggerFactory.getLogger(IuniPageNetflowDailyStatAction.class);
	
	@Autowired
	private IuniPageNetflowDailyStatService iuniPageNetflowDailyStatService;
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	private Page onePage = new Page();
	
	private Page twoPage = new Page();
	
	/**
	 * IUNI商城页面受访数据统计查询视图
	 * @return String
	 */
	public String iuniPageNetflowDailyStatView() {
		List<Map<String, Object>> pageNetflowSumByDateList = null;
		List<Map<String, Object>> pageNetflowDailyStatList = null;
		
		try {
			// 生成查询参数Map
			Map<String, Object> params_sum = genParamMap("pageNetflowDailyStat");
			Map<String, Object> params_daily = genParamMap("pageNetflowDailyStat");
			
			Integer totalRecord_sum = iuniPageNetflowDailyStatService.queryIpndsSumOnDateCount(params_sum);
			Integer totalRecord_daily = iuniPageNetflowDailyStatService.queryIpndsCount(params_daily);
			
			// 根据当前页、总记录数、页大小获得Page
			onePage = Page.genPage(onePage.getCurrentPage(), totalRecord_sum, onePage.getPageSize());
			twoPage = Page.genPage(twoPage.getCurrentPage(), totalRecord_daily, twoPage.getPageSize());
			
			// 新增Page至参数Map
			setPageInfo2Map(onePage, params_sum);
			setPageInfo2Map(twoPage, params_daily);
			
			pageNetflowSumByDateList = iuniPageNetflowDailyStatService.queryIpndsSumOnDateByPage(params_sum);
			pageNetflowDailyStatList = iuniPageNetflowDailyStatService.queryIpndsByPage(params_daily);
			
			request.setAttribute("pageNetflowSumByDateList", pageNetflowSumByDateList);
			request.setAttribute("pageNetflowDailyStatList", pageNetflowDailyStatList);
			
		} catch (DBException dbex) {
			logger.error("IuniPageNetflowDailyStatAction.iuniPageNetflowDailyStatView found DBException", dbex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("IuniPageNetflowDailyStatAction.iuniPageNetflowDailyStatView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI商城页面受访数据统计列表导出至Excel
	 * @return String
	 */
	public String iuniPageNetflowDailyStat2Excel() {
		List<Map<String, Object>> pageNetflowSumByDateList = null;
		List<Map<String, Object>> pageNetflowDailyStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("pageNetflowDailyStat");
			
			pageNetflowSumByDateList = iuniPageNetflowDailyStatService.queryIpndsSumOnDateByExample(params);
			pageNetflowDailyStatList = iuniPageNetflowDailyStatService.queryIpndsByExample(params);
			
			// 生成导出数据列名列表
			columnNames = genPageNetflowDailyStatColNames();
			// 生成导出数据列名变量列表
			columns = genPageNetflowDailyStatCols();
			
			if(CollectionUtils.isNotEmpty(pageNetflowDailyStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName_sum = "IUNI商城页面受访数据汇总统计";
				String sheetName_daily = "IUNI商城页面受访数据每日统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "IUNI商城页面受访数据统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName_sum, pageNetflowSumByDateList);
				sheetDataList.put(sheetName_daily, pageNetflowDailyStatList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniPageNetflowDailyStatAction.iuniPageNetflowDailyStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniPageNetflowDailyStatAction.iuniPageNetflowDailyStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniPageNetflowDailyStatAction.iuniPageNetflowDailyStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniPageNetflowDailyStatAction.iuniPageNetflowDailyStat2Excel found Exception", ex);
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
		
		if("pageNetflowDailyStat".equals(condition)) {
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
	private List<String> genPageNetflowDailyStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("statDate");
		cols.add("pageUrl");
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
	private List<String> genPageNetflowDailyStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计日期");
		colNames.add("受访页面");
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
