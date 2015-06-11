package com.iuni.dp.service.common.exception;

/**
 * 参数异常
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public class ParameterException extends RuntimeException{

	private static final long serialVersionUID = -6958540330091444885L;

	public ParameterException(String msg){
        super(msg);
    }

    public ParameterException(String msg, Throwable t){
        super(msg, t);
        setStackTrace(t.getStackTrace());
    }
}