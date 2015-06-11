package com.iuni.dp.persist.dataalarm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述：告警排期bean对象(一条排期记录对应一种具体的消息策略)<br>
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
public class AlarmSchedule extends MsgStrategy implements Serializable {
	
	private static final long serialVersionUID = 1066325844385598541L;
	
	//告警排期ID
	private Integer alarmScheduleId;
	
	//消息告警ID
	private Integer msgAlarmId;
	
	//消息类型ID
	private Integer msgTypeId;

	//下次执行时间
	private Date nextExecuteTime;
	
	//已执行失败次数,缺省值为0
	private Integer executeFailNum = 0;
	
	//创建时间
	private Date createTime;
	
	//备注
	private String remark;
	
	//消息告警对象
	private MsgAlarm msgAlarm = new MsgAlarm();

	public AlarmSchedule(){
		super();
	}
	
	public AlarmSchedule(Integer msgAlarmId,Integer msgTypeId,Date nextExecuteTime){
		this();
		this.msgAlarmId = msgAlarmId;
		this.msgTypeId = msgTypeId;
		this.nextExecuteTime =  nextExecuteTime;
	}
	
	public Integer getAlarmScheduleId() {
		return alarmScheduleId;
	}

	public void setAlarmScheduleId(Integer alarmScheduleId) {
		this.alarmScheduleId = alarmScheduleId;
	}

	public Integer getMsgAlarmId() {
		return msgAlarmId;
	}

	public void setMsgAlarmId(Integer msgAlarmId) {
		this.msgAlarmId = msgAlarmId;
	}
	
	public Integer getMsgTypeId() {
		return msgTypeId;
	}

	public void setMsgTypeId(Integer msgTypeId) {
		this.msgTypeId = msgTypeId;
	}

	public Date getNextExecuteTime() {
		return nextExecuteTime;
	}

	public void setNextExecuteTime(Date nextExecuteTime) {
		this.nextExecuteTime = nextExecuteTime;
	}

	public Integer getExecuteFailNum() {
		return executeFailNum;
	}

	public void setExecuteFailNum(Integer executeFailNum) {
		this.executeFailNum = executeFailNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public MsgAlarm getMsgAlarm() {
		return msgAlarm;
	}

	public void setMsgAlarm(MsgAlarm msgAlarm) {
		this.msgAlarm = msgAlarm;
	}

	@Override
	public String toString() {
		//return ToStringBuilder.reflectionToString(this);
		StringBuilder builder = new StringBuilder();
		builder.append("alarmScheduleId=").append(alarmScheduleId)
		       .append(",msgAlarmId=").append(msgAlarmId)
		       .append(",msgTypeId=").append(msgTypeId)
		       .append(",nextExecuteTime=").append(nextExecuteTime)
		       .append(",executeFailNum=").append(executeFailNum)
		       .append(",createTime=").append(createTime)
		       .append(",remark=").append(remark)
		       .append(",").append(super.toString());
		return builder.toString();
	}
	
}
