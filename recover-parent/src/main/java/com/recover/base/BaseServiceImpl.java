package com.recover.base;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * 常用的增删改查  实现类
 *@author xiashitao
 *@date 2017年10月31日  
 *@version 1.0
 */
public abstract class BaseServiceImpl <T extends BaseEntity> implements BaseService<T> {

	@Autowired
	protected Mapper<T> mapper;
	
	private static final int PAGE_INDEX = 1;
	private static final int PAGE_SIZE = 20;
	
	public Mapper<T> getMapper() {
		return mapper;
	}

	@Override
	public int save(T entity) {
		Long id = entity.getId();
		if(id == null) {
			return mapper.insert(entity);	
		} else {
			return mapper.updateByPrimaryKey(entity);
		}
	}

	@Override
	public int add(T entity) {
		return mapper.insert(entity);
	}

	@Override
	public int remove(Object id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int edit(T entity) {
		return mapper.updateByPrimaryKey(entity);
	}

	@Override
	public List<T> list(T t) {
		if(t != null) {
			return mapper.select(t);
		} else {
			return mapper.selectAll();
		}
	}
	
	@Override
	public List<T> list(Example exp) {
		if(exp != null) {
			return mapper.selectByExample(exp);
		} else {
			return mapper.selectAll();
		}
	}

	@Override
	public PageList<T> page(T t, Integer pageIndex, Integer pageSize) {
		startPage(pageIndex, pageSize);
		if(t != null) {
			return new PageList<T>(mapper.select(t));
		} else {
			return new PageList<T>(mapper.selectAll());
		}
	}

	protected PageList<T> page(Example exp, Integer pageIndex, Integer pageSize) {
		startPage(pageIndex, pageSize);
		if(exp != null) {
			return new PageList<T>((Page<T>)mapper.selectByExample(exp));
		} else {
			return new PageList<T>((Page<T>)mapper.selectAll());
		}
	}

	@Override
	public T get(Object id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	protected void startPage(Integer pageIndex, Integer pageSize) {
		if(pageIndex == null) {
			pageIndex = PAGE_INDEX;
		}
		if(pageSize == null) {
			pageSize = PAGE_SIZE;
		}
		PageHelper.startPage(pageIndex, pageSize);
	}

}
