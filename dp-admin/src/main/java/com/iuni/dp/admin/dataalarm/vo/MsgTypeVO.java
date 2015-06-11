package com.iuni.dp.admin.dataalarm.vo;

/**
 * 消息类型信息VO类
 * @author CaiKe
 * @version V1.0.0
 */
public class MsgTypeVO {

	private String msgTypeCode;
	
	private String msgTypeName;
	
	private Integer flag;
	
	private Integer sendEmailFlag;
	
	private Integer sendSmsFlag;
	
	private Integer senderStrategyCode;
	
	private Integer executeStrategyCode;
	
	private Integer failStrategyCode;
	
	private String beginDate;
	
	private String endDate;

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

	public Integer getSendEmailFlag() {
		return sendEmailFlag;
	}

	public void setSendEmailFlag(Integer sendEmailFlag) {
		this.sendEmailFlag = sendEmailFlag;
	}

	public Integer getSendSmsFlag() {
		return sendSmsFlag;
	}

	public void setSendSmsFlag(Integer sendSmsFlag) {
		this.sendSmsFlag = sendSmsFlag;
	}

	public Integer getSenderStrategyCode() {
		return senderStrategyCode;
	}

	public void setSenderStrategyCode(Integer senderStrategyCode) {
		this.senderStrategyCode = senderStrategyCode;
	}

	public Integer getExecuteStrategyCode() {
		return executeStrategyCode;
	}

	public void setExecuteStrategyCode(Integer executeStrategyCode) {
		this.executeStrategyCode = executeStrategyCode;
	}

	public Integer getFailStrategyCode() {
		return failStrategyCode;
	}

	public void setFailStrategyCode(Integer failStrategyCode) {
		this.failStrategyCode = failStrategyCode;
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
		return "MsgTypeVO [msgTypeCode=" + msgTypeCode + ", msgTypeName="
				+ msgTypeName + ", flag=" + flag + ", sendEmailFlag="
				+ sendEmailFlag + ", sendSmsFlag=" + sendSmsFlag
				+ ", senderStrategyCode=" + senderStrategyCode
				+ ", executeStrategyCode=" + executeStrategyCode
				+ ", failStrategyCode=" + failStrategyCode + ", beginDate="
				+ beginDate + ", endDate=" + endDate + "]";
	}
	
}
