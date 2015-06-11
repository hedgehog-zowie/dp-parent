package com.iuni.dp.service.dataalarm.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.dataalarm.model.MsgType;

public interface MsgTypeService {

	/**
	 * 通过消息类型ID获取消息类型对象
	 * @param String msgTypeId
	 * @return MsgType
	 */
	public MsgType getMsgTypeByMsgTypeId(String msgTypeId);
	
	/**
	 * 消息类型列表查询
	 * @param MsgType msgType
	 * @return List<MsgType>
	 */
	public List<MsgType> getMsgTypeList(MsgType msgType);
	
	/**
	 * 获取全都消息类型列表
	 * @return List<MsgType>
	 */
	public List<MsgType> getAllMsgTypeList();
	
	/**
	 * 消息类型列表分页查询
	 * @param Map<String, Object> params
	 * @return List<MsgType>
	 */
	public List<MsgType> fetchMsgTypeByPage(Map<String, Object> params);
	
	/**
	 * 消息类型列表总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer fetchTotalCount(Map<String, Object> params);
	
	/**
	 * 消息类型缓存刷新
	 * @param 
	 * @return void
	 */
	public void refreshMsgType();
	
	/**
	 * 消息类型缓存更新
	 * @param msgType
	 */
	public void updateMsgTypeCache(MsgType msgType);
	
	/**
	 * 新增消息类型入库
	 * @param MsgType msgType
	 * @param List<Integer> receivePersonIds
	 */
	public void saveMsgType(MsgType msgType, List<Integer> receivePersonIds);
	
	/**
	 * 更新消息类型
	 * @param MsgType msgType
	 * @param List<Integer> receivePersonIds
	 */
	public void updateMsgType(MsgType msgType, List<Integer> receivePersonIds);
	
	/**
	 * 删除消息类型
	 * @param MsgType msgType
	 */
	public void removeMsgType(MsgType msgType);
	
}
