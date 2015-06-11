package com.iuni.dp.service.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iuni.dp.persist.common.constants.TypeEnum;
import com.iuni.dp.service.common.bean.Result;

/**
 * 异常处理Processor
 * 返回结果对象
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public class ExceptionProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionProcessor.class);
	
	/**
	 * 参数异常处理
	 * @Title: getParameterExceptionResult 
	 * @param   
	 * @return Result    返回类型 
	 * @throws
	 */
	public static Result getParameterExceptionResult(ParameterException e){
		Result result = new Result();
		result.setState(Result.FAIL);
		result.setErrorCode(TypeEnum.BusiExcEnum.PARAMETER_EXCEPTION.getKey());
		result.setErrorMessage(e.getMessage());
		logger.error("parameter exception:"+result.getErrorMessage());
		return result;
	}
	
	/**
	 * 业务异常处理
	 * @Title: getBusinessExceptionResult 
	 * @param   
	 * @return Result    返回类型 
	 * @throws
	 */
	public static Result getBusinessExceptionResult(BusinessException e){		
		String code = e.getCode();
		String msg = e.getMessage();
		Result result = new Result();
		result.setState(Result.FAIL);
		if(StringUtils.isNotBlank(code)){
			result.setErrorCode(code);
			if(StringUtils.isNotBlank(msg)){
				result.setErrorMessage(msg);
			}
		}else{
			result.setErrorCode(TypeEnum.BusiExcEnum.BUSINESS_EXCEPTION.getKey());
			result.setErrorMessage(msg);
		}			
		
		logger.error("business exception:"+result.getErrorMessage());
		return result;
	}
	
	/**
	 * 系统异常处理
	 * @Title: getExceptionResult 
	 * @param   
	 * @return Result    返回类型 
	 * @throws
	 */
	public static Result getExceptionResult(Exception e){
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
	    Result result = new Result();
		result.setState(Result.FAIL);
		result.setErrorCode(TypeEnum.BusiExcEnum.SYSTEM_EXCEPTION.getKey());
		result.setErrorMessage(sw.toString());
		logger.error("system exception:"+sw.toString());
		return result;
	}
	
	public static Result getExceptionResult(ParameterException e){
		Result result = new Result();
		result.setState(Result.FAIL);
		result.setErrorCode(TypeEnum.BusiExcEnum.PARAMETER_EXCEPTION.getKey());
		result.setErrorMessage(e.getMessage());
		logger.error("parameter exception:"+result.getErrorMessage());
		return result;
	}

}
