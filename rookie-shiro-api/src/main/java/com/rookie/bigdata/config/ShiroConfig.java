package com.rookie.bigdata.config;

import com.rookie.bigdata.shiro.OAuth2Filter;
import com.rookie.bigdata.shiro.OAuth2Realm;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Classname ShiroConfig
 * @Description TODO
 * @Author rookie
 * @Date 2021/7/13 11:42
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {


    /**
     * 这个在引入shiro spring的时候框架已经帮我们注入进来了，所以不需要我们再去进行注入了，参考{@link org.apache.shiro.spring.config.ShiroBeanConfiguration}
     *
     * @return
     */
//    @Bean
//    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }


    /**
     * 安全管理器
     */
    @Bean
    public SecurityManager securityManager(OAuth2Realm oAuth2Realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        // 自定义Realm验证
        securityManager.setRealm(oAuth2Realm);
        return securityManager;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //设置权限管理器
        shiroFilter.setSecurityManager(securityManager);

        //创建自定义过滤器，并进行设置
        Map<String, Filter> filters = new HashMap<>();
        filters.put("oauth2", new OAuth2Filter());
        shiroFilter.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();
        //任何用户都可以进行登录操作
        filterMap.put("/sys/login", "anon");
        filterMap.put("/api/**", "oauth2");
        // filterMap.put("/**", "anon");
        shiroFilter.setFilterChainDefinitionMap(filterMap);


        return shiroFilter;
    }


    /**
     * 用来对密码进行加密的服务类
     *
     * @return
     */
    @Bean
    public PasswordService password() {

        return new DefaultPasswordService();

    }

    /**
     * @return
     */
    @Bean
    public OAuth2Realm realm() {
        OAuth2Realm oAuth2Realm = new OAuth2Realm();

        return oAuth2Realm;
    }

}
