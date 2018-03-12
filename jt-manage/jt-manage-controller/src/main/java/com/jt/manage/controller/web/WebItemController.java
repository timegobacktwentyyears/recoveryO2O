package com.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.pojo.Item;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/web")
public class WebItemController {
	
	@Autowired
	private ItemService itemService;
	
	//url :http://manage.jt.com/web/item/"+itemId
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public Item findItemById(@PathVariable Long itemId){
		
		System.out.println("后台程序调用成功");
		return itemService.findItemById(itemId);
	}
	
	
	
}
