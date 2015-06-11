package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 金立相关APP应用基本使用情况日统计 Model
 * @author CaiKe
 * @version dp-persist_V1.0.2
 */
public class GnAppUsageDailyStat implements Serializable {

	private static final long serialVersionUID = 3151661244451945882L;
	
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
	 * App每日平均使用时长
	 */
	private String avgTime;
	
	/**
	 * App每日启动次数
	 */
	private Integer launchNum;
	
	/**
	 * App每日启动用户数
	 */
	private Integer launchUserNum;
	
	/**
	 * App每日新增用户数
	 */
	private Integer newUserNum;
	
	/**
	 * 统计日期
	 */
	private String statDate;
	
	/**
	 * 记录创建时间
	 */
	private Timestamp createTime;

	public GnAppUsageDailyStat() {
		super();
	}

	public GnAppUsageDailyStat(String appName, String channelName,
			String apkVersion, String avgTime, Integer launchNum,
			Integer launchUserNum, Integer newUserNum, String statDate) {
		super();
		this.appName = appName;
		this.channelName = channelName;
		this.apkVersion = apkVersion;
		this.avgTime = avgTime;
		this.launchNum = launchNum;
		this.launchUserNum = launchUserNum;
		this.newUserNum = newUserNum;
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

	public String getAvgTime() {
		return avgTime;
	}

	public void setAvgTime(String avgTime) {
		this.avgTime = avgTime;
	}

	public Integer getLaunchNum() {
		return launchNum;
	}

	public void setLaunchNum(Integer launchNum) {
		this.launchNum = launchNum;
	}

	public Integer getLaunchUserNum() {
		return launchUserNum;
	}

	public void setLaunchUserNum(Integer launchUserNum) {
		this.launchUserNum = launchUserNum;
	}

	public Integer getNewUserNum() {
		return newUserNum;
	}

	public void setNewUserNum(Integer newUserNum) {
		this.newUserNum = newUserNum;
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
		return "GnAppUsageDailyStat [appName=" + appName + ", channelName="
				+ channelName + ", apkVersion=" + apkVersion + ", avgTime="
				+ avgTime + ", launchNum=" + launchNum + ", launchUserNum="
				+ launchUserNum + ", newUserNum=" + newUserNum + ", statDate="
				+ statDate + "]";
	}

}
