package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class IuniBuriedPointSite implements Serializable {

	private static final long serialVersionUID = -7172081078156923695L;

	private Long id;
	
	private String websiteCode;
	
	private String website;
	
	private String pageName;
	
	private String pagePosition;
	
	private String pointFlag;
	
	private String pointType;
	
	private String remark;
	
	private String bizDate;
	
	private Timestamp createTime;
	
	private Long rowNum;

	public IuniBuriedPointSite() {
		super();
	}

	public IuniBuriedPointSite(String websiteCode, String website,
			String pageName, String pagePosition, String pointFlag,
			String pointType, String remark, String bizDate) {
		super();
		this.websiteCode = websiteCode;
		this.website = website;
		this.pageName = pageName;
		this.pagePosition = pagePosition;
		this.pointFlag = pointFlag;
		this.pointType = pointType;
		this.remark = remark;
		this.bizDate = bizDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWebsiteCode() {
		return websiteCode;
	}

	public void setWebsiteCode(String websiteCode) {
		this.websiteCode = websiteCode;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPagePosition() {
		return pagePosition;
	}

	public void setPagePosition(String pagePosition) {
		this.pagePosition = pagePosition;
	}

	public String getPointFlag() {
		return pointFlag;
	}

	public void setPointFlag(String pointFlag) {
		this.pointFlag = pointFlag;
	}

	public String getPointType() {
		return pointType;
	}

	public void setPointType(String pointType) {
		this.pointType = pointType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBizDate() {
		return bizDate;
	}

	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getRowNum() {
		return rowNum;
	}

	public void setRowNum(Long rowNum) {
		this.rowNum = rowNum;
	}

	@Override
	public String toString() {
		return "IuniBuriedPointSite [websiteCode=" + websiteCode + ", website="
				+ website + ", pageName=" + pageName + ", pagePosition="
				+ pagePosition + ", pointFlag=" + pointFlag + ", pointType="
				+ pointType + ", remark=" + remark + ", bizDate=" + bizDate
				+ ", rowNum=" + rowNum + "]";
	}

}
