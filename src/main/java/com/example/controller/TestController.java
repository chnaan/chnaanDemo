package com.example.controller;

import com.example.dto.ReplaceMaterialDTO;
import com.example.service.AnonymousClassTest;
import com.example.service.LambadaService;
import com.example.service.SystemService;
import com.example.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO 描述
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/11/16 16:13
 */
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    AnonymousClassTest anonymousClassTest;

    @Autowired
    LambadaService lambadaService;

    @Autowired
    SystemService systemService;

    @RequestMapping("/hello")
    public String hello(@Validated ReplaceMaterialDTO dto) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field[] fields = dto.getClass().getDeclaredFields();
        String str = "this is test";
        List<String> stringList = new ArrayList<>();
        stringList.add("121000258");
        stringList.add("12300045");
        stringList.add("12412345");
        dto.setMaterialCode("12412345");
        //return testService.checkMatGroup(dto);
        //return anonymousClassTest.test2();
        //return lambadaService.useTest(str);
        return systemService.systemTest();
    }
}
