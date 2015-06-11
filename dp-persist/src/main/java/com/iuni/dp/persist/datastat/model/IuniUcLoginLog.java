package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * IUNI UC Login Log Model
 * @author Kenneth.Cai@iuni.com
 * dp-persist-1.1.4
 */
public class IuniUcLoginLog implements Serializable {

	private static final long serialVersionUID = -1147902767605069182L;

	/**
	 * 用户登陆日志ID
	 */
	private Long id;
	
	/**
	 * 登陆用户ID
	 */
	private String userId;
	
	/**
	 * 登陆用户名
	 */
	private String userName;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 电子邮箱
	 */
	private String email;
	
	/**
	 * 登陆结果(0.成功 1.用户名或密码错误 2.验证码错误 3.系统异常)
	 */
	private String loginResult;
	
	/**
	 * 登陆的错误次数
	 */
	private Integer loginError;
	
	/**
	 * 登录来源(w:电脑端 c:手机客户端)
	 */
	private String loginFrom;
	
	/**
	 * 登录IP
	 */
	private String loginIp;
	
	/**
	 * 登录时间
	 */
	private Timestamp loginTime;

	public IuniUcLoginLog() {
		super();
	}

	public IuniUcLoginLog(String userId, String userName, String mobile,
			String email, String loginResult, Integer loginError,
			String loginFrom, String loginIp, Timestamp loginTime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.mobile = mobile;
		this.email = email;
		this.loginResult = loginResult;
		this.loginError = loginError;
		this.loginFrom = loginFrom;
		this.loginIp = loginIp;
		this.loginTime = loginTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}

	public Integer getLoginError() {
		return loginError;
	}

	public void setLoginError(Integer loginError) {
		this.loginError = loginError;
	}

	public String getLoginFrom() {
		return loginFrom;
	}

	public void setLoginFrom(String loginFrom) {
		this.loginFrom = loginFrom;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	@Override
	public String toString() {
		return "IuniUcLoginLog [userId=" + userId + ", userName=" + userName
				+ ", mobile=" + mobile + ", email=" + email + ", loginResult="
				+ loginResult + ", loginError=" + loginError + ", loginFrom="
				+ loginFrom + ", loginIp=" + loginIp + ", loginTime="
				+ loginTime + "]";
	}

}
