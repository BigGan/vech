package com.hdu.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public String defaultExceptionHandler(HttpServletRequest req, Exception e)  {
		return "对不起，服务器繁忙.....";
	}
}
