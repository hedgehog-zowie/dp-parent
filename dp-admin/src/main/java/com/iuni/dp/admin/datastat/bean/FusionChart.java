package com.iuni.dp.admin.datastat.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName FusionChart
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class FusionChart implements Serializable {

	private static final long serialVersionUID = -3553566245309063979L;

	private FusionChartConfig chart;
	
	private List<FusionChartCategory> categories;
	
	private List<FusionChartData> data;
	
	private List<FusionChartDataset> dataset;

	public FusionChartConfig getChart() {
		return chart;
	}

	public void setChart(FusionChartConfig chart) {
		this.chart = chart;
	}

	public List<FusionChartCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<FusionChartCategory> categories) {
		this.categories = categories;
	}

	public List<FusionChartData> getData() {
		return data;
	}

	public void setData(List<FusionChartData> data) {
		this.data = data;
	}

	public List<FusionChartDataset> getDataset() {
		return dataset;
	}

	public void setDataset(List<FusionChartDataset> dataset) {
		this.dataset = dataset;
	}

	@Override
	public String toString() {
		return "FusionChart [chart=" + chart + "]";
	}
	
}
