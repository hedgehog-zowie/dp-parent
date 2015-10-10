package com.iuni.dp.admin.datastat.action.iuniwms;

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

import com.iuni.dp.persist.datastat.common.model.IuniWmsWarehouse;
import com.iuni.dp.service.datastat.service.common.IuniWarehouseService;
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
import com.iuni.dp.service.datastat.service.wms.IuniWmsTransferService;

@Controller("iuniWmsTransferAction")
@Scope("prototype")
public class IuniWmsTransferAction extends BaseAction {

	private static final long serialVersionUID = 6480886298470413701L;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static Calendar calendar = Calendar.getInstance();
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IuniWmsTransferService iuniWmsTransferService;
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;

	// 调出仓位
	private String warehouseName;
	// 调入仓位
	private String transferSale;
	// 物料编码
	private String materialCode;

	@Autowired
	private IuniWarehouseService warehouseService;
	/**
	 * 调出仓库
	 */
	private static String outWarehouseCode;
	/**
	 * 调入仓库
	 */
	private static String inWarehouseCode;
	/**
	 * 仓库列表
	 */
	private List<IuniWmsWarehouse> warehouseList;

	/**
	 * 初始化仓库列表
	 */
	private void initWarehouse() {
		warehouseList = warehouseService.queryAllWarehouse();
	}

