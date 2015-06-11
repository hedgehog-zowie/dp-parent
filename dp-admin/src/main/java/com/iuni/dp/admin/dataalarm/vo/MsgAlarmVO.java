package com.iuni.dp.admin.dataalarm.vo;

/**
 * 告警消息信息VO类
 * @author CaiKe
 * @version V1.0.0
 */
public class MsgAlarmVO {

	private String msgSubject;
	
	private Integer executeSucessNum;
	
	private Integer state;
	
	private String beginDate;
	
	private String endDate;

	public String getMsgSubject() {
		return msgSubject;
	}

	public void setMsgSubject(String msgSubject) {
		this.msgSubject = msgSubject;
	}

	public Integer getExecuteSucessNum() {
		return executeSucessNum;
	}

	public void setExecuteSucessNum(Integer executeSucessNum) {
		this.executeSucessNum = executeSucessNum;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "MsgAlarmVO [msgSubject=" + msgSubject + ", executeSucessNum="
				+ executeSucessNum + ", state=" + state + ", beginDate="
				+ beginDate + ", endDate=" + endDate + "]";
	}
	
}
