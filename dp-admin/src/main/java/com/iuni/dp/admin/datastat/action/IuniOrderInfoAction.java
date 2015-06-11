package com.iuni.dp.admin.datastat.action;

import java.io.BufferedReader;
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

import com.iuni.dp.admin.datastat.constants.OrderRefer;
import com.iuni.dp.persist.datastat.common.model.OrderSource;
import com.iuni.dp.service.datastat.service.common.OrderSourceService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.admin.common.utils.StringUtil;
import com.iuni.dp.service.common.bean.BaseResult;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.IuniOrderInfoService;

/**
 * 
 * @author Kenneth.Cai@iuni.com
 * @version dp-admin-1.1.2
 */
@Controller("iuniOrderInfoAction")
@Scope("prototype")
public class IuniOrderInfoAction extends BaseAction {

	private static final long serialVersionUID = 9091799807199886180L;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private Logger logger = LoggerFactory.getLogger(IuniOrderInfoAction.class);

	@Autowired
	private IuniOrderInfoService iuniOrderInfoService; 
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	private Page onePage = new Page();
	
	private Page twoPage = new Page();

	@Autowired
	private OrderSourceService orderSourceService;
	private List<OrderSource> orderSourceList = new ArrayList<OrderSource>();
	// 订单来源
	private String sourceCode = "IUNI";

