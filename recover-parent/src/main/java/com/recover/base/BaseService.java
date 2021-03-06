package com.recover.base;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

/**
 * 常用的增删改查  接口
 *@author xiashitao
 *@date 2017年10月31日  
 *@version 1.0
 */
public interface BaseService<T> {
	
	public int save(T t);

	public int add(T t);
	
	public int remove(Object id);
	
	public int edit(T t);
	
	public List<T> list(T t);
	
	public List<T> list(Example exp);
	
	public PageList<T> page(T t, Integer pageIndex, Integer pageSize);
	
	public T get(Object id);
	
}
