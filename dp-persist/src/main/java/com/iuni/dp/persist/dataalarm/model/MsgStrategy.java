package com.iuni.dp.persist.dataalarm.model;

import com.iuni.dp.persist.common.constants.TypeEnum;

/**
 * 功能描述：消息类型中的消息策略bean对象(一种消息类型对应一种具体的消息策略)<br>
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
public class MsgStrategy {
	
    // 是否发送邮件  1:发送 0：不发送 缺省值 0
	private Integer sendEmailFlag = Integer.getInteger(TypeEnum.sendFlagEnum.NO_SEND.getKey());
	// 是否发送短信  1:发送 0：不发送 缺省值 0
	private Integer sendSmsFlag = Integer.getInteger(TypeEnum.sendFlagEnum.NO_SEND.getKey());
	
	// 发送策略code :11:立即发送  12：延迟发送 13:定时发送  14: 循环发送  缺省值为11
	private int senderStrategyCode = TypeEnum.senderStrategyCodeEnum.IMMEDIATELY_SEND.getKey();
	// 延时发送时间(相对于系统当前时间，单位：秒),缺省值为0
	private Integer senderDelaySeconds = 0;
	// 定时发送时间(相对于每天的凌晨0时,单位：秒),缺省值为0
	private Integer senderTimingSeconds = 0;
	
	// 执行策略Code 21:一次执行 22：多次执行
	private int executeStrategyCode = TypeEnum.executeStrategyCodeEnum.EXECUTE_AT_A_TIME.getKey();
	// 执行次数，,缺省值为0
	private Integer executeNumber = 0;
	// 执行延时时间（相对于上次执行完成的时间，单位：秒）,缺省值为0
	private Integer executeDelaySeconds = 0;
	
	// 失败策略Code 31:不处理 32：立即重试N次 33:延时重试N次
    private int failStrategyCode = TypeEnum.failStrategyCodeEnum.DO_NOT_DEAL_WITH.getKey();
    // 失败重试次数,缺省值为0
    private Integer failRepeatNumber = 0;
    // 失败重试延时时间（相对于上次失败重试执行完成的时间，单位：秒）,缺省值为0
    private Integer failDelaySeconds = 0;

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

	public int getSenderStrategyCode() {
		return senderStrategyCode;
	}

	public void setSenderStrategyCode(int senderStrategyCode) {
		this.senderStrategyCode = senderStrategyCode;
	}

	public Integer getSenderDelaySeconds() {
		return senderDelaySeconds;
	}

	public void setSenderDelaySeconds(Integer senderDelaySeconds) {
		this.senderDelaySeconds = senderDelaySeconds;
	}

	public Integer getSenderTimingSeconds() {
		return senderTimingSeconds;
	}

	public void setSenderTimingSeconds(Integer senderTimingSeconds) {
		this.senderTimingSeconds = senderTimingSeconds;
	}

	public int getExecuteStrategyCode() {
		return executeStrategyCode;
	}

	public void setExecuteStrategyCode(int executeStrategyCode) {
		this.executeStrategyCode = executeStrategyCode;
	}

	public Integer getExecuteNumber() {
		return executeNumber;
	}

	public void setExecuteNumber(Integer executeNumber) {
		this.executeNumber = executeNumber;
	}

	public Integer getExecuteDelaySeconds() {
		return executeDelaySeconds;
	}

	public void setExecuteDelaySeconds(Integer executeDelaySeconds) {
		this.executeDelaySeconds = executeDelaySeconds;
	}

	public int getFailStrategyCode() {
		return failStrategyCode;
	}

	public void setFailStrategyCode(int failStrategyCode) {
		this.failStrategyCode = failStrategyCode;
	}

	public Integer getFailRepeatNumber() {
		return failRepeatNumber;
	}

	public void setFailRepeatNumber(Integer failRepeatNumber) {
		this.failRepeatNumber = failRepeatNumber;
	}

	public Integer getFailDelaySeconds() {
		return failDelaySeconds;
	}

	public void setFailDelaySeconds(Integer failDelaySeconds) {
		this.failDelaySeconds = failDelaySeconds;
	}
    
    @Override
	public String toString() {
		//return ToStringBuilder.reflectionToString(this);
		StringBuilder builder = new StringBuilder();
		builder.append("sendEmailFlag=").append(sendEmailFlag)
			   .append(",sendSmsFlag=").append(sendSmsFlag)
			   .append(",senderStrategyCode=").append(senderStrategyCode)
			   .append(",senderDelaySeconds=").append(senderDelaySeconds)
			   .append(",senderTimingSeconds=").append(senderTimingSeconds)
			   .append(",executeStrategyCode=").append(executeStrategyCode)
			   .append(",executeNumber=").append(executeNumber)
			   .append(",executeDelaySeconds=").append(executeDelaySeconds)
			   .append(",failStrategyCode=").append(failStrategyCode)
			   .append(",failRepeatNumber=").append(failRepeatNumber)
			   .append(",failDelaySeconds=").append(failDelaySeconds);
		return builder.toString();
	}

}
