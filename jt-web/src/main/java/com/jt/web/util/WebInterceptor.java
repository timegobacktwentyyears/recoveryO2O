package com.jt.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.util.CookieUtils;
import com.jt.web.pojo.User;

import redis.clients.jedis.JedisCluster;

//定义springMVC中的拦截器
//登录验证拦截器
public class WebInterceptor implements HandlerInterceptor{

	@Autowired
	private JedisCluster jedisCluster;
	private static final ObjectMapper objectMapper=new ObjectMapper();
	private UserThreadLocal userThreadLocal=new UserThreadLocal();
	/**
	 * 请求执行之前拦截的操作
	 */
	//在往订单和购物车系统跳的时候，先判断如果radis中没有存有该ticket的话就会转到登录页面进行登录
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/* 1.先获取ticket信息
		 * 2.获取userJSON串
		 * 3.将数据转化为User对象
		 */
		String ticket=CookieUtils.getCookieValue(request, "JT_TICKET");
		if(!StringUtils.isEmpty(ticket)){
			String userJSON=jedisCluster.get(ticket);
			if(!StringUtils.isEmpty(userJSON)){
				//如果jedis中获取ticket信息时不为空
				User user = objectMapper.readValue(userJSON, User.class);
				
				//将数据保存到threadlocal中
				UserThreadLocal.setUser(user);
				
				return true;//表示拦截器放行
			}
		}
		//转向用户登录界面
		response.sendRedirect("/user/login.html");
		return false;//表示不能访问到程序指定的页面，应该按照设定进行跳转
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
