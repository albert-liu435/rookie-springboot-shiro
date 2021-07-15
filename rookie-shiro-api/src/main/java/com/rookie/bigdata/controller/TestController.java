package com.rookie.bigdata.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestController
 * @Description TODO
 * @Author rookie
 * @Date 2021/7/14 16:37
 * @Version 1.0
 */
@RestController
@RequestMapping("api")
public class TestController {




    @RequestMapping("test/hello")
    public String Hello(){


        return "hello shiro";
    }


    @RequestMapping("test/test")
    public String Hello2(){


        return "hello shiro2";
    }
}
