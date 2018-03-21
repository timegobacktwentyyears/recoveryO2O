package com.recover.service;

import com.recover.VO.ArticleVO;
import com.recover.dao.OrderDao;
import com.recover.dao.UserDao;
import com.recover.entity.Article;
import com.recover.entity.Order;
import com.recover.entity.User;
import com.recover.enums.ApiCode;
import com.recover.exception.MineException;
import com.recover.util.ApiResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sanyue
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    public List<Order> orderList(@Param("userId") Long userId) {
        List<Order> orders = orderDao.listByuserId(userId);
        return orders;
    }

    /**
     * @param article   包括name  num orderType
     * @param message  买家留言
     * @param userId 用户id
     * @return
     */
    public ApiResult submit(@Param("articleList") ArticleVO article, @Param("message") String message, @Param("userId") Long userId) {
        if (article == null) {
            throw new MineException("请先选择下单废弃资源");
        }
        User user = userDao.getOne(userId);
        //加入订单表操作
        Order order = new Order();
        order.setBuyerMessage(message);
        order.setBuyerNick(user.getNickName());
        order.setUserId(userId);
        order.setPostFee("0");
        order.setOrderType(article.getOrderType());
        //TODO  需要计算订单金额
        order.setPayment("100");
        //线上支付
        order.setPaymentType(1);
        order.setArticleName(article.getName());
        order.setArticleNum(article.getNum());
        orderDao.insert(order);

        return new ApiResult(ApiCode.SUCCESS.getCode(), "下单成功");
    }
}
