package com.stone.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stone.domain.CmMenu;
import com.stone.domain.CmRole;
import com.stone.domain.CmRoleMenu;

import java.util.Date;
import java.util.List;

public interface CmRoleMapper extends BaseMapper<CmRole> {
    void roleMenuInsert(String roleCode, Integer menuId, Date createTime);

    void updateByRoleCode(String roleCode, Integer menuId);

    List<CmMenu> selectCmmenu();

    List<CmRoleMenu> selectRoleMenu(String roleCode);

    void deleteroleMenu(String roleCode);

    CmRole selectCmRole(String roleCode);

    CmMenu selectById(Integer id);
}
