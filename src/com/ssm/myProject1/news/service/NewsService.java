package com.ssm.myProject1.news.service;

import com.ssm.myProject1.object.InputObject;
import com.ssm.myProject1.object.OutputObject;

public interface NewsService {
	//查询所有新闻
	public void selectAllNews(InputObject inputObject,OutputObject outputObject) throws Exception;
	//增加新闻
	public void addNews(InputObject inputObject,OutputObject outputObject) throws Exception;
	//删除新闻
	public void deleteNews(InputObject inputObject,OutputObject outputObject) throws Exception;
	
	
	
	//test
	public void test(InputObject inputObject,OutputObject outputObject) throws Exception;
	//查找某用户
	public void test1(InputObject inputObject,OutputObject outputObject) throws Exception;
}
