package com.recover.entity;

public class Sort extends BaseEntity {
    /**
     * 分类名
     */
    private String name;

    /**
     * 详细信息
     */
    private String sortDesc;

    /**
     * 单价
     */
    private Double price;

    /**
     * 分类级别
     */
    private Integer level;

    /**
     * 资源类型  0 可再生 1不可再生资源 '
     */
    private Integer type;

    /**
     * 父id
     */
    private Long parentId;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
