package com.example.config;

import com.example.service.BeanTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO 描述
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/24 14:52
 */
@Configuration
public class ConfigTest {

    @Bean
    public BeanTest testBeanName(){
        return new BeanTest();
    }
}
