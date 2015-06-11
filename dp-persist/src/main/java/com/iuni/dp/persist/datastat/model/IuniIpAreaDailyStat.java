package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * IUNI商城独立IP数区域分布Model
 * @author CaiKe
 * @version dp-persist-V1.0.2
 */
public class IuniIpAreaDailyStat implements Serializable {

	private static final long serialVersionUID = -7952896802144327757L;
	
	private Long id;
	
	private String urlDomain;
	
	private String statDate;
	
	private String ipArea;
	
	private String ipNum;
	
	private Timestamp createTime;

	public IuniIpAreaDailyStat() {
		super();
	}

	public IuniIpAreaDailyStat(String urlDomain, String statDate,
			String ipArea, String ipNum) {
		super();
		this.urlDomain = urlDomain;
		this.statDate = statDate;
		this.ipArea = ipArea;
		this.ipNum = ipNum;
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

	public String getStatDate() {
		return statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	public String getIpArea() {
		return ipArea;
	}

	public void setIpArea(String ipArea) {
		this.ipArea = ipArea;
	}

	public String getIpNum() {
		return ipNum;
	}

	public void setIpNum(String ipNum) {
		this.ipNum = ipNum;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "IuniIpAreaDailyStat [urlDomain=" + urlDomain + ", statDate="
				+ statDate + ", ipArea=" + ipArea + ", ipNum=" + ipNum + "]";
	}

}
