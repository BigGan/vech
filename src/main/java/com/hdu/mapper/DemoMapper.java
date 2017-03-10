package com.hdu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hdu.eneity.Demo;

public interface DemoMapper {
	//#{name}:参数占位符
	@Select("select *from Demo where name=#{name}")
	public List<Demo> likeName(String name);
	
	@Select("select *from Demo where id = #{id}")
	public Demo getById(long id);
	
	@Select("select name from Demo where id = #{id}")
	public String getNameById(long id);

	//@Select("select * from Demo")
	public List<Demo> getNameDemo();
	
	/**
	 * 保存数据.
	 */
	@Insert("insert into Demo(name) values(#{name})")
	@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
	public void save(Demo demo);
}
