package com.jt.sso.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;

@Table(name="tb_user")   //定义表名
public class User extends BasePojo{
	
	@Id   //主键信息
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	//用户id号
	private String username;	//用户名
	private String password;	//密码  需要将密码进行加密处理
	private String phone;		//电话号码
	private String email;		//表示邮箱地址  邮箱的内容写的是电话号码  为了入库不出错
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
