package com.iuni.dp.service.datastat.constants;

/**
 * PF上报数据统计分析维度
 * @author CaiKe
 * @version V1.0.0
 */
public enum PfRptDataStatField {

	ResponseEnd("responseEnd"), DomCtxLoadedEnd("domContentLoadedEventEnd");
	
	private String value;

	private PfRptDataStatField(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
