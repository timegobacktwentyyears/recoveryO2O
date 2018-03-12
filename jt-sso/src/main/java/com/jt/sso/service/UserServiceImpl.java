package com.jt.sso.service;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;

import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * SELECT COUNT(*) FROM tb_user WHERE username = 'admin123'
	   SELECT COUNT(*) FROM tb_user WHERE phone = '177777777'
       SELECT COUNT(*) FROM tb_user WHERE email = '177777777'
       
       boolean  true 已经存入  false 可以使用
	 */
	@Override
	public Boolean findCheckUser(String param, int type) {
		
		String cloumn = null;
		
		switch (type) {
		case 1:
			cloumn = "username";
			break;
		case 2:
			cloumn = "phone";
			break;
		case 3:
			cloumn = "email";
			break;
		}
		int countNum = userMapper.findCheckUser(cloumn,param);
		
		// boolean  true 已经存入  false 可以使用
		
		return countNum > 0 ? true : false;
	}

	@Override
	public String saveUser(User user) {
		
		//准备数据
		//1.将密码进行MD5加密      通过工具类进行加密
		String md5Password = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5Password);
		user.setEmail(user.getPhone()); //为了防止null数据 引入电话
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		
		userMapper.insert(user);
		
		//将用户名直接返回
		return user.getUsername();
	}
	
	
	@Override //要求返回一个ticket
	public String findLogin(String username, String password) {
		
		//为了实现查询,将密码进行加密处理
		String md5Password = DigestUtils.md5Hex(password);
		
		User user = userMapper.findUserByU_P(username, md5Password);
		//证明用户名和密码正确
		try {
			if(user != null){
				String temp = "JT_TICKET_"+System.currentTimeMillis()+username;
				String ticket = DigestUtils.md5Hex(temp);
				String userJSON = objectMapper.writeValueAsString(user);
				
				jedisCluster.set(ticket, userJSON); //将数据存入redis中
				return ticket;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	

}
