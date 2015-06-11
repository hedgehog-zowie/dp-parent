package com.iuni.dp.persist.dataalarm.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.dataalarm.model.BusLog;

public interface BusLogDAO extends BaseDao<BusLog, Serializable> {
	
	/**
	 * 获取全部业务日志信息列表
	 * @return List<BusLog>
	 */
	public List<BusLog> getAllBusLog();
	
	/**
	 * 业务日志信息列表分页条件查询
	 * @param Map<String, Object> params
	 * @return List<BusLog>
	 */
	public List<BusLog> getBusLogByPage(Map<String,Object> params);
	
	/**
	 * 日志信息列表总数目条件查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer getTotalCount(Map<String,Object> params);
	
	/**
	 * 批量保存业务日志信息
	 * @param List<MsgAlarm> msgAlarmList
	 * @return List<String[]>
	 */
	public List<Integer> batchSaveBusLog(final List<BusLog> busLogList);

	/**
	 * 单条业务日志数据入库
	 * @param BusLog busLog
	 * @return int
	 */
	public int saveBusLog(BusLog busLog);
	
}
