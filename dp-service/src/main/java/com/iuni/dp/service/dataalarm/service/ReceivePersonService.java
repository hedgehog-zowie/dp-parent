package com.iuni.dp.service.dataalarm.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.dataalarm.model.ReceivePerson;

/**
 * ReceivePerson Service Interface
 * @author CaiKe
 * @version V1.0.0 
 */
public interface ReceivePersonService {

	/**
	 * 保存接收人信息入库
	 * @param receivePerson
	 */
	public void saveReceivePerson(ReceivePerson receivePerson);
	
	/**
	 * 获取当前接收人信息列表
	 * @return List<ReceivePerson>
	 */
	public List<ReceivePerson> fetchAllReceivePerson();
	
	/**
	 * 接收人信息列表分页查询
	 * @param params
	 * @return List<ReceivePerson>
	 */
	public List<ReceivePerson> fetchReceivePersonByPage(Map<String, Object> params);

	/**
	 * 接收人信息列表总数目分页查询
	 * @param params
	 * @return Integer
	 */
	public Integer fetchTotalCount(Map<String, Object> params);
	
	/**
	 * 根据ID获取接收人信息
	 * @param id
	 * @return ReceivePerson
	 */
	public ReceivePerson fetchReceivePersonById(String id);
	
	/**
	 * 更新单个接收人信息
	 * @param receivePerson
	 */
	public void updateReceivePerson(ReceivePerson receivePerson);
	
	/**
	 * 删除单个接收人信息
	 * @param id
	 */
	public void removeReceivePersonById(String id);
	
	/**
	 * 根据消息类型Id获取对应接收人列表信息
	 * @param msgTypeId
	 * @return List<ReceivePerson>
	 */
	public List<ReceivePerson> fetchReceivePersonsByMsgTypeId(String msgTypeId);
	
}
