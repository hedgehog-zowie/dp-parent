package com.iuni.dp.service.common.exception;

/**
 * DB操作业务异常类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public class DBException extends RuntimeException {

	private static final long serialVersionUID = 6328920766455624093L;
	
	public DBException(String message) {
		super(message);
	}
	
	public DBException(Throwable cause) {
		super(cause);
	}
	
	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

}
