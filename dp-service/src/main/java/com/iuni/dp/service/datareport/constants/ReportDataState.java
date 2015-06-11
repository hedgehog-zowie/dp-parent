package com.iuni.dp.service.datareport.constants;

public enum ReportDataState {

	Normal("normal"), Abnormal("abnormal");
	
	private String value;
	
	private ReportDataState(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
