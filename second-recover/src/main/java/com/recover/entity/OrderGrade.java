package com.recover.entity;

import javax.persistence.Entity;

/**
 * @author sanyue
 */
@Entity
public class OrderGrade extends BaseEntity{
    private Long orderId;
    /**
     * 订单评分  1-5
     */
    private Integer orderGrade;
    /**
     * 对回收员的评分  1-5',
     */
    private Integer userGrade;
    /**
     * 用户评价 1000111001110000，  详见userReview类 ',
     */
    private String userView;
    /**
     * 用户手打的评价
     */
    private String reviewText;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderGrade() {
        return orderGrade;
    }

    public void setOrderGrade(Integer orderGrade) {
        this.orderGrade = orderGrade;
    }

    public Integer getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(Integer userGrade) {
        this.userGrade = userGrade;
    }

    public String getUserView() {
        return userView;
    }

    public void setUserView(String userView) {
        this.userView = userView;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