	/**
	 * IUNI商城订单区域分布统计查询视图
	 * @return String
	 */
	public String iuniOrderAreaStatView() {
		List<Map<String, Object>> orderAreaSumList = null;
		List<Map<String, Object>> orderAreaStatList = null;
		try {
			// 查询来源
			orderSourceList = orderSourceService.getAllOmOrderSource();
			// 生成查询参数Map
			Map<String, Object> params_sum = genParamMap("orderAreaStat");
			Map<String, Object> params_daily = genParamMap("orderAreaStat");

			Integer totalRecord_sum = iuniOrderInfoService.queryIuniOrderAreaSumCount(params_sum);
			Integer totalRecord_daily = iuniOrderInfoService.queryIuniOrderAreaStatCount(params_daily);
			
			// 根据当前页、总记录数、页大小获得Page
			onePage = Page.genPage(onePage.getCurrentPage(), totalRecord_sum, onePage.getPageSize());
			twoPage = Page.genPage(twoPage.getCurrentPage(), totalRecord_daily, twoPage.getPageSize());
			
			// 新增Page至参数Map
			setPageInfo2Map(onePage, params_sum);
			setPageInfo2Map(twoPage, params_daily);
			
			orderAreaSumList = iuniOrderInfoService.queryIuniOrderAreaSumByPage(params_sum);
			orderAreaStatList = iuniOrderInfoService.queryIuniOrderAreaStatByPage(params_daily);
			
			request.setAttribute("orderAreaSumList", orderAreaSumList);
			request.setAttribute("orderAreaStatList", orderAreaStatList);
			
		} catch (DBException dbex) {
			logger.error("IuniOrderInfoAction.iuniOrderAreaStatView found DBException", dbex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("IuniOrderInfoAction.iuniOrderAreaStatView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI商城订单区域分布统计列表导出至Excel
	 * @return String
	 */
	public String iuniOrderAreaStat2Excel() {
		List<Map<String, Object>> orderAreaSumList = null;
		List<Map<String, Object>> orderAreaStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("orderAreaStat");
			
			orderAreaSumList = iuniOrderInfoService.queryIuniOrderAreaSumByExample(params);
			orderAreaStatList = iuniOrderInfoService.queryIuniOrderAreaStatByExample(params);
			
			// 生成导出数据列名列表
			columnNames = genIuniOrderAreaStatColNames();
			// 生成导出数据列名变量列表
			columns = genIuniOrderAreaStatCols();
			
			if(CollectionUtils.isNotEmpty(orderAreaStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName_sum = "IUNI商城订单区域分布汇总统计";
				String sheetName_daily = "IUNI商城订单区域分布每日统计";
				String exportDate = dateFormat.format(new Date());
				fileName = "IUNI商城订单区域分布统计报表_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName_sum, orderAreaSumList);
				sheetDataList.put(sheetName_daily, orderAreaStatList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniOrderInfoAction.iuniOrderAreaStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniOrderInfoAction.iuniOrderAreaStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniOrderInfoAction.iuniOrderAreaStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniOrderInfoAction.iuniOrderAreaStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * 按JSON格式获取IUNI OS用户数版本分布统计报表FusionChart参数
	 */
	public void getIuniOrderStat4CsByJSON() {
		BaseResult result = new BaseResult();
		List<Map<String, Object>> orderStat4CsList = null;
		
		try {
			String data = null;
			String contentType = request.getContentType();
			
			if(StringUtils.isBlank(contentType)) {
				result.setState(BaseResult.FAIL);
				printJson2(result, false, false);
				return;
			}
			
			if(contentType.startsWith("application/x-www-form-urlencoded")) {
				data = request.getParameter("data");
			} else if(contentType.startsWith("application/json")) {
				BufferedReader reader = request.getReader();
				String line = null;
				StringBuffer jsonBuf = new StringBuffer();
				while((line = reader.readLine()) != null) {  
					jsonBuf.append(line);  
			    }
				data = jsonBuf.toString();
			}
			
			if(StringUtils.isBlank(data)) {
				result.setState(BaseResult.FAIL);
				printJson2(result, false, false);
				return;
			}
			// 反序列化请求参数
			Map<String, Object> queryParams = JSON.parseObject(data, Map.class);
			if (null == queryParams.get("beginDate")
					|| !StringUtil.isValidDate((String) queryParams
							.get("beginDate"))
					|| null == queryParams.get("endDate")
					|| !StringUtil.isValidDate((String) queryParams
							.get("endDate"))) {
				result.setState(BaseResult.FAIL);
				printJson2(result, false, false);
				return;
			}
			orderStat4CsList = iuniOrderInfoService.queryOrderStat4Cs(queryParams);
			
			result.setState(BaseResult.SUCCESS);
			result.setData(orderStat4CsList);
			int dateSize = CollectionUtils.isEmpty(orderStat4CsList) ? 0 : orderStat4CsList.size();
			logger.debug(
					"IuniOrderInfoAction.getIuniOrderStat4CsByJSON dataSize={}",
					new Object[] { dateSize });
			
		} catch (Exception e) {
			result.setState(BaseResult.FAIL);
			logger.error("IuniOrderInfoAction.getIuniOrderStat4CsByJSON found Exception", e);
		}
		
		printJson2(result, Boolean.TRUE, Boolean.FALSE);
		
		logger.debug("IuniOrderInfoAction.getIuniOrderStat4CsByJSON invoke success.");
	}

    /**
     * 按JSON格式获取在线客服销售数据
     */
    public void getIuniOrderStat4CsByUser() {
        BaseResult result = new BaseResult();
        List<Map<String, Object>> orderStat4CsList = null;

        try {
            String data = null;
            String contentType = request.getContentType();

            if(StringUtils.isBlank(contentType)) {
                result.setState(BaseResult.FAIL);
                printJson2(result, false, false);
                return;
            }

            if(contentType.startsWith("application/x-www-form-urlencoded")) {
                data = request.getParameter("data");
            } else if(contentType.startsWith("application/json")) {
                BufferedReader reader = request.getReader();
                String line = null;
                StringBuffer jsonBuf = new StringBuffer();
                while((line = reader.readLine()) != null) {
                    jsonBuf.append(line);
                }
                data = jsonBuf.toString();
            }

            if(StringUtils.isBlank(data)) {
                result.setState(BaseResult.FAIL);
                printJson2(result, false, false);
                return;
            }
            // 反序列化请求参数
            Map<String, Object> queryParams = JSON.parseObject(data, Map.class);
            if (null == queryParams.get("beginDate")
                    || !StringUtil.isValidDate((String) queryParams
                    .get("beginDate"))
                    || null == queryParams.get("endDate")
                    || !StringUtil.isValidDate((String) queryParams
                    .get("endDate"))) {
                result.setState(BaseResult.FAIL);
                printJson2(result, false, false);
                return;
            }
            orderStat4CsList = iuniOrderInfoService.queryOrderStat4CsByUser(queryParams);

            result.setState(BaseResult.SUCCESS);
            result.setData(orderStat4CsList);
            int dateSize = CollectionUtils.isEmpty(orderStat4CsList) ? 0 : orderStat4CsList.size();
            logger.debug(
                    "IuniOrderInfoAction.getIuniOrderStat4CsByJSON dataSize={}",
                    new Object[] { dateSize });

        } catch (Exception e) {
            result.setState(BaseResult.FAIL);
            logger.error("IuniOrderInfoAction.getIuniOrderStat4CsByJSON found Exception", e);
        }

        printJson2(result, Boolean.TRUE, Boolean.FALSE);

        logger.debug("IuniOrderInfoAction.getIuniOrderStat4CsByJSON invoke success.");
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
		
		if(StringUtils.isNotBlank(statParams.get("country"))) {
			params.put("country", statParams.get("country"));			
		}
		
		if(StringUtils.isNotBlank(statParams.get("province"))) {
			params.put("province", statParams.get("province"));			
		}
		
		if(StringUtils.isNotBlank(statParams.get("city"))) {
			params.put("city", statParams.get("city"));			
		}
		
		if(StringUtils.isNotBlank(statParams.get("district"))) {
			params.put("district", statParams.get("district"));			
		}

		if(StringUtils.isNotBlank(statParams.get("beginDate"))) {
			params.put("beginDate", statParams.get("beginDate"));			
		}
		if(StringUtils.isNotBlank(statParams.get("endDate"))) {
			params.put("endDate", statParams.get("endDate"));
		}

        if(StringUtils.isNotBlank(sourceCode) && !"0".equals(sourceCode)) {
            params.put("orderSource", sourceCode);
        }
		
		return params;
	}
	
	/**
	 * 生成IUNI商城订单区域分布统计报表导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genIuniOrderAreaStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("statDate");
		cols.add("country");
		cols.add("province");
		cols.add("city");
		cols.add("district");
		cols.add("salesNum");
		
		return cols;
	}
	
	/**
	 * 生成IUNI商城订单区域分布统计报表导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genIuniOrderAreaStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("统计日期");
		colNames.add("销售国家");
		colNames.add("销售省区");
		colNames.add("销售市区");
		colNames.add("销售区县");
		colNames.add("销售数量");
		
		return colNames;
	}
	
	/**
	 * 新增Page至参数Map
	 * @param params
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

	public List<OrderSource> getOrderSourceList() {
		return orderSourceList;
	}

	public void setOrderSourceList(List<OrderSource> orderSourceList) {
		this.orderSourceList = orderSourceList;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
}
