package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * IuniWmsTransfer Model
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.5
 */
public class IuniWmsTransfer implements Serializable {

	private static final long serialVersionUID = 7932444442278708474L;

	private String id;
	
	private String warehouseId;
	
	private String transferTo;
	
	private String status;
	
	private String remark;
	
	private String logisticName;
	
	private String logisticNo;
	
	private String handledBy;
	
	private String consignee;
	
	private String contact;
	
	private String po;
	
	private String transferSale;
	
	private String transferSend;
	
	private String transferInvoice;
	
	private BigDecimal orderAmount;
	
	private String transType;
	
	private Date shippingTime;
	
	private Date createTime;

	public IuniWmsTransfer() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getTransferTo() {
		return transferTo;
	}

	public void setTransferTo(String transferTo) {
		this.transferTo = transferTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLogisticName() {
		return logisticName;
	}

	public void setLogisticName(String logisticName) {
		this.logisticName = logisticName;
	}

	public String getLogisticNo() {
		return logisticNo;
	}

	public void setLogisticNo(String logisticNo) {
		this.logisticNo = logisticNo;
	}

	public String getHandledBy() {
		return handledBy;
	}

	public void setHandledBy(String handledBy) {
		this.handledBy = handledBy;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public String getTransferSale() {
		return transferSale;
	}

	public void setTransferSale(String transferSale) {
		this.transferSale = transferSale;
	}

	public String getTransferSend() {
		return transferSend;
	}

	public void setTransferSend(String transferSend) {
		this.transferSend = transferSend;
	}

	public String getTransferInvoice() {
		return transferInvoice;
	}

	public void setTransferInvoice(String transferInvoice) {
		this.transferInvoice = transferInvoice;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public Date getShippingTime() {
		return shippingTime;
	}

	public void setShippingTime(Date shippingTime) {
		this.shippingTime = shippingTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "IuniWmsTransfer [id=" + id + ", warehouseId=" + warehouseId
				+ ", transferTo=" + transferTo + ", status=" + status
				+ ", remark=" + remark + ", logisticName=" + logisticName
				+ ", logisticNo=" + logisticNo + ", handledBy=" + handledBy
				+ ", consignee=" + consignee + ", contact=" + contact + ", po="
				+ po + ", transferSale=" + transferSale + ", transferSend="
				+ transferSend + ", transferInvoice=" + transferInvoice
				+ ", orderAmount=" + orderAmount + ", transType=" + transType
				+ ", shippingTime=" + shippingTime + ", createTime="
				+ createTime + "]";
	}
	
}
