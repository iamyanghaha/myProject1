package com.ssm.myProject1.JobInf.service;

import com.ssm.myProject1.object.InputObject;
import com.ssm.myProject1.object.OutputObject;

public interface JobInfService {
	//查询所有新闻
		public void selectAll(InputObject inputObject,OutputObject outputObject) throws Exception;
		//增加新闻
		public void addJobInf(InputObject inputObject,OutputObject outputObject) throws Exception;
		//删除新闻
		public void deleteJobInf(InputObject inputObject,OutputObject outputObject) throws Exception;
			
		public void updateJobInf(InputObject inputObject,OutputObject outputObject) throws Exception;
		
}
