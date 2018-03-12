package com.jt.manage.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	///page/item-add  使用restFul结构进行简化
	///page/item-edit
	@RequestMapping("/page/{module}")
	public String index(@PathVariable String module){
		
		//跳转到系统首页
		return module;
	}

}
