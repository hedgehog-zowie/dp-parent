package com.iuni.dp.service.dataalarm.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.dataalarm.model.BusLog;
import com.iuni.dp.persist.dataalarm.model.MsgAlarm;

public interface BusLogService {
	
	/**
	 * 获取全部业务日志信息列表
	 * @return List<BusLog>
	 */
	public List<BusLog> fetchAllBusLog();
	
	/**
	 * 业务日志信息列表分页条件查询
	 * @param Map<String, Object> params
	 * @return List<BusLog>
	 */
	public List<BusLog> fetchBusLogByPage(Map<String,Object> params);
	
	/**
	 * 业务日志信息列表总数目条件查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer fetchTotalCount(Map<String,Object> params);
	
	/**
	 * @Description: 保存单条业务数据对象的业务操作日志
	 * @param MsgAlarm afterContent  业务操作保存后内容对象
	 * @param Integer executResult  业务执行结果 1:sucess 0:fail 缺省值为1
	 * @param String remark        业务执行结果描述信息
	 * @return void
	 */
	public void saveBusLog(MsgAlarm afterContent,Integer executResult,String remark);
	
	/**
	 * 批量保存业务数据对象的业务操作日志
	 * @param List<MsgAlarm> busDataObjectList 业务数据对象列表
	 * @param Integer executResult 业务执行结果 1:sucess 0:fail 缺省值为1
	 * @param String remark        业务执行结果描述信息
	 * @return void
	 */
	public void batchSaveBusLog(List<MsgAlarm> busDataObjectList,Integer executResult,String remark);
	
}
