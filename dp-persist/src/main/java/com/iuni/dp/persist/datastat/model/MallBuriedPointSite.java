package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 商城前端埋点位置Model
 * @author CaiKe
 * @version dp-persist-1.0.1
 */
public class MallBuriedPointSite implements Serializable {

	private static final long serialVersionUID = -8214228436665365762L;

	private Long id;
	
	private String website;
	
	private String pageName;
	
	private String pagePosition;
	
	private String pointFlag;
	
	private String remark;
	
	private String bizDate;
	
	private String pointType;
	
	private Timestamp createTime;
	
	private Long rowNum;

	public MallBuriedPointSite() {
		super();
	}

	public MallBuriedPointSite(String website, String pageName,
			String pagePosition, String pointFlag, String remark,
			String bizDate, String pointType) {
		super();
		this.website = website;
		this.pageName = pageName;
		this.pagePosition = pagePosition;
		this.pointFlag = pointFlag;
		this.remark = remark;
		this.bizDate = bizDate;
		this.pointType = pointType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPointType() {
		return pointType;
	}

	public void setPointType(String pointType) {
		this.pointType = pointType;
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
		return "MallBuriedPointSite [website=" + website + ", pageName="
				+ pageName + ", pagePosition=" + pagePosition + ", pointFlag="
				+ pointFlag + ", remark=" + remark + ", bizDate=" + bizDate
				+ ", pointType=" + pointType + ", rowNum=" + rowNum + "]";
	}

}
