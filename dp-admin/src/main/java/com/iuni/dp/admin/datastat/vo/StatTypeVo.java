package com.iuni.dp.admin.datastat.vo;

/**
 * 统计类型VO
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class StatTypeVo {

	/**
	 * 统计分析计划类型编码
	 */
	private String code;
	
	/**
	 * 统计分析计划类型名称
	 */
	private String name;
	
	/**
	 * 统计策略编码
	 * (11： 固定时间统一调度统计分析结果；12：固定间隔时间快照统计分析结果)
	 */
	private Integer statStrategyCode;
	
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

	public Integer getStatStrategyCode() {
		return statStrategyCode;
	}

	public void setStatStrategyCode(Integer statStrategyCode) {
		this.statStrategyCode = statStrategyCode;
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
		return "StatTypeVo [code=" + code + ", name=" + name
				+ ", statStrategyCode=" + statStrategyCode + ", beginDate="
				+ beginDate + ", endDate=" + endDate + "]";
	}

}
