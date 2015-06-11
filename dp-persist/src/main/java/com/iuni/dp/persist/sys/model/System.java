package com.iuni.dp.persist.sys.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * System 系统表
 * 
 * @author menglei
 */
public class System implements Serializable {

	private Integer sys_id;
	private String sys_name;
	private String url;
	private Integer state;
	private Date create_time;
	private String creator;
	private Date modify_time;
	private String modifier;
	private static final long serialVersionUID = -4259842040839100297L;

	// 当前系统id
	public final static int CUR_SYS_ID = -1;

	public System() {
		super();
	}
	
	public System(String sys_name,String sys_id){
		this();
		this.sys_name = sys_name;
		this.sys_id = Integer.parseInt(sys_id);
	}

	public Integer getSys_id() {
		return sys_id;
	}

	public void setSys_id(Integer sys_id) {
		this.sys_id = sys_id;
	}

	public String getSys_name() {
		return sys_name;
	}

	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
