/*
 * @(#)WmsOrderGoods.java 2013-12-23
 *
 * Copyright 2013 Shenzhen Gionee,Inc. All rights reserved.
 */
package com.iuni.dp.persist.datastat.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ZuoChangjun 2013-12-23
 */
public class WmsOrderGoods {
	private Date jhTime; // 捡货时间
	private String batchCode;// 拣货批次号
	
	private String orderCode;// 订单编号
	private String orderSource;//订单来源
	private String orderUser;// 下单人
	private Date orderTime; // 下单时间
	private String paymentName;// 支付方式名称
	private String payNo;// 支付流水
	private String shippingName;// 配送方式名称
	private String shippingNo;// 配送单号
	private String orderStatus;
	private String invoiceTitle;// 发票抬头
	private BigDecimal invoiceAmount;// 发票金额
	private String consignee;//收货人
	private String address;//收货地址
	private String shortAddress;//收货地址(截取)
	
	private String skuName;// SKU名称
	private BigDecimal unitPrice;// 单价
	private Integer quantity;// 数量
	private BigDecimal subtotalPrice;// 价格小计
	
	private String postscript;// 订单附言
	private String remark;//客服备注
	
	
	public Date getJhTime() {
		return jhTime;
	}
	public void setJhTime(Date jhTime) {
		this.jhTime = jhTime;
	}
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	public String getOrderUser() {
		return orderUser;
	}
	public void setOrderUser(String orderUser) {
		this.orderUser = orderUser;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getShippingNo() {
		return shippingNo;
	}
	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getSubtotalPrice() {
		return subtotalPrice;
	}
	public void setSubtotalPrice(BigDecimal subtotalPrice) {
		this.subtotalPrice = subtotalPrice;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostscript() {
		return postscript;
	}
	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getShortAddress() {
		return shortAddress;
	}
	public void setShortAddress(String shortAddress) {
		this.shortAddress = shortAddress;
	}
	
}
