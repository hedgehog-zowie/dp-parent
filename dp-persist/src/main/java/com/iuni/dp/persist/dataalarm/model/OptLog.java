package com.iuni.dp.persist.dataalarm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志对象
 * @author CaiKe
 * @version V1.0.0
 */
public class OptLog implements Serializable {

	private static final long serialVersionUID = 1378370120877949658L;
	
	private Integer optLogId;
	
	private Integer operatorId;
	
	private String operatorName;
	
	private String operatorIp;
	
	private Integer optType;
	
	private Integer busType;
	
	private Integer executeResult;
	
	private Date createTime;
	
	private String remark;

	public Integer getOptLogId() {
		return optLogId;
	}

	public void setOptLogId(Integer optLogId) {
		this.optLogId = optLogId;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorIp() {
		return operatorIp;
	}

	public void setOperatorIp(String operatorIp) {
		this.operatorIp = operatorIp;
	}

	public Integer getOptType() {
		return optType;
	}

	public void setOptType(Integer optType) {
		this.optType = optType;
	}

	public Integer getBusType() {
		return busType;
	}

	public void setBusType(Integer busType) {
		this.busType = busType;
	}

	public Integer getExecuteResult() {
		return executeResult;
	}

	public void setExecuteResult(Integer executeResult) {
		this.executeResult = executeResult;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "OptLog [optLogId=" + optLogId + ", operatorId=" + operatorId
				+ ", operatorName=" + operatorName + ", operatorIp="
				+ operatorIp + ", optType=" + optType + ", busType=" + busType
				+ ", executeResult=" + executeResult + ", createTime="
				+ createTime + ", remark=" + remark + "]";
	}
	
}
