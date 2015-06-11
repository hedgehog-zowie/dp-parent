package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据对象
 * @since 2013-07-08
 * @version dp-admin-1.0.0
 */
public class MallAdOrderDailyStat implements Serializable {

    private static final long serialVersionUID = 137327267491546762L;

    /**
     * column MALL_AD_ORDER_DAILY_STAT.ID
     */
    private Long id;

    /**
     * column MALL_AD_ORDER_DAILY_STAT.AD_ID
     */
    private Long adId;

    /**
     * column MALL_AD_ORDER_DAILY_STAT.CHANNEL_NAME
     */
    private String channelName;

    /**
     * column MALL_AD_ORDER_DAILY_STAT.VALID_ORDER_NUM
     */
    private int validOrderNum;

    /**
     * column MALL_AD_ORDER_DAILY_STAT.TOTAL_ORDER_NUM
     */
    private int totalOrderNum;

    /**
     * column MALL_AD_ORDER_DAILY_STAT.BACK_ORDER_NUM
     */
    private int backOrderNum;

    /**
     * column MALL_AD_ORDER_DAILY_STAT.BIZ_DATE
     */
    private String bizDate;

    /**
     * column MALL_AD_ORDER_DAILY_STAT.CREATE_TIME
     */
    private Date createTime;

    public MallAdOrderDailyStat() {
        super();
    }

    public MallAdOrderDailyStat(Long id, Long adId, String channelName, int validOrderNum, int totalOrderNum, int backOrderNum, String bizDate, Date createTime) {
        this.id = id;
        this.adId = adId;
        this.channelName = channelName;
        this.validOrderNum = validOrderNum;
        this.totalOrderNum = totalOrderNum;
        this.backOrderNum = backOrderNum;
        this.bizDate = bizDate;
        this.createTime = createTime;
    }

    /**
     * getter for Column MALL_AD_ORDER_DAILY_STAT.ID
     */
    public Long getId() {
        return id;
    }

    /**
     * setter for Column MALL_AD_ORDER_DAILY_STAT.ID
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter for Column MALL_AD_ORDER_DAILY_STAT.AD_ID
     */
    public Long getAdId() {
        return adId;
    }

    /**
     * setter for Column MALL_AD_ORDER_DAILY_STAT.AD_ID
     * @param adId
     */
    public void setAdId(Long adId) {
        this.adId = adId;
    }

    /**
     * getter for Column MALL_AD_ORDER_DAILY_STAT.CHANNEL_NAME
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * setter for Column MALL_AD_ORDER_DAILY_STAT.CHANNEL_NAME
     * @param channelName
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * getter for Column MALL_AD_ORDER_DAILY_STAT.VALID_ORDER_NUM
     */
    public int getValidOrderNum() {
        return validOrderNum;
    }

    /**
     * setter for Column MALL_AD_ORDER_DAILY_STAT.VALID_ORDER_NUM
     * @param validOrderNum
     */
    public void setValidOrderNum(int validOrderNum) {
        this.validOrderNum = validOrderNum;
    }

    /**
     * getter for Column MALL_AD_ORDER_DAILY_STAT.TOTAL_ORDER_NUM
     */
    public int getTotalOrderNum() {
        return totalOrderNum;
    }

    /**
     * setter for Column MALL_AD_ORDER_DAILY_STAT.TOTAL_ORDER_NUM
     * @param totalOrderNum
     */
    public void setTotalOrderNum(int totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
    }

    /**
     * getter for Column MALL_AD_ORDER_DAILY_STAT.BACK_ORDER_NUM
     */
    public int getBackOrderNum() {
        return backOrderNum;
    }

    /**
     * setter for Column MALL_AD_ORDER_DAILY_STAT.BACK_ORDER_NUM
     * @param backOrderNum
     */
    public void setBackOrderNum(int backOrderNum) {
        this.backOrderNum = backOrderNum;
    }

    /**
     * getter for Column MALL_AD_ORDER_DAILY_STAT.BIZ_DATE
     */
    public String getBizDate() {
        return bizDate;
    }

    /**
     * setter for Column MALL_AD_ORDER_DAILY_STAT.BIZ_DATE
     * @param bizDate
     */
    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    /**
     * getter for Column MALL_AD_ORDER_DAILY_STAT.CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * setter for Column MALL_AD_ORDER_DAILY_STAT.CREATE_TIME
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}