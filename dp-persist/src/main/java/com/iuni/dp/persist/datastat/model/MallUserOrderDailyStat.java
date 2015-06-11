package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商城用户订单信息日统计
 * @since 2013-09-06
 * @version dp-admin-1.0.0
 */
public class MallUserOrderDailyStat implements Serializable {

    private static final long serialVersionUID = 137845711782104772L;

    /**
     * column MALL_USER_ORDER_DAILY_STAT.ID
     */
    private Long id;

    /**
     * column MALL_USER_ORDER_DAILY_STAT.REG_USER_NUM
     */
    private Integer regUserNum;

    /**
     * column MALL_USER_ORDER_DAILY_STAT.TOTAL_ORDER_NUM
     */
    private Integer totalOrderNum;

    /**
     * column MALL_USER_ORDER_DAILY_STAT.TOTAL_ORDER_AMOUNT
     */
    private Integer totalOrderAmount;

    /**
     * column MALL_USER_ORDER_DAILY_STAT.VALID_ORDER_NUM
     */
    private Integer validOrderNum;

    /**
     * column MALL_USER_ORDER_DAILY_STAT.VALID_ORDER_AMOUNT
     */
    private Integer validOrderAmount;

    /**
     * column MALL_USER_ORDER_DAILY_STAT.BIZ_DATE
     */
    private String bizDate;

    /**
     * column MALL_USER_ORDER_DAILY_STAT.CREATE_TIME
     */
    private Date createTime;
    
    /**
     * ��Աƽ����Ч������=��Ч������/ע���Ա��
     */
    private String orderNumPerUser;
    /**
     * ��Աƽ����Ч������=��Ч�������/ע���Ա��
     */
    private String amountPerUser;

    public MallUserOrderDailyStat() {
        super();
    }

    public MallUserOrderDailyStat(Long id, Integer regUserNum, Integer totalOrderNum, Integer totalOrderAmount, Integer validOrderNum, Integer validOrderAmount, String bizDate, Date createTime) {
        this.id = id;
        this.regUserNum = regUserNum;
        this.totalOrderNum = totalOrderNum;
        this.totalOrderAmount = totalOrderAmount;
        this.validOrderNum = validOrderNum;
        this.validOrderAmount = validOrderAmount;
        this.bizDate = bizDate;
        this.createTime = createTime;
    }

    /**
     * getter for Column MALL_USER_ORDER_DAILY_STAT.ID
     */
    public Long getId() {
        return id;
    }

    /**
     * setter for Column MALL_USER_ORDER_DAILY_STAT.ID
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter for Column MALL_USER_ORDER_DAILY_STAT.REG_USER_NUM
     */
    public Integer getRegUserNum() {
        return regUserNum;
    }

    /**
     * setter for Column MALL_USER_ORDER_DAILY_STAT.REG_USER_NUM
     * @param regUserNum
     */
    public void setRegUserNum(Integer regUserNum) {
        this.regUserNum = regUserNum;
    }

    /**
     * getter for Column MALL_USER_ORDER_DAILY_STAT.TOTAL_ORDER_NUM
     */
    public Integer getTotalOrderNum() {
        return totalOrderNum;
    }

    /**
     * setter for Column MALL_USER_ORDER_DAILY_STAT.TOTAL_ORDER_NUM
     * @param totalOrderNum
     */
    public void setTotalOrderNum(Integer totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
    }

    /**
     * getter for Column MALL_USER_ORDER_DAILY_STAT.TOTAL_ORDER_AMOUNT
     */
    public Integer getTotalOrderAmount() {
        return totalOrderAmount;
    }

    /**
     * setter for Column MALL_USER_ORDER_DAILY_STAT.TOTAL_ORDER_AMOUNT
     * @param totalOrderAmount
     */
    public void setTotalOrderAmount(Integer totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    /**
     * getter for Column MALL_USER_ORDER_DAILY_STAT.VALID_ORDER_NUM
     */
    public Integer getValidOrderNum() {
        return validOrderNum;
    }

    /**
     * setter for Column MALL_USER_ORDER_DAILY_STAT.VALID_ORDER_NUM
     * @param validOrderNum
     */
    public void setValidOrderNum(Integer validOrderNum) {
        this.validOrderNum = validOrderNum;
    }

    /**
     * getter for Column MALL_USER_ORDER_DAILY_STAT.VALID_ORDER_AMOUNT
     */
    public Integer getValidOrderAmount() {
        return validOrderAmount;
    }

    /**
     * setter for Column MALL_USER_ORDER_DAILY_STAT.VALID_ORDER_AMOUNT
     * @param validOrderAmount
     */
    public void setValidOrderAmount(Integer validOrderAmount) {
        this.validOrderAmount = validOrderAmount;
    }

    /**
     * getter for Column MALL_USER_ORDER_DAILY_STAT.BIZ_DATE
     */
    public String getBizDate() {
        return bizDate;
    }

    /**
     * setter for Column MALL_USER_ORDER_DAILY_STAT.BIZ_DATE
     * @param bizDate
     */
    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    /**
     * getter for Column MALL_USER_ORDER_DAILY_STAT.CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * setter for Column MALL_USER_ORDER_DAILY_STAT.CREATE_TIME
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getOrderNumPerUser() {
		return orderNumPerUser;
	}

	public void setOrderNumPerUser(String orderNumPerUser) {
		this.orderNumPerUser = orderNumPerUser;
	}

	public String getAmountPerUser() {
		return amountPerUser;
	}

	public void setAmountPerUser(String amountPerUser) {
		this.amountPerUser = amountPerUser;
	}
}