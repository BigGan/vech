package com.hdu.controller.hello;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.hdu.config.ExceptionHandler.GlobalDefaultExceptionHandler;
import com.hdu.eneity.Demo;
import com.hdu.model.Message;
import com.hdu.service.DemoService;

@RestController
public class DemoController {
	
	private static Logger log = Logger.getLogger(DemoController.class);
	
	@Resource
	private DemoService demoService;


	@RequestMapping("/test")
	public String test(){
		Demo loaded = demoService.getById(1l);
		System.out.println("loaded="+loaded);
		Demo cached = demoService.getById(1l);
		System.out.println("cached="+cached);
		loaded = demoService.getById(2l);
		System.out.println("loaded2="+loaded);
		return"ok";

	}

	@RequestMapping("/likeName")
	public List<Demo> likeName(String name){
		/*
		 * 第一个参数：第几页;
		 * 第二个参数：每页获取的条数.
		 */
		PageHelper.startPage(1, 2);
		return demoService.likeName(name);
	}
	
	@RequestMapping("/save")
	public Demo save(){
		Demo demo = new Demo();
		demo.setName("海杰hhASD");
		demoService.save(demo);
		return demo;
	}
	
	@RequestMapping("/query")
	public Message query(){
		return 	demoService.getDemo().toMessage();
	}
	
}
