package com.jt.manage.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.ItemCatResult;
import com.jt.manage.service.ItemCatService;

@Controller
public class WebItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private static final Logger looger = Logger.getLogger(WebItemCatController.class);
	
	//http://manage.jt.com/web/itemcat/all?callback=category.getDataService

	//1.采用ajax回显的方式现实
	/*@RequestMapping("/web/itemcat/all")
	public void findItemCatAll(String callback,HttpServletResponse response){
		
		//应该通过后台程序查询出全部的商品分类数据
		ItemCatResult itemCatList = itemCatService.findItemCatAll();
		try {
			String JSONResult = objectMapper.writeValueAsString(itemCatList);
			System.out.println(JSONResult);
		
			response.setContentType("text/html;charset=utf-8");
			
			//为了JSONP调用实现回显   hello({id:1,name:tom})
			String resultJSON = callback+"("+JSONResult+")";
			System.out.println(resultJSON);
			response.getWriter().write(resultJSON);	
		} catch (Exception e) {
			e.printStackTrace();
			looger.error(e.getMessage());
		}
		
		
	}*/
	
	//2 采用JSON对象实现JSONP的放回
	@RequestMapping("/web/itemcat/all")
	@ResponseBody
	public Object findItemCatAll(String callback){
		
		//应该通过后台程序查询出全部的商品分类数据
		ItemCatResult itemCatList = itemCatService.findItemCatAll();
		MappingJacksonValue jacksonValue =new MappingJacksonValue(itemCatList);
		//实现JSONP的提交方式     callback就是js调用的函数
		jacksonValue.setJsonpFunction(callback);
		
		return jacksonValue;	
	}
	
	
	
	
	
	
	
}
