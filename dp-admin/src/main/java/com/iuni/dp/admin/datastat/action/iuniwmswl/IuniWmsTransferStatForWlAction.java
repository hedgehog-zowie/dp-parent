package com.iuni.dp.admin.datastat.action.iuniwmswl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
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
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsTransferStatForWlService;

/**
 * WMS(物流)调拨明细统计报表
* @author dan.wang@iuni.com
 *
 */
@Controller("iuniWmsTransferStatForWlAction")
@Scope("prototype")
public class IuniWmsTransferStatForWlAction extends BaseAction{

	private static final long serialVersionUID = 5375866174081826230L;
	
	private String flag;
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private InputStream excelStream;
	
	private String fileName;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private Page onePage = new Page();
	private Page twoPage = new Page();
	
	private String transferType;
	private String transType;
	
	@Autowired
	private IuniWmsTransferStatForWlService iuniWmsTransferStatForWlService;
	/**
	 * 
	 * @return
	 */
	public String iuniWmsTransferStatViewForWl(){
		if(transType!=null){
			transferType = transType;
		}
		
		List<Map<String,Object>> iuniWmsTransferStatForWlList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> iuniWmsReceiveForWLList = new ArrayList<Map<String,Object>>();
		try {
			//1 生产查询参数
			Map<String,Object> queryParams = genParamMap();
			
			//2 得到总记录生成page
			Integer totalRecord = iuniWmsTransferStatForWlService.queryIuniWmsTransferStatForWlCount(queryParams);
			onePage = Page.genPage(onePage.getCurrentPage(), totalRecord, onePage.getPageSize());
			queryParams.put("startRec", onePage.getStartRec());
			queryParams.put("endRec", onePage.getEndRec());
			// 3 查询数据
			iuniWmsTransferStatForWlList = iuniWmsTransferStatForWlService.queryIuniWmsTransferStatForWlByPage(queryParams);
			
			/* receive          */
			Map<String,Object> queryParams2 = genParamMap();
			Integer totalRecord2 = iuniWmsTransferStatForWlService.queryIuniWmsReceiveForWLCount(queryParams2);
			twoPage = Page.genPage(twoPage.getCurrentPage(), totalRecord2, twoPage.getPageSize());
			queryParams2.put("startRec", twoPage.getStartRec());
			queryParams2.put("endRec", twoPage.getEndRec());	
			
			iuniWmsReceiveForWLList = iuniWmsTransferStatForWlService.queryIuniWmsReceiveForWLByPage(queryParams2);
			
			request.setAttribute("iuniWmsTransferStatForWlList", iuniWmsTransferStatForWlList);
			request.setAttribute("iuniWmsReceiveForWLList", iuniWmsReceiveForWLList);
			
		} catch (DBException e) {
			logger.error("IuniWmsTransferStatForWlAction.iuniWmsTransferStatViewForWl found DBException", e);
            return ERROR;
		} catch (Exception e){
			logger.error("IuniWmsTransferStatForWlAction.iuniWmsTransferStatViewForWl found DBException", e);
			e.printStackTrace();
            return ERROR;
		}

		
		
		return SUCCESS;
	}
	
	
	public String iuniWmsTransferStatViewForWl2Excel(){
		
		// 查询全部数据
		List<Map<String,Object>> transferForWl2ExcelList =  iuniWmsTransferStatForWlService.queryIuniWmsTransferForWL2Excel(genParamMap());
		
		List<Map<String,Object>> receiveForWl2ExcelList =  iuniWmsTransferStatForWlService.queryIuniWmsReceiveForWL2Excel(genParamMap());
		
		if(!(CollectionUtils.isEmpty(transferForWl2ExcelList) && CollectionUtils.isEmpty(receiveForWl2ExcelList))){
			//
			String transferSheetName = "调出总仓";
			String receiveSheetName = "调入总仓";
			fileName = "IUNI WMS调拨明细报表（物流）" + "_" + dateFormat.format(new Date());
			try {
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				logger.error("IuniWmsTransferStatForWlAction.iuniWmsTransferStatViewForWl2Excel found UnsupportedEncodingException", e);
				return ERROR;
			}
			
			Map<String, List<String>> columnNamesMap = new HashMap<String, List<String>>();
			columnNamesMap.put(transferSheetName, gencolumnNames());
			columnNamesMap.put(receiveSheetName, genReceivecolumnNames());
			Map<String, List<String>> columnsMap = new HashMap<String, List<String>>();
			columnsMap.put(transferSheetName, gencolumns());
			columnsMap.put(receiveSheetName, genReceivecolumns());
			Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();
			sheetDataList.put(transferSheetName, transferForWl2ExcelList);
			sheetDataList.put(receiveSheetName, receiveForWl2ExcelList);
			XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNamesMap, columnsMap, sheetDataList);
			
			/*XSSFWorkbook workbook = new XSSFWorkbook();
			workbook = ExcelUtil.getWorkbook4Xssf2(workbook,gencolumnNames(), gencolumns(), transferForWl2ExcelList, transferSheetName);
			workbook = ExcelUtil.getWorkbook4Xssf2(workbook,genReceivecolumnNames(), genReceivecolumns(), receiveForWl2ExcelList, receiveSheetName);*/
			//io
			ByteArrayOutputStream bos = null; 
			try {
				bos = new ByteArrayOutputStream();
				workbook.write(bos);
				byte[] b = bos.toByteArray();
				excelStream = new ByteArrayInputStream(b);
				
			} catch (IOException e) {
				logger.error("IuniWmsTransferStatForWlAction.iuniWmsTransferStatViewForWl2Excel found IOException",e);
			} finally{
				try {
					bos.close();
				} catch (IOException e) {
					logger.error("IuniWmsTransferStatForWlAction.iuniWmsTransferStatViewForWl2Excel found IOException",e);
				}
				
			}//end io
		}else{
			return ERROR;
		}
		return SUCCESS;
	}
	
	private List<String> gencolumns() {
		List<String> columns = new ArrayList<String>();
		columns.add("RN");
		columns.add("shippingTime");
		columns.add("billType");
		columns.add("transferName");
		columns.add("wareHouse");
		columns.add("transferId");
		columns.add("outOrderCode");
		columns.add("sku");
		columns.add("materialCode");
		columns.add("skuName");
		columns.add("quantity");
		columns.add("transferType");
		return columns;
	}

	private List<String> gencolumnNames() {
		List<String> columnNames = new ArrayList<String>();
		columnNames.add("序号");
		columnNames.add("日期");
		columnNames.add("调拨类型");
		columnNames.add("调入方");
		columnNames.add("调出方");
		columnNames.add("调拨批次号");
		columnNames.add("外部订单号");
		columnNames.add("SKU");
		columnNames.add("ERP物料编号");
		columnNames.add("名称规格");
		columnNames.add("调拨数量");
		columnNames.add("产品状态");
		return columnNames;
	}
	
	private List<String> genReceivecolumns() {
		List<String> columns = new ArrayList<String>();
		columns.add("RN");
		columns.add("handledTime");
		columns.add("receiveType");
		columns.add("wareHouse");
		columns.add("transferPartnerName");
		columns.add("receiveCode");
		columns.add("sku");
		columns.add("materialCode");
		columns.add("skuName");
		columns.add("quantity");
		columns.add("wareStatus");
		return columns;
	}


	private List<String> genReceivecolumnNames() {
		List<String> columnNames = new ArrayList<String>();
		columnNames.add("序号");
		columnNames.add("日期");
		columnNames.add("业务类型");
		columnNames.add("调入方");
		columnNames.add("调出方");
		columnNames.add("调拨批次号");
		columnNames.add("SKU");
		columnNames.add("ERP物料编号");
		columnNames.add("名称规格");
		columnNames.add("调拨数量");
		columnNames.add("产品状态");
		return columnNames;
	}

	private Map<String, Object> genParamMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		
		//默认查询日期
		if("default".equals(flag)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
			String eDate = dateFormat.format(calendar.getTime()); 
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-6);
			String sDate = dateFormat.format(calendar.getTime());
			map.put("beginDate", sDate);
			map.put("endDate", eDate);
			
			// 设置日期过滤框默认值
			statParams.put("beginDate", sDate);
			statParams.put("endDate", eDate);
		} 
		
		map.put("beginDate", statParams.get("beginDate"));
		map.put("endDate", statParams.get("endDate"));
		
		return map;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
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


	public String getTransferType() {
		return transferType;
	}


	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}


	public String getTransType() {
		return transType;
	}


	public void setTransType(String transType) {
		this.transType = transType;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
