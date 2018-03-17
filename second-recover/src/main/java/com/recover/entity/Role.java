package com.recover.entity;

public class Role extends BaseEntity {
    private String roleName;

    /**
     * 角色类型：
     */
    private Integer roleType;

    /**
     * TEANTOWNER(1), ADMIN(2), MEMBER(3)
     */
    private Integer roleValue;

    /**
     * 角色描述
     */
    private String roleDesc;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(Integer roleValue) {
        this.roleValue = roleValue;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
