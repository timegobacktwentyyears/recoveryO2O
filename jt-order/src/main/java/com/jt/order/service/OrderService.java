package com.jt.order.service;

import com.jt.order.pojo.Order;

public interface OrderService {

	public String saveOrder(String orderJSON);
	
	//根据订单编号查询订单信息
	public Order findOrderById(String orderId);

}
