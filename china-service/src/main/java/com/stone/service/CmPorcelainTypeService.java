package com.stone.service;

import com.stone.domain.CmPorcelainType;

import java.util.List;

public interface CmPorcelainTypeService {

    //添加分类
    CmPorcelainType addPorcelainType(String porcelainType);

    //检验分类名称唯一性
    CmPorcelainType checkPorcelainType(String porcelainType);

    //查找所有分类
    List<CmPorcelainType> findAll();

    //修改分类名称
    int updatePorcelainType(String porcelainType, String typeCode);

    //删除分类
    boolean deletePorcelainType(String typeCode);
}