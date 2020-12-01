package com.example.chnaan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.example.controller"})
@ComponentScan(basePackages = {"com.example.service"})
public class ChnaanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChnaanApplication.class, args);
    }

}
