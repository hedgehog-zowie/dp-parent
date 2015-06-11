package com.iuni.dp.admin.datastat.vo;

/**
 * 统计项目VO
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class StatItemVo {

	/**
	 * 统计分析计划项目编码
	 */
	private String code;
	
	/**
	 * 统计分析计划项目名称
	 */
	private String name;
	
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
		return "StatItemVo [code=" + code + ", name=" + name + ", rptDataType="
				+ rptDataType + ", statField=" + statField + ", beginDate="
				+ beginDate + ", endDate=" + endDate + "]";
	}
	
}
