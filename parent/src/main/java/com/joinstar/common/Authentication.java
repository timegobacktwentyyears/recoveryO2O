package com.joinstar.common;

import java.util.List;

/**
 * token中的内容
 *@author xiashitao
 *@date 2017年11月14日 上午9:20:57 
 *@version 1.0
 */
public class Authentication {
	
	private Long userId;
	
	private List<Long> userRoles;
	
	private Long platformId;
	private Long orgId;
	private String roleNames;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getRoleNames() {
		return roleNames;
	}
	
	
	
	public List<Long> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<Long> userRoles) {
		this.userRoles = userRoles;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	
}

