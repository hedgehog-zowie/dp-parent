package com.iuni.dp.persist.dataalarm.model;

public class PersonRelMsgType {
	
	private Integer personRelMsgTypeId;
	
	private Integer receivePersonId;
	
	private Integer msgTypeId;

	public Integer getPersonRelMsgTypeId() {
		return personRelMsgTypeId;
	}

	public void setPersonRelMsgTypeId(Integer personRelMsgTypeId) {
		this.personRelMsgTypeId = personRelMsgTypeId;
	}

	public Integer getReceivePersonId() {
		return receivePersonId;
	}

	public void setReceivePersonId(Integer receivePersonId) {
		this.receivePersonId = receivePersonId;
	}

	public Integer getMsgTypeId() {
		return msgTypeId;
	}

	public void setMsgTypeId(Integer msgTypeId) {
		this.msgTypeId = msgTypeId;
	}

	@Override
	public String toString() {
		return "PersonRelMsgType [personRelMsgTypeId=" + personRelMsgTypeId
				+ ", receivePersonId=" + receivePersonId + ", msgTypeId="
				+ msgTypeId + "]";
	}
	
}
