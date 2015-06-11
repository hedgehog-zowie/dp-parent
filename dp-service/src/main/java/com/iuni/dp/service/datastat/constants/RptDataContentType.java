package com.iuni.dp.service.datastat.constants;

/**
 * 上报数据内容类型
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public enum RptDataContentType {

	PF("pf"), STP("stp"), PV("pv"), MALL_AD_CHANNEL("mall_ad_channel"), MALL_SALE("mall_sale"), 
	GIONEE_APP_USEANDORDER_DATA("gionee_app_useandorder_data"), 
	/**
	 * IUNI前端网络流量数据每日统计
	 */
	IUNI_NETFLOW_DATA_DAILY("iuni_netflow_data_daily"),
	/**
	 * 统计IUNI商城每日IP区域统计
	 */
	IUNI_IPAREA_DATA_DAILY("iuni_iparea_data_daily"),
	/**
	 * 统计IUNI商城每日埋点数据统计
	 */
	IUNI_BP_DATA_DAILY("iuni_bp_data_daily"),
	IUNI_DAILY_DATA("iuni_daily_data");
	
	private String value;

	private RptDataContentType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
