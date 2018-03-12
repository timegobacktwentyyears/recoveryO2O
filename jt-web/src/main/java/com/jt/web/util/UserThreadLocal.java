package com.jt.web.util;

import com.jt.web.pojo.User;

public class UserThreadLocal {

	/*
	 * 1泛型内容，主要看以后存储的数据，如果需要存储多个数据 需要添加map
	 * 2.如果以后只需要单个数据，则写单个数据泛型即可
	 */
	private static ThreadLocal<User> threadLocal=new ThreadLocal<User>();
	
	public static User getUser() {
		return threadLocal.get();
	}
	public static  void setUser(User user){
		threadLocal.set(user);
	}
}
