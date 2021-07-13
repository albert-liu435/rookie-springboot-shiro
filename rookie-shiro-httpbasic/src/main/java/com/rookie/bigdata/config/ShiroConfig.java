package com.rookie.bigdata.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Classname ShiroConfig
 * @Description TODO
 * @Author rookie
 * @Date 2021/7/13 9:51
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {


    /**
     * 配置 C3P0的ComboPooledDataSource
     *
     * @return
     * @throws PropertyVetoException
     */
    @Bean
    public ComboPooledDataSource buildDataSource() throws PropertyVetoException {
        //设置数据库链接
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/shiro");
        dataSource.setUser("root");
        dataSource.setPassword("root");

        return dataSource;

    }


    @Bean
    public JdbcRealm buildRealm(ComboPooledDataSource dataSource) {
        //创建JdbcRealm
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        //securityManager.setRealm(jdbcRealm);
//        //认证的过程中，查看是否有相应的权限
//        jdbcRealm.setPermissionsLookupEnabled(true);

        return jdbcRealm;

    }


    @Bean("securityManager")
    public SecurityManager securityManager(JdbcRealm jdbcRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(jdbcRealm);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        Map<String, String> filterMap = new LinkedHashMap<>(8);
        //对所有的请求添加BasicHttpAuthenticationFilter过滤器
        filterMap.put("/**", "authcBasic");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }


}
