package com.iuni.dp.admin.datastat.bean;

import java.io.Serializable;

/**
 * @ClassName FusionChartData
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class FusionChartData implements Serializable {

	private static final long serialVersionUID = 5339973301404341936L;

	private String label;
	
	private String value;
	
	private String issliced;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIssliced() {
		return issliced;
	}

	public void setIssliced(String issliced) {
		this.issliced = issliced;
	}

	@Override
	public String toString() {
		return "FusionChartData [label=" + label + ", value=" + value + "]";
	}
	
}