	/**
	 * IUNI WMS调拨单明细按条件统计 
	 * @return String
	 */
	public String iuniWmsTransferStatView() {
		List<Map<String, Object>> iuniWmsTransferStatList = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("iuniWmsTransferStat");
			
			Integer totalRecord = iuniWmsTransferService.queryIuniWmsTransferStatCount(params);
			
			//根据当前页、总记录数、页大小获得Page
			//page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			page = Page.genPage(page.getCurrentPage(), totalRecord, 50);
			
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			iuniWmsTransferStatList = iuniWmsTransferService.queryIuniWmsTransferStatByPage(params);
			
			request.setAttribute("iuniWmsTransferStatList", iuniWmsTransferStatList);
			
		} catch (DBException dbex) {
			logger.error("IuniWmsTransferAction.iuniWmsTransferStatView found DBException", dbex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("IuniWmsTransferAction.iuniWmsTransferStatView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI WMS调拨单明细按条件统计列表导出至Excel
	 * @return String
	 */
	public String iuniWmsTransferStat2Excel() {
		List<Map<String, Object>> iuniWmsTransferStatList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("iuniWmsTransferStat");
			iuniWmsTransferStatList = iuniWmsTransferService.queryIuniWmsTransferStatByExample(params);
			// 生成导出数据列名列表
			columnNames = genIuniWmsTransferStatColNames();
			// 生成导出数据列名变量列表
			columns = genIuniWmsTransferStatCols();
			
			if(CollectionUtils.isNotEmpty(iuniWmsTransferStatList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "IUNI WMS调拨单日明细表";
				String exportDate = dateFormat.format(new Date());
				fileName = sheetName + "_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, iuniWmsTransferStatList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniWmsTransferAction.iuniWmsTransferStat2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniWmsTransferAction.iuniWmsTransferStat2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniWmsTransferAction.iuniWmsTransferStat2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniWmsTransferAction.iuniWmsTransferStat2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * IUNI WMS内部调拔明细按条件统计 
	 * @return String
	 */
	public String iuniInTransferDetailsView() {
		initWarehouse();

		List<Map<String, Object>> iuniInTransferDetailsList;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("iuniInTransferDetails");
			
			Integer totalRecord = iuniWmsTransferService.queryIuniInTransferDetailsCount(params);
			
			//根据当前页、总记录数、页大小获得Page
			//page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			page = Page.genPage(page.getCurrentPage(), totalRecord, 50);
			
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			iuniInTransferDetailsList = iuniWmsTransferService.queryIuniInTransferDetailsByPage(params);
			
			request.setAttribute("iuniInTransferDetailsList", iuniInTransferDetailsList);
			
		} catch (DBException dbex) {
			logger.error("IuniWmsTransferAction.iuniInTransferDetailsView found DBException", dbex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("IuniWmsTransferAction.iuniInTransferDetailsView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI WMS内部调拔明细按条件统计列表导出至Excel
	 * @return String
	 */
	public String iuniInTransferDetails2Excel() {
		List<Map<String, Object>> iuniInTransferDetailsList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("iuniInTransferDetails");
			iuniInTransferDetailsList = iuniWmsTransferService.queryIuniInTransferDetailsByExample(params);
			// 生成导出数据列名列表
			columnNames = genIuniInTransferDetailsColNames();
			// 生成导出数据列名变量列表
			columns = genIuniInTransferDetailsCols();
			
			if(CollectionUtils.isNotEmpty(iuniInTransferDetailsList) && CollectionUtils.isNotEmpty(columnNames) 
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "IUNI WMS内部调拔明细表";
				String exportDate = dateFormat.format(new Date());
				fileName = sheetName + "_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, iuniInTransferDetailsList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniWmsTransferAction.iuniInTransferDetails2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniWmsTransferAction.iuniInTransferDetails2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniWmsTransferAction.iuniInTransferDetails2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniWmsTransferAction.iuniInTransferDetails2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}
	
	/**
	 * 生成导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genIuniWmsTransferStatCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("shippingTime");
		cols.add("transferId");
		cols.add("warehouse");
		cols.add("consignee");
		cols.add("transferTo");
		cols.add("contact");
		cols.add("skuName");
		cols.add("quantity");
		cols.add("measureUnit");
		cols.add("logisticNo");
		cols.add("status");
		cols.add("transferSend");
		cols.add("returnNum");
		
		return cols;
	}
	
	/**
	 * 生成导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genIuniWmsTransferStatColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("发货时间");
		colNames.add("调拨批次号");
		colNames.add("发货仓");
		colNames.add("收货人");
		colNames.add("收货地址");
		colNames.add("联系电话");
		colNames.add("SKU名称");
		colNames.add("数量");
		colNames.add("单位");
		colNames.add("物流单号");
		colNames.add("调货状态");
		colNames.add("目的地");
		colNames.add("已退货数量");
		
		return colNames;
	}
	
	/**
	 * 生成导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genIuniInTransferDetailsCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
		cols.add("shippingTime");
		cols.add("transferCode");
		cols.add("warehouseName");
		cols.add("transferSale");
		cols.add("skuCode");
		cols.add("materialCode");
//		cols.add("goodsName");
		cols.add("skuName");
		cols.add("quantity");
		return cols;
	}
	
	/**
	 * 生成导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genIuniInTransferDetailsColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
		colNames.add("日期");
		colNames.add("调拔单号");
		colNames.add("调出仓位");
		colNames.add("调入仓位");
		colNames.add("SKU");
		colNames.add("物料编码");
//		colNames.add("商品名称");
		colNames.add("规格型号");
		colNames.add("数量");
		
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

		// 调出仓位
		if (StringUtils.isNotBlank(outWarehouseCode))
			params.put("outWarehouseCode", outWarehouseCode.trim());
		// 调入仓位
		if (StringUtils.isNotBlank(transferSale))
			params.put("transferSale", transferSale.trim());
		// 物料编码
		if (StringUtils.isNotBlank(materialCode))
			params.put("materialCode", materialCode.trim());
		
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

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getTransferSale() {
		return transferSale;
	}

	public void setTransferSale(String transferSale) {
		this.transferSale = transferSale;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public static String getOutWarehouseCode() {
		return outWarehouseCode;
	}

	public static void setOutWarehouseCode(String outWarehouseCode) {
		IuniWmsTransferAction.outWarehouseCode = outWarehouseCode;
	}

	public static String getInWarehouseCode() {
		return inWarehouseCode;
	}

	public static void setInWarehouseCode(String inWarehouseCode) {
		IuniWmsTransferAction.inWarehouseCode = inWarehouseCode;
	}

	public List<IuniWmsWarehouse> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<IuniWmsWarehouse> warehouseList) {
		this.warehouseList = warehouseList;
	}
}
