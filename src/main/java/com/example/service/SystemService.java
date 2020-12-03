package com.example.service;

import org.springframework.stereotype.Service;

/**
 * 测试System方法
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/03 10:30
 */
@Service
public class SystemService {
    public String systemTest(){
        System.getenv().entrySet().forEach(entry->{
            System.out.println(entry);
        });
       return "1";
    }
}
