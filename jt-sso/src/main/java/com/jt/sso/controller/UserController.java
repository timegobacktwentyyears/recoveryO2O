package com.jt.sso.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	private ObjectMapper objectMapper= new ObjectMapper();
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	/**
	 * 1.url http://sso.jt.com/user/check/admin12345/1&callback=jsonp1510
	 * 2.参数的类型   可选参数1 username、2 phone、3 email
	 * 3.用户名/电话/邮箱的校验规则????
	 * 	 SELECT COUNT(*) FROM tb_user WHERE username = 'admin123'
		 SELECT COUNT(*) FROM tb_user WHERE phone = '177777777'
         SELECT COUNT(*) FROM tb_user WHERE email = '177777777'
	 * @return
	 */
	
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object findCheckUser(@PathVariable String param,@PathVariable int type,String callback){
		
		//调用业务逻辑方法 返回查询的结果   true表示已经存入    false表示可以使用
		Boolean checkResult = userService.findCheckUser(param,type);
		
		//定义返回值结果
		SysResult sysResult = SysResult.oK(checkResult);
		
		//将数据通过JSON串的形式返回给   JSONP请求
		MappingJacksonValue jacksonValue = new MappingJacksonValue(sysResult);
		
		//JSONP 需要添加方法名称
		jacksonValue.setJsonpFunction(callback);
		
		return jacksonValue;
	}
	
	
	//前台调用注册机制  SSO的Controller  /user/register
	@RequestMapping("/register")
	@ResponseBody   
	public SysResult doRegister(User user){
		
		try {
			//将用户入库操作后,将用户名返回
			String username = userService.saveUser(user);
			return SysResult.oK(username);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return SysResult.build(201, "用户新增失败");
		}
		
	}
	
	
	//前台调用单点的登陆操作
	
	@RequestMapping("/login")
	@ResponseBody
	public SysResult login(@RequestParam("u")String username,
			@RequestParam("p")String password){
		
		//查询获取用户的ticket
		String ticket = userService.findLogin(username,password);
		
		if(StringUtils.isEmpty(ticket)){
			//根据用户名和密码查询失败
			return SysResult.build(201, "用户名或密码错误");
		}else{
			//ticket中有数据
			return SysResult.oK(ticket);
		}
		
	}
	
	//http://sso.jt.com/user/query/_ticket
	@RequestMapping("/query/{_ticket}")
	@ResponseBody
	public Object query(@PathVariable("_ticket")String _ticket,String callback){
		
		//判断redis中是否存有该ticket
		if(jedisCluster.exists(_ticket)){
			try {
				String JSONResult=jedisCluster.get(_ticket);
				
				MappingJacksonValue jacksonValue=new MappingJacksonValue(SysResult.oK(JSONResult));
				jacksonValue.setJsonpFunction(callback);
				return jacksonValue;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return null;
			}
			
		}else{
			return null;
		}
	}
	
	
	
	
	
	
	
	

}
