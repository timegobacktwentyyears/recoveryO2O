package com.jt.order.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.order.mapper.OrderMapper;
import com.jt.order.pojo.Order;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderMapper orderMapper;
	
	
	@Override
	public String saveOrder(String orderJSON) {
		
		//1.将JSON串进行格式转化,转化为Order对象
		try {
			Order order = objectMapper.readValue(orderJSON, Order.class);	
			//定义OrderId   登录用户id+当前时间戳
			String orderId = order.getUserId() +"" +System.currentTimeMillis();
			//为order对象赋值
			order.setOrderId(orderId);
			order.setCreateTime(new Date());
			order.setUpdateTime(order.getCreateTime());
			//为物流信息赋值
			order.getOrderShipping().setCreated(order.getCreateTime());
			order.getOrderShipping().setUpdated(order.getCreateTime());
			orderMapper.saveOrder(order);
			
			return orderId; 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		return null;
	}


	@Override
	public Order findOrderById(String orderId) {
	
		Order order = orderMapper.findOrderById(orderId);
		return order;
	}

}
