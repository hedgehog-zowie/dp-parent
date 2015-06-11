package com.iuni.dp.admin.datastat.action.iuniwms;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.wms.IuniAlipayService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller("iuniAlipayAction")
@Scope("prototype")
public class IuniAlipayAction extends BaseAction {

	private static final long serialVersionUID = 6480886298470413701L;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static Calendar calendar = Calendar.getInstance();
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;

	// 订单号
	private String orderCode;

	@Autowired
	private IuniAlipayService alipayService;

	/**
	 * IUNI支付宝对账报表
	 * @return String
	 */
	public String iuniAlipay() {
		List<Map<String, Object>> iuniAlipayList = null;

		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap();

			Integer totalRecord = alipayService.queryIuniAlipayCount(params);

			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());

			//新增Page至参数Map
			setPageInfo2Map(page, params);

			iuniAlipayList = alipayService.queryIuniAlipayByPage(params);

			request.setAttribute("iuniAlipayList", iuniAlipayList);

		} catch (DBException dbex) {
			logger.error("IuniAlipayAction.iuniAlipay found DBException", dbex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("IuniAlipayAction.iuniAlipay found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * IUNI支付宝对账报表导出至Excel
	 * @return String
	 */
	public String iuniAlipay2Excel() {
		List<Map<String, Object>> iuniAlipayList = null;
		List<String> columnNames;
		List<String> columns;

		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap();
			iuniAlipayList = alipayService.queryIuniAlipayByPage(params);
			// 生成导出数据列名列表
			columnNames = genIuniAlipayColNames();
			// 生成导出数据列名变量列表
			columns = genIuniAlipayCols();

			if(CollectionUtils.isNotEmpty(iuniAlipayList) && CollectionUtils.isNotEmpty(columnNames)
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();

				String sheetName = "IUNI支付宝对账报表";
				fileName = sheetName + "_" + params.get("beginDate") + "-" + params.get("endDate");
				fileName = new String(fileName.getBytes(), "ISO8859-1");

				sheetDataList.put(sheetName, iuniAlipayList);

				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);

				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();
					workbook.write(baos);
					byte[] ba = baos.toByteArray();
					excelStream = new ByteArrayInputStream(ba);

				} catch (IOException e) {
					logger.error("IuniAlipayAction.iuniAlipay2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniAlipayAction.iuniAlipay2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}

		} catch (DBException dbex) {
			logger.error("IuniAlipayAction.iuniAlipay2Excel found DBException", dbex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("IuniAlipayAction.iuniAlipay2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 生成导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genIuniAlipayCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("alipayOrderNo");
		cols.add("merchartOrderNo");
		cols.add("createTime");
		cols.add("oppositeName");
		cols.add("osn");
		cols.add("orderCode");
		cols.add("inAmount");
		cols.add("outAmount");
		cols.add("balance");
		cols.add("type");
		cols.add("memo");
		return cols;
	}
	
	/**
	 * 生成导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genIuniAlipayColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("业务流水号");
		colNames.add("商户订单号");
		colNames.add("发生时间");
		colNames.add("对方账号");
		colNames.add("天猫外部订单号");
		colNames.add("内部订单号");
		colNames.add("收入金额（+元）");
		colNames.add("支出金额（-元）");
		colNames.add("账户余额（元）");
		colNames.add("业务类型");
		colNames.add("备注");

		return colNames;
	}
	
	/**
	 * 生成查询参数Map
	 * @return Map<String, Object>
	 * @throws ParseException 
	 */
	private Map<String, Object> genParamMap() throws ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		
		if("default".equals(flag)) {
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
			String eDate = dateFormat.format(calendar.getTime()); 
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-6);
			String sDate = dateFormat.format(calendar.getTime());
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

		// 订单号
		if (StringUtils.isNotBlank(orderCode))
			params.put("orderCode", orderCode.trim());
		
		return params;
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

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
}
