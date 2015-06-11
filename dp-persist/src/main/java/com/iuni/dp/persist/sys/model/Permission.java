package com.iuni.dp.persist.sys.model;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Permission
 * 菜单权限
 * @author menglei
 */
public class Permission {
	
	private long id; 
	private long u_id;
	private int m_id; 
	private String create_time;
	private long creator;
	private String modify_time;
	private long modifier;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getU_id() {
		return u_id;
	}
	public void setU_id(long uId) {
		this.u_id = uId;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int mId) {
		this.m_id = mId;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String createTime) {
		this.create_time = createTime;
	}
	public long getCreator() {
		return creator;
	}
	public void setCreator(long creator) {
		this.creator = creator;
	}
	public String getModify_time() {
		return modify_time;
	}
	public void setModify_time(String modifyTime) {
		this.modify_time = modifyTime;
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
