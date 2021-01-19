package com.example.service;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * 测试cglib动态代理
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/25 10:32
 */
@Service
public class BeanTestProxyService {

    public String cglibProxyTest(){

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BeanTest.class);

        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("代理方法前：");
                Object obj = methodProxy.invokeSuper(o,objects);
                System.out.println("代理方法后");
                return obj;
            }});
        BeanTest beanTest = (BeanTest) enhancer.create();

        return beanTest.testBean();
    }
}
