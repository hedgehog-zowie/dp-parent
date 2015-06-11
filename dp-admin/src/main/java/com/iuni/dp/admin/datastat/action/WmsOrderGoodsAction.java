/*
 * @(#)WmsOrderGoodsAction.java 2013-12-23
 *
 * Copyright 2013 Shenzhen Gionee,Inc. All rights reserved.
 */
package com.iuni.dp.admin.datastat.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.admin.datastat.utils.ExcelUtils;
import com.iuni.dp.persist.datastat.model.WmsOrderGoods;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.datastat.service.WmsOrderGoodsService;

/**
 *
 * @author ZuoChangjun 2013-12-23
 */
@Controller("wmsOrderGoodsAction")
@Scope("prototype")
public class WmsOrderGoodsAction  extends BaseAction{

	private static final Logger logger = LoggerFactory.getLogger(WmsOrderGoodsAction.class);
	private String beginDate;// 下单开始日期
	private String endDate;// 下单结束日期
	
	private String jhBeginDate;// 拣货开始日期
	private String jhEndDate;// 拣货结束日期
	private String batchCode;// 拣货批次号
	private String batchCodeStr;//以单个空格、逗号、分号、斜杠、单竖线连接
	private String orderCode;// 订单号
	private String shippingNo;// 运单号
	/**
	 * wms销售数据列表
	 */
	private List<WmsOrderGoods> wmsOrderGoodsList;
	@Autowired
	private WmsOrderGoodsService wmsOrderGoodsService;
	
	/**
	 * 分页查询wms销售数据
	 * 
	 * @return
	 */
	public String queryWmsOrderGoodsList() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
		
		paramsMap.put("jhBeginDate", StringUtils.defaultIfEmpty(jhBeginDate, null));
		paramsMap.put("jhEndDate", StringUtils.defaultIfEmpty(jhEndDate, null));
		paramsMap.put("batchCodes", splitBatchCodeStr(batchCodeStr));
		paramsMap.put("orderCode", StringUtils.defaultIfEmpty(orderCode, null));
		paramsMap.put("shippingNo", StringUtils.defaultIfEmpty(shippingNo, null));
		try {
			// 查询总记录
			Integer totalRecord = wmsOrderGoodsService.queryWmsOrderGoodsListCount(paramsMap);
			if (totalRecord == null || totalRecord.intValue() == 0) {
				return SUCCESS;
			}
			// 根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord,20);
			// 设置分页起始值
			paramsMap.put("startRec", page.getStartRec());
			paramsMap.put("endRec", page.getEndRec());
			// 查询列表
			wmsOrderGoodsList = wmsOrderGoodsService.queryWmsOrderGoodsList(paramsMap);
			if(!CollectionUtils.isEmpty(wmsOrderGoodsList)){
				for(WmsOrderGoods goods:wmsOrderGoodsList){
					goods.setAddress(goods.getAddress() == null?"":goods.getAddress().replace("\"", "'"));
					if(goods.getAddress().length()>6){
						goods.setShortAddress(goods.getAddress().substring(0, 6)+"…");
					}else{
						goods.setShortAddress(goods.getAddress());
					}
				}
			}
		} catch (ServiceException se) {
			logger.error("queryWmsOrderGoodsList error:" + se.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("queryWmsOrderGoodsList error:" + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 导出excel金立商城销售数据
	 * 
	 * @return
	 */
	public void wmsOrderGoodsList2Excel() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		// 设置分页起始值
		paramsMap.put("startRec", 1);
		paramsMap.put("endRec", Integer.MAX_VALUE);
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.isNotBlank(endDate)?endDate + " 23:59:59":null);
		
		paramsMap.put("jhBeginDate", StringUtils.defaultIfEmpty(jhBeginDate, null));
		paramsMap.put("jhEndDate", StringUtils.isNotBlank(jhEndDate)?jhEndDate + " 23:59:59":null);
		paramsMap.put("batchCodes", splitBatchCodeStr(batchCodeStr));
		paramsMap.put("orderCode", StringUtils.defaultIfEmpty(orderCode, null));
		paramsMap.put("shippingNo", StringUtils.defaultIfEmpty(shippingNo, null));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "WMS订单数据_" + sdf.format(new Date());
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("wmsOrderGoodsList2Excel UnsupportedEncodingException:" + e.getMessage());
		}
		int columnNum = 21;
		ExcelUtils exportExcelUtils = new ExcelUtils(response,fileName, columnNum);
		try {
			// 查询列表
			wmsOrderGoodsList = wmsOrderGoodsService.queryWmsOrderGoodsList(paramsMap);
			exportExcelUtils.wmsOrderGoodsList2Excel(wmsOrderGoodsList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("wmsOrderGoodsList2Excel error:" + e.getMessage());
		}
	}

	public String[] splitBatchCodeStr(String batchCodeStr){
		String[] batchCodes = new String[1];
		if(StringUtils.isBlank(batchCodeStr)){
			return null;
		}
		if(batchCodeStr.contains(" ")){
			batchCodes = batchCodeStr.split(" ");
		}
		else if(batchCodeStr.contains(",")){
			batchCodes = batchCodeStr.split(",");
		}
		else if(batchCodeStr.contains(";")){
			batchCodes = batchCodeStr.split(";");
		}
		else if(batchCodeStr.contains("/")){
			batchCodes = batchCodeStr.split("/");
		}
		else if(batchCodeStr.contains("\\")){
			batchCodes = batchCodeStr.split("\\\\");
		}
		else if(batchCodeStr.contains("|")){
			batchCodes = batchCodeStr.split("\\|");
		}
		else {
			batchCodes[0] = batchCodeStr;
		}	
		return batchCodes;
	}
	
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<WmsOrderGoods> getWmsOrderGoodsList() {
		return wmsOrderGoodsList;
	}

	public void setWmsOrderGoodsList(List<WmsOrderGoods> wmsOrderGoodsList) {
		this.wmsOrderGoodsList = wmsOrderGoodsList;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getJhBeginDate() {
		return jhBeginDate;
	}

	public void setJhBeginDate(String jhBeginDate) {
		this.jhBeginDate = jhBeginDate;
	}

	public String getJhEndDate() {
		return jhEndDate;
	}

	public void setJhEndDate(String jhEndDate) {
		this.jhEndDate = jhEndDate;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getShippingNo() {
		return shippingNo;
	}

	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
	}

	public String getBatchCodeStr() {
		return batchCodeStr;
	}

	public void setBatchCodeStr(String batchCodeStr) {
		this.batchCodeStr = batchCodeStr;
	}
}
