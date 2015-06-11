package com.iuni.dp.admin.datastat.vo;

/**
 * 统计计划VO
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class StatSchemeVo {

	/**
	 * 统计分析计划编码
	 */
	private String code;
	
	/**
	 * 统计分析计划名称
	 */
	private String name;
	
	/**
	 * 统计分析计划状态
	 * 0：未启用; 1：启用
	 */
	private Integer status;
	
	/**
	 * 统计策略编码
	 * 11： 定时调度统计分析类型; 12：间隔快照统计分析类型
	 */
	private Integer statStrategyCode;
	
	/**
	 * 上报数据类型
	 */
	private String rptDataType;
	
	/**
	 * 统计维度
	 */
	private String statField;
	
	/**
	 * 查询开始时间
	 */
	private String beginDate;
	
	/**
	 * 查询结束时间
	 */
	private String endDate;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatStrategyCode() {
		return statStrategyCode;
	}

	public void setStatStrategyCode(Integer statStrategyCode) {
		this.statStrategyCode = statStrategyCode;
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

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "StatSchemeVo [code=" + code + ", name=" + name + ", status="
				+ status + ", statStrategyCode=" + statStrategyCode
				+ ", rptDataType=" + rptDataType + ", statField=" + statField
				+ ", beginDate=" + beginDate + ", endDate=" + endDate + "]";
	}

}
