package com.iuni.dp.admin.dataalarm.vo;

/**
 * 接收人信息VO类
 * @author CaiKe
 * @version V1.0.0
 */
public class ReceivePersonVO {

	private String code;
	
	private String name;
	
	private String mobile;
	
	private String email;
	
	private String beginDate;
	
	private String endDate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return "ReceivePersonVO [code=" + code + ", name=" + name + ", mobile="
				+ mobile + ", email=" + email + ", beginDate=" + beginDate
				+ ", endDate=" + endDate + "]";
	}
	
}
