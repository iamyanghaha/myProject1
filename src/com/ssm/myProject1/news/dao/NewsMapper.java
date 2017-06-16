package com.ssm.myProject1.news.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;


public interface NewsMapper {
	
	//查询所有新闻
	//public List<Map<String, Object>> selectAllNews(PageBounds pageBounds) throws Exception;
	public List<Map<String, Object>> selectAllNews() throws Exception;
	
	//增加新闻
	public void addNews(Map<String, Object> map) throws Exception;
	//删除新闻
	public void deleteNews(Map<String, Object> map) throws Exception;
	
	
	//test
	public List<Map<String, Object>> test() throws Exception;
	
	public Map<String, Object> test1(Map<String, Object> map) throws Exception;
	
	
}
