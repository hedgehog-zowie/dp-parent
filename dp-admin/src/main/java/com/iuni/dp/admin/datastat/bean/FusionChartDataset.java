package com.iuni.dp.admin.datastat.bean;

/**
 * @ClassName FusionChartDataset
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class FusionChartDataset {

	private String seriesname;
	
	private String color;
	
	private String linethickness;
	
	private String data;

	public String getSeriesname() {
		return seriesname;
	}

	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLinethickness() {
		return linethickness;
	}

	public void setLinethickness(String linethickness) {
		this.linethickness = linethickness;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "FusionChartDataset [seriesname=" + seriesname + "]";
	}
	
}
