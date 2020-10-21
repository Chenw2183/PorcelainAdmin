package com.stone.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.bean.PageQueryBean;
import com.stone.domain.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface CmRoleService extends IService<CmRole> {

    //分页查询
    Page<CmRole> listPage(PageQueryBean<CmRole> pageQueryBean);

    //根据ID更新
    void updateByIds(CmRole cmRole);

    //根据ID查询
    CmRole selectByIds(long id);
    //根据roleCode查询
    CmRole selectByRoleCode(String roleCode);

    //添加
    Page<CmRole> addToRole(CmRoleCompetence cmRoleCompetence);

    //编辑
    List<CmRoleMenu> Selectedit(CmRole cmRole);

    //编辑保存
    void updateByCode(CmRoleCompetence cmRoleCompetence);

    //查询权限表
    List<CmMenu> selectCmmenu();

}
