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
import com.iuni.dp.service.datastat.service.MallBuriedPointSiteService;

/**
 * 前端埋点相关统计 Controller
 * @author CaiKe
 * @version dp-admin-V1.0.1
 */
@Controller("mallBuriedPointStatAction")
@Scope("prototype")
public class MallBuriedPointStatAction extends BaseAction {

	private static final long serialVersionUID = -1800108825427853794L;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static Calendar calendar = Calendar.getInstance();
	
	private Logger logger = LoggerFactory.getLogger(MallBuriedPointSiteAction.class);
	
	@Autowired
	private MallBuriedPointSiteService mallBuriedPointSiteService;
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	public String mbpsClickrateStatView() {
		List<Map<String, Object>> mbpsClickrateStatList = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("mbpsClickrate");
			
			Integer totalRecord = mallBuriedPointSiteService.queryClickRateCount(params);
			
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			mbpsClickrateStatList = mallBuriedPointSiteService.queryClickRateByPage(params);
			
			request.setAttribute("mbpsClickrateStatList", mbpsClickrateStatList);
			
		} catch (DBException dbex) {
			logger.error("MallBuriedPointStatAction.mbpsClickrateStatView found DBException", dbex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("MallBuriedPointStatAction.mbpsClickrateStatView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 玩机圈APP客户端渠道新增用户数统计列表导出至Excel
	 * @return String
	 */
	public String mbpsClickrateStat2Excel() {
		List<Map<String, Object>> mbpsClickrateStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("mbpsClickrate");
			mbpsClickrateStatList = mallBuriedPointSiteService.queryClickRateByExample(params);
			// 生成导出数据列名列表
			columnNames = genMbpsClickrateStatColNames();
			// 生成导出数据列名变量列表
			columns = genMbpsClickrateStatCols();
			
			if(CollectionUtils.isNotEmpty(mbpsClickrateStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "商城" + params.get("bpType") + "点击率明细表";
				String exportDate = dateFormat.format(new Date());
				fileName = sheetName + "_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, mbpsClickrateStatList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("MallBuriedPointStatAction.mbpsClickrateStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("MallBuriedPointStatAction.mbpsClickrateStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("MallBuriedPointStatAction.mbpsClickrateStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("MallBuriedPointStatAction.mbpsClickrateStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * 生成导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genMbpsClickrateStatCols() {
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
	private List<String> genMbpsClickrateStatColNames() {
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
		
		// 设置数据上报切分表名后缀
		List<String> rdList = new ArrayList<String>();
		
		if("default".equals(flag)) {
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
			String rd_1 = String.valueOf(calendar.get(Calendar.MONTH)+1);
			String eDate = dateFormat.format(calendar.getTime()); 
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-6);
			String rd_2 = String.valueOf(calendar.get(Calendar.MONTH)+1);
			String sDate = dateFormat.format(calendar.getTime());
			params.put("beginDate", sDate);
			params.put("endDate", eDate);
			
			// 设置日期过滤框默认值
			statParams.put("beginDate", sDate);
			statParams.put("endDate", eDate);
			
			// 设置埋点类型默认值
			statParams.put("bpType", "e7_presale");
			
			if(rd_1.equals(rd_2)) {
				rdList.add(rd_1);
			} else {
				rdList.add(rd_1);
				rdList.add(rd_2);
			}
			params.put("rdList", rdList);
			
		} else {
			String beginDate = statParams.get("beginDate");
			String endDate = statParams.get("endDate");
			
			if(StringUtils.isNotBlank(beginDate)) {
				params.put("beginDate", beginDate);			
			}
			
			if(StringUtils.isNotBlank(endDate)) {
				params.put("endDate", endDate);
			}
			
			calendar.setTime(dateFormat.parse(beginDate));
			int bMonth = calendar.get(Calendar.MONTH)+1;
			calendar.setTime(dateFormat.parse(endDate));
			int eMonth = calendar.get(Calendar.MONTH)+1;
			for(int i = 0; i <= eMonth - bMonth; i++) {
				rdList.add(String.valueOf(bMonth+i));
			}
			params.put("rdList", rdList);
		}
		
		String bpType = statParams.get("bpType");
		
		if(StringUtils.isNotBlank(bpType)) {
			params.put("pointType", bpType);
		}
		
		if("e7_presale".equals(bpType)) {
			params.put("bpType", "E7预售");
		} else if("e7_sale".equals(bpType)) {
			params.put("bpType", "E7开售");
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
