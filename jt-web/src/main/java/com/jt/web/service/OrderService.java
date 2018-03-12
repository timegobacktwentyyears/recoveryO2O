package com.jt.web.service;

import com.jt.web.pojo.Order;

public interface OrderService {
	
	public String saveOrder(Order order) throws Exception;
	
	public Order findOrderById(String id);
}
