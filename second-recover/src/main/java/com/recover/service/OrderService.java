package com.recover.service;

import com.recover.dao.OrderDao;
import com.recover.entity.Order;
import com.recover.util.ServiceUtils;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author sanyue
 */
@Service
public class OrderService {

    private OrderDao orderDao;

    public List<Order> orderList(@Param("userId") Long userId){
        List<Order> orders = orderDao.listByuserId(userId);
        return orders;
    }
}
