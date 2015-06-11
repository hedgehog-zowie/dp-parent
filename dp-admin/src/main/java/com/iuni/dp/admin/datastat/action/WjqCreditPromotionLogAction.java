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
import org.apache.commons.lang.math.NumberUtils;
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
import com.iuni.dp.service.datastat.service.WjqCreditPromotionLogService;

/**
 * 玩机圈用户推广日志相关统计Action
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
@Controller("wjqCreditPromotionLogAction")
@Scope("prototype")
public class WjqCreditPromotionLogAction extends BaseAction {

	private static final long serialVersionUID = -2268939030249116810L;

	private static final Logger logger = LoggerFactory.getLogger(WjqCreditPromotionLogAction.class);
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	@Autowired
	private WjqCreditPromotionLogService wjqCreditPromotionLogService;
	
	/**
	 * 玩机圈用户推广数据统计条件查询视图
	 * @return String
	 */
	public String wjqUserPromotionStatView() {

		List<Map<String, Object>> wjqUserPromotions = null;

		try {
			
			//生成查询参数Map
			Map<String, Integer> condition = new HashMap<String, Integer>();
			condition.put("dynamic_tbl_rptData", 1);
			
			Map<String, Object> params = genParamMap(condition);
			
			if(null != statParams.get("isGroupedByDay") 
					&& "groupedByDay".equals(statParams.get("isGroupedByDay"))) {
				Integer totalRecord = wjqCreditPromotionLogService.fetchWjqUserPromotionCount(params);
				//根据当前页、总记录数、页大小获得Page
				page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
				//新增Page至参数Map
				setPageInfo2Map(page, params);
				
				wjqUserPromotions = wjqCreditPromotionLogService.fetchWjqUserPromotionByPage(params);
			} else {
				Integer totalRecord = wjqCreditPromotionLogService.fetchWjqUserPromotionSumCount(params);
				//根据当前页、总记录数、页大小获得Page
				page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
				//新增Page至参数Map
				setPageInfo2Map(page, params);
				
				wjqUserPromotions = wjqCreditPromotionLogService.fetchWjqUserPromotionSumByPage(params);
			}
			
			request.setAttribute("userPromotions", wjqUserPromotions);
			
		} catch (DBException dbex) {
			logger.error("WjqCreditPromotionLogAction.wjqUserPromotionView found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("WjqCreditPromotionLogAction.wjqUserPromotionView found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	
	}
	
	/**
	 * 玩机圈用户推广数据统计导出至Excel
	 * @return String
	 */
	public String wjqUserPromotionStat2Excel() {

		List<Map<String, Object>> wjqUserPromotions = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Integer> condition = new HashMap<String, Integer>();
			condition.put("dynamic_tbl_rptData", 1);
			
			Map<String, Object> params = genParamMap(condition);
			
			if(null != statParams.get("isGroupedByDay") 
					&& "groupedByDay".equals(statParams.get("isGroupedByDay"))) {
				wjqUserPromotions = wjqCreditPromotionLogService.fetchWjqUserPromotionByExample(params);
			} else {
				wjqUserPromotions = wjqCreditPromotionLogService.fetchWjqUserPromotionSumByExample(params);
			}
			
			// 生成导出数据列名列表
			columnNames = genWjqUserPromotionStatColNames();
			// 生成导出数据列名变量列表
			columns = genWjqUserPromotionStatCols();
			
			if(CollectionUtils.isNotEmpty(wjqUserPromotions) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				String sheetName = "";
				String exportDate = dateFormat.format(new Date());
				
				sheetName = "玩机圈用户推广数据统计";
				fileName = "玩机圈用户推广报表_" + exportDate;
				
				sheetDataList.put(sheetName, wjqUserPromotions);
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("WjqCreditPromotionLogAction.wjqUserPromotionStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("WjqCreditPromotionLogAction.wjqUserPromotionStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("WjqCreditPromotionLogAction.wjqUserPromotionStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("WjqCreditPromotionLogAction.wjqUserPromotionStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	
	}
	
	/**
	 * 玩机圈帖子推广统计数据查询视图
	 * @return String
	 */
	public String wjqPostsPromotionStatView() {

		List<Map<String, Object>> wjqPostsPromotions = null;

		// 帖子主题ID不能为空
		if(StringUtils.isBlank(statParams.get("themeId")) 
				|| !NumberUtils.isDigits(statParams.get("themeId").trim())) {
			return SUCCESS;
		}
		
		try {
			//生成查询参数Map
			Map<String, Integer> condition = new HashMap<String, Integer>();
			condition.put("dynamic_tbl_rptData", 1);
			condition.put("dynamic_bizDate", 1);
			
			Map<String, Object> params = genParamMap(condition);
			Integer totalRecord = wjqCreditPromotionLogService.fetchWjqPostsPromotionCount(params);
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			wjqPostsPromotions = wjqCreditPromotionLogService.fetchWjqPostsPromotionByPage(params);
			
			request.setAttribute("postsPromotions", wjqPostsPromotions);
			
		} catch (DBException dbex) {
			logger.error("WjqCreditPromotionLogAction.wjqPostsPromotionStatView found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("WjqCreditPromotionLogAction.wjqPostsPromotionStatView found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	
	}
	
	/**
	 * 玩机圈帖子推广统计数据导出至Excel
	 * @return String
	 */
	public String wjqPostsPromotionStat2Excel() {

		List<Map<String, Object>> wjqPostsPromotions = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		// 帖子主题ID不能为空
		if(StringUtils.isBlank(statParams.get("themeId")) 
				|| !NumberUtils.isDigits(statParams.get("themeId").trim())) {
			return ERROR;
		}
		
		try {
			//生成查询参数Map
			Map<String, Integer> condition = new HashMap<String, Integer>();
			condition.put("dynamic_tbl_rptData", 1);
			condition.put("dynamic_bizDate", 1);
			
			Map<String, Object> params = genParamMap(condition);
			wjqPostsPromotions = wjqCreditPromotionLogService.fetchWjqPostsPromotionByExample(params);
			// 生成导出数据列名列表
			columnNames = genWjqPostsPromotionStatColNames();
			// 生成导出数据列名变量列表
			columns = genWjqPostsPromotionStatCols();
			
			if(CollectionUtils.isNotEmpty(wjqPostsPromotions) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				String sheetName = "";
				String exportDate = dateFormat.format(new Date());
				
				sheetName = "玩机圈帖子推广数据统计";
				fileName = "玩机圈帖子推广报表_" + exportDate;
				
				sheetDataList.put(sheetName, wjqPostsPromotions);
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("WjqCreditPromotionLogAction.wjqUserPromotionStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("WjqCreditPromotionLogAction.wjqUserPromotionStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("WjqCreditPromotionLogAction.wjqUserPromotionStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("WjqCreditPromotionLogAction.wjqUserPromotionStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	
	}
	
	/**
	 * 玩机圈全站用户推广数据查询预览视图
	 * @return String
	 */
	public void wjqUserPromotionPreviewByJSON() {
		List<Map<String, Object>> promotionPreview = null;
		
		try {
			//生成查询参数Map
			Map<String, Integer> condition = new HashMap<String, Integer>();
			condition.put("dynamic_tbl_rptData", 1);
			condition.put("current_bizDate", 1);
			
			// 当日玩机圈全站用户推广数据统计
			Map<String, Object> params = genParamMap(condition);
			promotionPreview = wjqCreditPromotionLogService.fetchWjqUserPromotionPreview(params);
			promotionPreview.get(0).put("bizDate", "今日(" + dateFormat.format(new Date()) + ")");
			
			// 玩机圈全站用户推广数据统计汇总
			params.remove("bizDate");
			List<Map<String, Object>> list = wjqCreditPromotionLogService.fetchWjqUserPromotionPreview(params);
			list.get(0).put("bizDate", "全部日期");
			promotionPreview.addAll(list);
			
			printJson2(promotionPreview, Boolean.TRUE, Boolean.TRUE);
			
		} catch (DBException dbex) {
			logger.error("WjqCreditPromotionLogAction.wjqUserPromotionPreview found DBException", dbex);
			
		} catch (Exception ex) {
			logger.error("WjqCreditPromotionLogAction.wjqUserPromotionPreview found Exception", ex);
		}
		
	}
	
	/**
	 * 生成玩机圈用户推广数据统计报表导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genWjqUserPromotionStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("bizDate");
		cols.add("userId");
		cols.add("userName");
		cols.add("regNum");
		cols.add("pv");
		cols.add("uv");
		cols.add("ip");
		
		return cols;
	}
	
	/**
	 * 生成玩机圈用户推广数据统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genWjqUserPromotionStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计日期 ");
		colNames.add("用户ID");
		colNames.add("用户名");
		colNames.add("注册数");
		colNames.add("PV");
		colNames.add("UV");
		colNames.add("IP");
		
		return colNames;
	}
	
	/**
	 * 生成玩机圈帖子推广数据统计报表导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genWjqPostsPromotionStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("bizDate");
		cols.add("userId");
		cols.add("userName");
		cols.add("regNum");
		cols.add("pv");
		cols.add("uv");
		cols.add("ip");
		
		return cols;
	}
	
	/**
	 * 生成玩机圈帖子推广数据统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genWjqPostsPromotionStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计日期 ");
		colNames.add("用户ID");
		colNames.add("用户名");
		colNames.add("贡献");
		colNames.add("PV");
		colNames.add("UV");
		colNames.add("IP");
		
		return colNames;
	}
	
	/**
	 * 生成查询参数Map
	 * @return Map<String, Object>
	 */
	private Map<String, Object> genParamMap(Map<String, Integer> condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(null != condition.get("dynamic_tbl_rptData") 
				&& 1 == condition.get("dynamic_tbl_rptData")) {
			Calendar cal = Calendar.getInstance();
			String tbl_rptData = "T_REPORT_DATA_" + (cal.get(Calendar.MONTH) + 1);
			params.put("tbl_rptData", tbl_rptData);
		}
		
		if(null != condition.get("dynamic_bizDate") 
				&& 1 == condition.get("dynamic_bizDate")) {
			String sDate = "1970-01-01";
			String eDate = dateFormat.format(new Date());
			if(StringUtils.isNotBlank(statParams.get("beginDate"))) {
				sDate = statParams.get("beginDate");		
			}
			
			if(StringUtils.isNotBlank(statParams.get("endDate"))) {
				eDate = statParams.get("endDate");
			}
			String dynamic_bizDate = sDate + " 至 " + eDate;
			params.put("dynamic_bizDate", dynamic_bizDate);
		}
		
		if(null != condition.get("current_bizDate") 
				&& 1 == condition.get("current_bizDate")) {
			String current_bizDate = dateFormat.format(new Date());
			params.put("bizDate", current_bizDate);
		}
		
		if("default".equals(flag)) {
			Calendar cal = Calendar.getInstance();
			String sDate = dateFormat.format(cal.getTime());
			String eDate = sDate;
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
		
		if(StringUtils.isNotBlank(statParams.get("userId")) 
				&& NumberUtils.isDigits(statParams.get("userId").trim())) {
			params.put("userId", Long.valueOf(statParams.get("userId").trim()));
		}
		
		if(StringUtils.isNotBlank(statParams.get("themeId")) 
				&& NumberUtils.isDigits(statParams.get("themeId").trim())) {
			params.put("themeId", Long.valueOf(statParams.get("themeId").trim()));
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
