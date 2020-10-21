package com.stone.controller;

import com.stone.domain.CmPorcelainType;
import com.stone.service.CmPorcelainTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/porcelainType")
public class PorcelainTypeController {
    @Autowired
    private CmPorcelainTypeService porcelainTypeService;

    //添加分类
    @PostMapping("/{porcelainType}")
    public Map<String, CmPorcelainType> addPorpelainType(@PathVariable("porcelainType") String porcelainType) {
        Map<String, CmPorcelainType> map = new HashMap<>();
        CmPorcelainType cmPorcelainType = porcelainTypeService.addPorcelainType(porcelainType);
        if (cmPorcelainType != null) {
            map.put("cmPorcelainType", cmPorcelainType);
            return map;
        } else {
            return null;
        }
    }

    //查找所有分类
    @PostMapping("/")
    public List<CmPorcelainType> findAll() {
        return porcelainTypeService.findAll();
    }

    //检验分类名是否唯一
    @PostMapping("/check/{porcelainType}")
    public CmPorcelainType checkPorcelainType(@PathVariable("porcelainType") String porcelainType) {
        return porcelainTypeService.checkPorcelainType(porcelainType);
    }

    //修改分类名称
    @PutMapping("/{porcelainType}/{typeCode}")
    public String updatePorcelainType(@PathVariable("porcelainType") String porcelainType
            , @PathVariable("typeCode") String typeCode) {
        if (porcelainTypeService.updatePorcelainType(porcelainType, typeCode) > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    //删除分类
    @DeleteMapping("/{typeCode}")
    public String deletePorcelainType(@PathVariable("typeCode") String typeCode) {
        if (porcelainTypeService.deletePorcelainType(typeCode)) {
            return "success";
        } else {
            return "error";
        }
    }


}
