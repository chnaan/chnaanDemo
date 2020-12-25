package com.example.service;

/**
 * TODO 描述
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/24 14:53
 */
public class BeanTest {
    public String testBean(){
        System.out.println("@bean注入");
        return "动态代理";
    }
}
