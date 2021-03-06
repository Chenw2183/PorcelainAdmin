package com.stone.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.bean.PageQueryBean;
import com.stone.domain.CmPorcelain;

public interface CmPorcelainService {
    //添加瓷器信息
    public CmPorcelain addPorcelain(CmPorcelain cmPorcelain);

    //分页查询所有瓷器信息
    public Page<CmPorcelain> findByPage(PageQueryBean<CmPorcelain> pageQueryBean);

    //检验瓷器是否已存在
    public CmPorcelain checkByName(String porcelainName);

    //通过编号修改瓷器信息
    public int updateCmPorcelain(CmPorcelain cmPorcelain);

    //通过ID修改瓷器信息
    public int updateCmPorcelainById(CmPorcelain cmPorcelain);

    //删除瓷器信息
    public boolean deleteCmPorcelain(String porcelainCode);
}
