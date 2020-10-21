package com.stone.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import javafx.scene.chart.ValueAxis;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import java.util.Date;

@TableName(value = "cm_user")
public class CmUser {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "user_code")
    private String userCode;
    @TableField(value = "user_name")
    private String userName;
    @TableField(value = "password")
    private String password;
    @TableField(value = "contacts")
    private String contacts;
    @TableField(value = "phone")
    private String phone;
    @TableField(value = "login_lock")
    private Integer loginLock;
    @TableField(value = "role_code")
    private String roleCode;
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Override
    public String toString() {
        return "CmUser{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", contacts='" + contacts + '\'' +
                ", phone='" + phone + '\'' +
                ", loginLock=" + loginLock +
                ", roleCode='" + roleCode + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getLoginLock() {
        return loginLock;
    }

    public void setLoginLock(Integer loginLock) {
        this.loginLock = loginLock;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
