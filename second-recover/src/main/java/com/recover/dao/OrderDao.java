package com.recover.dao;

import com.recover.entity.Order;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @author acer
 */
@Repository
public interface OrderDao {

    List<Order> listByuserId(Long userId);

    Integer insert(Order order);
}
