package com.iuni.dp.persist.dataalarm.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.dataalarm.model.MsgType;
import com.iuni.dp.persist.dataalarm.model.PersonRelMsgType;

public interface MsgTypeDAO extends BaseDao<MsgType, Serializable> {

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
	 * 全部消息类型列表查询
	 * @return
	 */
	public List<MsgType> getAllMsgTypeList();
	
	/**
	 * 消息类型列表分页查询
	 * @param Map<String, Object> params
	 * @return List<MsgType>
	 */
	public List<MsgType> getMsgTypeByPage(Map<String, Object> params);
	
	/**
	 * 消息类型列表总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer getTotalCount(Map<String, Object> params);
	
	/**
	 * 新增消息类型入库
	 * @param msgType
	 * @return
	 */
	public Integer insertMsgType(MsgType msgType);
	
	/**
	 * 新增消息类型接收人关系入库
	 * @param personRelMsgType
	 * @return Integer
	 */
	public Integer insertPersonRelMsgType(PersonRelMsgType personRelMsgType);
	
	/**
	 * 批量新增消息类型接收人关系入库
	 * @param personRelMsgTypes
	 * @return Integer
	 */
	public List<Integer> batchInsertPersonRelMsgType(final List<PersonRelMsgType> personRelMsgTypes);
	
	/**
	 * 更新消息类型
	 * @param MsgType msgType
	 */
	public Integer updateMsgType(MsgType msgType);
	
	/**
	 * 根据消息类型Id删除消息类型接收人关系
	 * @return
	 */
	public Integer deletePersonRelMsgType(PersonRelMsgType personRelMsgType);
	
	/**
	 * 批量删除消息类型接收人关系
	 * @param List<PersonRelMsgType> personRelMsgTypes
	 */
	public List<Integer> batchDeletePersonRelMsgType(final List<PersonRelMsgType> personRelMsgTypes);
	
	/**
	 * 删除消息类型
	 * @param MsgType msgType
	 */
	public Integer deleteMsgTypeById(Integer id);
	
}
