package com.recover.entity;

/**
 * @author sanyue
 */
public class User extends BaseEntity{
    private String userName;

    private String userPassword;
    /**
     * 用户简介
     */
    private String userDesc;
    /**
     * 用户类型：0 社区用户，1 回收员，2 管理员',
     */
    private Integer userType;
    /**
     * 用户地址
     */
    private Integer userArea;
    /**
     * 详细地址
     */
    private String fullAddress;
    /**
     * 电话
     */
    private Long phone;
    /**
     * '用户状态0：正常，1：禁用'
     */
    private Integer status;

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserArea() {
        return userArea;
    }

    public void setUserArea(Integer userArea) {
        this.userArea = userArea;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
