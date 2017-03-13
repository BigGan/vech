package com.hdu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义 静态资源访问路径
 * @author ghj
 */
@Configuration
public class WebAppConfiguration   extends WebMvcConfigurerAdapter {
	
		@Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/st/**").addResourceLocations("classpath:/static/");
	       /*  
	       * 如果我们将/myres/* 修改为 /* 与默认的相同时，则会覆盖系统的配置，
	       * 可以多次使用 addResourceLocations 添加目录，优先级先添加的高于后添加的。
                                其中 addResourceLocations 的参数是动参，
                                可以这样写 addResourceLocations(“classpath:/img1/”, “classpath:/img2/”, “classpath:/img3/”);
	       */
	        //st是自定义访问路径  addResourceLocations的话就是资源的路径
	        //registry.addResourceHandler("/st/**").addResourceLocations("file:D:/ghj.jpg");
	        super.addResourceHandlers(registry);
	    }
}
