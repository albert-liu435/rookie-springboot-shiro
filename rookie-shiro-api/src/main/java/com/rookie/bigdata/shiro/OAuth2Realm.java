package com.rookie.bigdata.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rookie.bigdata.entity.SysMenu;
import com.rookie.bigdata.entity.SysRole;
import com.rookie.bigdata.entity.SysUser;
import com.rookie.bigdata.service.SysMenuService;
import com.rookie.bigdata.service.SysRoleService;
import com.rookie.bigdata.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JWTUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Classname OAth2Realm
 * @Description TODO
 * @Author rookie
 * @Date 2021/7/13 11:48
 * @Version 1.0
 */
//@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;


    /**
     * 对用户进行授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();

        // 这里可以进行授权和处理
        Set<String> rolesSet = new HashSet<>();
        Set<String> permsSet = new HashSet<>();


        //查询出用户的角色和用户的权限
        List<SysRole> list = sysRoleService.getRoleByUserId(sysUser.getId());

        for (SysRole sysRole : list) {
            rolesSet.add(sysRole.getCode());
            List<SysMenu> menuList = sysMenuService.selectMenuByRoleId(sysRole.getId());
            for (SysMenu menu : menuList) {
                permsSet.add(menu.getApi());
            }
        }

        //将查到的权限和角色分别传入authorizationInfo中
        authorizationInfo.setStringPermissions(permsSet);
        authorizationInfo.setRoles(rolesSet);
        //赋予角色和权限成功！
        return authorizationInfo;

    }


    /**
     * 认证，即对用户的身份进行确认
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String accessToken = (String) token.getPrincipal();

        //对token解析出用户的姓名
        SysUser sysUser = JWTUtils.userDetails(accessToken);

        //判断用户是否存在
//        TUser tUser1 = new TUser();
//        tUser1.setUsername(tUser.getUsername());
//        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>(tUser1);
//        TUser one = tUserService.getOne(queryWrapper);
        SysUser sysUserByName = sysUserService.getSysUserByName(sysUser.getUsername());

        if (null == sysUserByName) {
            throw new UnknownAccountException("用户不存在");
        }


        return new SimpleAuthenticationInfo(sysUserByName, accessToken, getName());
    }
}
