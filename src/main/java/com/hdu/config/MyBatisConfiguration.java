package com.hdu.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.pagehelper.PageHelper;

@Configuration
@MapperScan("com.hdu.mapper")
public class MyBatisConfiguration {
	
		 @Autowired
		 private Environment env;
	
		 @Bean
	      public DataSource getDataSource() throws Exception{
	          Properties props = new Properties();
	          props.put("driverClassName", env.getProperty("jdbc.driverClassName"));
	          props.put("url", env.getProperty("jdbc.url"));
	          props.put("username", env.getProperty("jdbc.username"));
	          props.put("password", env.getProperty("jdbc.password"));
	          return DruidDataSourceFactory.createDataSource(props);
	      }
		 
		 
		 @Bean
	     public PageHelper pageHelper() {
			System.out.println("MyBatisConfiguration.pageHelper()");
	        PageHelper pageHelper = new PageHelper();
	        Properties p = new Properties();
	        p.setProperty("offsetAsPageNum", "true");
	        p.setProperty("rowBoundsWithCount", "true");
	        p.setProperty("reasonable", "true");
	        pageHelper.setProperties(p);
	        return pageHelper;
	     }
		 

	    @Bean(name = "sqlSessionFactory")
	    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
		    SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		    fb.setDataSource(ds);//指定数据源(这个必须有，否则报错)
		    fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//指定xml文件位置
		    return fb.getObject();
	        }


	
}
