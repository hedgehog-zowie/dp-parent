package com.iuni.dp.persist.datareport.model;

import java.util.Date;

/**
 * 按月上报数据Model
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public class ReportDataByMon extends ReportData {

	private static final long serialVersionUID = -5227892454511922734L;

	protected String month;

	public ReportDataByMon() {
		super();
	}

	public ReportDataByMon(String urlDomain, String sourceId, String userId,
			String sessionId, String sourceIp, String reportType,
			String reportContent, String browserInfo, String cookieInfo,
			String ipArea, String ipLocation, String appName,
			String appChannel, String appVersion, String appImei,
			String appModel, String appIp, Date createTime, String month) {
		super(urlDomain, sourceId, userId, sessionId, sourceIp, reportType,
				reportContent, browserInfo, cookieInfo, ipArea, ipLocation,
				appName, appChannel, appVersion, appImei, appModel, appIp,
				createTime);
		this.month = month;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "ReportDataByMon [month=" + month + ", urlDomain=" + urlDomain
				+ ", sourceId=" + sourceId + ", userId=" + userId
				+ ", sessionId=" + sessionId + ", sourceIp=" + sourceIp
				+ ", reportType=" + reportType + ", reportContent="
				+ reportContent + ", browserInfo=" + browserInfo
				+ ", cookieInfo=" + cookieInfo + ", ipArea=" + ipArea
				+ ", ipLocation=" + ipLocation + ", appName=" + appName
				+ ", appChannel=" + appChannel + ", appVersion=" + appVersion
				+ ", appImei=" + appImei + ", appModel=" + appModel
				+ ", appIp=" + appIp + ", createTime=" + createTime + "]";
	}

}
