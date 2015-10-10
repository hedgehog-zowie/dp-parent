package com.iuni.dp.admin.datastat.action.iuniwms;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.persist.datastat.common.model.IuniWmsWarehouse;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.common.IuniWarehouseService;
import com.iuni.dp.service.datastat.service.wms.IuniWmsStockService;
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

/**
 * IuniWmsStock Action
 * @author Kenneth.Cai@iuni.com
 * @version dp-admin-V1.1.5
 */
@Controller("iuniWmsStockAction")
@Scope("prototype")
public class IuniWmsStockAction extends BaseAction {

	private static final long serialVersionUID = -4338586763773583721L;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static Calendar calendar = Calendar.getInstance();
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IuniWmsStockService iuniWmsStockServie;

	private Map<String, String> statParams = new HashMap<String, String>();
	
	private InputStream excelStream;
	
	private String fileName;
	
	private String flag;

	private String wareHouse;

	private String materialCode;

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

	/**
	 * 初始化仓库列表
	 */
	private void initWarehouse() {
		warehouseList = warehouseService.queryAllWarehouse();
	}

    /**
	 * IUNI WMS库存明细按条件统计
	 * @return String
	 */
	public String iuniWmsStockDetailsView() {
		initWarehouse();

		List<Map<String, Object>> iuniWmsStockDetailsList = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("iuniWmsStockDetails");
			
			Integer totalRecord = iuniWmsStockServie.queryIuniWmsStockDetailsCount(params);
			
			//根据当前页、总记录数、页大小获得Page
			//page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			page = Page.genPage(page.getCurrentPage(), totalRecord, 50);
			
			//新增Page至参数Map
			setPageInfo2Map(page, params);
			
			iuniWmsStockDetailsList = iuniWmsStockServie.queryIuniWmsStockDetailsByPage(params);
			
			request.setAttribute("iuniWmsStockDetailsList", iuniWmsStockDetailsList);
			
		} catch (DBException dbex) {
			logger.error("IuniWmsStockAction.iuniWmsStockDetailsView found DBException", dbex);
			return ERROR;
		} catch (Exception ex) {
			logger.error("IuniWmsStockAction.iuniWmsStockDetailsView found Exception", ex);
            ex.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * IUNI WMS库存明细按条件统计列表导出至Excel
	 * @return String
	 */
	public String iuniWmsStockDetails2Excel() {
		List<Map<String, Object>> iuniWmsStockDetailsList = null;
		List<String> columnNames = null;
		List<String> columns = null;
		
		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("iuniWmsStockDetails");
			iuniWmsStockDetailsList = iuniWmsStockServie.queryIuniWmsStockDetailsByExample(params);
			// 生成导出数据列名列表
			columnNames = genIuniNoInvoiceSalesDetailsColNames();
			// 生成导出数据列名变量列表
			columns = genIuniNoInvoiceSalesDetailsCols();
			
			if(CollectionUtils.isNotEmpty(iuniWmsStockDetailsList) && CollectionUtils.isNotEmpty(columnNames)
					&& CollectionUtils.isNotEmpty(columns)) {
				// 按Sheet数据列表
				Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
				
				String sheetName = "IUNI WMS库存明细表";
				String exportDate = dateFormat.format(new Date());
				fileName = sheetName + "_" + exportDate;
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				
				sheetDataList.put(sheetName, iuniWmsStockDetailsList);
				
				XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);
				
				ByteArrayOutputStream baos = null;
				try {
					baos = new ByteArrayOutputStream();  
					workbook.write(baos);
					byte[] ba = baos.toByteArray();  
					excelStream = new ByteArrayInputStream(ba);
					
				} catch (IOException e) {
					logger.error("IuniWmsStockAction.iuniWmsStockDetails2Excel found IOException.", e);
				}  finally {
					if(null != baos) {
						try {
							baos.close();
						} catch (IOException e) {
							logger.error("IuniWmsStockAction.iuniWmsStockDetails2Excel found IOException.", e);
						}
					}
				}
			} else {
				return ERROR;
			}
			
		} catch (DBException dbex) {
			logger.error("IuniWmsStockAction.iuniWmsStockDetails2Excel found DBException", dbex);
			return ERROR;

		} catch (Exception ex) {
			logger.error("IuniWmsStockAction.iuniWmsStockDetails2Excel found Exception", ex);
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 生成导出数据列名变量列表
	 * @return List<String>
	 */
	private List<String> genIuniNoInvoiceSalesDetailsCols() {
		List<String> cols = new ArrayList<String>();
		cols.add("rowNum");
        cols.add("date");
        cols.add("wareHouse");
		cols.add("skuCode");
		cols.add("materialCode");
//		cols.add("goodsName");
		cols.add("skuName");
		cols.add("measureUnit");
		cols.add("acceptedGoods");
		cols.add("defectiveGoods");
		cols.add("totalGoods");

		return cols;
	}
	
	/**
	 * 生成导出数据列名列表
	 * @return List<String>
	 */
	private List<String> genIuniNoInvoiceSalesDetailsColNames() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("序号");
        colNames.add("日期");
        colNames.add("仓库");
		colNames.add("SKU");
		colNames.add("物料编码");
//		colNames.add("商品名称");
		colNames.add("规格型号");
		colNames.add("单位");
		colNames.add("良品");
		colNames.add("次品");
		colNames.add("库存合计");
		
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

		// 仓库
		if (StringUtils.isNotBlank(warehouseCode))
			params.put("warehouseCode", warehouseCode.trim());
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

	public String getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public static String getWarehouseCode() {
		return warehouseCode;
	}

	public static void setWarehouseCode(String warehouseCode) {
		IuniWmsStockAction.warehouseCode = warehouseCode;
	}

	public List<IuniWmsWarehouse> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<IuniWmsWarehouse> warehouseList) {
		this.warehouseList = warehouseList;
	}
}
