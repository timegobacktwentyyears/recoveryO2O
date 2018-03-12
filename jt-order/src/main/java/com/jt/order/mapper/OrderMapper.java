package com.jt.order.mapper;

import java.util.Date;

import com.jt.order.pojo.Order;

public interface OrderMapper {

	void saveOrder(Order order);

	Order findOrderById(String orderId);

	void updateStatus(Date twoDay);

}
