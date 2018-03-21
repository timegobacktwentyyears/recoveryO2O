package com.recover.VO;

import com.recover.entity.Article;

/**
 * @author sanyue
 */
public class ArticleVO extends Article{
    /**
     * 数量
     */
    private Double num;
    /**
     * 订单类型 0:回收订单  1：二手交易订单 2：爱心捐赠订单
     */
    private Integer orderType;

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }
}
