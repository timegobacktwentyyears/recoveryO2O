package com.recover.service;

import com.recover.VO.ArticleVO;
import com.recover.dao.OrderDao;
import com.recover.entity.Article;
import com.recover.entity.Order;
import com.recover.exception.MineException;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sanyue
 */
@Service
public class OrderService {

    private OrderDao orderDao;

    public List<Order> orderList(@Param("userId") Long userId) {
        List<Order> orders = orderDao.listByuserId(userId);
        return orders;
    }

    public void submit(@Param("articleList") List<ArticleVO> articleList, @Param("userId") Long userId) {
        if (articleList == null){
            throw new MineException("请先选择下单废弃资源");
        }
        
    }
}
