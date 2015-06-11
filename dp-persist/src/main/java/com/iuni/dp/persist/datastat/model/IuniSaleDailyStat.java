package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * IUNI商城日销售情况
 * @since 2013-08-19
 * @version dp-admin-1.0.0
 */
public class IuniSaleDailyStat implements Serializable {

    private static final long serialVersionUID = 137690341861258753L;

    /**
     * column MALL_SALE_DAILY_STAT.ID
     * 主键
     */
    private Long id;

    /**
     * column MALL_SALE_DAILY_STAT.TOTAL_ORDER_NUM
     * 订单总数
     */
    private Integer totalOrderNum;

    /**
     * column MALL_SALE_DAILY_STAT.TOTAL_ORDER_AMOUNT
     * 订单总金额
     */
    private Integer totalOrderAmount;

    /**
     * column MALL_SALE_DAILY_STAT.TOTAL_GOODS_NUM
     * 订单商品总件数
     */
    private Integer totalGoodsNum;

    /**
     * column MALL_SALE_DAILY_STAT.ONLINE_PAY_ORDER_NUM
     * 在线支付订单数
     */
    private Integer onlinePayOrderNum;

    /**
     * column MALL_SALE_DAILY_STAT.OFFLINE_PAY_ORDER_NUM
     * 货到付款订单数
     */
    private Integer offlinePayOrderNum;

    /**
     * column MALL_SALE_DAILY_STAT.RETURNED_ORDER_NUM
     * 退货订单数
     */
    private Integer returnedOrderNum;

    /**
     * column MALL_SALE_DAILY_STAT.REFUSED_ORDER_NUM
     * 拒收订单数
     */
    private Integer refusedOrderNum;

    /**
     * column MALL_SALE_DAILY_STAT.VALID_ORDER_NUM
     * 有效订单数
     */
    private Integer validOrderNum;

    /**
     * column MALL_SALE_DAILY_STAT.VALID_ORDER_AMOUNT
     * 有效订单金额
     */
    private Integer validOrderAmount;

    /**
     * column MALL_SALE_DAILY_STAT.VALID_GOODS_NUM
     * 有效订单商品总件数
     */
    private Integer validGoodsNum;

    /**
     * column MALL_SALE_DAILY_STAT.PAYED_ORDER_NUM
     * 已支付订单数
     */
    private Integer payedOrderNum;

    /**
     * column MALL_SALE_DAILY_STAT.PRE_PAYED_ORDER_NUM
     * 预约且已支付订单数
     */
    private Integer prePayedOrderNum;

    /**
     * column MALL_SALE_DAILY_STAT.BIZ_DATE
     * 业务数据所属日期，格式：yyyy-MM-dd
     */
    private String bizDate;

    /**
     * column MALL_SALE_DAILY_STAT.CREATE_TIME
     */
    private Date createTime;

    /**
     * 有效订单比率=有效订单数/总订单数
     */
    private String validOrderRate;
    /**
     * 客单价=有效订单总金额/有效订单数
     */
    private String amountPerOrder;
    
    /**
     * 客件数=有效订单总件数/有效订单数
     */
    private String goodsNumPerOrder;
    
    public IuniSaleDailyStat() {
        super();
    }

    public IuniSaleDailyStat(Long id, Integer totalOrderNum,
			Integer totalOrderAmount, Integer totalGoodsNum,
			Integer onlinePayOrderNum, Integer offlinePayOrderNum,
			Integer returnedOrderNum, Integer refusedOrderNum,
			Integer validOrderNum, Integer validOrderAmount,
			Integer validGoodsNum, Integer payedOrderNum,
			Integer prePayedOrderNum, String bizDate, Date createTime,
			String validOrderRate, String amountPerOrder,
			String goodsNumPerOrder) {
		super();
		this.id = id;
		this.totalOrderNum = totalOrderNum;
		this.totalOrderAmount = totalOrderAmount;
		this.totalGoodsNum = totalGoodsNum;
		this.onlinePayOrderNum = onlinePayOrderNum;
		this.offlinePayOrderNum = offlinePayOrderNum;
		this.returnedOrderNum = returnedOrderNum;
		this.refusedOrderNum = refusedOrderNum;
		this.validOrderNum = validOrderNum;
		this.validOrderAmount = validOrderAmount;
		this.validGoodsNum = validGoodsNum;
		this.payedOrderNum = payedOrderNum;
		this.prePayedOrderNum = prePayedOrderNum;
		this.bizDate = bizDate;
		this.createTime = createTime;
		this.validOrderRate = validOrderRate;
		this.amountPerOrder = amountPerOrder;
		this.goodsNumPerOrder = goodsNumPerOrder;
	}



	/**
     * getter for Column MALL_SALE_DAILY_STAT.ID
     */
    public Long getId() {
        return id;
    }

    /**
     * setter for Column MALL_SALE_DAILY_STAT.ID
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter for Column MALL_SALE_DAILY_STAT.TOTAL_ORDER_NUM
     */
    public Integer getTotalOrderNum() {
        return totalOrderNum;
    }

    /**
     * setter for Column MALL_SALE_DAILY_STAT.TOTAL_ORDER_NUM
     * @param totalOrderNum
     */
    public void setTotalOrderNum(Integer totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
    }

    /**
     * getter for Column MALL_SALE_DAILY_STAT.TOTAL_ORDER_AMOUNT
     */
    public Integer getTotalOrderAmount() {
        return totalOrderAmount;
    }

