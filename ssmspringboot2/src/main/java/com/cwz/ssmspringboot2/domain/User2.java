package com.cwz.ssmspringboot2.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class User2 implements Serializable {

    private Double WX_004_0019;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date DATA_TIME;

    public Double getWX_004_0019() {
        return WX_004_0019;
    }

    public void setWX_004_0019(Double WX_004_0019) {
        this.WX_004_0019 = WX_004_0019;
    }


    public Date getDATA_TIME() {
        return DATA_TIME;
    }

    public void setDATA_TIME(Date DATA_TIME) {
        this.DATA_TIME = DATA_TIME;
    }

    @Override
    public String toString() {
        return "User2{" +
                "WX_004_0019=" + WX_004_0019 +
                ", DATA_TIME=" + DATA_TIME +
                '}';
    }
}
