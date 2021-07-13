package com.rookie.bigdata.service.impl;

import com.rookie.bigdata.entity.SysUser;
import com.rookie.bigdata.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Classname SysUserServiceImplTest
 * @Description TODO
 * @Author rookie
 * @Date 2021/7/13 12:05
 * @Version 1.0
 */
@SpringBootTest
class SysUserServiceImplTest {


    @Autowired
    private SysUserService sysUserService;

    @Test
    void getSysUserByName() {

        SysUser admin = sysUserService.getSysUserByName("admin");
        System.out.println(admin);
    }
}