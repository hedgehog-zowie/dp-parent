package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;

/**
 * IuniOrderInfo Model
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.2
 */
public class IuniOrderInfo implements Serializable {

	private static final long serialVersionUID = -6375447572845555106L;

	private String orderSn;
	
	private String userId;
	
	private String orderStatus;
	
	private String shippingStatus;
	
	private String payStatus;
	
	private String country;
	
	private String province;
	
	private String city;
	
	private String district;
	
	private Long addTime;
	
	private Long payTime;
	
	private Long shippingTime;
	
	private String orderType;
	
	private String oLevel;

	public IuniOrderInfo() {
		super();
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}

	public Long getPayTime() {
		return payTime;
	}

	public void setPayTime(Long payTime) {
		this.payTime = payTime;
	}

	public Long getShippingTime() {
		return shippingTime;
	}

	public void setShippingTime(Long shippingTime) {
		this.shippingTime = shippingTime;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getoLevel() {
		return oLevel;
	}

	public void setoLevel(String oLevel) {
		this.oLevel = oLevel;
	}

	@Override
	public String toString() {
		return "IuniOrderInfo [orderSn=" + orderSn + ", userId=" + userId
				+ ", orderStatus=" + orderStatus + ", shippingStatus="
				+ shippingStatus + ", payStatus=" + payStatus + ", country="
				+ country + ", province=" + province + ", city=" + city
				+ ", district=" + district + ", addTime=" + addTime
				+ ", payTime=" + payTime + ", shippingTime=" + shippingTime
				+ ", orderType=" + orderType + ", oLevel=" + oLevel + "]";
	}

}
