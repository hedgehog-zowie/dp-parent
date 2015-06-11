package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 金立相关APP应用关联订单情况日统计 Model
 * @author CaiKe
 * @version dp-persist-V1.0.2
 */
public class GnAppOrderDailyStat implements Serializable {

	private static final long serialVersionUID = 5058716282783103151L;
	
	/**
	 * ID
	 */
	private Long id;
	
	/**
	 * App应用名
	 */
	private String appName;
	
	/**
	 * App渠道名
	 */
	private String channelName;
	
	/**
	 * APK版本号
	 */
	private String apkVersion;
	
	/**
	 * 每日订单数
	 */
	private Integer orderNum;
	
	/**
	 * 每日应支付金额
	 */
	private BigDecimal payAmount;
	
	/**
	 * 每日支付订单数
	 */
	private Integer payNum;
	
	/**
	 * 每日已支付金额
	 */
	private BigDecimal paiedAmount;
	
	/**
	 * 每日退款订单数
	 */
	private Integer refundNum;
	
	/**
	 * 每日退款金额
	 */
	private BigDecimal refundAmount;
	
	/**
	 * 统计日期
	 */
	private String statDate;
	
	/**
	 * 记录创建时间
	 */
	private Timestamp createTime;

	public GnAppOrderDailyStat() {
		super();
	}

	public GnAppOrderDailyStat(String appName, String channelName,
			String apkVersion, Integer orderNum, BigDecimal payAmount,
			Integer payNum, BigDecimal paiedAmount, Integer refundNum,
			BigDecimal refundAmount, String statDate) {
		super();
		this.appName = appName;
		this.channelName = channelName;
		this.apkVersion = apkVersion;
		this.orderNum = orderNum;
		this.payAmount = payAmount;
		this.payNum = payNum;
		this.paiedAmount = paiedAmount;
		this.refundNum = refundNum;
		this.refundAmount = refundAmount;
		this.statDate = statDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getApkVersion() {
		return apkVersion;
	}

	public void setApkVersion(String apkVersion) {
		this.apkVersion = apkVersion;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getPayNum() {
		return payNum;
	}

	public void setPayNum(Integer payNum) {
		this.payNum = payNum;
	}

	public BigDecimal getPaiedAmount() {
		return paiedAmount;
	}

	public void setPaiedAmount(BigDecimal paiedAmount) {
		this.paiedAmount = paiedAmount;
	}

	public Integer getRefundNum() {
		return refundNum;
	}

	public void setRefundNum(Integer refundNum) {
		this.refundNum = refundNum;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getStatDate() {
		return statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "GnAppOrderDailyStat [appName=" + appName + ", channelName="
				+ channelName + ", apkVersion=" + apkVersion + ", orderNum="
				+ orderNum + ", payAmount=" + payAmount + ", payNum=" + payNum
				+ ", paiedAmount=" + paiedAmount + ", refundNum=" + refundNum
				+ ", refundAmount=" + refundAmount + ", statDate=" + statDate
				+ "]";
	}

}
