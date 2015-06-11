package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据对象
 * @since 2013-07-08
 * @version dp-admin-1.0.0
 */
public class MallAdPvuvDailyStat implements Serializable {

    private static final long serialVersionUID = 137327270733236250L;

    /**
     * column MALL_AD_PVUV_DAILY_STAT.ID
     */
    private Long id;

    /**
     * column MALL_AD_PVUV_DAILY_STAT.AD_ID
     */
    private Long adId;

    /**
     * column MALL_AD_PVUV_DAILY_STAT.CHANNEL_NAME
     */
    private String channelName;

    /**
     * column MALL_AD_PVUV_DAILY_STAT.CHANNEL_PV
     */
    private int channelPv;

    /**
     * column MALL_AD_PVUV_DAILY_STAT.CHANNEL_UV
     */
    private int channelUv;

    /**
     * column MALL_AD_PVUV_DAILY_STAT.BIZ_DATE
     */
    private String bizDate;

    /**
     * column MALL_AD_PVUV_DAILY_STAT.CREATE_TIME
     */
    private Date createTime;

    public MallAdPvuvDailyStat() {
        super();
    }

    public MallAdPvuvDailyStat(Long id, Long adId, String channelName, int channelPv, int channelUv, String bizDate, Date createTime) {
        this.id = id;
        this.adId = adId;
        this.channelName = channelName;
        this.channelPv = channelPv;
        this.channelUv = channelUv;
        this.bizDate = bizDate;
        this.createTime = createTime;
    }

    /**
     * getter for Column MALL_AD_PVUV_DAILY_STAT.ID
     */
    public Long getId() {
        return id;
    }

    /**
     * setter for Column MALL_AD_PVUV_DAILY_STAT.ID
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter for Column MALL_AD_PVUV_DAILY_STAT.AD_ID
     */
    public Long getAdId() {
        return adId;
    }

    /**
     * setter for Column MALL_AD_PVUV_DAILY_STAT.AD_ID
     * @param adId
     */
    public void setAdId(Long adId) {
        this.adId = adId;
    }

    /**
     * getter for Column MALL_AD_PVUV_DAILY_STAT.CHANNEL_NAME
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * setter for Column MALL_AD_PVUV_DAILY_STAT.CHANNEL_NAME
     * @param channelName
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * getter for Column MALL_AD_PVUV_DAILY_STAT.CHANNEL_PV
     */
    public int getChannelPv() {
        return channelPv;
    }

    /**
     * setter for Column MALL_AD_PVUV_DAILY_STAT.CHANNEL_PV
     * @param channelPv
     */
    public void setChannelPv(int channelPv) {
        this.channelPv = channelPv;
    }

    /**
     * getter for Column MALL_AD_PVUV_DAILY_STAT.CHANNEL_UV
     */
    public int getChannelUv() {
        return channelUv;
    }

    /**
     * setter for Column MALL_AD_PVUV_DAILY_STAT.CHANNEL_UV
     * @param channelUv
     */
    public void setChannelUv(int channelUv) {
        this.channelUv = channelUv;
    }

    /**
     * getter for Column MALL_AD_PVUV_DAILY_STAT.BIZ_DATE
     */
    public String getBizDate() {
        return bizDate;
    }

    /**
     * setter for Column MALL_AD_PVUV_DAILY_STAT.BIZ_DATE
     * @param bizDate
     */
    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    /**
     * getter for Column MALL_AD_PVUV_DAILY_STAT.CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * setter for Column MALL_AD_PVUV_DAILY_STAT.CREATE_TIME
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}