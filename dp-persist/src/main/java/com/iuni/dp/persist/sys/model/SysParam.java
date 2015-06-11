package com.iuni.dp.persist.sys.model;

import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 系统管理配置参数
 * @author menglei
 */
public class SysParam implements java.io.Serializable {

	private static final long serialVersionUID = -9009383008700430825L;
	private String paramName;// 参数名
	private String paramVal;// 参数值
	private String paramDesc;// 参数描述
	private Date createTime;// 创建时间
	private long createor;// 创建者
	private Date modifyTime;// 修改时间
	private long modifier;// 修改者
	
	public SysParam() {
		super();
	}

	public SysParam(String paramName, String paramVal, String paramDesc,Date createTime, long createor, Date modifyTime, long modifier) {
		this();
		this.paramName = paramName;
		this.paramVal = paramVal;
		this.paramDesc = paramDesc;
		this.createTime = createTime;
		this.createor = createor;
		this.modifyTime = modifyTime;
		this.modifier = modifier;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamVal() {
		return paramVal;
	}

	public void setParamVal(String paramVal) {
		this.paramVal = paramVal;
	}

	public String getParamDesc() {
		return paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getCreateor() {
		return createor;
	}

	public void setCreateor(long createor) {
		this.createor = createor;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public long getModifier() {
		return modifier;
	}

	public void setModifier(long modifier) {
		this.modifier = modifier;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
