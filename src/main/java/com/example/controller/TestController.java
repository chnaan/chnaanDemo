package com.example.controller;

import com.example.dto.ReplaceMaterialDTO;
import com.example.service.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/11/16 16:13
 */
@RestController
public class TestController implements ApplicationContextAware {

    ApplicationContext appctx;

    @Autowired
    TestService testService;

    @Autowired
    AnonymousClassService anonymousClassService;

    @Autowired
    LambadaService lambadaService;

    @Autowired
    SystemService systemService;

    @Autowired
    MultiThread multiThread;

    @Autowired
    GetUrlService getUrlService;

    @Autowired
    SeleniumService seleniumService;

    @Autowired
    RestTemplateService restTemplateService;

    @Autowired
    BeanTestProxyService beanTestProxyService;

    @Autowired
    ApplicationContextService applicationContextService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appctx=applicationContext;
    }

    @RequestMapping("/hello")
    public String hello(@Validated ReplaceMaterialDTO dto) throws IOException {


//        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
//
//        for(int i=0;i<stack.length;i++){
//            System.out.println(stack[i].getClassName()+":"+stack[i].getMethodName()+"-----");
//        }

        return beanTestProxyService.cglibProxyTest();
    }


}
