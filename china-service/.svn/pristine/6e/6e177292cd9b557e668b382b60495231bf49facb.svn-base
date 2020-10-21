package com.stone.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stone.bean.PageQueryBean;
import com.stone.domain.*;
import com.stone.mapper.CmRoleMapper;
import com.stone.mapper.CmUserMapper;
import com.stone.service.CmRoleService;
import com.stone.utils.MD5;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CmRoleServiceImpl extends ServiceImpl<CmRoleMapper, CmRole> implements CmRoleService {

    @Autowired
    CmRoleMapper cmRoleMapper;

    @Autowired
    private CmUserMapper cmUserMapper;

    @Override
    public Page<CmRole> listPage(PageQueryBean<CmRole> pageQueryBean) {
        // 分页
        int pageNum = pageQueryBean.getPageNum() == null ? 0 : pageQueryBean.getPageNum();
        int pageSize = pageQueryBean.getPageSize() == null ? 10 : pageQueryBean.getPageSize();
        Page<CmRole> page = new Page<>(pageNum, pageSize);
        // 获取查询条件
        QueryWrapper<CmRole> wrapper = new QueryWrapper<>();
        CmRole condition = pageQueryBean.getQueryCondition();
        if (condition != null) {
            if (StringUtils.isNotEmpty(condition.getRoleCode())) {
                wrapper = wrapper.like("role_code", condition.getRoleCode());
            }
            if (StringUtils.isNotEmpty(condition.getRoleName())) {
                wrapper = wrapper.like("role_name", condition.getRoleName());
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
    public void updateByIds(CmRole cmRole) {
        CmRole cmRole1 = new CmRole();
        cmRole1.setId(cmRole.getId());
        cmRole1.setStatus(cmRole.getStatus());
        cmRoleMapper.updateById(cmRole1);
    }

    @Override
    public CmRole selectByIds(long id) {
        return cmRoleMapper.selectById(id);
    }

    @Override
    public CmRole selectByRoleCode(String roleCode) {
        QueryWrapper<CmRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_code",roleCode);
        return cmRoleMapper.selectOne(wrapper);
    }

    @Override
    public Page<CmRole> addToRole(CmRoleCompetence cmRoleCompetence) {

        PageQueryBean<CmRole> pageQueryBeans = new PageQueryBean<>();
        CmRole cmRole = new CmRole();
        cmRole.setRoleCode(cmRoleCompetence.getRoleCode());
        cmRole.setRoleName(cmRoleCompetence.getRoleName());
        pageQueryBeans.setQueryCondition(cmRole);

        //判断Role表中是否存在账 不能重复添加
        if (listPage(pageQueryBeans).getTotal() > 0) {
            return null;
        } else {

            CmRole cmRoles = new CmRole();
            cmRoles.setId(null);
            cmRoles.setRoleCode(cmRoleCompetence.getRoleCode());
            cmRoles.setRoleName(cmRoleCompetence.getRoleName());
            cmRoles.setStatus(cmRoleCompetence.getStatus());
            cmRoles.setDescription(cmRoleCompetence.getDescription());
            cmRoles.setCreateTime(new Date());
            cmRoleMapper.insert(cmRoles);
//          cm_role_menu添加权限
            for (int i = 0; i < cmRoleCompetence.getMenuList().size(); i++) {
                cmRoleMapper.roleMenuInsert(cmRoleCompetence.getRoleCode(), cmRoleCompetence.getMenuList().get(i), new Date());
            }

            return listPage(pageQueryBeans);

        }
    }

    @Override
    public List<CmRoleMenu> Selectedit(CmRole cmRole) {
        return cmRoleMapper.selectRoleMenu(cmRole.getRoleCode());
    }

    @Override
    public void updateByCode(CmRoleCompetence cmRoleCompetence) {
        //删除全部权限
        cmRoleMapper.deleteroleMenu(cmRoleCompetence.getRoleCode());
        //重新插入权限
        for (int i = 0; i < cmRoleCompetence.getMenuList().size(); i++) {
            cmRoleMapper.roleMenuInsert(cmRoleCompetence.getRoleCode(), cmRoleCompetence.getMenuList().get(i), new Date());
        }

    }

    @Override
    public List<CmMenu> selectCmmenu(){
        return cmRoleMapper.selectCmmenu();
    }

}
