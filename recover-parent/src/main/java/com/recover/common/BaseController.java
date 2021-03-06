package com.recover.common;

import java.util.List;

/**
 * 公用的controler 获取用户的相关信息
 *@author xiashitao
 *@date 2017年11月2日 上午10:29:58 
 *@version 1.0
 */
public abstract class BaseController {
	
	
	public Long getUserId() {
		return SecurityContext.getContext().getUserId();
	}
	
	public List<Long> getUserRoles() {
		return SecurityContext.getContext().getUserRoles();
	}
	
	public Long getPlatformId() {
		return SecurityContext.getContext().getPlatformId();
	}
	
	public Long getOrgId() {
		return SecurityContext.getContext().getOrgId();
	}
	
	public String getRoleNames() {
		return SecurityContext.getContext().getRoleNames();
	}
	
	public boolean hasRole(String roleName) {
		return SecurityContext.getContext().getRoleNames().contains(roleName);
	}
		
}