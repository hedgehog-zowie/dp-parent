package com.iuni.dp.persist.datastat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.datastat.model.IuniSmsSendlogHis;

/**
 * IuniSmsSendlogHis DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.2
 */
public interface IuniSmsSendlogHisDao extends
		BaseDao<IuniSmsSendlogHis, Serializable> {

	/**
	 * IUNI SMS每日发送量查询
	 * @return List
	 */
	public List<Map<String, Object>> selectSendNumDailyByExample(Map<String, Object> params);
	
	/**
	 * IUNI SMS每日发送量分页查询
	 * @return List
	 */
	public List<Map<String, Object>> selectSendNumDailyByPage(Map<String, Object> params);
	
	/**
	 * IUNI SMS每日发送量查询记录数
	 * @return List
	 */
	public Integer selectSendNumDailyCount(Map<String, Object> params);
	
	/**
	 * IUNI SMS按时间发送量汇总查询
	 * @return List
	 */
	public List<Map<String, Object>> selectSendNumSumByExample(Map<String, Object> params);
	
}
