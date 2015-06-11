package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * IUNI商城页面流量相关数据每日统计MODEL
 * @author CaiKe
 * @version dp-persist-V1.0.2
 */
public class IuniPageNetflowDailyStat implements Serializable {

	private static final long serialVersionUID = 6149888200617005492L;
	
	/**
	 * ID
	 */
	private Long id;
	
	/**
	 * 数据上报URL域
	 */
	private String urlDomain;
	
	/**
	 * 数据上报页面URL
	 */
	private String pageUrl;
	
	/**
	 * IUNI商城页面每日浏览次数汇总数
	 */
	private Integer pv;
	
	/**
	 * IUNI商城页面每日独立访客汇总数
	 */
	private Integer uv;
	
	/**
	 * IUNI商城页面每日访问次数汇总数
	 */
	private Integer vv;
	
	/**
	 * IUNI商城页面每日独立IP汇总数
	 */
	private Integer ip;
	
	/**
	 * IUNI商城页面流量相关数据日统计时间
	 */
	private String statDate;
	
	/**
	 * IUNI商城页面流量相关数据日统计数据创建时间
	 */
	private Timestamp createTime;

	public IuniPageNetflowDailyStat() {
		super();
	}

	public IuniPageNetflowDailyStat(String urlDomain, String pageUrl,
			Integer pv, Integer uv, Integer vv, Integer ip, String statDate) {
		super();
		this.urlDomain = urlDomain;
		this.pageUrl = pageUrl;
		this.pv = pv;
		this.uv = uv;
		this.vv = vv;
		this.ip = ip;
		this.statDate = statDate;
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

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
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
		return "IuniPageNetflowDailyStat [urlDomain=" + urlDomain
				+ ", pageUrl=" + pageUrl + ", pv=" + pv + ", uv=" + uv
				+ ", vv=" + vv + ", ip=" + ip + ", statDate=" + statDate + "]";
	}

}
