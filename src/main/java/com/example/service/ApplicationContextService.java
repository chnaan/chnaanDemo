package com.example.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 *测试获取应用上下文
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/24 14:40
 */
@Service
public class ApplicationContextService implements ApplicationContextAware {

    ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public String applicationConTextTest(){
        List<String> beanNameList = Arrays.asList(applicationContext.getBeanDefinitionNames());
        beanNameList.sort(String::compareTo);
        beanNameList.forEach(System.out::println);
        return "";
    }
}
