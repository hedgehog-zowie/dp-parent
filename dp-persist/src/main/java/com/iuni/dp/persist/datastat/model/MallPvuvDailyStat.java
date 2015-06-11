package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 商城PV/UV基础数据日统计Model
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class MallPvuvDailyStat implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private Integer id;
	
	/**
     * 上报数据所属站点
     */
    private String urlDomain;

    /**
     * 每日PV汇总数
     */
    private Integer uv;

    /**
     * 每日UV汇总数
     */
    private Integer pv;
    
    /**
     * 每日独立IP数
     */
    private Integer ip;

    /**
     * 业务数据所属日期
     */
    private String bizDate;

    /**
     * 记录创建时间
     */
    private Timestamp createTime;

	public MallPvuvDailyStat() {
		super();
	}

	public MallPvuvDailyStat(String urlDomain, Integer uv, Integer pv,
			Integer ip, String bizDate, Timestamp createTime) {
		super();
		this.urlDomain = urlDomain;
		this.uv = uv;
		this.pv = pv;
		this.ip = ip;
		this.bizDate = bizDate;
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrlDomain() {
		return urlDomain;
	}

	public void setUrlDomain(String urlDomain) {
		this.urlDomain = urlDomain;
	}

	public Integer getUv() {
		return uv;
	}

	public void setUv(Integer uv) {
		this.uv = uv;
	}

	public Integer getPv() {
		return pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	public Integer getIp() {
		return ip;
	}

	public void setIp(Integer ip) {
		this.ip = ip;
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

	@Override
	public String toString() {
		return "MallPvuvDailyStat [urlDomain=" + urlDomain + ", uv=" + uv
				+ ", pv=" + pv + ", ip=" + ip + ", bizDate=" + bizDate
				+ ", createTime=" + createTime + "]";
	}

}
