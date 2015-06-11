package com.iuni.dp.persist.datastat.model;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public class AlipayRecord {

    private static final long serialVersionUID = 2576416512633551235L;

    protected long id;

    private String alipayOrderNo;

    private String balance;

    private String businessType;

    private String createTime;

    private String inAmount;

    private String memo;

    private String merchantOrderNo;

    private String optUserId;

    private String outAmount;

    private String selfUserId;

    private String type;

    public String getAlipayOrderNo() {
        return this.alipayOrderNo;
    }

    public void setAlipayOrderNo(String alipayOrderNo) {
        this.alipayOrderNo = alipayOrderNo;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getInAmount() {
        return this.inAmount;
    }

    public void setInAmount(String inAmount) {
        this.inAmount = inAmount;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMerchantOrderNo() {
        return this.merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getOptUserId() {
        return this.optUserId;
    }

    public void setOptUserId(String optUserId) {
        this.optUserId = optUserId;
    }

    public String getOutAmount() {
        return this.outAmount;
    }

    public void setOutAmount(String outAmount) {
        this.outAmount = outAmount;
    }

    public String getSelfUserId() {
        return this.selfUserId;
    }

    public void setSelfUserId(String selfUserId) {
        this.selfUserId = selfUserId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AlipayRecord{" +
                "alipayOrderNo='" + alipayOrderNo + '\'' +
                ", balance='" + balance + '\'' +
                ", businessType='" + businessType + '\'' +
                ", createTime='" + createTime + '\'' +
                ", inAmount='" + inAmount + '\'' +
                ", memo='" + memo + '\'' +
                ", merchantOrderNo='" + merchantOrderNo + '\'' +
                ", optUserId='" + optUserId + '\'' +
                ", outAmount='" + outAmount + '\'' +
                ", selfUserId='" + selfUserId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
