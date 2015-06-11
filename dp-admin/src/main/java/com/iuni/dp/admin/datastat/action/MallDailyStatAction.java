package com.iuni.dp.admin.datastat.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.iuni.dp.service.datastat.service.MallPvuvDailyStatService;

/**
 * 商城日常基础数据每日统计Action
 * 
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
@Controller("mallDailyStatAction")
@Scope("prototype")
public class MallDailyStatAction extends BaseAction {

	private static final long serialVersionUID = 3173971924385841641L;

	private static final Logger logger = LoggerFactory
			.getLogger(MallDailyStatAction.class);

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private MallPvuvDailyStatService mallPvuvDailyStatService;

	private Map<String, String> statParams;
	
	private InputStream excelStream;
	
	private String fileName;
	
	/**
	 * 商城日常基础数据每日统计列表视图 
	 * @return String
	 */
	public String mallDailyStatListView() {

		List<Map<String, Object>> mallDailyStatList = null;

		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap();
			//接收人信息列表总数目分页查询
			Integer totalRecord = mallPvuvDailyStatService.getMallDailyBaseStatCount(params);
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(params);
			
			mallDailyStatList = mallPvuvDailyStatService.getMallDailyBaseStatByPage(params);
			
			request.setAttribute("mallDailyStatList", mallDailyStatList);
			
		} catch (DBException dbex) {
			logger.error("MallDailyStatAction.statSchemeListView found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("MallDailyStatAction.statSchemeListView found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	
	}
	
	/**
	 * 商城日常基础数据每日统计列表导出至Excel
	 * @return String
	 */
	public String mallDailyStatList2Excel() {

		List<Map<String, Object>> mallDailyStatList = null;

		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap();
			mallDailyStatList = mallPvuvDailyStatService.getMallDailyBaseStatByExample(params);
			
			List<String> columnNames = genMallDailyStatColNames();
			
			List<String> columns = genMallDailyStatCols();
			
			if(CollectionUtils.isNotEmpty(mallDailyStatList)) {
				String sheetName = "金立商城日常数据总表";
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4Xssf(columnNames, columns, mallDailyStatList, sheetName);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					String exportDate = dateFormat.format(new Date());
					
					fileName = sheetName + "_" + exportDate;
					fileName = new String(fileName.getBytes(), "ISO8859-1");
					
				} catch (IOException e) {
					logger.error("ExcelUtil.workbook2InputStream found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("ExcelUtil.workbook2InputStream found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("MallDailyStatAction.statSchemeListView found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("MallDailyStatAction.statSchemeListView found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	
	}
	
	/**
	 * 生成查询参数Map
	 * @return Map<String, Object>
	 */
	private Map<String, Object> genParamMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("urlDomain", "rd.gionee.com");
		
		if(null != statParams) {
			if(StringUtils.isNotBlank(statParams.get("pvFrom"))) {
				params.put("pvFrom", statParams.get("pvFrom"));
			}
			if(StringUtils.isNotBlank(statParams.get("pvTo"))) {
				params.put("pvTo", statParams.get("pvTo"));			
			}
			if(StringUtils.isNotBlank(statParams.get("uvFrom"))) {
				params.put("uvFrom", statParams.get("uvFrom"));
			}
			if(StringUtils.isNotBlank(statParams.get("uvTo"))) {
				params.put("uvTo", statParams.get("uvTo"));
			}
			if(StringUtils.isNotBlank(statParams.get("ordNumFrom"))) {
				params.put("ordNumFrom", statParams.get("ordNumFrom"));
			}
			if(StringUtils.isNotBlank(statParams.get("ordNumTo"))) {
				params.put("ordNumTo", statParams.get("ordNumTo"));
			}
			if(StringUtils.isNotBlank(statParams.get("payNumFrom"))) {
				params.put("payNumFrom", statParams.get("payNumFrom"));
			}
			if(StringUtils.isNotBlank(statParams.get("payNumTo"))) {
				params.put("payNumTo", statParams.get("payNumTo"));
			}
			if(StringUtils.isNotBlank(statParams.get("beginDate"))) {
				params.put("beginDate", statParams.get("beginDate"));			
			}
			if(StringUtils.isNotBlank(statParams.get("endDate"))) {
				params.put("endDate", statParams.get("endDate"));
			}
		}
		
		return params;
	}
	
	/**
	 * 新增Page至参数Map
	 * @param Map<String, Object> params
	 */
	private void setPageInfo2Map(Map<String, Object> params) {
		params.put("startRec", page.getStartRec());
		params.put("endRec", page.getEndRec());
	}
	
	/**
	 * 生成导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genMallDailyStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("bizDate");
		cols.add("uv");
		cols.add("pv");
		cols.add("orderNum");
		cols.add("payNum");
		cols.add("visitDepth");
		cols.add("payRate");
		cols.add("orderRate");
		
		return cols;
	}
	
	/**
	 * 生成导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genMallDailyStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计日期");
		colNames.add("UV");
		colNames.add("PV");
		colNames.add("订单数");
		colNames.add("支付数");
		colNames.add("访问深度");
		colNames.add("订单支付率");
		colNames.add("下单率");
		
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
	
}
