package com.jt.sso.mapper;

import org.apache.ibatis.annotations.Param;

import com.jt.common.mapper.SysMapper;
import com.jt.sso.pojo.User;

public interface UserMapper extends SysMapper<User> {

	public int findCheckUser(@Param("cloumn")String cloumn,@Param("param")String param);
	
	//根据用户名和密码查询用户信息
	public User findUserByU_P(@Param("username")String username,@Param("password")String password);
	
}
