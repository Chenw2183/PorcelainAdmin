package com.cwz.ssmspringboot2.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {

    private Integer CITY_ID;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date DATA_TIME;
    private Double WX_004_0019;
    private Double WX_004_1010;
    private Double WX_004_0020;
    private Double WX_004_1011;
    private Double WX_004_1012;

    public Integer getCITY_ID() {
        return CITY_ID;
    }

    public void setCITY_ID(Integer CITY_ID) {
        this.CITY_ID = CITY_ID;
    }

    public Date getDATA_TIME() {
        return DATA_TIME;
    }

    public void setDATA_TIME(Date DATA_TIME) {
        this.DATA_TIME = DATA_TIME;
    }

    public Double getWX_004_0019() {
        return WX_004_0019;
    }

    public void setWX_004_0019(Double WX_004_0019) {
        this.WX_004_0019 = WX_004_0019;
    }

    public Double getWX_004_1010() {
        return WX_004_1010;
    }

    public void setWX_004_1010(Double WX_004_1010) {
        this.WX_004_1010 = WX_004_1010;
    }

    public Double getWX_004_0020() {
        return WX_004_0020;
    }

    public void setWX_004_0020(Double WX_004_0020) {
        this.WX_004_0020 = WX_004_0020;
    }

    public Double getWX_004_1011() {
        return WX_004_1011;
    }

    public void setWX_004_1011(Double WX_004_1011) {
        this.WX_004_1011 = WX_004_1011;
    }

    public Double getWX_004_1012() {
        return WX_004_1012;
    }

    public void setWX_004_1012(Double WX_004_1012) {
        this.WX_004_1012 = WX_004_1012;
    }

    @Override
    public String toString() {
        return "User{" +
                "CITY_ID=" + CITY_ID +
                ", DATA_TIME=" + DATA_TIME +
                ", WX_004_0019=" + WX_004_0019 +
                ", WX_004_1010=" + WX_004_1010 +
                ", WX_004_0020=" + WX_004_0020 +
                ", WX_004_1011=" + WX_004_1011 +
                ", WX_004_1012=" + WX_004_1012 +
                '}';
    }
}
