package com.jt.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jt.web.pojo.Item;
import com.jt.web.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String toSearch(@RequestParam("q")String keyWord,Model model){
		
		try {
			//接收get方式过来的，并转码
			keyWord=new String(keyWord.getBytes("ISO8859-1"),"utf-8");
			
			//根据关键字查询数据库信息，将集合返回
			List<Item>itemList=searchService.findItemListByKey(keyWord);
			
			//准备页面数据  将查询的关键字返回给页面
			model.addAttribute("query", keyWord);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return "/search";
	}
}
