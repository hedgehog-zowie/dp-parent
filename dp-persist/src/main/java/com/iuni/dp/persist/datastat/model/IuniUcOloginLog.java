package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * IUNI用户中心第三方联合登录日志
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.5
 */
public class IuniUcOloginLog implements Serializable {

	private static final long serialVersionUID = 1884494082582767232L;

	/**
	 * 第三方联合登录日志ID
	 */
	private Long id;
	
	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 登陆结果( 0.成功 1用户验证失败 2系统异常)
	 */
	private String loginResult;
	
	/**
	 * 外部登录类型
	 */
	private String oType;
	
	/**
	 * 是否绑定登陆(1:绑定方式登陆 0:非绑定方式登陆)
	 */
	private Boolean isBind;
	
	/**
	 * 登陆IP
	 */
	private String loginIp;
	
	/**
	 * 登录时间
	 */
	private Timestamp loginTime;

	public IuniUcOloginLog() {
		super();
	}

	public IuniUcOloginLog(String userId, String loginResult, String oType,
			Boolean isBind, String loginIp, Timestamp loginTime) {
		super();
		this.userId = userId;
		this.loginResult = loginResult;
		this.oType = oType;
		this.isBind = isBind;
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

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}

	public String getoType() {
		return oType;
	}

	public void setoType(String oType) {
		this.oType = oType;
	}

	public Boolean getIsBind() {
		return isBind;
	}

	public void setIsBind(Boolean isBind) {
		this.isBind = isBind;
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
		return "IuniUcOloginLog [userId=" + userId + ", loginResult="
				+ loginResult + ", oType=" + oType + ", isBind=" + isBind
				+ ", loginIp=" + loginIp + ", loginTime=" + loginTime + "]";
	}
	
}
