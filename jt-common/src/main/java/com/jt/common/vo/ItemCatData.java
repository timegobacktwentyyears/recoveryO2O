package com.jt.common.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
//经过分析，该对象是一级菜单和二级菜单的包装对象  不适用于三级菜单
public class ItemCatData {
	//序列化成json数据时为 u
	//为什么不使用全名，url name items  是为了在网络传输中减少数据量
	@JsonProperty("u")
	private String url;
	
	@JsonProperty("n")
	private String name;
	
	@JsonProperty("i")
	private List<?> items;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<?> getItems() {
		return items;
	}
	public void setItems(List<?> items) {
		this.items = items;
	}
}
