package com.stone.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.bean.PageQueryBean;
import com.stone.domain.CmMenu;
import com.stone.domain.CmRoleMenu;
import com.stone.domain.CmUser;
import com.stone.domain.CmUserLogIn;
import com.stone.service.CmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CmUserService cmUserService;

    //添加企业信息
    @PostMapping("/")
    public Map<String,CmUser> addUser(@RequestBody CmUser cmUser) throws Exception{
        Map<String,CmUser> map = new HashMap<>();
        CmUser cmUser1 = cmUserService.addUser(cmUser);
        if (cmUser1 != null){
            map.put("CmUser",cmUser1);
            return map;
        }else {
            return null;
        }
    }

    @GetMapping("/")
    public List<CmUser> selectAll(){
        List<CmUser> all = cmUserService.findAll();
        if (all != null){
            return all;
        }else {
            return null;
        }
    }

    //分页查找
    @PostMapping("/findByPage")
    public Page<CmUser> findByPage(@RequestBody PageQueryBean<CmUser> pageQueryBean){
        return cmUserService.findByPage(pageQueryBean);
    }

    //通过企业编号查找
    @PostMapping("/{userCode}")
    public CmUser findById(@PathVariable("userCode") String userCode){
        return cmUserService.findByUserCode(userCode);
    }
    //检验用户名是否唯一
    @GetMapping("/checkByUserName/{username}")
    public CmUser checkByUserName(@PathVariable("username") String username){
        return cmUserService.checkUser(username);
    }
    //检验用户账号（企业编号）是否唯一
    @GetMapping("/checkByUserCode")
    public CmUser checkByUserCode(String userCode){
        return cmUserService.checkUserCode(userCode);
    }

    //更新企业信息
    @PutMapping("/update")
    public String update(@RequestBody CmUser cmUser)throws Exception{
        if (cmUserService.updateUser(cmUser) > 0){
            return "success";
        }else {
            return "error";
        }
    }
    //修改账号状态 0：不冻结 1：冻结
    @PutMapping("/updateStatus")
    public String updateStatus(@RequestBody CmUser cmUser){
        if (cmUserService.updateUserStatus(cmUser)){
            return "success";
        }else {
            return "error";
        }
    }


    /**
     * 登录
     * @return
     */
    @RequestMapping(value="/logIn")
    public List<CmMenu> logIn(@RequestBody CmUserLogIn cmUserLogIn) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return cmUserService.logIn(cmUserLogIn);
    }
}
