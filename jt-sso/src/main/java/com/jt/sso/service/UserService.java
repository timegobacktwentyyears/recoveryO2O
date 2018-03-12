package com.jt.sso.service;

import com.jt.sso.pojo.User;

public interface UserService {

	public Boolean findCheckUser(String param, int type);
	
	//入库操作
	public String saveUser(User user);
	
	//用户的登陆操作
	public String findLogin(String username, String password);

}
