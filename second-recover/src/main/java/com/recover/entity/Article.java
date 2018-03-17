package com.recover.entity;

public class Article extends BaseEntity {

    /**
     * 资源名
     */
    private String name;

    /**
     * 详细信息
     */
    private String sortDesc;

    /**
     * 类别id
     */
    private Long sortId;

    private Long userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortDesc() {
        return sortDesc;
    }

    public void setSortDesc(String sortDesc) {
        this.sortDesc = sortDesc;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
