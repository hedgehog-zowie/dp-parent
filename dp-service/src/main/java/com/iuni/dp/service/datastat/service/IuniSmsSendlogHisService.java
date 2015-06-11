package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

/**
 * IuniSmsSendlogHis Service
 * @author Kenneth.Cai@iuni.com
 * @version dp-service-1.1.2
 */
public interface IuniSmsSendlogHisService {

	/**
	 * IUNI SMS每日发送量查询
	 * @return List
	 */
	public List<Map<String, Object>> querySendNumDailyByExample(Map<String, Object> params);
	
	/**
	 * IUNI SMS每日发送量分页查询
	 * @return List
	 */
	public List<Map<String, Object>> querySendNumDailyByPage(Map<String, Object> params);
	
	/**
	 * IUNI SMS每日发送量查询记录数
	 * @return List
	 */
	public Integer querySendNumDailyCount(Map<String, Object> params);
	
	/**
	 * IUNI SMS按时间发送量汇总查询
	 * @return List
	 */
	public List<Map<String, Object>> querySendNumSumByExample(Map<String, Object> params);
	
}
