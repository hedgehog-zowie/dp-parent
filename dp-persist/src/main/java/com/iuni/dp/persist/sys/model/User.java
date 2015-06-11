package com.iuni.dp.persist.sys.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.iuni.dp.persist.common.constants.TypeEnum;

public class User implements Serializable {
	
	private String contact_name;
	private String user_ids;
	private Long user_id;
	private String user_name;
	private String password;
	private String merchant_code;
	private String mobile;
	private String phone;
	private String create_time;
	private long creator;
	private String modify_time;
	private long modifier;
	private long start;
	private long end;
	private int size;
	private long id;
	//用户状态 1：正常 0：冻结
	private int status = Integer.parseInt(TypeEnum.UserStateEnum.USER_STATE_NORMAL.getKey());
	
	//用户类型 1：管理员 0：操作员
	private int user_type;
	
	private static final long serialVersionUID = -4259842040839100297L;
	
	public User(){
		super();
	}
	
	public User(String user_name,String user_id){
		this();
		this.user_name = user_name;
		this.user_id = Long.parseLong(user_id);
	}
	
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String createTime) {
		create_time = createTime;
	}
	public String getModify_time() {
		return modify_time;
	}
	public void setModify_time(String modifyTime) {
		modify_time = modifyTime;
	}
	public String getUser_ids() {
		return user_ids;
	}
	public void setUser_ids(String user_ids) {
		this.user_ids = user_ids;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMerchant_code() {
		return merchant_code;
	}
	public void setMerchant_code(String merchant_code) {
		this.merchant_code = merchant_code;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	 
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getCreator() {
		return creator;
	}
	public void setCreator(long creator) {
		this.creator = creator;
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
