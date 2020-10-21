package com.stone.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@TableName(value = "cm_porcelain")
public class CmPorcelain {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "porcelain_code")
    private String porcelainCode;
    @TableField(value = "porcelain_name")
    private String porcelainName;
    @TableField(value = "porcelain_type")
    private String porcelainType;
    @TableField(value = "user_code")
    private String userCode;
    @TableField(value = "origin_place")
    private String originPlace;
    @TableField(value = "material")
    private String material;
    @TableField(value = "brand")
    private String brand;
    @TableField(value = "height")
    private Double height;
    @TableField(value = "width")
    private Double width;
    @TableField(value = "weight")
    private Double weight;
    @TableField(value = "status")
    private Integer status;
    @TableField(value = "audit_content")
    private String auditContent;
    @TableField(value = "url")
    private String url;
    @TableField(value = "submit_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date submitTime;

    @Override
    public String toString() {
        return "CmPorcelain{" +
                "id=" + id +
                ", porcelainCode='" + porcelainCode + '\'' +
                ", porcelainName='" + porcelainName + '\'' +
                ", porcelainType='" + porcelainType + '\'' +
                ", userCode='" + userCode + '\'' +
                ", originPlace='" + originPlace + '\'' +
                ", material='" + material + '\'' +
                ", brand='" + brand + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", weight=" + weight +
                ", status=" + status +
                ", auditContent='" + auditContent + '\'' +
                ", url='" + url + '\'' +
                ", submitTime=" + submitTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPorcelainCode() {
        return porcelainCode;
    }

    public void setPorcelainCode(String porcelainCode) {
        this.porcelainCode = porcelainCode;
    }

    public String getPorcelainName() {
        return porcelainName;
    }

    public void setPorcelainName(String porcelainName) {
        this.porcelainName = porcelainName;
    }

    public String getPorcelainType() {
        return porcelainType;
    }

    public void setPorcelainType(String porcelainType) {
        this.porcelainType = porcelainType;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuditContent() {
        return auditContent;
    }

    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}
