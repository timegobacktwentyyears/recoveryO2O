package com.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.web.pojo.Item;
import com.jt.web.service.ItemService;
import com.jt.web.service.ItemServiceImpl;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	//根据商品的id查询商品信息
	@RequestMapping("/items/{itemId}")
	public String findItemById(@PathVariable Long itemId,Model model){
		
		Item item = itemService.findItemById(itemId);
		System.out.println("成功获取后台数据"+item.getImage());
		model.addAttribute("item", item);
		//获取数据后应该跳转到商品详细信息页面
		return "item";
	}

}
