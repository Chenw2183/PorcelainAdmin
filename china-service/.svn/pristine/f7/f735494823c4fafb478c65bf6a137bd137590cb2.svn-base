package com.stone.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.bean.PageQueryBean;
import com.stone.domain.CmPorcelain;
import com.stone.service.CmPorcelainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

@RestController
@RequestMapping("/porcelain")
public class PorcelainController {
    @Autowired
    private CmPorcelainService cmPorcelainService;

    //上传文件
    @PostMapping("/upload")
    public String upload(MultipartFile file, Long id) throws Exception {
        if (file.isEmpty()) {
            return "上传失败,重新上传!";
        } else {
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("user.properties"));
            String fileName = properties.getProperty("fileName");
            String originalFilename = file.getOriginalFilename();
            File file1 = new File(fileName);
            if (!file1.exists()) {
                file1.mkdirs();
            }
            String s = UUID.randomUUID().toString();
            originalFilename = s + "-" + originalFilename;
            file.transferTo(new File(fileName + originalFilename));
            String url = fileName + originalFilename;
            CmPorcelain cmPorcelain = new CmPorcelain();
            cmPorcelain.setId(id);
            cmPorcelain.setUrl(url);
            if (cmPorcelainService.updateCmPorcelainById(cmPorcelain) > 0) {
                return "success";
            } else {
                return "error";
            }
        }
    }

    //添加瓷器信息
    @PostMapping("/addPorcelain")
    public Map<String, CmPorcelain> addPorcelain(@RequestBody CmPorcelain cmPorcelain) {
        Map<String, CmPorcelain> map = new HashMap<>();
        CmPorcelain cmPorcelain1 = cmPorcelainService.addPorcelain(cmPorcelain);
        if (cmPorcelain1 != null) {
            map.put("CmPorcelain", cmPorcelain1);
            return map;
        } else {
            return null;
        }
    }

    //分页查询所有瓷器信息
    @PostMapping("/selectByPage")
    public Page<CmPorcelain> selectByPage(@RequestBody PageQueryBean<CmPorcelain> pageQueryBean) {
        return cmPorcelainService.findByPage(pageQueryBean);
    }

    //检验瓷器是否已存在
    @PostMapping("/{porcelainName}")
    public String checkByPorcelainName(@PathVariable("porcelainName") String porcelainName) {
        if (cmPorcelainService.checkByName(porcelainName) != null) {
            return "success";
        } else {
            return "error";
        }
    }

    //修改瓷器信息
    @PutMapping("/updatePorcelain")
    public String updatePorcelain(@RequestBody CmPorcelain cmPorcelain) {
        if (cmPorcelainService.updateCmPorcelain(cmPorcelain) > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    //删除瓷器信息
    @DeleteMapping("/{porcelainCode}")
    public String deletePorcelain(@PathVariable("porcelainCode") String porcelainCode) {
        if (cmPorcelainService.deleteCmPorcelain(porcelainCode)) {
            return "success";
        } else {
            return "error";
        }
    }
}
