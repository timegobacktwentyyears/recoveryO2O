package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Order;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private HttpClientService httpClientService;
	
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public String saveOrder(Order order) throws Exception{
		
		//将order对象转化为JSON串进行传递
		String orderJSON = objectMapper.writeValueAsString(order);
		
		//定义url
		String url = "http://order.jt.com/order/create";
		
		Map<String, String> map = new HashMap<String,String>();
		map.put("orderJSON", orderJSON);
		//orderJSON的数据名称必须与远程方法的接收端保持一致,否则传输失败
		
		String orderId = httpClientService.doPost(url, map, "utf-8");
		
		return orderId;
	}

	@Override
	public Order findOrderById(String id) {
		
		String url = "http://order.jt.com/order/query/"+id;
		
		try {
			String orderJSON = httpClientService.doGet(url);
			Order order = objectMapper.readValue(orderJSON, Order.class);
			
			return order;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
