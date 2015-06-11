package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 金立相关客户端登入登出记录日志Model
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class GnAppAccessLog implements Serializable {

	private static final long serialVersionUID = 413108509210340726L;
	
	/**
	 * ID
	 */
	private Long id;
	
	/**
	 * APP客户端名称
	 */
	private String appName;
	
	/**
	 * 渠道名
	 */
	private String channelName;
	
	/**
	 * APK版本号
	 */
	private String apkVersion;
	
	/**
	 * 终端IMEI号
	 */
	private String imei;
	
	/**
	 * APP注册用户ID
	 */
	private String registerUserId;
	
	/**
	 * APP启用时间
	 */
	private Timestamp startupTime;
	
	/**
	 * APP退出时间
	 */
	private Timestamp shutdownTime;
	
	/**
	 * 记录日志状态
	 * 0：登入，1：登出
	 */
	private Integer status;
	
	/**
	 * 移动终端机型
	 */
	private String mobileModel;
	
	/**
	 * 移动终端号码
	 */
	private String mobileNumber;
	
	/**
	 * 终端机型分辨率高度
	 */
	private Integer modelHeight;
	
	/**
	 * 终端机型分辨率宽度
	 */
	private Integer modelWidth;
	
	/**
	 * 终端通信网络模式
	 */
	private String networkMode;
	
	/**
	 * 终端通信网络IP
	 */
	private String networkIp;
	
	/**
	 * 记录上报时间
	 */
	private Timestamp reportTime;
	
	/**
	 * 记录创建时间
	 */
	private Timestamp createTime;

	public GnAppAccessLog() {
		super();
	}

	public GnAppAccessLog(String appName, String channelName,
			String apkVersion, String imei, String registerUserId,
			Timestamp startupTime, Timestamp shutdownTime, Integer status,
			String mobileModel, String mobileNumber, Integer modelHeight,
			Integer modelWidth, String networkMode, String networkIp) {
		super();
		this.appName = appName;
		this.channelName = channelName;
		this.apkVersion = apkVersion;
		this.imei = imei;
		this.registerUserId = registerUserId;
		this.startupTime = startupTime;
		this.shutdownTime = shutdownTime;
		this.status = status;
		this.mobileModel = mobileModel;
		this.mobileNumber = mobileNumber;
		this.modelHeight = modelHeight;
		this.modelWidth = modelWidth;
		this.networkMode = networkMode;
		this.networkIp = networkIp;
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

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getRegisterUserId() {
		return registerUserId;
	}

	public void setRegisterUserId(String registerUserId) {
		this.registerUserId = registerUserId;
	}

	public Timestamp getStartupTime() {
		return startupTime;
	}

	public void setStartupTime(Timestamp startupTime) {
		this.startupTime = startupTime;
	}

	public Timestamp getShutdownTime() {
		return shutdownTime;
	}

	public void setShutdownTime(Timestamp shutdownTime) {
		this.shutdownTime = shutdownTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMobileModel() {
		return mobileModel;
	}

	public void setMobileModel(String mobileModel) {
		this.mobileModel = mobileModel;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Integer getModelHeight() {
		return modelHeight;
	}

	public void setModelHeight(Integer modelHeight) {
		this.modelHeight = modelHeight;
	}

	public Integer getModelWidth() {
		return modelWidth;
	}

	public void setModelWidth(Integer modelWidth) {
		this.modelWidth = modelWidth;
	}

	public String getNetworkMode() {
		return networkMode;
	}

	public void setNetworkMode(String networkMode) {
		this.networkMode = networkMode;
	}

	public String getNetworkIp() {
		return networkIp;
	}

	public void setNetworkIp(String networkIp) {
		this.networkIp = networkIp;
	}

	public Timestamp getReportTime() {
		return reportTime;
	}

	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "GnAppAccessLog [appName=" + appName + ", channelName="
				+ channelName + ", apkVersion=" + apkVersion + ", imei=" + imei
				+ ", registerUserId=" + registerUserId + ", startupTime="
				+ startupTime + ", shutdownTime=" + shutdownTime + ", status="
				+ status + ", mobileModel=" + mobileModel + ", mobileNumber="
				+ mobileNumber + ", modelHeight=" + modelHeight
				+ ", modelWidth=" + modelWidth + ", networkMode=" + networkMode
				+ ", networkIp=" + networkIp + "]";
	}

}
