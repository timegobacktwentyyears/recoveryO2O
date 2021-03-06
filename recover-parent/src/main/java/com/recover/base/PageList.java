package com.recover.base;

import java.util.List;

import com.github.pagehelper.Page;


/**
 * 分页集合
 *@author xiashitao
 *@date 2017年10月31日  
 *@version 1.0
 */
public class PageList<T> implements java.io.Serializable {

	
	
	private static final long serialVersionUID = -9181780985867587618L;
	
	private Integer pageIndex;//当前第几页
	private Integer pageSize;//每页大小
	private Long total;//总记录数
	private List<T> rows;
	
	public PageList() {
		
	}
	
	public PageList(List<T> list) {
		if(list instanceof Page) {
			Page page = (Page)list;
			this.pageIndex = page.getPageNum();
			this.pageSize = page.getPageSize();
			this.total = page.getTotal();
			this.rows = list;
		}
	}

	public Integer getPageIndex() {
		return pageIndex;
	}



	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}



	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
	
}
