package com.example.controller;

import com.example.Constant.MessageConstant;
import com.example.dto.ReplaceMaterialDTO;
import com.example.dto.UserDTO;
import com.example.service.*;
import com.example.util.MessageUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    UserService userService;

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

    @Autowired
    ElasticSearchService elasticSearchService;




    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appctx=applicationContext;
    }

    @RequestMapping("/hello")
    public String hello(@Validated ReplaceMaterialDTO dto) throws IOException, IllegalAccessException, InterruptedException, InstantiationException {


//        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
//
//        for(int i=0;i<stack.length;i++){
//            System.out.println(stack[i].getClassName()+":"+stack[i].getMethodName()+"-----");
//        }
        ReplaceMaterialDTO replaceMaterialDTO = new ReplaceMaterialDTO();
        //testService.getAttr(replaceMaterialDTO)
        //seleniumService.test();
        //multiThread.test();
        //elasticSearchService.addTest();
        List<ReplaceMaterialDTO> list = new ArrayList<>();
        list.add(replaceMaterialDTO);
        Map<String,String> property = new HashMap<>();
        property.put("replaceGroup","name");
        List<UserDTO> userDTO = testService.copyProperties(list, property, UserDTO.class);
        return "";
    }

    @GetMapping("/i18n")
    public String i18n() {
        System.out.println(MessageUtils.get(MessageConstant.DATA_IS_WRONG));
        return MessageUtils.get(MessageConstant.DATA_IS_WRONG);
    }

    @RequestMapping("/haha")
    public void setUser() {
        userService.test();
    }


}
