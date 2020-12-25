package com.example.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

/**
 * T多线程学习
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/17 17:57
 */
@Service
public class MultiThread {
    private void testThread(String s){

        new Runnable(){
            @Override
            public void run() {
                System.out.println(s);
            }
        };
    }

    public String test(){
        long startTime =  System.currentTimeMillis();
        long thread1= (System.currentTimeMillis()-startTime);

        long startTime2 =  System.currentTimeMillis();
        long thread2= (System.currentTimeMillis()-startTime2);
        return "多线程："+thread1+"*********"+"单线程："+thread2;
    }

    private String thread1(){
        AtomicReference<Long> q= new AtomicReference<>(1L);
        new Thread(() -> {
            for (int i=0;i<1000000000;i++){
                q.set(q.get() + 1L);
            }
        }).start();

        new Thread(() -> {
            for (int i=0;i<1000000000;i++){
                q.set(q.get() + 1L);
            }
        }).start();
        return String.valueOf(q);
    }

    private String thread2(){
        long p=1L;

        for (int i=0;i<1000000000;i++){
            p=p+1L;
        }
        for (int i=0;i<1000000000;i++){
            p=p+1L;
        }
        return String.valueOf(p);
    }

}
