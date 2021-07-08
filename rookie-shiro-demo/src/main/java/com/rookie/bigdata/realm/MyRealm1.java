package com.rookie.bigdata.realm;

import cn.hutool.crypto.SecureUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;

/**
 * @Classname MyRealm1
 * @Description TODO
 * @Author rookie
 * @Date 2021/7/8 11:47
 * @Version 1.0
 */
public class MyRealm1 implements Realm {
    @Override
    public String getName() {
        return "MyRealm1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof BearerToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //assertCredentialsMatch
        Subject subject = SecurityUtils.getSubject();
        String nameAndpassword = "root:admin";
        //对用户和密码进行加密
        String md5Str = SecureUtil.md5(nameAndpassword);
        if (md5Str.equals(token.getPrincipal())) {
            return new SimpleAuthenticationInfo("root", "admin", getName());
        }
        throw new AuthenticationException("用户名或密码错误");
    }
}
