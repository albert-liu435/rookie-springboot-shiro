package com.rookie.bigdata;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import java.beans.PropertyVetoException;

/**
 * @Classname JdbcRealmTutorial
 * @Description TODO
 * @Author rookie
 * @Date 2021/7/9 12:26
 * @Version 1.0
 */
public class JdbcRealmTutorial {

    public static void main(String[] args) throws PropertyVetoException {


        //设置数据库链接
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/shiro");
        dataSource.setUser("root");
        dataSource.setPassword("root");

        //创建DefaultSecurityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        SecurityUtils.setSecurityManager(securityManager);
        //创建JdbcRealm
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        securityManager.setRealm(jdbcRealm);
        //认证的过程中，查看是否有相应的权限
        jdbcRealm.setPermissionsLookupEnabled(true);
        // get the currently executing user:
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");

        subject.login(token);
        //不跑出异常说明登录成功
        System.out.println("登录成功");

        subject.login(token);
        System.out.println();
        //不跑出异常说明登录成功
        System.out.println("登录成功");
        //判断是否有相应角色
        boolean admin = subject.hasRole("admin");
        System.out.println(admin);

        boolean update = subject.isPermitted("update");
        System.out.println(update);

    }
}
