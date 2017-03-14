package com.hdu;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;


@SpringBootApplication
@ComponentScan(basePackages={"com.ghj","com.hdu"})
public class App 
{
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}
	
	
	/**
	 * 对文件做一些限制
	 * @return
	 */
	@Bean 
    public MultipartConfigElement multipartConfigElement() { 
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
        factory.setMaxFileSize("128KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("256KB"); 
        //Sets the directory location where files will be stored.
        //factory.setLocation("路径地址");
        return factory.createMultipartConfig(); 
    } 
	
	public static void main( String[] args )
    {
		/**
		 * 不设置banner
		 */
		//SpringApplication.run(App.class,args);
		
		/**
		 * 关闭
		 *     .   ____          _            __ _ _  
		   /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \  
		  ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \  
		   \\/  ___)| |_)| | | | | || (_| |  ) ) ) )  
		    '  |____| .__|_| |_|_| |_\__, | / / / /  
		   =========|_|==============|___/=/_/_/_/  
		   :: Spring Boot ::        (v1.3.3.RELEASE)  
		 */
		SpringApplication application = new SpringApplication(App.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
        
    }
}
