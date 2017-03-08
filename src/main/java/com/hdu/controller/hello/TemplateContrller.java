package com.hdu.controller.hello;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templates")
public class TemplateContrller {

	@RequestMapping("/helloFtl")
	public String helloFtl(Map<String,Object> map){
		map.put("hello","from TemplateController.helloFtl");
		return "/hello";
	}


}
