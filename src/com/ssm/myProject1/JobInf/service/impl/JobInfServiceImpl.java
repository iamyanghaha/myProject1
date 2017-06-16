package com.ssm.myProject1.JobInf.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.ssm.myProject1.JobInf.dao.JobInfMapper;
import com.ssm.myProject1.object.InputObject;
import com.ssm.myProject1.object.OutputObject;
import com.ssm.service.JobInfService;

@Service
public class JobInfServiceImpl implements JobInfService{
	@Resource
	private JobInfMapper jobInfMapper; 
	
	@Override
	public void selectAll(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		Map<String, Object> map = inputObject.getParams();
		
		List<Map<String,Object>> allNews = jobInfMapper.selectAllNews();
		outputObject.setBeans(allNews);
		outputObject.setreturnCode(0);
		outputObject.setreturnMessage("成功");
	}

	@Override
	public void addJobInf(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		Map<String, Object> map = inputObject.getParams();
		
		jobInfMapper.addJobInf(map);
		outputObject.setreturnCode(0);
		outputObject.setreturnMessage("添加成功");
		
	}

	@Override
	public void deleteJobInf(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		Map<String, Object> map = inputObject.getParams();
		
		jobInfMapper.deleteJobInf(map);
		System.out.println("===============delete====  " + map);
		outputObject.setreturnCode(0);
		outputObject.setreturnMessage("删除成功");
		
	}

	@Override
	public void updateJobInf(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		Map<String, Object> map = inputObject.getParams();
		
		jobInfMapper.updateJobInf(map);
		
		outputObject.setreturnCode(0);
		outputObject.setreturnMessage("添加成功");
			
	}

	@Override
	public void test(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void test1(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
