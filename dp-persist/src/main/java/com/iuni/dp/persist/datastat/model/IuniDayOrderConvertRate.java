/*
 * @(#)IuniDayOrderConvertRate.java 2013-12-18
 *
 * Copyright 2013 Shenzhen Gionee,Inc. All rights reserved.
 */
package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;

/**
 * 日订单转化率
 * @author ZuoChangjun 2013-12-18
 */
public class IuniDayOrderConvertRate implements Serializable{
	
	private static final long serialVersionUID = 1475999533952353699L;
	/**
     * IUNI商城pv
     */
    private Integer pv;

    /**
     * IUNI商城uv
     */
    private Integer uv;

	/**
     * 订单总数
     */
    private Integer totalOrderNum;

    /**
     * 已支付订单数
     */
    private Integer payedOrderNum;
    /**
     * 在线支付订单数
     */
    private Integer onlinePayOrderNum;

    /**
     * 业务数据所属日期，格式：yyyy-MM-dd
     */
    private String bizDate;

    /**
     * 下单转化率  = 订单总数/商城UV
     */
    private String orderConvertRate;
    /**
     * 已支付订单比例  = 已支付订单/订单总数(以%计数，保留小数点后2位)		
     */
    private String payedOrderRate;
    
    /**
     * 在线支付订单比例  = 以线支付方式的支付订单/订单总数(以%计数，保留小数点后2位)
     */
    private String onlinePayOrderRate;

	public Integer getPv() {
		return pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	public Integer getUv() {
		return uv;
	}

	public void setUv(Integer uv) {
		this.uv = uv;
	}

	public Integer getTotalOrderNum() {
		return totalOrderNum;
	}

	public void setTotalOrderNum(Integer totalOrderNum) {
		this.totalOrderNum = totalOrderNum;
	}

	public Integer getOnlinePayOrderNum() {
		return onlinePayOrderNum;
	}

	public void setOnlinePayOrderNum(Integer onlinePayOrderNum) {
		this.onlinePayOrderNum = onlinePayOrderNum;
	}

	public String getBizDate() {
		return bizDate;
	}

	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}

	public String getOrderConvertRate() {
		return orderConvertRate;
	}

	public void setOrderConvertRate(String orderConvertRate) {
		this.orderConvertRate = orderConvertRate;
	}

	public String getOnlinePayOrderRate() {
		return onlinePayOrderRate;
	}

	public void setOnlinePayOrderRate(String onlinePayOrderRate) {
		this.onlinePayOrderRate = onlinePayOrderRate;
	}

	public Integer getPayedOrderNum() {
		return payedOrderNum;
	}

	public void setPayedOrderNum(Integer payedOrderNum) {
		this.payedOrderNum = payedOrderNum;
	}

	public String getPayedOrderRate() {
		return payedOrderRate;
	}

	public void setPayedOrderRate(String payedOrderRate) {
		this.payedOrderRate = payedOrderRate;
	}
   
}
