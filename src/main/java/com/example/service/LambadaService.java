package com.example.service;

import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 测试lambada表达式
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/01 10:44
 */
@Service
public class LambadaService {
    private void consumerTest(String str, Consumer<String> consumer){
        consumer.accept(str);
    }

    private String supplierTest(Supplier<String> supplier){
        return supplier.get();
    }
    Supplier<String> supplierAnotherTest = ()->"AnotherTest";

    private Boolean predicateTest(String str,Predicate<String> predicate){
        return predicate.test(str);
    }
    Predicate<String> predicateAnotherTest = s -> s.equals("test");

    private Integer functionTest(String str,Function<String,Integer> function){
        return function.apply(str);
    }
    Function<String,Integer> functionAnotherTest = Integer::parseInt;


    public String useTest(String str){
        consumerTest(str,(s -> System.out.println(s.toUpperCase())));
        String haha = supplierTest(()-> "haha");
        Boolean bool = predicateTest(str,(s -> s.equals("test")));
        Integer num = functionTest("120",(Integer::parseInt));
        return str;
    }
}
