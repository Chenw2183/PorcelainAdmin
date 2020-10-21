package com.stone.domain;

import java.util.List;

public class CmRoleCompetence {
    private String roleCode;
    private String roleName;
    private Integer status = 0;
    private String description;

    private List<Integer> menuList;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Integer> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Integer> menuList) {
        this.menuList = menuList;
    }
}
