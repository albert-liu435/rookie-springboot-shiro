package com.rookie.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname ShiroApplication
 * @Description  shiro 框架步骤  1、可以采用手机号，邮箱进行注册
 *                              2、可以采用手机号和邮箱进行登录
 *                              3、
 * @Author rookie
 * @Date 2021/7/15 10:29
 * @Version 1.0
 */
@SpringBootApplication
public class ShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }
}
