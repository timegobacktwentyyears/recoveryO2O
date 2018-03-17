package com.recover.entity;

public class Area extends BaseEntity {
    private String name;
    /**
     * 地址等级
     */
    private Integer level;
    /**
     * 上级地址id
     */
    private Long parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
