package com.cwz.ssmspringboot2.service;

import com.cwz.ssmspringboot2.domain.User;
import com.cwz.ssmspringboot2.domain.User2;

import java.util.List;

public interface UserService {

    /**
     * 查询7天数据
     *
     * @param date 需要查询的日期后7天所有数据
     * @return
     */
    List<User> SelectAll(String date, int contrastday);

    /**
     * 按照日期 查询区间中全省的GSM无线话务量的指标值
     *
     * @param nowdate
     * @param beforedate
     * @return
     */
    List<User2> ProvinceSelect(String WX_004_XXXX, String nowdate, String beforedate);

}
