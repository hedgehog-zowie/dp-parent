package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 固定时间统一调度统计分析结果Model
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class StatScheduledResult implements Serializable {

	private static final long serialVersionUID = 5447201515378430274L;
	
	/**
	 * 调度统计分析结果ID
	 */
	private Integer id;
	
	/**
	 * 上报数据来源ID
	 */
	private String sourceId;
	
	/**
	 * 统计分析计划ID
	 */
	private Integer statSchemeId;
	
	/**
	 * 统计类型
	 * (100：0~1秒；101：1~2秒；102：2~3秒；103：3~4秒；104：4~5秒；105：5~10秒；106：10~20；107：20~30；108：30~60；109：大于60)
	 */
	private Integer statType;
	
	/**
	 * 统计数目
	 */
	private Integer statNum;
	
	/**
	 * 统计分析调度处理的上报数据时间
	 */
	private Date scheduledTime;
	
	/**
	 * 统计分析结果创建时间
	 */
	private Date createTime;
	
	/**
	 * 统计分析计划
	 */
	private StatScheme statScheme;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getStatSchemeId() {
		return statSchemeId;
	}

	public void setStatSchemeId(Integer statSchemeId) {
		this.statSchemeId = statSchemeId;
	}

	public Integer getStatType() {
		return statType;
	}

	public void setStatType(Integer statType) {
		this.statType = statType;
	}

	public Integer getStatNum() {
		return statNum;
	}

	public void setStatNum(Integer statNum) {
		this.statNum = statNum;
	}

	public Date getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public StatScheme getStatScheme() {
		return statScheme;
	}

	public void setStatScheme(StatScheme statScheme) {
		this.statScheme = statScheme;
	}

	@Override
	public String toString() {
		return "StatScheduledResult [id=" + id + ", sourceId=" + sourceId
				+ ", statSchemeId=" + statSchemeId + ", statType=" + statType
				+ ", statNum=" + statNum + ", scheduledTime=" + scheduledTime
				+ ", createTime=" + createTime + "]";
	}

}
