package com.recover.common;

public class UserToken implements java.io.Serializable {
	
	private static final long serialVersionUID = 4250323558709324012L;
	private Long id;
	private String username;
	private String orgIds;
	
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
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	
}
