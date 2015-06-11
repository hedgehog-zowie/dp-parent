package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * IuniReturnGoodsInfo Model
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.5
 */
public class IuniReturnGoodsInfo implements Serializable {

	private static final long serialVersionUID = 3798815205071248798L;

	private String deliverySn;
	
	private String orderSn;
	
	private String invoiceNo;
	
	private Date addTime;
	
	private Date orderTime;
	
	private Date processTime;
	
	private String status;

	public IuniReturnGoodsInfo() {
		super();
	}

	public String getDeliverySn() {
		return deliverySn;
	}

	public void setDeliverySn(String deliverySn) {
		this.deliverySn = deliverySn;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "IuniReturnGoodsInfo [deliverySn=" + deliverySn + ", orderSn="
				+ orderSn + ", invoiceNo=" + invoiceNo + ", addTime=" + addTime
				+ ", orderTime=" + orderTime + ", processTime=" + processTime
				+ ", status=" + status + "]";
	}
	
}
