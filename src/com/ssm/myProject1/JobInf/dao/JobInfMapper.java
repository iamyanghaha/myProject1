package com.ssm.myProject1.JobInf.dao;

import java.util.List;
import java.util.Map;

public interface JobInfMapper {
	//查询所有新闻
			//public List<Map<String, Object>> selectAllNews(PageBounds pageBounds) throws Exception;
			public List<Map<String, Object>> selectAllNews() throws Exception;
			
			//增加新闻
			public void addJobInf(Map<String, Object> map) throws Exception;
			//删除新闻
			public void deleteJobInf(Map<String, Object> map) throws Exception;
			
			public void updateJobInf(Map<String, Object> map) throws Exception;
			
			
			//test
			public List<Map<String, Object>> test() throws Exception;
			
			public Map<String, Object> test1(Map<String, Object> map) throws Exception;
			
}
