package com.ssm.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;




public class CommonInterceptor extends HandlerInterceptorAdapter {
    private static final String[] IGNORE_URI = {"/login.html", "/Login/","/user/loginCheck","/user/insert"};
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        String url = request.getRequestURL().toString();
        System.out.println(">>>: " + url);
        for (String s : IGNORE_URI) {
            if (url.contains(s)) {
            	System.out.println("非拦截");
                flag = true;
                break;
            }
        }
        if (!flag) {
        	Map user =  (Map) request.getSession().getAttribute("user");
        if(user == null){
        	System.out.println(user);
        	request.getRequestDispatcher("/login.html").forward(request, response);
        	return false;
        }
        }
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
