package com.stone.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stone.domain.CmPorcelain;
import com.stone.domain.CmProductCheck;
import com.stone.domain.CmRole;
import com.stone.domain.CmUser;

import java.util.List;

public interface CmProductCheckMapper extends BaseMapper<CmPorcelain> {
    List<CmProductCheck> selectCheck(Integer status);

    CmUser selectByUserName(String userCode);

    void updatePorcelain(String porcelainCode, String url);

    void updatePorcelainstatus(String porcelainCode, Integer status);

    void updatePorcelainauditContent(String porcelainCode, String auditContent);
}
