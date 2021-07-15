package com.rookie.bigdata.shiro;

import java.util.Map;

/**
 * @Classname ShiroService
 * @Description TODO
 * @Author rookie
 * @Date 2021/7/13 17:26
 * @Version 1.0
 */
public interface ShiroService {

    /**
     * 初始化权限
     *
     * @return
     */
    Map<String, String> loadFilterChainDefinitionMap();
}
