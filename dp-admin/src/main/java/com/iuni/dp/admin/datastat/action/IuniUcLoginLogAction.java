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
import com.iuni.dp.service.datastat.service.IuniUcLoginLogService;

/**
 * IuniUcLoginLog Action
 * @author Kenneth.Cai@iuni.com
 * @version dp-admin-1.1.4
 */
@Controller("iuniUcLoginLogAction")
@Scope("prototype")
public class IuniUcLoginLogAction extends BaseAction {

	private static final long serialVersionUID = 1189989703887322828L;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IuniUcLoginLogService iuniUcLoginLogService;
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	/**
	 * IUNI用户登陆频次统计查询视图
	 * @return String
	 */
	public String iuniUserLoginFreqView() {
		List<Map<String, Object>> iuniUserLoginFreqList = null;
		
		try {
			// 生成查询参数Map
			Map<String, Object> params = genParamMap("iuniUserLoginFreq");
			
			Integer totalRecord = iuniUcLoginLogService.queryIuniUserLoginFreqCount(params);
			
			// 根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			// 新增Page至参数Map
			setPageInfo2Map(page, params);
			
			iuniUserLoginFreqList = iuniUcLoginLogService.queryIuniUserLoginFreqByPage(params);
			
			request.setAttribute("iuniUserLoginFreqList", iuniUserLoginFreqList);
			
		} catch (DBException dbex) {
			logger.error("IuniUcLoginLogAction.iuniUserLoginFreqView found DBException", dbex);
			return ERROR;
		}catch (Exception ex) {
			logger.error("IuniUcLoginLogAction.iuniUserLoginFreqView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI用户登陆频次统计列表导出至Excel
	 * @return String
	 */
	public String iuniUserLoginFreq2Excel() {
		List<Map<String, Object>> iuniUserLoginFreqList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			// 生成查询参数Map
			Map<String, Object> params = genParamMap("iuniUserLoginFreq");
			iuniUserLoginFreqList = iuniUcLoginLogService.queryIuniUserLoginFreqByExample(params);
			
			// 生成导出数据列名列表
			columnNames = genIuniUserLoginFreqColNames();
			// 生成导出数据列名变量列表
			columns = genIuniUserLoginFreqCols();
			
			if(CollectionUtils.isNotEmpty(iuniUserLoginFreqList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName_daily = "IUNI用户登陆频次统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "IUNI用户登陆频次统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName_daily, iuniUserLoginFreqList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniUcLoginLogAction.iuniUserLoginFreq2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniUcLoginLogAction.iuniUserLoginFreq2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniUcLoginLogAction.iuniUserLoginFreq2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniUcLoginLogAction.iuniUserLoginFreq2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * IUNI用户登陆次数统计查询视图
	 * @return String
	 */
	public String iuniUserLoginNumView() {
		List<Map<String, Object>> iuniUserLoginNumList = null;
		
		try {
			// 生成查询参数Map
			Map<String, Object> params = genParamMap("iuniUserLoginNum");
			
			Integer totalRecord = iuniUcLoginLogService.queryIuniUserLoginNumCount(params);
			
			// 根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			// 新增Page至参数Map
			setPageInfo2Map(page, params);
			
			iuniUserLoginNumList = iuniUcLoginLogService.queryIuniUserLoginNumByPage(params);
			
			request.setAttribute("iuniUserLoginNumList", iuniUserLoginNumList);
			
		} catch (DBException dbex) {
			logger.error("IuniUcLoginLogAction.iuniUserLoginNumView found DBException", dbex);
			return ERROR;
		}catch (Exception ex) {
			logger.error("IuniUcLoginLogAction.iuniUserLoginNumView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI用户登陆次数统计列表导出至Excel
	 * @return String
	 */
	public String iuniUserLoginNum2Excel() {
		List<Map<String, Object>> iuniUserLoginNumList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			// 生成查询参数Map
			Map<String, Object> params = genParamMap("iuniUserLoginNum");
			iuniUserLoginNumList = iuniUcLoginLogService.queryIuniUserLoginNumByExample(params);
			
			// 生成导出数据列名列表
			columnNames = genIuniUserLoginNumColNames();
			// 生成导出数据列名变量列表
			columns = genIuniUserLoginNumCols();
			
			if(CollectionUtils.isNotEmpty(iuniUserLoginNumList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName_daily = "IUNI用户登陆次数统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "IUNI用户登陆次数统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName_daily, iuniUserLoginNumList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniUcLoginLogAction.iuniUserLoginNum2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniUcLoginLogAction.iuniUserLoginNum2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniUcLoginLogAction.iuniUserLoginNum2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniUcLoginLogAction.iuniUserLoginNum2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * IUNI用户注册留存数统计查询视图
	 * @return String
	 */
	public String iuniUserRegRetainView() {
		List<Map<String, Object>> iuniUserRegRetainList = null;
		
		try {
			// 生成查询参数Map
			Map<String, Object> params = genParamMap("iuniUserLoginRetain");
			
			Integer totalRecord = iuniUcLoginLogService.queryIuniUserRegRetainCount(params);
			
			// 根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			// 新增Page至参数Map
			setPageInfo2Map(page, params);
			
			iuniUserRegRetainList = iuniUcLoginLogService.queryIuniUserRegRetainByPage(params);
			
			request.setAttribute("iuniUserRegRetainList", iuniUserRegRetainList);
			
		} catch (DBException dbex) {
			logger.error("IuniUcLoginLogAction.iuniUserRegRetainView found DBException", dbex);
			return ERROR;
		}catch (Exception ex) {
			logger.error("IuniUcLoginLogAction.iuniUserRegRetainView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI用户注册留存数统计列表导出至Excel
	 * @return String
	 */
	public String iuniUserRegRetain2Excel() {
		List<Map<String, Object>> iuniUserRegRetainList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			// 生成查询参数Map
			Map<String, Object> params = genParamMap("iuniUserLoginRetain");
			iuniUserRegRetainList = iuniUcLoginLogService.queryIuniUserRegRetainByExample(params);
			
			// 生成导出数据列名列表
			columnNames = genIuniUserRegRetainColNames();
			// 生成导出数据列名变量列表
			columns = genIuniUserRegRetainCols();
			
			if(CollectionUtils.isNotEmpty(iuniUserRegRetainList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName_daily = "IUNI用户注册留存数统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "IUNI用户注册留存数统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName_daily, iuniUserRegRetainList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniUcLoginLogAction.iuniUserRegRetain2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniUcLoginLogAction.iuniUserRegRetain2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniUcLoginLogAction.iuniUserRegRetain2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniUcLoginLogAction.iuniUserRegRetain2Excel found Exception", ex);
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
	private List<String> genIuniUserLoginFreqCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("regTime");
		cols.add("userId");
		cols.add("userName");
		cols.add("email");
		cols.add("mobile");
		cols.add("s1day");
		cols.add("s2to3day");
		cols.add("s4to7day");
		cols.add("s8to30day");
		cols.add("s31to90day");
		
		return cols;
	}
	
	/**
	 * 生成注册用户数统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genIuniUserLoginFreqColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("注册时间");
		colNames.add("用户ID");
		colNames.add("用户名");
		colNames.add("电子邮箱");
		colNames.add("手机");
		colNames.add("1日是否登录");
		colNames.add("2-3天内是否登录");
		colNames.add("4-7天是否登录");
		colNames.add("8-30天是否登录");
		colNames.add("31-90天是否登录");
		
		return colNames;
	}
	
	/**
	 * 生成注册用户数统计报表导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genIuniUserLoginNumCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("statDate");
		cols.add("userId");
		cols.add("userName");
		cols.add("email");
		cols.add("mobile");
		cols.add("loginNum");
		
		return cols;
	}
	
	/**
	 * 生成注册用户数统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genIuniUserLoginNumColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计时间");
		colNames.add("用户ID");
		colNames.add("用户名");
		colNames.add("电子邮箱");
		colNames.add("手机");
		colNames.add("登录次数");
		
		return colNames;
	}
	
	/**
	 * 生成注册用户数统计报表导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genIuniUserRegRetainCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("regTime");
		cols.add("regNum");
		cols.add("retainUserOn1day");
		cols.add("retainUserOn2To3day");
		cols.add("retainUserOn4To7day");
		cols.add("retainUserOn8To30day");
		cols.add("retainUserOn31To90day");
		
		return cols;
	}
	
	/**
	 * 生成注册用户数统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genIuniUserRegRetainColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("注册时间");
		colNames.add("注册用户数");
		colNames.add("1日留存数");
		colNames.add("2-3日留存数");
		colNames.add("4-7日留存数");
		colNames.add("8-30日留存数");
		colNames.add("31-90日留存数");
		
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
