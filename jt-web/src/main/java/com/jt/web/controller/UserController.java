package com.jt.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.util.CookieUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JedisCluster jedisCluster;
	///user/register  转向注册页面
	@RequestMapping("/register")
	public String register(){
		
		return "register";
	}
	
	// /user/login.html
	@RequestMapping("/login")
	public String login(){
		
		return "login";
	}
	
	//SpringMVC只要拦截请求,会找到最接近的请求路径与之匹配
	//http://www.jt.com/service/被springMVC拦截     user/doRegister
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult doRegister(User user){
		
		SysResult sysResult = userService.doRegister(user);
		return sysResult;
	}
	
	
	/**
	 * 用户的登陆操作
	 * 1.判断数据是否为空
	 * 2.调用service进行查询操作
	 * 3.返回结果集
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult doLogin(String username,String password,HttpServletRequest request,
			HttpServletResponse response){
		
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			return SysResult.build(201, "用户名或密码不能为空");
		}
		
		SysResult  result =  userService.doLogin(username,password);
		
		String ticket = (String) result.getData();
		CookieUtils.setCookie(request, response, "JT_TICKET", ticket);
		
		//通过request中直接获取cookie对象也可以
		return result;
		
	}
	
	
	//登出操作
	@RequestMapping("/logout")
	public String logOut(HttpServletRequest request,HttpServletResponse response){
		
		String cookieName="JT_TICKET";
		//删除cookie
		CookieUtils.deleteCookie(request, response, "JT_TICKET");
		
		//删除redis中的ticket
		String key = CookieUtils.getCookieValue(request, cookieName);
		jedisCluster.del(key);
		
		//返回值结果应该是伪静态的
		return "redirect:/index.html";
	}
	
	
	
	
	
	
	
	
}
