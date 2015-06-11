package com.iuni.dp.persist.dataalarm.model;

import java.util.Date;

/**
 * 功能描述：接收人信息bean对象(一种消息类型对应多个接收人)<br>
 * 创建人：menglei <br>
 * 创建时间：2013-04-16<br>
 * 版本：1.0.0.1 <br>
 * 版权拥有：金立电商<br>
 * ====================================== <br>
 * 修改记录                                                                                    <br>
 * ====================================== <br>
 *  序号    姓名      日期      版本           简单描述 <br>
 *
 */
public class ReceivePerson {
	
	// 接收人ID
	private Integer receivePersonId;
	
	// 接收人CODE
	private String code;
	
	// 接收人名称
	private String name;
	
	// 接收电话
	private String mobile;
	
	// 接收邮箱
	private String email;
	
	// 创建时间
	private Date createTime;
	
	//创建人
	private String creator;
	
	// 备注
	private String remark;
	
	// 与消息类型链接状态
	private String relStatus;
	
	public Integer getReceivePersonId() {
		return receivePersonId;
	}

	public void setReceivePersonId(Integer receivePersonId) {
		this.receivePersonId = receivePersonId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public String getRelStatus() {
		return relStatus;
	}

	public void setRelStatus(String relStatus) {
		this.relStatus = relStatus;
	}

	@Override
	public String toString() {
		//return ToStringBuilder.reflectionToString(this);
		StringBuilder builder = new StringBuilder();
		builder.append("receivePersonId=").append(receivePersonId)
			   .append(",code=").append(code)
			   .append(",name=").append(name)
			   .append(",mobile=").append(mobile)
			   .append(",email=").append(email)
			   .append(",createTime=").append(createTime)
			   .append(",creator=").append(creator)
			   .append(",remark=").append(remark);
		return builder.toString();
	}

}
