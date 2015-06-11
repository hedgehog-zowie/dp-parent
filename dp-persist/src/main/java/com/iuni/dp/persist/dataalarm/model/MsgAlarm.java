package com.iuni.dp.persist.dataalarm.model;

import java.io.Serializable;
//import org.apache.commons.lang.builder.ToStringBuilder;
import java.util.Date;

import com.iuni.dp.persist.common.constants.TypeEnum;

/**
 * 功能描述：消息告警bean对象<br>
 * 创建人：menglei <br>
 * 创建时间：2013-04-15<br>
 * 版本：1.0.0.1 <br>
 * 版权拥有：金立电商<br>
 * ====================================== <br>
 * 修改记录                                                                                    <br>
 * ====================================== <br>
 *  序号    姓名      日期      版本           简单描述 <br>
 *
 */
public class MsgAlarm implements Serializable {

	private static final long serialVersionUID = 1066325844385598541L;

	// 消息告警ID
	private Integer msgAlarmId;
	
	// 消息类型ID
	private String msgTypeId;
	
	// 消息主题
	private String msgSubject;
	
	// 消息内容
	private String msgContent;

	// 已执行成功次数,缺省值为0
	private Integer executeSucessNum = 0 ;
	
	// 创建时间,默认为系统当前时间
	private Date createTime;
	
	// 告警状态 1:进行中 2:处理完毕,缺省值为1
	private Integer state = TypeEnum.alarmStateEnum.GOING_ON.getKey();
	
	//备注
	private String remark;
	
	// 消息类型
	private MsgType msgType;
	
	public MsgAlarm(){
		super();
	}
	
	public MsgAlarm(String msgTypeId, String msgSubject, String msgContent) {
		this();
		this.msgTypeId = msgTypeId;
		this.msgSubject = msgSubject;
		this.msgContent = msgContent;
	}

	public Integer getMsgAlarmId() {
		return msgAlarmId;
	}

	public void setMsgAlarmId(Integer msgAlarmId) {
		this.msgAlarmId = msgAlarmId;
	}

	public String getMsgTypeId() {
		return msgTypeId;
	}

	public void setMsgTypeId(String msgTypeId) {
		this.msgTypeId = msgTypeId;
	}

	public String getMsgSubject() {
		return msgSubject;
	}

	public void setMsgSubject(String msgSubject) {
		this.msgSubject = msgSubject;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Integer getExecuteSucessNum() {
		return executeSucessNum;
	}

	public void setExecuteSucessNum(Integer executeSucessNum) {
		this.executeSucessNum = executeSucessNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}

	@Override
	public String toString() {
		//return ToStringBuilder.reflectionToString(this);
		StringBuilder builder = new StringBuilder();
		builder.append("msgTypeId=").append(msgTypeId)
		       .append(",msgSubject=").append(msgSubject)
			   .append(",msgContent=").append(msgContent)
			   .append(",executeSucessNum=").append(executeSucessNum)
			   .append(",state=").append(state)
			   .append(",remark=").append(remark);
		return builder.toString();
	}

}
