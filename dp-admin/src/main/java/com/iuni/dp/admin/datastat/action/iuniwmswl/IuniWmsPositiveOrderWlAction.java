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
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsOrderEffectiveTimeService;

/**
 * 正向订单时效统计表(物流部)
 * @author dan.wang@iuni.com
 *
 */
@Controller("iuniWmsPositiveOrderWlAction")
@Scope("prototype")
public class IuniWmsPositiveOrderWlAction extends BaseAction {

	private static final long serialVersionUID = -5915135355000248097L;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	private String flag;
	private String fileName;
	
	private InputStream excelStream;
	private final Logger logger = LoggerFactory.getLogger(IuniWmsPositiveOrderWlAction.class);
	
	@Autowired
	private IuniWmsOrderEffectiveTimeService iuniWmsOrderEffectiveTimeService;
	
	
	public String iuniWmsPositiveOrderWl(){
		
		List<Map<String,Object>> posOrderList = new ArrayList<Map<String,Object>>();
		
		try {
			//设置查询参数
			Map<String,Object> queryParams = genParamMap();
			//查询数量
			Integer count = iuniWmsOrderEffectiveTimeService.queryIuniWmsPositiveOrderWlCount(queryParams);
			//生成page
			page = Page.genPage(page.getCurrentPage(), count, page.getPageSize());
			queryParams.put("startRec", page.getStartRec());
			queryParams.put("endRec", page.getEndRec());
			//按分页查询
			posOrderList = iuniWmsOrderEffectiveTimeService.queryIuniWmsPositiveOrderWl(queryParams);
			
		} catch (Exception e) {
			logger.error("IuniWmsPositiveOrderWlAction.iuniWmsPositiveOrderWl() Exception",e);
			e.printStackTrace();
			return ERROR;
		}
		
		request.setAttribute("posOrderList", posOrderList);
		return SUCCESS;
	} 
	
	public String iuniWmsPositiveOrderWl2Excel(){
		Map<String,Object> queryParams = genParamMap();
		List<Map<String,Object>> excelList = iuniWmsOrderEffectiveTimeService.queryIuniWmsPositiveOrderWl2Excel(queryParams);
		if(CollectionUtils.isNotEmpty(excelList)){
			String sheetName = "正向订单时效统计表";
			fileName = sheetName + "_" + dateFormat.format(new Date());
			try {
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			XSSFWorkbook workbook = ExcelUtil.getWorkbook4Xssf(gencolumnNames(), gencolumns(),  excelList, sheetName);
			//io
			ByteArrayOutputStream bos = null; 
			try {
				bos = new ByteArrayOutputStream();
				workbook.write(bos);
				byte[] b = bos.toByteArray();
				excelStream = new ByteArrayInputStream(b);
				
			} catch (IOException e) {
				logger.error("IuniWmsStockMoveViewForWlAction.iuniWmsStockMoveViewForWl2Excel found IOException",e);
			} finally{
				try {
					bos.close();
				} catch (IOException e) {
					logger.error("IuniWmsStockMoveViewForWlAction.iuniWmsStockMoveViewForWl2Excel found IOException",e);
				}
				
			}//end io
		}else return ERROR;
		return SUCCESS;
	}
	
	private List<String> gencolumns() {
		List<String> columns = new ArrayList<String>();
		columns.add("RN");
		columns.add("orderCode");
		columns.add("opTime");
		columns.add("time1");
		columns.add("time2");
		columns.add("time3");
		columns.add("time4");
		columns.add("time5");
		columns.add("time6");
		
		columns.add("time21");
		columns.add("time32");
		columns.add("time43");
		columns.add("time54");
		columns.add("time65");
		columns.add("time61");
		return columns;
	}

	private List<String> gencolumnNames() {			
		List<String> columnNames = new ArrayList<String>();
		columnNames.add("序号");
		columnNames.add("订单号");
		columnNames.add("出库日期");
		
		columnNames.add("已筛单时间");
		columnNames.add("已打单时间");
		columnNames.add("配货中时间");
		columnNames.add("已配货时间");
		columnNames.add("待出库时间");
		columnNames.add("已出库时间");
		
		columnNames.add("已筛单-已打单时长");
		columnNames.add("已打单-配货中时长");
		columnNames.add("配货中-已配货时长");
		columnNames.add("已配货 -待出库时长");
		columnNames.add("待出库-已出库时长");
		columnNames.add("合计时长");
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


	public Map<String, String> getStatParams() {
		return statParams;
	}


	public void setStatParams(Map<String, String> statParams) {
		this.statParams = statParams;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
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
