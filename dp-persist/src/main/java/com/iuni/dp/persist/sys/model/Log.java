package com.iuni.dp.persist.sys.model;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.iuni.dp.persist.common.constants.TypeEnum.BusLogTypeEnum;
import com.iuni.dp.persist.common.constants.TypeEnum.SysLogTypeEnum;

/**
 * log日志
 * @author menglei
 */
public class Log implements java.io.Serializable {

	private static final long serialVersionUID = 7233554320754524890L;
	private long logId;
	private long operId;
	private String operName;
	private String operIp;
	private String memo;
	private String logType;//日志类型，1-系统日志；2-业务日志；
	private String logTypeName;
	private String logbusType;//业务类型：21：商品维护, 22：审核商品,23：商品同步 ,24：用户登陆 
	private String logsysType;//系统日志类型：11：自身系统异常, 12：外部系统对接异常
	private Date createTime;
	private int logindex;//用于批量保存的时候排序
	private String merchantCode;
	private String merchantName;
	
	public Log(){
		super();
	}
	
	public Log(long operId, String operName, String operIp, String memo,String logType) {
		this();
		this.operId = operId;
		this.operName = operName;
		this.operIp = operIp;
		this.memo = memo;
		this.logType = logType;
	}
	
	public Log(long operId, String operName, String operIp, String memo,SysLogTypeEnum  syslogType) {		
		this();	
		this.operId = operId;
		this.operName = operName;
		this.operIp = operIp;
		this.memo = memo;
		this.logType = syslogType.getKey();
	//	this.logsysType=syslogType.getKey();
	}
	
	public Log(long operId, String operName, String operIp, String memo,BusLogTypeEnum  buslogType) {		
		this();		
		this.operId = operId;
		this.operName = operName;
		this.operIp = operIp;
		this.memo = memo;
		this.logType = buslogType.getKey();
		//this.logbusType=buslogType.getKey();
	}

	public long getLogId() {
		return logId;
	}
	public void setLogId(long logId) {
		this.logId = logId;
	}
	public long getOperId() {
		return operId;
	}
	public void setOperId(long operId) {
		this.operId = operId;
	}
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public String getOperIp() {
		return operIp;
	}
	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getLogbusType() {
		return logbusType;
	}
	public void setLogbusType(String logbusType) {
		this.logbusType = logbusType;
	}
	public String getLogsysType() {
		return logsysType;
	}
	public void setLogsysType(String logsysType) {
		this.logsysType = logsysType;
	}
	public int getLogindex() {
		return logindex;
	}
	public void setLogindex(int logindex) {
		this.logindex = logindex;
	}
	public String getLogTypeName() {
		return logTypeName;
	}
	public void setLogTypeName(String logTypeName) {
		this.logTypeName = logTypeName;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	 
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
