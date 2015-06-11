package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * IuniWmsSalesOrder Model
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.5
 */
public class IuniWmsSalesOrder implements Serializable {

	private static final long serialVersionUID = 8507355413648955565L;

	private String orderCode;
	
	private String batchCode;
	
	private String orderSource;
	
	private String consignee;
	
	private String province;
	
	private String city;
	
	private String address;
	
	private String mobile;
	
	private Integer paymentType;
	
	private String shippingName;
	
	private String shippingNo;
	
	private Date orderTime;
	
	private Date shippingTime;
	
	private String payNo;
	
	private BigDecimal orderAmount;
	
	private BigDecimal payAmount;
	
	private BigDecimal paidAmount;
	
	private String invoiceEnabled;
	
	private String invoiceTitle;
	
	private BigDecimal invoiceAmount;
	
	private String orderStatus;
	
	private BigDecimal weight;

	public IuniWmsSalesOrder() {
		super();
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
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

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getShippingTime() {
		return shippingTime;
	}

	public void setShippingTime(Date shippingTime) {
		this.shippingTime = shippingTime;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getInvoiceEnabled() {
		return invoiceEnabled;
	}

	public void setInvoiceEnabled(String invoiceEnabled) {
		this.invoiceEnabled = invoiceEnabled;
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "IuniWmsSalesOrder [orderCode=" + orderCode + ", batchCode="
				+ batchCode + ", orderSource=" + orderSource + ", consignee="
				+ consignee + ", province=" + province + ", city=" + city
				+ ", address=" + address + ", mobile=" + mobile
				+ ", paymentType=" + paymentType + ", shippingName="
				+ shippingName + ", shippingNo=" + shippingNo + ", orderTime="
				+ orderTime + ", shippingTime=" + shippingTime + ", payNo="
				+ payNo + ", orderAmount=" + orderAmount + ", payAmount="
				+ payAmount + ", paidAmount=" + paidAmount
				+ ", invoiceEnabled=" + invoiceEnabled + ", invoiceTitle="
				+ invoiceTitle + ", invoiceAmount=" + invoiceAmount
				+ ", orderStatus=" + orderStatus + ", weight=" + weight + "]";
	}
	
}
