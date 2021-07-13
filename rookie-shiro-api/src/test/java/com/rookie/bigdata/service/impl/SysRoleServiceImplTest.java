package com.rookie.bigdata.service.impl;

import com.rookie.bigdata.entity.SysRole;
import com.rookie.bigdata.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Classname SysRoleServiceImplTest
 * @Description TODO
 * @Author rookie
 * @Date 2021/7/13 15:51
 * @Version 1.0
 */
@SpringBootTest
class SysRoleServiceImplTest {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    void getRoleByUserId() {

        List<SysRole> roleByUserId = sysRoleService.getRoleByUserId(1);
        for (SysRole sysRole : roleByUserId) {

            System.out.println(sysRole);
        }
    }
}