package com.jt.web.service;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;

public interface UserService {

	//实现用户的注册
	public SysResult doRegister(User user);
	
	//用户登陆
	public SysResult doLogin(String username, String password);
	
}
