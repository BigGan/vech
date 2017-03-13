package com.hdu.controller.hello;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdu.config.ExceptionHandler.GlobalDefaultExceptionHandler;

@Controller
public class TemplateTController {
	/**
	 * 返回html模板.
	 */
	private static Logger log = Logger.getLogger(TemplateTController.class);
	
	@RequestMapping("/helloHtml")
	public String helloHtml(Map<String,Object> map){
		log.info("log4j测试");
		map.put("hello","from TemplateController.helloHtml");
		return "/hello";
	}
}
