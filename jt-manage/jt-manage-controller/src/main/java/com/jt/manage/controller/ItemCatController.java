package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	//如果页面初始化时,应该展现全部的一级菜单  
	@RequestMapping("/list")
	@ResponseBody  //将数据转化为JSON串  调用对象的get方法形成JSON串
	public List<ItemCat> findItemCatList(@RequestParam(defaultValue="0")Long id){
		//id号其实就是上级Id
		return itemCatService.findItemCatList(id);
	}
	
	
	
	
	
}
