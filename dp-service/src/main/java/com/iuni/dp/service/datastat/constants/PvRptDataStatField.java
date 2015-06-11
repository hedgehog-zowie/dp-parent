package com.iuni.dp.service.datastat.constants;

/**
 * PV上报数据统计分析维度
 * @author CaiKe
 * @version V1.0.0
 */
public enum PvRptDataStatField {

	Day("day"), Month("month");
	
	private String value;

	private PvRptDataStatField(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
