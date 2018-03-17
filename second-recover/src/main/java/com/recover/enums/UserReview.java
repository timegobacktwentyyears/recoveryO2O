package com.recover.enums;

/**
 * 固定的用户评价
 *
 * @author sanyue
 */
public enum UserReview {

    //TODO   完善用户评价模块
    /**
     * 对回收员正面的评价
     */
    STAFF_REVIEW_POSITIVE_01("回收员的工作态度认真且热情"),

    /**
     * 对回收员负面的评价
     */
    STAFF_REVIEW_NAGETIVE_01("回收员分类不仔细"),
    STAFF_REVIEW_NAGETIVE_02("回收员计算价格不准确"),



    END("");

    private String review;
    UserReview(String review){
        this.review = review;
    }
    public String getReview(){
        return this.review;
    }
}
