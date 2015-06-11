package com.iuni.dp.service.datareport.constants;

/**
 * 上报数据类型
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public enum ReportDataType {

	CommonRptData("commonReportData"), GnAppRptData("gnAppReportData");
	
	private String value;
	
	private ReportDataType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
