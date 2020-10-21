package com.cwz.ssmspringboot2.service.impl;

import com.cwz.ssmspringboot2.dao.UserDao;
import com.cwz.ssmspringboot2.domain.User;
import com.cwz.ssmspringboot2.domain.User2;
import com.cwz.ssmspringboot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> SelectAll(String date, int contrastday) {
        List<User> all = userDao.SelectAll(date, contrastday);
        return all;
    }

    @Override
    public List<User2> ProvinceSelect(String WX_004_XXXX, String nowdate, String beforedate) {
        List<User2> ps = userDao.ProvinceSelect(WX_004_XXXX, nowdate, beforedate);
        return ps;
    }

}
