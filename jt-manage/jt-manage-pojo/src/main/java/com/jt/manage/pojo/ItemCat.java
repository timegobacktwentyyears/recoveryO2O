package com.jt.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jt.common.po.BasePojo;

@Table(name="tb_item_cat")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ItemCat  extends BasePojo{

	@Id		//主键信息
	@GeneratedValue(strategy=GenerationType.IDENTITY)//主键自增
	private Long id; 			//商品分类id号
	private Long parentId ;		//上级分类的id
	private String name;		//商品的名称
	private Integer status;		//1 正常 2删除
	private Integer sortOrder;	//排序号
	private Boolean isParent;		//是否是上级类目 1级类目 或二级类目    tinyInt
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	
	//为了满足EasyUI树形结构，添加get方法
	public String getText(){
		return name;
	}
	
	//如果是上级分类菜单则关闭，否则“open”
	//节点状态 
	public String getState(){
		
		return isParent ? "closed" : "open";
	}
	
}
