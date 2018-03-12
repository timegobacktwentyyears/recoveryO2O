package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private HttpClientService httpClientService;
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	//工具类  只实例化一次 不允许别人修改
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	
	//httpClient不能直接传递对象. 只能传递string字符串等集合数据
	public SysResult doRegister(User user) {
		Map<String, String> userMap = new HashMap<String,String>();
		userMap.put("username", user.getUsername());
		userMap.put("password", user.getPassword());
		userMap.put("phone", user.getPhone());
		userMap.put("email", user.getEmail());
		
		//定义远程代用的url
		String url = "http://sso.jt.com/user/register";
		
		try {
			String sysResultJSON = httpClientService.doPost(url,userMap);
			
			//想获取JSON串中的数据data---知识扩展
			JsonNode jsonNode = objectMapper.readTree(sysResultJSON);
			
			//表示将数据转化为String 类型
			String data = jsonNode.get("data").asText();
			System.out.println("从JSON传中获取数据为:"+data);
			
			return objectMapper.readValue(sysResultJSON, SysResult.class);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			
			return SysResult.build(201, "用户注册失败"); 
		}
	}


	public SysResult doLogin(String username, String password) {
		
		//请求的地址
		String url = "http://sso.jt.com/user/login";
		
		Map<String, String> userMap = new HashMap<String,String>();
		userMap.put("u", username);
		userMap.put("p", password);
		
		try {
			String sysresultJSON = httpClientService.doPost(url, userMap);
			
			return objectMapper.readValue(sysresultJSON, SysResult.class);	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "用户登陆失败");
		}
		
	}

}
