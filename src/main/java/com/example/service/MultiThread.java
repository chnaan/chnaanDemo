package com.example.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * T多线程学习
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/17 17:57
 */
@Service
public class MultiThread {

    public String test(){
        long startTime2 =  System.currentTimeMillis();
        thread2();
        System.out.println("单线程："+"    :     "+(System.currentTimeMillis()-startTime2));

        long startTime =  System.currentTimeMillis();
        if (thread1()==2000000000L){
            System.out.println("多线程："+"    :     "+(System.currentTimeMillis()-startTime)/1000);
        }

        return "";
    }

    private Long thread1(){
        AtomicLong m = new AtomicLong();
        AtomicLong n = new AtomicLong();
        new Thread(() -> {
            for (int i=0;i<1000000000;i++){
                m.set(m.get() + 1L);
            }
        }).start();

        new Thread(() -> {
            for (int i=0;i<1000000000;i++){
                n.set(n.get() + 1L);
            }
        }).start();
        return m.get()+n.get();
    }

    private Long thread2(){
        long p=1L;

        for (int i=0;i<1000000000;i++){
            p=p+1L;
        }
        for (int i=0;i<1000000000;i++){
            p=p+1L;
        }
        return p;
    }

}
