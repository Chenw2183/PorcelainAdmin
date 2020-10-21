package com.stone.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stone.bean.PageQueryBean;
import com.stone.domain.CmPorcelain;
import com.stone.domain.CmProductCheck;
import com.stone.domain.CmRole;
import com.stone.domain.CmUser;
import com.stone.mapper.CmPorcelainMapper;
import com.stone.mapper.CmProductCheckMapper;
import com.stone.mapper.CmRoleMapper;
import com.stone.service.CmProductCheckService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CmProductCheckServiceImpl extends ServiceImpl<CmPorcelainMapper, CmPorcelain>  implements CmProductCheckService {




    @Autowired
    CmProductCheckMapper cmProductCheckMapper;


    @Override
    public Page<CmPorcelain> listPage(PageQueryBean<CmPorcelain> pageQueryBean) {
        // 分页
        int pageNum = pageQueryBean.getPageNum() == null ? 0 : pageQueryBean.getPageNum();
        int pageSize = pageQueryBean.getPageSize() == null ? 10 : pageQueryBean.getPageSize();
        Page<CmPorcelain> page = new Page<>(pageNum, pageSize);
        // 获取查询条件
        QueryWrapper<CmPorcelain> wrapper = new QueryWrapper<>();
        CmPorcelain condition = pageQueryBean.getQueryCondition();
        if (condition != null) {
            if (StringUtils.isNotEmpty(condition.getPorcelainCode())) {
                wrapper = wrapper.eq("porcelain_code", condition.getPorcelainCode());
            }
            if (StringUtils.isNotEmpty(condition.getPorcelainName())) {
                wrapper = wrapper.like("porcelain_name", condition.getPorcelainName());
            }
            if (StringUtils.isNotEmpty(condition.getUserCode())) {
                wrapper = wrapper.like("user_code", condition.getUserCode());
            }
            if (condition.getStatus() != null) {
                wrapper = wrapper.eq("status", condition.getStatus());
            }
        }
        // 拼接排序条件
        if (StringUtils.isNotEmpty(pageQueryBean.getOrderByColumn())) {
            if (StringUtils.isNotEmpty(pageQueryBean.getIsAsc()) && "desc".equals(pageQueryBean.getIsAsc().toLowerCase())) {
                wrapper = wrapper.orderByDesc(pageQueryBean.getOrderByColumn());
            } else {
                wrapper = wrapper.orderByAsc(pageQueryBean.getOrderByColumn());
            }
        }
        return this.baseMapper.selectPage(page, wrapper);
    }

    @Override
    public List<CmProductCheck> selectCheck(Integer status) {
        List<CmProductCheck> list = cmProductCheckMapper.selectCheck(status);

        for (int i = 0; i < list.size(); i++) {
            if (cmProductCheckMapper.selectByUserName(list.get(i).getUserCode()) != null) {
                list.get(i).setUserName(cmProductCheckMapper.selectByUserName(list.get(i).getUserCode()).getUserName());
            }
        }


        return list;
    }

    @Override
    public void operatingBy(CmProductCheck CmProductCheck) {
        //更新状态
        cmProductCheckMapper.updatePorcelainstatus(CmProductCheck.getPorcelainCode(), 1);
        //更新URL
        if (CmProductCheck.getUrl() != null && CmProductCheck.getUrl() != "") {
            cmProductCheckMapper.updatePorcelain(CmProductCheck.getPorcelainCode(), CmProductCheck.getUrl());
        }

    }

    @Override
    public void operatingEdit(CmProductCheck CmProductCheck) {
        //更新URL
        if (CmProductCheck.getUrl() != null && CmProductCheck.getUrl() != "") {
            cmProductCheckMapper.updatePorcelain(CmProductCheck.getPorcelainCode(), CmProductCheck.getUrl());
        }
    }

    @Override
    public void operatingReject(CmProductCheck CmProductCheck) {
        //更新状态
        cmProductCheckMapper.updatePorcelainstatus(CmProductCheck.getPorcelainCode(), -1);

        cmProductCheckMapper.updatePorcelainauditContent(CmProductCheck.getPorcelainCode(), CmProductCheck.getAuditContent());
    }


}
