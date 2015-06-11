/*
 * @(#)IuniDayPvuvUserReg.java 2013-12-20
 *
 * Copyright 2013 Shenzhen Gionee,Inc. All rights reserved.
 */
package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;

/**
 * IUNI商城日PV UV及注册情况
 * @author ZuoChangjun 2013-12-20
 */
public class IuniDayPvuvUserReg implements Serializable{

	private static final long serialVersionUID = -6761296628325464459L;

	/**
     * IUNI商城pv
     */
    private Integer pv;

    /**
     * IUNI商城uv
     */
    private Integer uv;

	/**
     * IUNI商城会员注册数
     */
    private Integer gioneeRegNum;
    
    /**
     * 注册成功率=商城注册会员数/注册页UV		
     */
    private String regSuccRate;

	/**
     * sina登录(注册)数
     */
    private Integer sinaRegNum;
	/**
     * qq登录(注册)数
     */
    private Integer qqRegNum;
	/**
     * 支付宝登录(注册)数
     */
    private Integer zfbRegNum;
	/**
     * 豆瓣登录(注册)数
     */
    private Integer doubanRegNum;

    /**
     * 购买活跃用户数：时间点倒推30天下单的用户			
     */
    private Integer buyUserNum;
    
    /**
     * 访问活跃用户数：时间点倒推15天进行过商城登录的用户			
     */
    private Integer loginUserNum;
    
    /**
     * 业务数据所属日期，格式：yyyy-MM-dd
     */
    private String bizDate;

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

	public Integer getGioneeRegNum() {
		return gioneeRegNum;
	}

	public void setGioneeRegNum(Integer gioneeRegNum) {
		this.gioneeRegNum = gioneeRegNum;
	}

	public Integer getSinaRegNum() {
		return sinaRegNum;
	}

	public void setSinaRegNum(Integer sinaRegNum) {
		this.sinaRegNum = sinaRegNum;
	}

	public Integer getQqRegNum() {
		return qqRegNum;
	}

	public void setQqRegNum(Integer qqRegNum) {
		this.qqRegNum = qqRegNum;
	}

	public Integer getZfbRegNum() {
		return zfbRegNum;
	}

	public void setZfbRegNum(Integer zfbRegNum) {
		this.zfbRegNum = zfbRegNum;
	}

	public Integer getDoubanRegNum() {
		return doubanRegNum;
	}

	public void setDoubanRegNum(Integer doubanRegNum) {
		this.doubanRegNum = doubanRegNum;
	}

	public String getRegSuccRate() {
		return regSuccRate;
	}

	public void setRegSuccRate(String regSuccRate) {
		this.regSuccRate = regSuccRate;
	}

	public Integer getBuyUserNum() {
		return buyUserNum;
	}

	public void setBuyUserNum(Integer buyUserNum) {
		this.buyUserNum = buyUserNum;
	}

	public Integer getLoginUserNum() {
		return loginUserNum;
	}

	public void setLoginUserNum(Integer loginUserNum) {
		this.loginUserNum = loginUserNum;
	}

	public String getBizDate() {
		return bizDate;
	}

	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}
    
}
