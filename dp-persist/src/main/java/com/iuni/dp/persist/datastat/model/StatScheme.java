package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * 统计分析计划Model
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class StatScheme implements Serializable {

	private static final long serialVersionUID = 5385988126214552237L;

	/**
	 * 统计分析计划编码
	 */
	private String code;

    /**
     * 统计分析计划ID
     */
    private Integer id;

	/**
	 * 统计分析计划名称
	 */
	private String name;
	
	/**
	 * 统计策略编码
	 * 11： 定时调度统计分析类型; 12：间隔快照统计分析类型
	 */
	private Integer statStrategyCode;

	/**
	 * 定时调度统计分析类型定时时间
	 */
	private Time statScheduledTime;
	
	/**
	 * 间隔快照统计分析类型间隔时间(单位：秒)
	 */
	private Integer statSnapshotTime;
	
	/**
	 * 上报数据类型
	 */
	private String rptDataType;
	
	/**
	 * 统计维度
	 */
	private String statField;
	
	/**
	 * 统计分析计划状态
	 * 0：未启用; 1：启用
	 */
	private Integer status;
	
	/**
	 * 统计分析计划创建时间
	 */
	private Date createTime;
	
	/**
	 * 统计分析计划创建人ID
	 */
	private Integer creatorId;
	
	/**
	 * 统计分析计划备注
	 */
	private String remark;
	
	/**
	 * 创建人
	 */
	private User creator;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatStrategyCode() {
		return statStrategyCode;
	}

	public void setStatStrategyCode(Integer statStrategyCode) {
		this.statStrategyCode = statStrategyCode;
	}

	public Time getStatScheduledTime() {
		return statScheduledTime;
	}

	public void setStatScheduledTime(Time statScheduledTime) {
		this.statScheduledTime = statScheduledTime;
	}

	public Integer getStatSnapshotTime() {
		return statSnapshotTime;
	}

	public void setStatSnapshotTime(Integer statSnapshotTime) {
		this.statSnapshotTime = statSnapshotTime;
	}

	public String getRptDataType() {
		return rptDataType;
	}

	public void setRptDataType(String rptDataType) {
		this.rptDataType = rptDataType;
	}

	public String getStatField() {
		return statField;
	}

	public void setStatField(String statField) {
		this.statField = statField;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "StatScheme [id=" + id + ", code=" + code + ", name=" + name
				+ ", statStrategyCode=" + statStrategyCode
				+ ", statScheduledTime=" + statScheduledTime
				+ ", statSnapshotTime=" + statSnapshotTime + ", rptDataType="
				+ rptDataType + ", statField=" + statField + ", status="
				+ status + ", createTime=" + createTime + ", creatorId="
				+ creatorId + ", remark=" + remark + "]";
	}

}
