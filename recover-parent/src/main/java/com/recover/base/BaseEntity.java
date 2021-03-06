package com.recover.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 主键ID
 *@author xiashitao
 *@date 2017年10月31日  
 *@version 1.0
 */
public abstract class BaseEntity implements java.io.Serializable {
	
	
	private static final long serialVersionUID = 6807996517218108170L;
	@Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
