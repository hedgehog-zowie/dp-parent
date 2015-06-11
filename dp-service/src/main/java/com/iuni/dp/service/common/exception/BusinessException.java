package com.iuni.dp.service.common.exception;

/**
 * 业务异常
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = -5290798039864539530L;
	private String code;
	
    public BusinessException(String msg){
        super(msg);
    }
    
    public BusinessException(Throwable t) {
		super(t);
	}
    
    public BusinessException(String code, String msg){
        super(msg);
        this.code = code;
    }
    
    public BusinessException(String msg, Throwable t){
        super(msg, t);
        setStackTrace(t.getStackTrace());
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}