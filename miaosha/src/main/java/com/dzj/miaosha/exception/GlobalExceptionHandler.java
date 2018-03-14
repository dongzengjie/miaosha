package com.dzj.miaosha.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzj.miaosha.controller.UserLoginController;
import com.dzj.miaosha.emuns.GlobalEnums;
import com.dzj.miaosha.result.Result;

/**
 * 全局异常处理器
 * @author DZJ
 *
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler  {
	private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(value=Exception.class)
	public Result<String> exceptionHandler(HttpServletRequest request,Exception e){
		log.error(e.getMessage());
		if(e instanceof MiaoshaException) {
			MiaoshaException ex = (MiaoshaException) e;
			return Result.error(ex.getMessage(),ex.getCode());
		}else if(e instanceof BindException) {
			BindException  bex= (BindException) e;
			List<ObjectError> errors = bex.getAllErrors();
			ObjectError error = errors.get(0);
			String msg = error.getDefaultMessage();
			return Result.error("参数绑定异常："+ msg,-1112);
		}else {
			return Result.error(GlobalEnums.Server_ERROR.getStateInfo(),GlobalEnums.Server_ERROR.getState());
		}
		
		
	}
}
