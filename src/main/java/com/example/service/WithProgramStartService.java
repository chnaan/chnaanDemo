package com.example.service;

import com.example.annotion.MatGroup;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.lang.reflect.Field;

/**
 *方法随项目启动
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/28 15:24
 */
//@Component
public class WithProgramStartService implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

        testInterfaceParam(new AnonyImpl());
        Field[] fields = FieldUtils.getFieldsWithAnnotation(AnonyImpl.class, MatGroup.class);
        for (Field field : fields){
            System.out.println(field);
        }
    }

    private void testInterfaceParam(AnonymousClassInterface classInterface){
        System.out.println(classInterface.getClass());
    }

}
