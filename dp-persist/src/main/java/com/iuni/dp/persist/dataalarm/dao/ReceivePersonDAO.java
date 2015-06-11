package com.iuni.dp.persist.dataalarm.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.dataalarm.model.ReceivePerson;

public interface ReceivePersonDAO extends BaseDao<ReceivePerson, Serializable> {

	/**
	 * 通过消息类型ID获取接收人信息对象列表
	 * @param String msgTypeId
	 * @return List<ReceivePerson>
	 */
	public List<ReceivePerson> getReceivePersonListByMsgTypeId(String msgTypeId);
	
	/**
	 * 新增单个接收人信息
	 * @param receivePerson
	 */
	public void insertReceivePerson(ReceivePerson receivePerson);
	
	/**
	 * 查询当前所有接收人信息列表
	 * @return List<ReceivePerson>
	 */
	public List<ReceivePerson> getAllReceivePerson();
	
	/**
	 * 接收人信息列表分页查询
	 * @param params
	 * @return List<ReceivePerson>
	 */
	public List<ReceivePerson> getReceivePersonByPage(Map<String, Object> params);
	
	/**
	 * 接收人信息列表总数目条件查询
	 * @param params
	 * @return Integer
	 */
	public Integer getTotalCount(Map<String, Object> params);
	
	/**
	 * 根据ID获取接收人信息
	 * @param id
	 * @return ReceivePerson
	 */
	public ReceivePerson getReceivePersonById(String id);
	
	/**
	 * 更新单个接收人信息
	 * @param receivePerson
	 */
	public void updateReceivePerson(ReceivePerson receivePerson);
	
	/**
	 * 删除单个接收人信息
	 * @param id
	 */
	public void deleteReceivePersonById(String id);
	
}
