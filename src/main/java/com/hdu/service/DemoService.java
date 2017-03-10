package com.hdu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hdu.mapper.DemoMapper;
import com.hdu.model.Demo;
import com.hdu.model.ResultModel;


public interface DemoService {

	public List<Demo> likeName(String name);
	
	public void save(Demo demo);
	
	public ResultModel getDemo();
}
