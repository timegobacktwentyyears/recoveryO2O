package com.recover.entity;

import java.sql.Timestamp;

/**
 * @author sanyue
 */
public class Order extends BaseEntity {
    private Long userId;
    /**
     * 订单类型 0:回收订单  1：二手交易订单 2：爱心捐赠订单
     */
    private Integer orderType;

    private String orderDesc;
    /**
     *  精确到2位小数；单位：元。如：200.09，表示：200元7分
     */
    private String payment;
    /**
     * //支付类型 1、在线支付，2、货到付款'
     */
    private Integer paymentType;
    /**
     * //邮费'邮费。精确到2位小数；单位：元。如：200.09'
     */
    private String postFee;
    /**
     * //状态：1、未付款2、已付款3、未发货4、已发货5、交易成功6、交易关闭
     */
    private Integer status;
    /**
     * //付款时间
     */
    private Timestamp paymentTime;
    /**
     * //发货时间
     */
    private Timestamp consignTime;
    /**
     *  //交易完成时间
     */
    private Timestamp endTime;
    /**
     * //交易的关闭时间
     */
    private Timestamp closeTime;
    /**
     * //快递名称
     */
    private String shippingName;
    /**
     * //物流单号
     */
    private String shippingCode;
    /**
     * //买家留言;
     */
    private String buyerMessage;
    /**
     * //买家昵称
     */
    private String buyerNick;
    /**
     * //买家是否评价
     */
    private Integer buyerRate;
    /**
     * 废物名称
     */
    private String articleName;
    /**
     * 废物数量
     */
    private Double articleNum;

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public Double getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(Double articleNum) {
        this.articleNum = articleNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getPostFee() {
        return postFee;
    }

    public void setPostFee(String postFee) {
        this.postFee = postFee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Timestamp paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Timestamp getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(Timestamp consignTime) {
        this.consignTime = consignTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public Integer getBuyerRate() {
        return buyerRate;
    }

    public void setBuyerRate(Integer buyerRate) {
        this.buyerRate = buyerRate;
    }
}
