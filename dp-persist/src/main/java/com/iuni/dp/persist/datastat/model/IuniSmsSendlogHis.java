package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * IuniSmsSendlogHis Model
 * @author Kenneth.Cai@iuni.com
 * @version dp-admin-1.1.2
 */
public class IuniSmsSendlogHis implements Serializable {

	private static final long serialVersionUID = 8737254739640578670L;

	private Long id;
    
    private Long smsId;

    private String mobile;

    private String smsContent;

    private String smsSource;

    private String processType;

    private Short sendStatus;
    
    private Short mtStatus;
    
    private Timestamp reqTime;

    private Timestamp sendTime;

    private Timestamp createTime;

	public IuniSmsSendlogHis() {
		super();
	}

	public IuniSmsSendlogHis(Long smsId, String mobile, String smsContent,
			String smsSource, String processType, Short sendStatus,
			Short mtStatus, Timestamp reqTime, Timestamp sendTime) {
		super();
		this.smsId = smsId;
		this.mobile = mobile;
		this.smsContent = smsContent;
		this.smsSource = smsSource;
		this.processType = processType;
		this.sendStatus = sendStatus;
		this.mtStatus = mtStatus;
		this.reqTime = reqTime;
		this.sendTime = sendTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSmsId() {
		return smsId;
	}

	public void setSmsId(Long smsId) {
		this.smsId = smsId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getSmsSource() {
		return smsSource;
	}

	public void setSmsSource(String smsSource) {
		this.smsSource = smsSource;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public Short getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Short sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Short getMtStatus() {
		return mtStatus;
	}

	public void setMtStatus(Short mtStatus) {
		this.mtStatus = mtStatus;
	}

	public Timestamp getReqTime() {
		return reqTime;
	}

	public void setReqTime(Timestamp reqTime) {
		this.reqTime = reqTime;
	}

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "IuniSmsSendlogHis [smsId=" + smsId + ", mobile=" + mobile
				+ ", smsContent=" + smsContent + ", smsSource=" + smsSource
				+ ", processType=" + processType + ", sendStatus=" + sendStatus
				+ ", mtStatus=" + mtStatus + ", reqTime=" + reqTime
				+ ", sendTime=" + sendTime + "]";
	}
	
}
