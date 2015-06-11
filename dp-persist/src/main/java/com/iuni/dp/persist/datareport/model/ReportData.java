package com.iuni.dp.persist.datareport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 上报数据Model
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public class ReportData implements Serializable {

	private static final long serialVersionUID = -2034172307222792876L;

	/**
	 * 上报数据ID
	 */
	protected Long id;
	
	/**
	 * 数据上报URL来源域名
	 */
	protected String urlDomain;
	
	/**
	 * 上报数据来源ID
	 */
	protected String sourceId;
	
	/**
	 * 上报数据用户ID
	 */
	protected String userId;
	
	/**
	 * 上把数据Session ID
	 */
	protected String sessionId;
	
	/**
	 * 上报数据来源IP
	 */
	protected String sourceIp;
	
	/**
	 * 上报数据类型
	 */
	protected String reportType;
	
	/**
	 * 上报数据内容
	 */
	protected String reportContent;
	
	/**
	 * 浏览器请求头相关信息
	 */
	protected String browserInfo;
	
	/**
	 * 数据上报请求Cookie信息
	 */
	protected String cookieInfo;
	
	/**
	 * IP段所属地理区域
	 */
	protected String ipArea;
	
	/**
	 * IP段具体地理位置或者服务定位
	 */
	protected String ipLocation;
	
	/**
	 * 来源APP应用名
	 */
	protected String appName;
	
	/**
	 * 来源APP应用渠道名
	 */
	protected String appChannel;
	
	/**
	 * 来源APP应用版本号
	 */
	protected String appVersion;
	
	/**
	 * 来源APP应用终端IMEI号
	 */
	protected String appImei;
	
	/**
	 * 来源APP应用终端机型
	 */
	protected String appModel;
	
	/**
	 * 来源APP应用网络IP
	 */
	protected String appIp;
	
	/**
	 * 上报时间
	 */
	protected Date createTime;
	
	/**
	 * 入库时间
	 */
	protected Date storageTime;

	public ReportData() {
		super();
	}

	public ReportData(String urlDomain, String sourceId, String userId,
			String sessionId, String sourceIp, String reportType,
			String reportContent, String browserInfo, String cookieInfo,
			String ipArea, String ipLocation, String appName,
			String appChannel, String appVersion, String appImei,
			String appModel, String appIp, Date createTime) {
		super();
		this.urlDomain = urlDomain;
		this.sourceId = sourceId;
		this.userId = userId;
		this.sessionId = sessionId;
		this.sourceIp = sourceIp;
		this.reportType = reportType;
		this.reportContent = reportContent;
		this.browserInfo = browserInfo;
		this.cookieInfo = cookieInfo;
		this.ipArea = ipArea;
		this.ipLocation = ipLocation;
		this.appName = appName;
		this.appChannel = appChannel;
		this.appVersion = appVersion;
		this.appImei = appImei;
		this.appModel = appModel;
		this.appIp = appIp;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrlDomain() {
		return urlDomain;
	}

	public void setUrlDomain(String urlDomain) {
		this.urlDomain = urlDomain;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public String getBrowserInfo() {
		return browserInfo;
	}

	public void setBrowserInfo(String browserInfo) {
		this.browserInfo = browserInfo;
	}

	public String getCookieInfo() {
		return cookieInfo;
	}

	public void setCookieInfo(String cookieInfo) {
		this.cookieInfo = cookieInfo;
	}

	public String getIpArea() {
		return ipArea;
	}

	public void setIpArea(String ipArea) {
		this.ipArea = ipArea;
	}

	public String getIpLocation() {
		return ipLocation;
	}

	public void setIpLocation(String ipLocation) {
		this.ipLocation = ipLocation;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppChannel() {
		return appChannel;
	}

	public void setAppChannel(String appChannel) {
		this.appChannel = appChannel;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppImei() {
		return appImei;
	}

	public void setAppImei(String appImei) {
		this.appImei = appImei;
	}

	public String getAppModel() {
		return appModel;
	}

	public void setAppModel(String appModel) {
		this.appModel = appModel;
	}

	public String getAppIp() {
		return appIp;
	}

	public void setAppIp(String appIp) {
		this.appIp = appIp;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStorageTime() {
		return storageTime;
	}

	public void setStorageTime(Date storageTime) {
		this.storageTime = storageTime;
	}

	@Override
	public String toString() {
		return "ReportData [urlDomain=" + urlDomain + ", sourceId=" + sourceId
				+ ", userId=" + userId + ", sessionId=" + sessionId
				+ ", sourceIp=" + sourceIp + ", reportType=" + reportType
				+ ", reportContent=" + reportContent + ", browserInfo="
				+ browserInfo + ", cookieInfo=" + cookieInfo + ", ipArea="
				+ ipArea + ", ipLocation=" + ipLocation + ", appName="
				+ appName + ", appChannel=" + appChannel + ", appVersion="
				+ appVersion + ", appImei=" + appImei + ", appModel="
				+ appModel + ", appIp=" + appIp + ", createTime=" + createTime
				+ "]";
	}

}
