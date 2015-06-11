package com.iuni.dp.persist.datastat.model;

import java.util.Date;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public class AlipayTradeRecord {

    private static final long serialVersionUID = 1829744119271298696L;

    protected long id;

    private String alipayOrderNo;

    private Date createTime;

    private String inOutType;

    private String merchantOrderNo;

    private Date modifiedTime;

    private String oppositeLogonId;

    private String oppositeName;

    private String oppositeUserId;

    private String orderFrom;

    private String orderStatus;

    private String orderTitle;

    private String orderType;

    private String ownerLogonId;

    private String ownerName;

    private String ownerUserId;

    private String serviceCharge;

    private String totalAmount;

    public String getAlipayOrderNo() {
        return this.alipayOrderNo;
    }

    public void setAlipayOrderNo(String alipayOrderNo) {
        this.alipayOrderNo = alipayOrderNo;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInOutType() {
        return this.inOutType;
    }

    public void setInOutType(String inOutType) {
        this.inOutType = inOutType;
    }

    public String getMerchantOrderNo() {
        return this.merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public Date getModifiedTime() {
        return this.modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getOppositeLogonId() {
        return this.oppositeLogonId;
    }

    public void setOppositeLogonId(String oppositeLogonId) {
        this.oppositeLogonId = oppositeLogonId;
    }

    public String getOppositeName() {
        return this.oppositeName;
    }

    public void setOppositeName(String oppositeName) {
        this.oppositeName = oppositeName;
    }

    public String getOppositeUserId() {
        return this.oppositeUserId;
    }

    public void setOppositeUserId(String oppositeUserId) {
        this.oppositeUserId = oppositeUserId;
    }

    public String getOrderFrom() {
        return this.orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderTitle() {
        return this.orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOwnerLogonId() {
        return this.ownerLogonId;
    }

    public void setOwnerLogonId(String ownerLogonId) {
        this.ownerLogonId = ownerLogonId;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerUserId() {
        return this.ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getServiceCharge() {
        return this.serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "TradeRecord{" +
                "alipayOrderNo='" + alipayOrderNo + '\'' +
                ", createTime=" + createTime +
                ", inOutType='" + inOutType + '\'' +
                ", merchantOrderNo='" + merchantOrderNo + '\'' +
                ", modifiedTime=" + modifiedTime +
                ", oppositeLogonId='" + oppositeLogonId + '\'' +
                ", oppositeName='" + oppositeName + '\'' +
                ", oppositeUserId='" + oppositeUserId + '\'' +
                ", orderFrom='" + orderFrom + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderTitle='" + orderTitle + '\'' +
                ", orderType='" + orderType + '\'' +
                ", ownerLogonId='" + ownerLogonId + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerUserId='" + ownerUserId + '\'' +
                ", serviceCharge='" + serviceCharge + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                '}';
    }
}
