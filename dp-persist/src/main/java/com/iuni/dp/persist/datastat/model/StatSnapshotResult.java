package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 固定间隔时间快照统计分析结果Model
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class StatSnapshotResult implements Serializable {

	private static final long serialVersionUID = 6576060381522983187L;

	/**
	 * 快照统计分析结果ID
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
	 * 快照时间
	 */
	private Date snapshotTime;
	
	/**
	 * 统计平均时间
	 * 单位毫秒(ms)
	 */
	private Integer statAvgTime;
	
	/**
	 * 创建时间
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

	public Date getSnapshotTime() {
		return snapshotTime;
	}

	public void setSnapshotTime(Date snapshotTime) {
		this.snapshotTime = snapshotTime;
	}

	public Integer getStatAvgTime() {
		return statAvgTime;
	}

	public void setStatAvgTime(Integer statAvgTime) {
		this.statAvgTime = statAvgTime;
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
		return "StatSnapshotResult [id=" + id + ", sourceId=" + sourceId
				+ ", statSchemeId=" + statSchemeId + ", snapshotTime="
				+ snapshotTime + ", statAvgTime=" + statAvgTime
				+ ", createTime=" + createTime + "]";
	}

}
