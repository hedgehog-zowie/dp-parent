package com.iuni.dp.persist.dataalarm.model;

import java.io.Serializable;
//import org.apache.commons.lang.builder.ToStringBuilder;
import java.util.Date;

/**
 * 功能描述：业务日志对象<br>
 * 创建人：menglei <br>
 * 创建时间：2013-04-23<br>
 * 版本：1.0.0.1 <br>
 * 版权拥有：金立电商<br>
 * ====================================== <br>
 * 修改记录                                                                                    <br>
 * ====================================== <br>
 *  序号    姓名      日期      版本           简单描述 <br>
 *
 */
public class BusLog implements Serializable {
	
	public Integer getBusLogId() {
		return busLogId;
	}

	public void setBusLogId(Integer busLogId) {
		this.busLogId = busLogId;
	}

	public String getOptId() {
		return optId;
	}

	public void setOptId(String optId) {
		this.optId = optId;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getBeforeContent() {
		return beforeContent;
	}

	public void setBeforeContent(String beforeContent) {
		this.beforeContent = beforeContent;
	}

	public String getAfterContent() {
		return afterContent;
	}

	public void setAfterContent(String afterContent) {
		this.afterContent = afterContent;
	}

	public Integer getExecutResult() {
		return executResult;
	}

	public void setExecutResult(Integer executResult) {
		this.executResult = executResult;
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
	
	private static final long serialVersionUID = 1066325844385598541L;

	// 业务日志ID
	private Integer busLogId;

	// 操作员ID 系统操作：99999 具体操作员：其它 缺省值为99999
	private String optId ;
	
	// 业务类型
	private String busType;
	
	// 操作前内容
	private String beforeContent;

	// 操作后内容
	private String afterContent;
	
	// 业务执行结果 1:sucess 0:fail 缺省值为1
    private Integer executResult ;
	
	// 业务执行时间
	private Date createTime;
	
	//业务日志备注
	private String remark;
	
	public BusLog(){
		super();
	}
	
	public BusLog(String optId,String busType,String beforeContent,String afterContent,Integer executResult,String remark){
		this();
		this.optId = optId;
		this.busType = busType;
		this.beforeContent = beforeContent;
		this.afterContent = afterContent;
		this.executResult = executResult;
		this.remark = remark;
	}

	@Override
	public String toString() {
		//return ToStringBuilder.reflectionToString(this);
		StringBuilder builder = new StringBuilder();
		builder.append("busLogId=").append(busLogId)
		       .append(",optId=").append(optId)
			   .append(",busType=").append(busType)
			   .append(",beforeContent=").append(beforeContent)
			   .append(",afterContent=").append(afterContent)
			   .append(",executResult=").append(executResult)
			   .append(",createTime=").append(createTime)
			   .append(",remark=").append(remark);
		return builder.toString();
	}

}
