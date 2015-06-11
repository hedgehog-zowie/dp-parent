package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * IuniBuriedPointDailyStat Model
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.3
 */
public class IuniBuriedPointDailyStat implements Serializable {

	private static final long serialVersionUID = -7237719617524602246L;

	private Long id;
	
	private String urlDomain;
	
	private String statDate;
	
	private String websiteCode;
	
	private String website;
	
	private String pageName;
	
	private String pagePosition;
	
	private String pointFlag;
	
	private String pointType;
	
	private Integer pv;
	
	private Integer uv;
	
	private Integer vv;
	
	private Integer ip;
	
	private Timestamp createTime;

	public IuniBuriedPointDailyStat() {
		super();
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

	public Integer getPv() {
		return pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	public Integer getUv() {
		return uv;
	}

	public void setUv(Integer uv) {
		this.uv = uv;
	}

	public Integer getVv() {
		return vv;
	}

	public void setVv(Integer vv) {
		this.vv = vv;
	}

	public Integer getIp() {
		return ip;
	}

	public void setIp(Integer ip) {
		this.ip = ip;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "IuniBuriedPointDailyStat [urlDomain=" + urlDomain
				+ ", statDate=" + statDate + ", websiteCode=" + websiteCode
				+ ", website=" + website + ", pageName=" + pageName
				+ ", pagePosition=" + pagePosition + ", pointFlag=" + pointFlag
				+ ", pointType=" + pointType + ", pv=" + pv + ", uv=" + uv
				+ ", vv=" + vv + ", ip=" + ip + "]";
	}
	
}
