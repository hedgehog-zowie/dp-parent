package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据对象
 * @since 2013-09-11
 * @version dp-admin-1.0.0
 */
public class MallYqfOrderDailyStat implements Serializable {

    private static final long serialVersionUID = 137888464180922322L;

    /**
     * column MALL_YQF_ORDER_DAILY_STAT.ID
     */
    private Long id;

    /**
     * column MALL_YQF_ORDER_DAILY_STAT.CID
     */
    private Long cid;

    /**
     * column MALL_YQF_ORDER_DAILY_STAT.SOURCE
     */
    private String source;

    /**
     * column MALL_YQF_ORDER_DAILY_STAT.CHANNEL
     */
    private String channel;

    /**
     * column MALL_YQF_ORDER_DAILY_STAT.TOTAL_ORDER_NUM
     */
    private Integer totalOrderNum;

    /**
     * column MALL_YQF_ORDER_DAILY_STAT.VALID_ORDER_NUM
     */
    private Integer validOrderNum;

    /**
     * column MALL_YQF_ORDER_DAILY_STAT.REFUSED_ORDER_NUM
     */
    private Integer refusedOrderNum;

    /**
     * column MALL_YQF_ORDER_DAILY_STAT.RETURNED_ORDER_NUM
     */
    private Integer returnedOrderNum;

    /**
     * column MALL_YQF_ORDER_DAILY_STAT.BIZ_DATE
     */
    private String bizDate;

    /**
     * column MALL_YQF_ORDER_DAILY_STAT.CREATE_TIME
     */
    private Date createTime;

    public MallYqfOrderDailyStat() {
        super();
    }

    public MallYqfOrderDailyStat(Long id, Long cid, String source, String channel, Integer totalOrderNum, Integer validOrderNum, Integer refusedOrderNum, Integer returnedOrderNum, String bizDate, Date createTime) {
        this.id = id;
        this.cid = cid;
        this.source = source;
        this.channel = channel;
        this.totalOrderNum = totalOrderNum;
        this.validOrderNum = validOrderNum;
        this.refusedOrderNum = refusedOrderNum;
        this.returnedOrderNum = returnedOrderNum;
        this.bizDate = bizDate;
        this.createTime = createTime;
    }

    /**
     * getter for Column MALL_YQF_ORDER_DAILY_STAT.ID
     */
    public Long getId() {
        return id;
    }

    /**
     * setter for Column MALL_YQF_ORDER_DAILY_STAT.ID
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter for Column MALL_YQF_ORDER_DAILY_STAT.CID
     */
    public Long getCid() {
        return cid;
    }

    /**
     * setter for Column MALL_YQF_ORDER_DAILY_STAT.CID
     * @param cid
     */
    public void setCid(Long cid) {
        this.cid = cid;
    }

    /**
     * getter for Column MALL_YQF_ORDER_DAILY_STAT.SOURCE
     */
    public String getSource() {
        return source;
    }

    /**
     * setter for Column MALL_YQF_ORDER_DAILY_STAT.SOURCE
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * getter for Column MALL_YQF_ORDER_DAILY_STAT.CHANNEL
     */
    public String getChannel() {
        return channel;
    }

    /**
     * setter for Column MALL_YQF_ORDER_DAILY_STAT.CHANNEL
     * @param channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * getter for Column MALL_YQF_ORDER_DAILY_STAT.TOTAL_ORDER_NUM
     */
    public Integer getTotalOrderNum() {
        return totalOrderNum;
    }

    /**
     * setter for Column MALL_YQF_ORDER_DAILY_STAT.TOTAL_ORDER_NUM
     * @param totalOrderNum
     */
    public void setTotalOrderNum(Integer totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
    }

    /**
     * getter for Column MALL_YQF_ORDER_DAILY_STAT.VALID_ORDER_NUM
     */
    public Integer getValidOrderNum() {
        return validOrderNum;
    }

    /**
     * setter for Column MALL_YQF_ORDER_DAILY_STAT.VALID_ORDER_NUM
     * @param validOrderNum
     */
    public void setValidOrderNum(Integer validOrderNum) {
        this.validOrderNum = validOrderNum;
    }

    /**
     * getter for Column MALL_YQF_ORDER_DAILY_STAT.REFUSED_ORDER_NUM
     */
    public Integer getRefusedOrderNum() {
        return refusedOrderNum;
    }

    /**
     * setter for Column MALL_YQF_ORDER_DAILY_STAT.REFUSED_ORDER_NUM
     * @param refusedOrderNum
     */
    public void setRefusedOrderNum(Integer refusedOrderNum) {
        this.refusedOrderNum = refusedOrderNum;
    }

    /**
     * getter for Column MALL_YQF_ORDER_DAILY_STAT.RETURNED_ORDER_NUM
     */
    public Integer getReturnedOrderNum() {
        return returnedOrderNum;
    }

    /**
     * setter for Column MALL_YQF_ORDER_DAILY_STAT.RETURNED_ORDER_NUM
     * @param returnedOrderNum
     */
    public void setReturnedOrderNum(Integer returnedOrderNum) {
        this.returnedOrderNum = returnedOrderNum;
    }

    /**
     * getter for Column MALL_YQF_ORDER_DAILY_STAT.BIZ_DATE
     */
    public String getBizDate() {
        return bizDate;
    }

    /**
     * setter for Column MALL_YQF_ORDER_DAILY_STAT.BIZ_DATE
     * @param bizDate
     */
    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    /**
     * getter for Column MALL_YQF_ORDER_DAILY_STAT.CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * setter for Column MALL_YQF_ORDER_DAILY_STAT.CREATE_TIME
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}