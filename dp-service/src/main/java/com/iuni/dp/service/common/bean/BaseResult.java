package com.iuni.dp.service.common.bean;

import java.io.Serializable;

/**
 * 基本返回结果对象Bean
 * 
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public class BaseResult implements Serializable {

	private static final long serialVersionUID = -6765372531760260268L;

	/**
	 * 返回的数据
	 */
	private Object data;
	
	/**
	 * 执行结果状态 1:成功 0：失败
	 */
	protected String state;

	/**
	 * 返回的错误码
	 */
	protected String errorCode;

	/**
	 * 返回的错误消息
	 */
	protected String errorMessage;

	/**
	 * 接口操作成功
	 */
	public static final String SUCCESS = "1";

	/**
	 * 接口操作失败
	 */
	public static final String FAIL = "0";

	public BaseResult() {

	}

	public BaseResult(String state, String errorCode, String errorMessage) {
		this.state = state;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public BaseResult(Object data, String state, String errorCode,
			String errorMessage) {
		super();
		this.data = data;
		this.state = state;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "BaseResult [state=" + state + ", errorCode=" + errorCode
				+ ", errorMessage=" + errorMessage + "]";
	}

}
