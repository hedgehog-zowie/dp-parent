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
import java.util.LinkedHashMap;
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
import com.iuni.dp.service.datastat.service.wms.IuniWmsProcurementDetailService;
import com.iuni.dp.service.datastat.service.common.IuniWmsSupplierService;

/**
 * 入库明细报表
 * @author dan.wang@iuni.com
 *
 */
@Controller("iuniWmsProcurementDetailAction")
@Scope("prototype")
public class IuniWmsProcurementDetailAction extends BaseAction{

	private static final long serialVersionUID = -765744506767689643L;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static Calendar calendar = Calendar.getInstance();
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private IuniWmsProcurementDetailService iuniWmsProcurementDetailService;
	@Autowired
	private IuniWmsSupplierService iuniWmsSupplierService;
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;
	
	//查询条件
	private String materialCode;
	private String originalCode;
	private String supplierId;
	private Map<String,String> suppliers = new LinkedHashMap<String, String>();
	private String receiveCode;

	@Autowired
	private IuniWarehouseService warehouseService;
	/**
	 * 仓库代码
	 */
	private static String warehouseCode;
	/**
	 * 仓库列表
	 */
	private List<IuniWmsWarehouse> warehouseList;

	public void findAllSuppliers(){
		suppliers.clear();
		suppliers = iuniWmsSupplierService.findAllSuppliers();
	}

	/**
	 * 初始化仓库列表
	 */
	private void initWarehouse() {
		warehouseList = warehouseService.queryAllWarehouse();
	}

	public String iuniWmsProcurementDetail(){
		findAllSuppliers();

		initWarehouse();
		
		List<Map<String, Object>> iuniWmsProcurementDetailList = new ArrayList<Map<String, Object>>();
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("iuniWmsProcurementDetail");
			
			Integer totalRecord = iuniWmsProcurementDetailService.queryIuniWmsProcurementDetailCount(params);
			
			//根据当前页、总记录数、页大小获得Page
			//page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			page = Page.genPage(page.getCurrentPage(), totalRecord, 50);
			
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			iuniWmsProcurementDetailList = iuniWmsProcurementDetailService.queryIuniWmsProcurementDetailByPage(params);
			request.setAttribute("iuniWmsProcurementDetailList", iuniWmsProcurementDetailList);
			
		} catch (DBException dbex) {
			logger.error("IuniWmsProcurementDetailAction.iuniWmsProcurementDetail found DBException", dbex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("IuniWmsProcurementDetailAction.iuniWmsProcurementDetail found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String iuniWmsProcurementDetail2Excel(){
		List<Map<String, Object>> iuniWmsProcurementDetail2ExcelList = null;
		try{
			Map<String, Object> params = genParamMap("iuniWmsProcurementDetail");
			iuniWmsProcurementDetail2ExcelList = iuniWmsProcurementDetailService.queryIuniWmsProcurementDetail2Excel(params);
			if(CollectionUtils.isNotEmpty(iuniWmsProcurementDetail2ExcelList)){
				String sheetName = "采购入库明细报表";
				fileName = sheetName + "_" + dateFormat.format(new Date());
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4Xssf(gencolumnNames(), gencolumns(),  iuniWmsProcurementDetail2ExcelList, sheetName);
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniWmsProcurementDetailAction.iuniWmsProcurementDetail2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniWmsProcurementDetailAction.iuniWmsProcurementDetail2Excel found IOException.", e);
						}
					}
				}
			}else return ERROR;
			
		}catch (DBException dbex) {
			logger.error("IuniWmsProcurementDetailAction.iuniWmsProcurementDetail2Excel found DBException", dbex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("IuniWmsProcurementDetailAction.iuniWmsProcurementDetail2Excel found Exception", ex);
			return ERROR;
		}
		return SUCCESS;
	}
	private List<String> gencolumnNames() {
		List<String> columnNames = new ArrayList<String>();
		columnNames.add("序号");
		columnNames.add("入库日期");
		columnNames.add("供应商");
		columnNames.add("采购订单号");
		columnNames.add("入库单号");
		columnNames.add("SKU");
		columnNames.add("物料编码");
//		columnNames.add("商品名称");
		columnNames.add("名称规格");
		columnNames.add("数量");
		columnNames.add("备注");
		return columnNames;
		
	}
	private List<String> gencolumns() {
		List<String> columns = new ArrayList<String>();
		columns.add("RN");
		columns.add("time");
		columns.add("supplierName");
		columns.add("originalCode");
		columns.add("receiveCode");
		columns.add("sku");
		columns.add("materialCode");
//		columns.add("waresName");
		columns.add("skuName");
		columns.add("quantity");
		columns.add("remark");
		return columns;
		
	}

	

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

		// 采购单号
		if (StringUtils.isNotBlank(originalCode))
			params.put("originalCode", originalCode.trim());
		// 入库单号
		if (StringUtils.isNotBlank(receiveCode))
			params.put("receiveCode", receiveCode.trim());
		// 物料编码
		if (StringUtils.isNotBlank(materialCode))
			params.put("materialCode", materialCode.trim());
		// 供应商
		if(StringUtils.isNotBlank(supplierId) && !"0".equals(supplierId.trim())){
			params.put("supplierId", supplierId.trim());
		}
		// 仓库
		if (StringUtils.isNotBlank(warehouseCode))
			params.put("warehouseCode", warehouseCode.trim());

		return params;
	}
	
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

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getOriginalCode() {
		return originalCode;
	}

	public void setOriginalCode(String originalCode) {
		this.originalCode = originalCode;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Map<String, String> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Map<String, String> suppliers) {
		this.suppliers = suppliers;
	}

	public String getReceiveCode() {
		return receiveCode;
	}

	public void setReceiveCode(String receiveCode) {
		this.receiveCode = receiveCode;
	}

	public static String getWarehouseCode() {
		return warehouseCode;
	}

	public static void setWarehouseCode(String warehouseCode) {
		IuniWmsProcurementDetailAction.warehouseCode = warehouseCode;
	}

	public List<IuniWmsWarehouse> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<IuniWmsWarehouse> warehouseList) {
		this.warehouseList = warehouseList;
	}
}
