package com.iuni.dp.persist.datareport.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 金立相关APP客户端IMEI首次启用记录日志Model
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
public class GnAppImeiLog implements Serializable {

	private static final long serialVersionUID = 2605185711644224406L;

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
	 * 终端首次启用APP时间
	 */
	private Timestamp firstLaunchTime;
	
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
	 * 定位信息-国家
	 */
	private String locationCountry;
	
	/**
	 * 定位信息-省份
	 */
	private String locationProvince;
	
	/**
	 * 定位信息-区市
	 */
	private String locationCity;
	
	/**
	 * 定位时间
	 */
	private Timestamp locationTime;
	
	/**
	 * 记录上报时间
	 */
	private Timestamp reportTime;
	
	/**
	 * 记录创建时间
	 */
	private Timestamp createTime;

	public GnAppImeiLog() {
		super();
	}

	public GnAppImeiLog(String appName, String channelName, String apkVersion,
			String imei, Timestamp firstLaunchTime, String mobileModel,
			String mobileNumber, Integer modelHeight, Integer modelWidth,
			String networkMode, String networkIp, Timestamp reportTime) {
		super();
		this.appName = appName;
		this.channelName = channelName;
		this.apkVersion = apkVersion;
		this.imei = imei;
		this.firstLaunchTime = firstLaunchTime;
		this.mobileModel = mobileModel;
		this.mobileNumber = mobileNumber;
		this.modelHeight = modelHeight;
		this.modelWidth = modelWidth;
		this.networkMode = networkMode;
		this.networkIp = networkIp;
		this.reportTime = reportTime;
	}

	public GnAppImeiLog(String appName, String channelName, String apkVersion,
			String imei, Timestamp firstLaunchTime, String mobileModel,
			String mobileNumber, Integer modelHeight, Integer modelWidth,
			String networkMode, String networkIp, String locationCountry,
			String locationProvince, String locationCity,
			Timestamp locationTime, Timestamp reportTime) {
		super();
		this.appName = appName;
		this.channelName = channelName;
		this.apkVersion = apkVersion;
		this.imei = imei;
		this.firstLaunchTime = firstLaunchTime;
		this.mobileModel = mobileModel;
		this.mobileNumber = mobileNumber;
		this.modelHeight = modelHeight;
		this.modelWidth = modelWidth;
		this.networkMode = networkMode;
		this.networkIp = networkIp;
		this.locationCountry = locationCountry;
		this.locationProvince = locationProvince;
		this.locationCity = locationCity;
		this.locationTime = locationTime;
		this.reportTime = reportTime;
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

	public Timestamp getFirstLaunchTime() {
		return firstLaunchTime;
	}

	public void setFirstLaunchTime(Timestamp firstLaunchTime) {
		this.firstLaunchTime = firstLaunchTime;
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

	public String getLocationCountry() {
		return locationCountry;
	}

	public void setLocationCountry(String locationCountry) {
		this.locationCountry = locationCountry;
	}

	public String getLocationProvince() {
		return locationProvince;
	}

	public void setLocationProvince(String locationProvince) {
		this.locationProvince = locationProvince;
	}

	public String getLocationCity() {
		return locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	public Timestamp getLocationTime() {
		return locationTime;
	}

	public void setLocationTime(Timestamp locationTime) {
		this.locationTime = locationTime;
	}

	@Override
	public String toString() {
		return "GnAppImeiLog [appName=" + appName + ", channelName="
				+ channelName + ", apkVersion=" + apkVersion + ", imei=" + imei
				+ ", firstLaunchTime=" + firstLaunchTime + ", mobileModel="
				+ mobileModel + ", mobileNumber=" + mobileNumber
				+ ", modelHeight=" + modelHeight + ", modelWidth=" + modelWidth
				+ ", networkMode=" + networkMode + ", networkIp=" + networkIp
				+ ", locationCountry=" + locationCountry
				+ ", locationProvince=" + locationProvince + ", locationCity="
				+ locationCity + ", locationTime=" + locationTime
				+ ", reportTime=" + reportTime + "]";
	}

}
