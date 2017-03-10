package com.hdu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hdu.eneity.Demo;
import com.hdu.mapper.DemoMapper;
import com.hdu.model.ResultModel;
import com.hdu.model.ReturnModel;
import com.hdu.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService{

	@Resource
	private DemoMapper demoMappper;
	
	public List<Demo> likeName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional//添加事务.
	public void save(Demo demo) {
		demoMappper.save(demo);
	}

	@Override
	public ResultModel getDemo() {
		 ReturnModel returnModel=new ReturnModel();
		 List<Demo> list=demoMappper.getNameDemo();
		 returnModel.setCode(200).setObj(list);
		 return returnModel;
	}

}
