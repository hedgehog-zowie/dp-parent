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
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iuni.dp.admin.common.action.BaseAction;
//import com.gionee.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.GnAppOrderDailyStatService;

/**
 * 金立相关App订单数据统计 Action
 * @author CaiKe
 * @version dp-admin-V1.0.2
 */
@Controller("gnAppOrderDailyStatAction")
@Scope("prototype")
public class GnAppOrderDailyStatAction extends BaseAction {

	private static final long serialVersionUID = 7407458847753856570L;

	private Logger logger = LoggerFactory.getLogger(GnAppOrderDailyStatAction.class);
	
//	private static final String WjqAppName = ParseProperties.getPropertiesValue("WanjiquanApp.name");
//	
//	private static final String ElifeAppName = ParseProperties.getPropertiesValue("ElifeApp.name");
//	
//	private static final String GioneeshopAppName = ParseProperties.getPropertiesValue("GioneeshopApp.name");
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private GnAppOrderDailyStatService gnAppOrderDailyStatService;
	
//	@Autowired
//	private GnAppUsageDailyStatService GnAppUsageDailyStatService;
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	/**
	 * GIONEE APP客户端渠道销售统计数据查询视图
	 * @return String
	 */
	public String gnAppChannelSalesView() {
		List<Map<String, Object>> channelSalesList = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("channelSales");
			
			Integer totalRecord = gnAppOrderDailyStatService.queryGnAppChannelSalesCount(params);
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			channelSalesList = gnAppOrderDailyStatService.queryGnAppChannelSalesByPage(params);
			
			request.setAttribute("channelSalesList", channelSalesList);
			
		} catch (DBException dbex) {
			logger.error("GnAppOrderDailyStatAction.gnAppChannelSalesView found DBException", dbex);
			return ERROR;
		}catch (Exception ex) {
			logger.error("GnAppOrderDailyStatAction.gnAppChannelSalesView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * GIONEE APP客户端渠道销售统计列表导出至Excel
	 * @return String
	 */
	public String gnAppChannelSales2Excel() {
		List<Map<String, Object>> channelSalesList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("channelSales");
			channelSalesList = gnAppOrderDailyStatService.queryGnAppChannelSalesByExample(params);
			// 生成导出数据列名列表
			columnNames = genChannelSalesColNames();
			// 生成导出数据列名变量列表
			columns = genChannelSalesCols();
			
			if(CollectionUtils.isNotEmpty(channelSalesList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "GIONEE APP客户端渠道销售数据统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "GIONEE APP客户端渠道销售统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, channelSalesList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("GnAppOrderDailyStatAction.gnAppChannelSales2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("GnAppOrderDailyStatAction.gnAppChannelSales2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("GnAppOrderDailyStatAction.gnAppChannelSales2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("GnAppOrderDailyStatAction.gnAppChannelSales2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * 生成注册用户数统计报表导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genChannelSalesCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("statDate");
		cols.add("channelName");
		cols.add("launchUserNum");
		cols.add("orderNum");
		cols.add("payNum");
		cols.add("orderRate");
		cols.add("closeRate");
		cols.add("payRate");
		cols.add("salesAmount");
		
		return cols;
	}
	
	/**
	 * 生成注册用户数统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genChannelSalesColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计日期");
		colNames.add("渠道名");
		colNames.add("启动用户");
		colNames.add("订单数");
		colNames.add("支付数");
		colNames.add("下单率");
		colNames.add("成交率");
		colNames.add("支付率");
		colNames.add("销售额");
		
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
