package com.stone.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stone.domain.CmPorcelainType;
import com.stone.mapper.CmPorcelainTypeMapper;
import com.stone.service.CmPorcelainTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.annotation.WebServlet;
import java.nio.file.Watchable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CmPorcelainTypeServiceImpl extends ServiceImpl<CmPorcelainTypeMapper, CmPorcelainType> implements CmPorcelainTypeService {
    @Autowired
    private CmPorcelainTypeMapper cmPorcelainTypeMapper;

    //添加分类
    @Override
    public CmPorcelainType addPorcelainType(String porcelainType) {
        CmPorcelainType cmPorcelainType = new CmPorcelainType();
        synchronized (this) {
            cmPorcelainType.setTypeName(porcelainType);
            SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmSS");
            Date date = new Date();
            cmPorcelainType.setTypeCode("t" + sd.format(date));
            cmPorcelainType.setCreateTime(date);
            cmPorcelainTypeMapper.insert(cmPorcelainType);
        }
        return cmPorcelainType;
    }

    //检验分类名称唯一性
    @Override
    public CmPorcelainType checkPorcelainType(String porcelainType) {
        QueryWrapper<CmPorcelainType> wrapper = new QueryWrapper<>();
        wrapper.eq("type_name", porcelainType);
        return cmPorcelainTypeMapper.selectOne(wrapper);
    }

    //查找所有分类
    @Override
    public List<CmPorcelainType> findAll() {
        return cmPorcelainTypeMapper.selectList(null);
    }

    //修改分类名称
    @Override
    public int updatePorcelainType(String porcelainType, String typeCode) {
        UpdateWrapper<CmPorcelainType> wrapper = new UpdateWrapper<>();
        wrapper.set("type_name", porcelainType)
                .eq("type_code", typeCode);
        return cmPorcelainTypeMapper.update(null, wrapper);
    }

    //删除分类
    @Override
    public boolean deletePorcelainType(String typeCode) {
        QueryWrapper<CmPorcelainType> wrapper = new QueryWrapper<>();
        wrapper.eq("type_code", typeCode);
        return this.remove(wrapper);
    }
}
