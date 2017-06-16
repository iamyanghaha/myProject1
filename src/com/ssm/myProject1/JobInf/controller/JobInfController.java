package com.ssm.myProject1.JobInf.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.myProject1.object.InputObject;
import com.ssm.myProject1.object.OutputObject;
import com.ssm.service.JobInfService;

@Controller
public class JobInfController {
	@Resource
	private JobInfService jobInfService;
	
	/**
	 * 功能：查询所有新闻
	 * 参数：pageNum（第几页）， onePageCount（一页有多少条内容）
	 */
	@RequestMapping("/post/jobInf/selectAll")
	@ResponseBody
	public void selectAll(InputObject inputObject,OutputObject outputObject) throws Exception{ 
		jobInfService.selectAll(inputObject, outputObject);	
	}
	
	/**
	 * 功能：增加新闻
	 * 参数：新闻标题，内容，时间
	 */
	@RequestMapping("/post/jobInf/addJobInf")
	@ResponseBody
	public void addJobInf(InputObject inputObject,OutputObject outputObject) throws Exception{ 
		jobInfService.addJobInf(inputObject, outputObject);	
	}
	
	/**
	 * 功能：修改职工信息
	 * 参数：职工id
	 */
	@RequestMapping("/post/jobInf/updateJobInf")
	@ResponseBody
	public void updateJobInf(InputObject inputObject,OutputObject outputObject) throws Exception{ 
		jobInfService.updateJobInf(inputObject, outputObject);	
	}
	
	/**
	 * 功能：删除新闻s
	 * 参数：新闻id
	 */
	@RequestMapping("/post/jobInf/deleteJobInf")
	@ResponseBody
	public void deleteJobInf(InputObject inputObject,OutputObject outputObject) throws Exception{ 
		jobInfService.deleteJobInf(inputObject, outputObject);	
	}
	
}
