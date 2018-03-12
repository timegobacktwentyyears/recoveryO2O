package com.jt.web.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Item;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private HttpClientService httpClientService;
	
	private static final ObjectMapper objcetMapper=new ObjectMapper();
	//完成根据关键字检索的操作
	@Override
	public List<Item> findItemListByKey(String keyWord) {
		
		//1.定义url
		String url="http://search.jt.com";
		
		//2.封装参数
		Map<String,String>searchMap=new HashMap<String ,String>();
		searchMap.put("keyword", keyWord);
		
		try {
			String itemListJson = httpClientService.doPost(url, searchMap);
			
			//需要检查返回的结果是否半酣全部数据  如果没有全部数据 需要将JSON设定为忽略未知字段
			Item[] items=objcetMapper.readValue(itemListJson, Item[].class);
			List<Item>itemList=Arrays.asList(items);
			
			return itemList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
