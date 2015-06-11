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
import com.iuni.dp.service.datastat.service.IuniSmsSendlogHisService;

@Controller("iuniSmsSendlogHisAction")
@Scope("prototype")
public class IuniSmsSendlogHisAction extends BaseAction {

	private static final long serialVersionUID = 6267248631349703349L;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private Logger logger = LoggerFactory.getLogger(IuniSmsSendlogHisAction.class);

	@Autowired
	private IuniSmsSendlogHisService iuniSmsSendlogHisService; 
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	/**
	 * IUNI商城流量统计查询视图
	 * @return String
	 */
	public String iuniSmsSendNumStatView() {
		List<Map<String, Object>> smsSendNumSumStatList = null;
		List<Map<String, Object>> smsSendNumDailyStatList = null;
		
		try {
			// 生成查询参数Map
			Map<String, Object> params = genParamMap("iuniSmsSendNumStat");
			
			smsSendNumSumStatList = iuniSmsSendlogHisService.querySendNumSumByExample(params);
			
			Integer totalRecord = iuniSmsSendlogHisService.querySendNumDailyCount(params);
			
			// 根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			// 新增Page至参数Map
			setPageInfo2Map(page, params);
			
			smsSendNumDailyStatList = iuniSmsSendlogHisService.querySendNumDailyByPage(params);
			
			request.setAttribute("smsSendNumSumStatList", smsSendNumSumStatList);
			request.setAttribute("smsSendNumDailyStatList", smsSendNumDailyStatList);
			
		} catch (DBException dbex) {
			logger.error("IuniSmsSendlogHisAction.iuniSmsSendNumStatView found DBException", dbex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("IuniSmsSendlogHisAction.iuniSmsSendNumStatView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI商城流量统计列表导出至Excel
	 * @return String
	 */
	public String iuniSmsSendNumStat2Excel() {
		List<Map<String, Object>> smsSendNumSumStatList = null;
		List<Map<String, Object>> smsSendNumDailyStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			// 生成查询参数Map
			Map<String, Object> params = genParamMap("iuniSmsSendNumStat");
			smsSendNumSumStatList = iuniSmsSendlogHisService.querySendNumSumByExample(params);
			smsSendNumDailyStatList = iuniSmsSendlogHisService.querySendNumDailyByExample(params);
			
			// 合并汇总项
//			netflowDailyStatList.addAll(0, netflowSumByDate);
			
			// 生成导出数据列名列表
			columnNames = genIuniSmsSendNumStatColNames();
			// 生成导出数据列名变量列表
			columns = genIuniSmsSendNumStatCols();
			
			if(CollectionUtils.isNotEmpty(smsSendNumDailyStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName_sum = "IUNI SMS发送量汇总统计";
				String sheetName_daily = "IUNI SMS发送量每日统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "IUNI SMS发送量统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName_sum, smsSendNumSumStatList);
				sheetDataList.put(sheetName_daily, smsSendNumDailyStatList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniSmsSendlogHisAction.iuniSmsSendNumStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniSmsSendlogHisAction.iuniSmsSendNumStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniSmsSendlogHisAction.iuniSmsSendNumStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniSmsSendlogHisAction.iuniSmsSendNumStat2Excel found Exception", ex);
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
		
		if(StringUtils.isNotBlank(statParams.get("processType"))) {
			params.put("processType", statParams.get("processType"));
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
	private List<String> genIuniSmsSendNumStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("statDate");
		cols.add("successSendNum");
		cols.add("failedSendNum");
		
		return cols;
	}
	
	/**
	 * 生成注册用户数统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genIuniSmsSendNumStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计日期");
		colNames.add("发送成功数");
		colNames.add("发送失败数");
		
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
