package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

/**
 * ReportDataByMon Service for Statistics
 * @author Kenneth.Cai@iuni.com
 * @version ips-V1.1.0
 */
public interface ReportDataByMonService {

	/**
	 * 获取当天上报数据流量数据汇总
	 * @param params
	 * @return List
	 */
	public List<Map<String, Object>> queryNetflowSumToday(Map<String, Object> params);
	
}
