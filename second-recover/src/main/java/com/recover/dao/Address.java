package com.recover.dao;

import com.recover.entity.BaseEntity;

import javax.persistence.Entity;

/**
 * @author sanyue
 */
@Entity
public class Address extends BaseEntity {

    private Long userId;

    private Long areaId;
    /**
     * 详细地址
     */
    private String fullAddress;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}