    /**
     * setter for Column MALL_SALE_DAILY_STAT.TOTAL_ORDER_AMOUNT
     * @param totalOrderAmount
     */
    public void setTotalOrderAmount(Integer totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    /**
     * getter for Column MALL_SALE_DAILY_STAT.TOTAL_GOODS_NUM
     */
    public Integer getTotalGoodsNum() {
        return totalGoodsNum;
    }

    /**
     * setter for Column MALL_SALE_DAILY_STAT.TOTAL_GOODS_NUM
     * @param totalGoodsNum
     */
    public void setTotalGoodsNum(Integer totalGoodsNum) {
        this.totalGoodsNum = totalGoodsNum;
    }

    /**
     * getter for Column MALL_SALE_DAILY_STAT.ONLINE_PAY_ORDER_NUM
     */
    public Integer getOnlinePayOrderNum() {
        return onlinePayOrderNum;
    }

    /**
     * setter for Column MALL_SALE_DAILY_STAT.ONLINE_PAY_ORDER_NUM
     * @param onlinePayOrderNum
     */
    public void setOnlinePayOrderNum(Integer onlinePayOrderNum) {
        this.onlinePayOrderNum = onlinePayOrderNum;
    }

    /**
     * getter for Column MALL_SALE_DAILY_STAT.OFFLINE_PAY_ORDER_NUM
     */
    public Integer getOfflinePayOrderNum() {
        return offlinePayOrderNum;
    }

    /**
     * setter for Column MALL_SALE_DAILY_STAT.OFFLINE_PAY_ORDER_NUM
     * @param offlinePayOrderNum
     */
    public void setOfflinePayOrderNum(Integer offlinePayOrderNum) {
        this.offlinePayOrderNum = offlinePayOrderNum;
    }

    /**
     * getter for Column MALL_SALE_DAILY_STAT.RETURNED_ORDER_NUM
     */
    public Integer getReturnedOrderNum() {
        return returnedOrderNum;
    }

    /**
     * setter for Column MALL_SALE_DAILY_STAT.RETURNED_ORDER_NUM
     * @param returnedOrderNum
     */
    public void setReturnedOrderNum(Integer returnedOrderNum) {
        this.returnedOrderNum = returnedOrderNum;
    }

    /**
     * getter for Column MALL_SALE_DAILY_STAT.REFUSED_ORDER_NUM
     */
    public Integer getRefusedOrderNum() {
        return refusedOrderNum;
    }

    /**
     * setter for Column MALL_SALE_DAILY_STAT.REFUSED_ORDER_NUM
     * @param refusedOrderNum
     */
    public void setRefusedOrderNum(Integer refusedOrderNum) {
        this.refusedOrderNum = refusedOrderNum;
    }

    /**
     * getter for Column MALL_SALE_DAILY_STAT.VALID_ORDER_NUM
     */
    public Integer getValidOrderNum() {
        return validOrderNum;
    }

    /**
     * setter for Column MALL_SALE_DAILY_STAT.VALID_ORDER_NUM
     * @param validOrderNum
     */
    public void setValidOrderNum(Integer validOrderNum) {
        this.validOrderNum = validOrderNum;
    }

    /**
     * getter for Column MALL_SALE_DAILY_STAT.VALID_ORDER_AMOUNT
     */
    public Integer getValidOrderAmount() {
        return validOrderAmount;
    }

    /**
     * setter for Column MALL_SALE_DAILY_STAT.VALID_ORDER_AMOUNT
     * @param validOrderAmount
     */
    public void setValidOrderAmount(Integer validOrderAmount) {
        this.validOrderAmount = validOrderAmount;
    }

    /**
     * getter for Column MALL_SALE_DAILY_STAT.VALID_GOODS_NUM
     */
    public Integer getValidGoodsNum() {
        return validGoodsNum;
    }

    /**
     * setter for Column MALL_SALE_DAILY_STAT.VALID_GOODS_NUM
     * @param validGoodsNum
     */
    public void setValidGoodsNum(Integer validGoodsNum) {
        this.validGoodsNum = validGoodsNum;
    }

    /**
     * getter for Column MALL_SALE_DAILY_STAT.PAYED_ORDER_NUM
     */
    public Integer getPayedOrderNum() {
        return payedOrderNum;
    }

    /**
     * setter for Column MALL_SALE_DAILY_STAT.PAYED_ORDER_NUM
     * @param payedOrderNum
     */
    public void setPayedOrderNum(Integer payedOrderNum) {
        this.payedOrderNum = payedOrderNum;
    }

    /**
     * getter for Column MALL_SALE_DAILY_STAT.PRE_PAYED_ORDER_NUM
     */
    public Integer getPrePayedOrderNum() {
        return prePayedOrderNum;
    }

    /**
     * setter for Column MALL_SALE_DAILY_STAT.PRE_PAYED_ORDER_NUM
     * @param prePayedOrderNum
     */
    public void setPrePayedOrderNum(Integer prePayedOrderNum) {
        this.prePayedOrderNum = prePayedOrderNum;
    }

    /**
     * getter for Column MALL_SALE_DAILY_STAT.BIZ_DATE
     */
    public String getBizDate() {
        return bizDate;
    }

    /**
     * setter for Column MALL_SALE_DAILY_STAT.BIZ_DATE
     * @param bizDate
     */
    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    /**
     * getter for Column MALL_SALE_DAILY_STAT.CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * setter for Column MALL_SALE_DAILY_STAT.CREATE_TIME
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getValidOrderRate() {
		return validOrderRate;
	}

	public void setValidOrderRate(String validOrderRate) {
		this.validOrderRate = validOrderRate;
	}

	public String getAmountPerOrder() {
		return amountPerOrder;
	}

	public void setAmountPerOrder(String amountPerOrder) {
		this.amountPerOrder = amountPerOrder;
	}

	public String getGoodsNumPerOrder() {
		return goodsNumPerOrder;
	}

	public void setGoodsNumPerOrder(String goodsNumPerOrder) {
		this.goodsNumPerOrder = goodsNumPerOrder;
	}

}