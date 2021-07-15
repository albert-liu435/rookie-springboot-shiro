package com.rookie.bigdata.shiro.impl;

import com.rookie.bigdata.entity.SysMenu;
import com.rookie.bigdata.entity.SysRole;
import com.rookie.bigdata.service.SysMenuService;
import com.rookie.bigdata.service.SysRoleService;
import com.rookie.bigdata.service.SysUserService;
import com.rookie.bigdata.shiro.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @Classname ShiroService
 * @Description TODO
 * @Author rookie
 * @Date 2021/7/13 17:27
 * @Version 1.0
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserService sysUserService;


    @Override
    public Map<String, String> loadFilterChainDefinitionMap() {


        Map<String, String> filterMap = new LinkedHashMap<>();
        //任何用户都可以进行登录操作
        filterMap.put("/sys/login", "anon");
        // filterMap.put("/api/**", "oauth2");
        // filterMap.put("/**", "oauth2");
        //查询出所有的资源
        List<SysMenu> menulist = sysMenuService.list();
        for (SysMenu sysMenu : menulist) {
            //API不为空
            if (!StringUtils.isEmpty(sysMenu.getApi())) {
                //根据相应的ID查询出用户的角色
                List<SysRole> roleList = sysRoleService.getRoleByMenuId(sysMenu.getId());
                StringJoiner zqRoles = new StringJoiner(",", "customerRoles[", "]");
                //自定义角色权限
                for (SysRole sysRole : roleList) {
                    zqRoles.add(sysRole.getCode());
                }

                //首先登录成功以后经过oauth2校验
                //角色权限 zqRoles：自定义的只需要满足其中一个角色即可访问  ;  roles[admin,guest] : 默认需要每个参数满足才算通过，相当于hasAllRoles()方法
                //认证自定义的url过滤器拦截权限
                filterMap.put(sysMenu.getApi(), "oauth2," + zqRoles.toString() + ",customerPerms[" + sysMenu.getCode() + "]");

            }


        }

        return filterMap;
    }
}
