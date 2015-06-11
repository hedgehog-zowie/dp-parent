package com.iuni.dp.admin.datastat.constants;

public enum StatSchemeTaskType {

	AddOrUpdate("addOrUpdate"), Remove("remove");
	
	private String value;

	private StatSchemeTaskType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
