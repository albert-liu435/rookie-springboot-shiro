package com.rookie.bigdata.service;

import com.rookie.bigdata.entity.SysMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Classname SysMenuServiceTest
 * @Description TODO
 * @Author rookie
 * @Date 2021/7/13 15:58
 * @Version 1.0
 */
@SpringBootTest
class SysMenuServiceTest {

    @Autowired
    private SysMenuService sysMenuService;

    @Test
    void selectMenuByRoleId() {

        List<SysMenu> sysMenus = sysMenuService.selectMenuByRoleId(1);
        for (SysMenu sysMenu : sysMenus) {
            System.out.println(sysMenu);
        }
    }
}