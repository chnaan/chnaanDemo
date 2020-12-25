package com.example.service;

import org.springframework.stereotype.Service;

/**
 * 匿名内部类测试
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/11/30 11:18
 */
@Service
public class AnonymousClassService {

    String test() {
        return new AnonymousClassInterface(){
            String study="study";
            public String study(){
                return study;
            }
        }.study();
    }

    public String test2(){
        return new AnonymousClassService(){
            String good = "good";

            @Override
            public String test(){
                return good;
            }
        }.test();
    }

}
