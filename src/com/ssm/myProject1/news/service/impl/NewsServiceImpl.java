package com.ssm.myProject1.news.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.ssm.myProject1.news.dao.NewsMapper;
import com.ssm.myProject1.news.service.NewsService;
import com.ssm.myProject1.object.InputObject;
import com.ssm.myProject1.object.OutputObject;

@Service
public class NewsServiceImpl implements NewsService{
	
	@Resource
	private NewsMapper newsMapper;
	
	/*
	 * 查询所有新闻
	 */
	public void selectAllNews(InputObject inputObject, OutputObject outputObject) throws Exception{
		Map<String, Object> map = inputObject.getParams();
		
		//得到分页参数
//		int pageNum = Integer.parseInt((String) map.get("pageNum"));//第几页
//		int onePageCount = Integer.parseInt((String) map.get("onePageCount"));//一页有多少条内容
		
		//调用数据库查新所有新闻
//		List<Map<String,Object>> allNews = newsMapper.selectAllNews(new PageBounds(pageNum, onePageCount, true));
		List<Map<String,Object>> allNews = newsMapper.selectAllNews();
		
		//获取新闻总数
//		PageList<Map<String, Object>> abilityInfoPageList = (PageList<Map<String, Object>>)allNews;
//		int total = abilityInfoPageList.getPaginator().getTotalCount();
		
		//将查询出来的所有新闻放进outputObject
		outputObject.setBeans(allNews);
//		outputObject.settotal(total);
		outputObject.setreturnCode(0);
		outputObject.setreturnMessage("成功");
	}
	
	/*
	 * 增加新闻
	 */
	public void addNews(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		newsMapper.addNews(map);
		
		outputObject.setreturnCode(0);
		outputObject.setreturnMessage("添加成功");
	}
	
	/*
	 * 删除新闻
	 */
	public void deleteNews(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		newsMapper.deleteNews(map);
		System.out.println("===============delete====  " + map);
		outputObject.setreturnCode(0);
		outputObject.setreturnMessage("删除成功");
	}
	
	
	
	
	/*
	 * test
	 */
	public void test(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		List<Map<String, Object>> list = newsMapper.test();
		outputObject.setBeans(list);
		outputObject.setreturnCode(0);
		outputObject.setreturnMessage("成功");
	}
	
	//通过id查找用户
	public void test1(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		//得到输入的参数
		Map<String, Object> map = inputObject.getParams();
		//通过数据库查询
		Map<String, Object> list = newsMapper.test1(map);
		//将查询到的结果放进outputObject（如果是单个信息，则使用setBean方法放入output
		//								如果是多个List<>，则使用setBeans方法放入output）
		outputObject.setBean(list);
		
		//设置成功的代码和信息
		outputObject.setreturnCode(0);
		outputObject.setreturnMessage("成功");
		
	}
	
}
