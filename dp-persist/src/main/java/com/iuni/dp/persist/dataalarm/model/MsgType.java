package com.iuni.dp.persist.dataalarm.model;

import java.io.Serializable;
import java.util.ArrayList;
//import org.apache.commons.lang.builder.ToStringBuilder;
import java.util.Date;
import java.util.List;

import com.iuni.dp.persist.common.constants.TypeEnum;

/**
 * 功能描述：消息类型bean对象<br>
 * 创建人：menglei <br>
 * 创建时间：2013-04-16<br>
 * 版本：1.0.0.1 <br>
 * 版权拥有：金立电商<br>
 * ====================================== <br>
 * 修改记录 <br>
 * ====================================== <br>
 * 序号 姓名 日期 版本 简单描述 <br>
 * 
 */
public class MsgType extends MsgStrategy implements Serializable {

	private static final long serialVersionUID = 1066325844385598541L;

	public MsgType() {
		super();
	}
	
	/**
	 * 消息类型ID
	 */
	private Integer msgTypeId;

	/**
	 * 消息类型编码
	 */
	private String msgTypeCode;

	/**
	 * 消息类型名称
	 */
	private String msgTypeName;

	/**
	 *  消息类型是否启用 1:启用 0:禁用 缺省值为1
	 */
	private Integer flag = TypeEnum.msgTypeFlagEnum.TO_ENABLE.getKey();

	/**
	 *  创建时间
	 */
	private Date createTime;

	/**
	 *  创建人
	 */
	private String creator;

	/**
	 *  备注
	 */
	private String remark;

	/**
	 *  接收人信息bean对象列表(一种消息类型对应多个接收人)
	 */
	private List<ReceivePerson> receivePersonList = new ArrayList<ReceivePerson>();

	public Integer getMsgTypeId() {
		return msgTypeId;
	}

	public void setMsgTypeId(Integer msgTypeId) {
		this.msgTypeId = msgTypeId;
	}

	public String getMsgTypeCode() {
		return msgTypeCode;
	}

	public void setMsgTypeCode(String msgTypeCode) {
		this.msgTypeCode = msgTypeCode;
	}

	public String getMsgTypeName() {
		return msgTypeName;
	}

	public void setMsgTypeName(String msgTypeName) {
		this.msgTypeName = msgTypeName;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ReceivePerson> getReceivePersonList() {
		return receivePersonList;
	}

	public void setReceivePersonList(List<ReceivePerson> receivePersonList) {
		this.receivePersonList = receivePersonList;
	}

	@Override
	public String toString() {
		return "MsgType [msgTypeId=" + msgTypeId + ", msgTypeCode="
				+ msgTypeCode + ", msgTypeName=" + msgTypeName + ", flag="
				+ flag + ", createTime=" + createTime + ", creator=" + creator
				+ ", remark=" + remark + ", receivePersonList="
				+ receivePersonList + "]";
	}

}
