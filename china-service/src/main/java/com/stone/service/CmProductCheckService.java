package com.stone.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.bean.PageQueryBean;
import com.stone.domain.CmPorcelain;
import com.stone.domain.CmProductCheck;
import com.stone.domain.CmRole;
import com.stone.domain.CmUser;

import java.util.List;

public interface CmProductCheckService extends IService<CmPorcelain> {

    //分页查询
    Page<CmPorcelain> listPage(PageQueryBean<CmPorcelain> pageQueryBean);

    List<CmProductCheck> selectCheck(Integer status);

    void operatingBy(CmProductCheck CmProductCheck);

    void operatingEdit(CmProductCheck CmProductCheck);

    void operatingReject(CmProductCheck CmProductCheck);
}
