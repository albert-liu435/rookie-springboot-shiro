package com.rookie.bigdata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rookie.bigdata.common.Result;
import com.rookie.bigdata.entity.SysUser;
import com.rookie.bigdata.service.SysUserService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import sun.security.util.Password;
import util.JWTUtils;

import java.util.HashMap;
import java.util.Map;

import static util.JWTUtils.TOKEN_HEADER;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rookie
 * @since 2021-07-13
 */
@RestController
@RequestMapping("/sys")
public class SysUserController {


    @Autowired
    private PasswordService passwordService;

    @Autowired
    private SysUserService sysUserService;


    /**
     * 进行登录操作,然后返回token值，后续登录需要一直在Header中携带着token
     * 账号：admin，密码：123456
     *
     * @param sysUser
     * @return
     */
    @RequestMapping("login")
    public Result login(@RequestBody SysUser sysUser) {


        SysUser sysUserByName = sysUserService.getSysUserByName(sysUser.getUsername());

        if (null == sysUserByName) {
            //返回账户或者密码错误
            return Result.fail(401, "账号或密码错误");
        }

        //验证密码
        boolean flag = passwordService.passwordsMatch(sysUser.getPassword(), sysUserByName.getPassword());
        if (!flag) {
            return Result.fail(401, "账号或密码错误");
        }

        //需要生成token
        String token = JWTUtils.create(sysUserByName);
        Map<String,String> map=new HashMap<>();
        map.put(TOKEN_HEADER,token);


        return Result.succ(200, "sucess", map);

    }


}
